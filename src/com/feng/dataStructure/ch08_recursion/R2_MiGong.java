package com.feng.dataStructure.ch08_recursion;

/*
 * 递归解决迷宫问题
 * 从 map[1][1] 找到 map[6][5]
 * 开始时，只有递归，没有回溯，
 * 查看回溯请求：
 *   1、map[1][2] = 1;map[2][2] = 1; ，在运行就看到了回溯，都设置为了 3
 * */
public class R2_MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地 图
        int[][] map = new int[8][7];
        // 使用 1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右置为 2
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板 ，用 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        // 输出  初始化的地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }

        // 使用  递归回溯  给小球找路
        setWay(map, 1, 1);

        // 输出 递归后的地图
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
    }

    // 使用  递归回溯  来给小球找路
    /*
     *
     * 说明：
     * 1、map表示地图
     * 2、i, j 表示从地图的哪个位置开始出发 ，（1 ， 1）；
     * 3、如果小球能到 map[6][5] 位置，则说明通路 找到。
     * 4、约定： 当 map[i][j] 为0 表示该点没有走过； 当为 1 表示墙；2 表示通路可以走； 3 表示该点已经走过。但是走不通
     * 5、在走迷宫时，需要确定一个策略（方法） 下-》右-》上-》左 ，
     * 如果该点走不通，再 回溯
     *
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true， 否则返回false
     * */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {       //  递归的条件
            return true;
        } else {
            if (map[i][j] == 0) {  // 如果当前这个点还没走过
                // 按照策略 下-》右-》上-》左  走
                map[i][j] = 2; // 假定改变是可以走通的
                if (setWay(map, i + 1, j)) { // 向下走
                    System.out.println("走过i=" + (i + 1) + ", j=" + j);
                    return true;
                } else if (setWay(map, i, j + 1)) {  // 向右走
                    System.out.println("走过i=" + i + ", j=" + (j + 1));
                    return true;
                } else if (setWay(map, i - 1, j)) {  // 向上走
                    System.out.println("走过i=" + (i - 1) + ", j=" + j);
                    return true;
                } else if (setWay(map, i, j - 1)) {  // 向左走
                    System.out.println("走过i=" + i + ", j=" + (j - 1));
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果 map[i][j] !=0, 可能是 1， 2， 3
                return false;
            }
        }
    }
}
