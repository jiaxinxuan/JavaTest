package com.algorithm.test;

import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 55238
 * 分治法 ：应用递归解决   分解问题-》解决问题-》合并问题
 */
public class findAlg {

    /**
     * 求解和最大 子数组
     */
    @Test
    public void findMaximumSubArrayTest(){
        int[] arr={13,-3,-25,-20,-3,-16,-23,-18,-20,-7,-12,-5,-22,-15,-4,-7};
        Map map=finMaximumSubArray(arr,1,16);
        map.forEach((m,n)-> System.out.println(m+" : "+n));
    }
    /**
     * 递归函数
     */
    public static Map<String,Integer> finMaximumSubArray(int[] arr,int low,int high){
        if (low==high){
            //递归出口
            Map map=new HashMap<>(16);
            map.put("low",low);
            map.put("high",high);
            map.put("sum",arr[low-1]);
            return map;
        }else{
            Map map=new HashMap<>(16);
            int mid=(low+high)/2;
            //分解数组
            Map<String,Integer> leftMap=finMaximumSubArray(arr,low,mid);
            Map<String,Integer> rightMap=finMaximumSubArray(arr,mid+1,high);
            Map<String,Integer> crossMap=findMaxCrossingSubArray(arr,low,mid,high);
            if (rightMap.get("sum") >= leftMap.get("sum") && rightMap.get("sum") >= crossMap.get("sum")){
                map.put("low",rightMap.get("low"));
                map.put("high",rightMap.get("high"));
                map.put("sum",rightMap.get("sum"));
                return map;
            }else if (leftMap.get("sum") >= rightMap.get("sum") && leftMap.get("sum") >= crossMap.get("sum")){
                map.put("low",leftMap.get("low"));
                map.put("high",leftMap.get("high"));
                map.put("sum",leftMap.get("sum"));
                return map;
            }else {
                map.put("low",crossMap.get("leftMax"));
                map.put("high",crossMap.get("rightMax"));
                map.put("sum",crossMap.get("sum"));
                return map;
            }
        }

    }
    /**
     * 寻找跨越中点的最大子数组
     */
    public static Map<String,Integer> findMaxCrossingSubArray(int[] arr, int low, int mid, int high){
        Map resultMap=new HashMap(16);
        int leftSum=0;
        resultMap.put("leftMax",mid);
        resultMap.put("rightMax",mid);
        int sum=0;
        //从中间开始往左边加，寻找左边最大值时的数组下标
        for (int i=mid-1;i>=low;i--){
            sum=sum+arr[i-1];
            if (sum>leftSum){
                leftSum=sum;
                resultMap.put("leftMax",i);
            }
        }
        int rightNum=0;
        sum=0;
        for (int i=mid;i<=high;i++){
            sum=sum+arr[i-1];
            if (sum>rightNum){
                rightNum=sum;
                resultMap.put("rightMax",i);
            }
        }
        resultMap.put("sum",leftSum+rightNum);
        return resultMap;
    }
}
