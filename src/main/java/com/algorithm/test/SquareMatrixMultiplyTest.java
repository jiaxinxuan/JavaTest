package com.algorithm.test;

import org.junit.Test;

/**
 * @author 55238
 */
public class SquareMatrixMultiplyTest {

    /**
     *
     */
    @Test
    public void test1(){
        //定义一个二维数组
        int[][] matrix1= {{1,2,4,4},{2,3,3,4},{5,3,4,4},{5,3,5,4}};
        int[][] matrix2= {{2,1,2,4},{2,2,3,3},{5,3,2,4},{5,2,3,5}};
        int[][] resultMatrix;
        System.out.println("输入矩阵1");
        printMatrix(matrix1);
        System.out.println("输入矩阵2");
        printMatrix(matrix2);
        //常规算法计算矩阵
        resultMatrix=MatrixMultiply(matrix1,matrix2);
        System.out.println("结果矩阵");
        printMatrix(resultMatrix);
        //Strassen算法计算矩阵
        resultMatrix=MatrixMultiplyStrassen(matrix1,matrix2);
        System.out.println("结果矩阵");
        printMatrix(resultMatrix);
    }

    /**
     * 矩阵乘法
     * 使用分治算法Strassen计算矩阵
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static int[][] MatrixMultiplyStrassen(int[][]matrix1 , int[][]matrix2){
        int[][] resultMatrix=new int[matrix1.length][matrix1.length];
        int n=matrix1.length;
        if (n==1) {
            resultMatrix[n][n] = matrix1[n][n] * matrix2[n][n];
        }else {

        }
        return resultMatrix;
    }

    /**
     *  矩阵的常规算法 默认矩阵都是 n*n
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static int[][] MatrixMultiply(int[][]matrix1 , int[][]matrix2){
        int[][] resultMatrix=new int[matrix1.length][matrix1.length];
        for (int i=0;i<matrix1.length;i++){
            for (int j=0;j<matrix1[i].length;j++){
                for (int k=0;k<matrix1.length;k++){
                    resultMatrix[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return resultMatrix;
    }

    /**
     * 打印矩阵
     * @param matrix
     */
    public static void printMatrix(int [][] matrix){

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
