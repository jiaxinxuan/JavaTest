package com.algorithm.test;

/**
 * 合并有序数组
 *
 * @author
 * @create 2018-03-08 11:53
 */
public class MergeArr {

    public static void main(String[] args) {
        Integer [] a={1,2,3};
        Integer [] b={4,7,8};
        Integer [] c=mergeArr(a,b);
        for (int i=0;i<c.length;i++) {
            System.out.println(c[i]);
        }
    }

    public static Integer [] mergeArr(Integer [] a,Integer [] b){

        Integer m=a.length;
        Integer n=b.length;
        Integer [] c =new Integer[100];
        Integer count=0;
        while (m>=0&&n>=0){
            if (a[a.length-m]>=b[b.length-n]){
                c[count]=b[b.length-n];
                n--;
            }else{
                c[count]=a[a.length-m];
                m--;
            }
            count++;
        }
        return c;
    }

}
