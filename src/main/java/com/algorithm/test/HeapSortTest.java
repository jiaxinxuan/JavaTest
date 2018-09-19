package com.algorithm.test;

import com.inteface.test.AbsTest;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆排序算法
 * @author 55238
 */
public class HeapSortTest {

    @Test
    public void maxHeapifyTest(){
        int [] arr={16,4,10,14,7,19,3,2,8,1};
        System.out.println(ArrayUtils.toString(heapSort(arr)));
//        maxHeapify(arr,1);
//        arr = buildMAxHeap(arr);
//        System.out.println(ArrayUtils.toString(arr));
//        arr=minHeapify(arr,1);
//        System.out.println(ArrayUtils.toString(arr));
    }

    /**
     *
     * @param arr
     * @return
     */
    public static int[] buildMAxHeap(int[] arr){
        for(int i=arr.length/2;i>=1;i--){
            maxHeapify(arr,i);
        }
        return arr;
    }

    public static List heapSort(int [] arr){
        //这个能保证根节点为最大值
        buildMAxHeap(arr);
        List list=new ArrayList<Integer>();
        list.add(arr[0]);
        int length=arr.length;
        for (int i=1 ;i<length;i++){
            int [] temp= ArrayUtils.subarray(arr,1,arr.length);
            buildMAxHeap(temp);
            list.add(temp[0]);
            arr=temp;
        }
        return list;
    }


    /**
     * 最大堆
     * 最大堆定义: A[parent[i]]>=A[i]
     * @return
     */
    public static int[] maxHeapify(int[] arr,int index){
        int left=heapLeftIndex(index);
        int right=heapRightIndex(index);
        int largest=0;
        if (left<=arr.length&&arr[index-1]<arr[left-1]){
            largest=left;
        }else{
            largest=index;
        }
        if (right<=arr.length&&arr[largest-1]<arr[right-1]){
            largest=right;
        }
        //交换最大值
        if (largest<=arr.length&&largest!=index){
            int swap=arr[largest-1];
            arr[largest-1]=arr[index-1];
            arr[index-1]=swap;
            maxHeapify(arr,largest);
        }
        return arr;
    }


    /**
     * 最小堆
     *
     * @param arr
     * @param index
     * @return
     */
    public static int[] minHeapify(int[] arr,int index){
        int left = heapLeftIndex(index);
        int right = heapRightIndex(index);
        int minimum = 0;
        if(left<=arr.length&&arr[left-1]>arr[index-1]){
            minimum=index;
        }else{
            minimum = left;
        }

        if (right<=arr.length&&arr[minimum-1]>arr[right-1]){
            minimum=right;
        }

        //交换最小值
        if (minimum<=arr.length&&minimum!=index){
            int swap=arr[minimum-1];
            arr[minimum-1]=arr[index-1];
            arr[index-1]=swap;
            minHeapify(arr,minimum);
        }
        return arr;
    }

    /**
     * index 的父节点
     * @param index
     * @return
     */
    public static int heapParentIndex(int index){
        return index/2;
    }

    /**
     * index 的左节点
     * @param index
     * @return
     */
    public static int heapLeftIndex(int index){
        return index*2;
    }

    /**
     * index 的右节点
     * @param index
     * @return
     */
    public static int heapRightIndex(int index){
        return index*2+1;
    }

}
