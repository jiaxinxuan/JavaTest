package com.algorithm.test;

/**
 * 二分查找
 *
 * @author
 * @create 2018-03-07 11:40
 */
public class Search {

    public static void main(String[] args) {
        Integer [] arr={1,2,3,4,5,6,7};
        System.out.println( binSearch(arr,1));
        System.out.println(recursionBinSearch(arr,5,0,arr.length-1));

    }

    /**
     * 简单二分查找非递归实现
     * @param array
     * @param num
     * @return
     * 实现思想-把左右点当做指针根据mid和查找值相比较的结果移动。
     * 重点是mid = (right+left)/2
     *
     *
     */
    public static Integer binSearch(Integer [] array,Integer num){

        Integer left = 0;
        Integer right = array.length-1;
        Integer mid;
        while (left <= right){
            mid = (right+left)/2;
            if (array[mid].equals(num)){
                return mid+1;
            }else if(num  > array[mid]){
                left=mid+1;
            }else if(num  < array[mid] ){
                right=mid-1;
            }
        }
        return -1;
    }


    /**
     * 二分查找的递归实现
     * @param arr 待查数组
     * @param key 要查找的值
     * @return
     */
    public static Integer recursionBinSearch(Integer [] arr,Integer key,Integer left,Integer right){

        if (left<=right){
            Integer mid = (left+right)/2;
            if (arr[mid].equals(key)){
                return mid+1;
            }else if(arr[mid]>key){
                return recursionBinSearch(arr,key,left,mid);
            }else{
                return recursionBinSearch(arr,key,mid,right);
            }

        }else{
            return -1;
        }

    }


}
