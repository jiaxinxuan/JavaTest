package com.zk.test;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import sun.misc.ThreadGroupUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @author 55238
 */
public class ZkTest {

    /** zookeeper地址 */
    static final String CONNECT_ADDR = "0.0.0.0:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 2000;
    /** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        //这里的水还是很深的，首先客户端去连接zk服务器是发生在回调之前的，先连接才会有事件。其次兰姆达表达式代表的只是zk客户端收到服务端事件的时候会回调执行的代码。
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME,
                event -> {
                    System.out.println(Thread.currentThread().getName()+"--------------------------------------");
                    // 获取事件的状态
                    Watcher.Event.KeeperState keeperState = event.getState();
                    Watcher.Event.EventType eventType = event.getType();

                    // 如果是建立连接
                    if (Watcher.Event.KeeperState.SyncConnected == keeperState) {
                        if (Watcher.Event.EventType.None == eventType) {
                            // 如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                            System.out.println("zk 建立连接");
                            connectedSemaphore.countDown();
                        }
                    }
                });

        // 进行阻塞
        System.out.println(Thread.currentThread().getName()+"--------------------------------------");
        connectedSemaphore.await();

        System.out.println("..");
        // 创建父节点
//         zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//         CreateMode.PERSISTENT);

        // 创建子节点
//         zk.create("/testRoot/children", "children data".getBytes(),
//         ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取节点洗信息
        // byte[] data = zk.getData("/testRoot", false, null);
        // System.out.println(new String(data));
        // System.out.println(zk.getChildren("/testRoot", false));

        // 修改节点的值
        // zk.setData("/testRoot", "modify data root".getBytes(), -1);
        // byte[] data = zk.getData("/testRoot", false, null);
        // System.out.println(new String(data));

        // 判断节点是否存在
         System.out.println(zk.exists("/testRoot/children", false));
        // 删除节点
         zk.delete("/testRoot/children", -1);
         System.out.println(zk.exists("/testRoot/children", false));

        zk.close();

    }
}