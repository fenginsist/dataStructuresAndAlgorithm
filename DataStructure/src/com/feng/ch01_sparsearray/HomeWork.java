package com.feng.ch01_sparsearray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeWork {

    public static void main(String[] args) {
        /*
         * 1. 展示二维数组
         * */
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 遍历原始二维数组
        System.out.println();
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {  // 每行数据
            for (int data : row) {     // 每列数组
                System.out.printf("%d\t", data);   // %d:表示将整数格式化为10进制整数  \t:相当于tab，缩进
            }
            System.out.println();
        }

        /*
         * 2. 将二维数组 转 稀疏数组的思路
         * */
        // 1、先遍历 二维数组，得到非0数据的个数
        int num = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    num++;
                }
            }
        }
        System.out.println("二维数组 非0数据 的个数：" + num);

        // 初始化 稀疏数组
        int[][] sparseArr = new int[num + 1][3];

        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = num;

        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;   //
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //稀疏数组 创建完成 ,输出稀疏数组
        System.out.println();
        System.out.println("二维数组 转成 稀疏数组：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        File file = new File("D:\\test\\arr\\arr.txt");

        FileOutputStream fos = null;

        byte[] contentBytes = chessArr1.toString().getBytes();


        try {
            boolean newFile = false;
            if (!file.exists()) {
                newFile = file.createNewFile();
            } else {
                System.out.println("文件存在，不用创建");
            }
            fos = new FileOutputStream(file);
            fos.write(contentBytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
