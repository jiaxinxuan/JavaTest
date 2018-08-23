package com.algorithm.test;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * 排序算法
 * @author 55238
 */
public class Insert {

    /**
     * 插入排序
     * 将待排序数组分为左有两部分，认为左边有序，右边无序，从右边无序数组中一个个取值，插入到有序数组里面去，左边数组要像右移动位置，腾出位置，
     */
    @Test
    public void insertionSort() {
        int[] array={4,1,3,2,7,5,8,4,2};
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            //平移数组,选取插入位置
            while (j >= 0 && key > array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            //
            array[j + 1] = key;
        }
        System.out.println("Insert.insertionSort");
        System.out.println("array = " + ArrayUtils.toString(array));
    }

    /**
     * 归并排序
     * 分治法 将数组拆分为两个数组，直至最小数组是有序的。
     * 递归拆分主数组，像二叉树一样，拆分至最小有序的小数组，之后递归体就是合并数组。
     * array = {8,7,5,4,4,3,2,2,1}
     */
    @Test
    public void mergerSort(){
        int[] arr={4,1,3,2,7,5,8,4,2,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8,3,2,7,5,8};
        System.out.println(ArrayUtils.toString(arr));
        System.out.println("array = " + ArrayUtils.toString(mergeSort(arr,0,arr.length)));
    }
    public static int[] mergeSort(int[] arr,int a,int b){
        if (a<b){
            int bin=(a+b)/2;
            mergeSort(arr,a,bin);
            mergeSort(arr,bin+1,b);
            merge(arr,a,bin,b);
            System.out.println(ArrayUtils.toString(arr));
        }
        return arr;
    }

    /**
     * 合并已排好序的元素，其中a/b/c是数组的下标，满足a<=b<c,要注意两个数组合并的条件
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int[] merge(int[] arr,int a,int b,int c){
        System.out.println(a+" "+c);
        //从主数组中截取已排序好的两个子数组
        int[] childArray1=ArrayUtils.subarray(arr,a,b+1);
        int[] childArray2=ArrayUtils.subarray(arr,b+1,c+1);
        //数组一中的元素个数
        int n1=childArray1.length;
        //数组二中的元素个数
        int n2=childArray2.length;
        System.out.println(ArrayUtils.toString(childArray1)+a+":"+b+"---"+ArrayUtils.toString(childArray2)+b+":"+c);
        int i=0,j=0;
        //合并两个有序的子数组到主数组里
        for (int k=a;k<=c;k++){
            //数组合并的条件，避免空堆，避免数组空指针,一定要把布尔值写成容易观察的值
            //子数组都有数据，左[i]>右[j]
            Boolean flag1=i<n1&&j<n2&&childArray1[i]>childArray2[j];
            //左有右无
            Boolean flag2=i<n1&&j>=n2;
            //左无右有
            Boolean flag3=i>=n1&&j<n2;
            //子数组都有数据，左[i]<=右[j]
            Boolean flag4=i<n1&&j<n2&&childArray1[i]<=childArray2[j];
            if (flag1||flag2){
                arr[k]=childArray1[i];
                i++;
            }else if (flag4||flag3){
                arr[k]=childArray2[j];
                j++;
            }
        }
        return arr;
    }
}
