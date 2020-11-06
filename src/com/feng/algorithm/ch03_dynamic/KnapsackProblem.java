package com.feng.algorithm.ch03_dynamic;

import java.util.Arrays;

public class KnapsackProblem {

    public static void main(String[] args) {

        int w[] = {1, 4, 3}; // 物品的重量

        int val[] = {1500, 3000, 2000}; // 物品的价值 这里 val[i] 就是前面讲的 价格

        int m = 4; // 背包的容量
        int n = val.length; // 物品的个数

        // v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        // 初始化 第一行 和 第一列 ，这里在本程序中，可以不做处理，因为默认为0,
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        // 根据前面得到公式 来动态规划处理
        for (int i = 1; i < v.length; i++) {  // 不处理第一行， i 从 1 开始
            for (int j = 1; j < v[i].length; j++) {// 不处理第一行， j 从 1 开始
                //公式
                if(w[i-1]> j) { // 因为我们程序i 是从1开始的，因此原来公式中的 w[i] 修改成 w[i-1]
                    v[i][j]=v[i-1][j];
                } else {
                    //说明:
                    //因为我们的i 从1开始的， 因此公式需要调整成
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);

                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }

                }
            }
        }


        // 遍历 二维数组
        System.out.println("数组 V");
        for (int[] row : v) {
            System.out.println(Arrays.toString(row));
        }

        // 遍历 二维数组
        System.out.println("数组 path");
        for (int[] row : path) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历path,  这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
		for(int i = 0; i < path.length; i++) {
			for(int j=0; j < path[i].length; j++) {
				if(path[i][j] == 1) {
					System.out.printf("第%d个商品放入到背包\n", i);
				}
			}
		}

        System.out.println("============================");

        //动脑筋
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        while(i > 0 && j > 0 ) { //从path的最后开始找
            if(path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1]; //w[i-1]
            }
            i--;
        }
    }
}
