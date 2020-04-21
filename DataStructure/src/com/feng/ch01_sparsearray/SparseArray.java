package com.feng.ch01_sparsearray;

public class SparseArray {

    public static void main(String[] args) {
        /*
        * 1. 展示二维数组
        * */
        // 创建一个原始的二维数组 11* 11
        // 0： 便是没有棋子， 1：黑子， 2：白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 输出二维数组长度
        System.out.println("数组有几行数据，chessArr1.length:" + chessArr1.length); // 求行
        // 第三行的长度
        System.out.println("数组第三行有几列数据，chessArr1.length[2]:" + chessArr1[2].length);  // 求列

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
        int[][] sparseArr = new int[num+1][3];

        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = num;

        int count = 0;
        for (int i=0; i<chessArr1.length; i++){
            for (int j = 0; j <chessArr1[i].length; j++){
                if (chessArr1[i][j]!=0){
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
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        /*
        * 3. 将稀疏数组 --》恢复成 原始的二维数组
        * */
        /**
         * 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的 chessArr2 = int
         * 2、在读取稀疏数组后几行的数据，并赋给原始的 二维数组即可。
         */
        // 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 遍历 稀疏数组的后面几行数据（从第二行开始），并赋给原始的二维数组即可。
        for (int i = 1; i< sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2){
            for (int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
