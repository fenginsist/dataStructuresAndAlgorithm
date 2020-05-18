package com.feng.ch10_search;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 二分查找：
 * 注意：使用二分查找的前提是 该数组是有序的.
 * 1、递归算法方式
 *      java.lang.StackOverflowError : 死循环
 *
 * 2、非递归方式
 * */
public class S2_BinarySearch {

    public static void main(String[] args) {
        int array[] = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println("数组为：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        int resIndex = binarySearchOne(array, 0, array.length - 1, 1000);
//        ArrayList resIndex = binarySearchAllElement(array, 0, array.length - 1, 1000);
        System.out.println("resIndex=" + resIndex);
        if (resIndex == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为=" + resIndex);
        }
    }

    /*
     * 二分查找算法
     * 注意点：
     *
     *
     * @param array 需要查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 如果找到就返回下标，如果没有找到，就返回 -1
     * */
    public static int binarySearchOne(int[] array, int left, int right, int findVal) {

        /*
         * 当 left > left 时，说明递归整个数组，但是没有找到，
         * */
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = array[mid];

        if (findVal > midVal) { // 向右递归
            return binarySearchOne(array, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearchOne(array, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /*
     * 完成一个 课后思考题
     * {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
     *
     * 思路分析
     * 1、在找到 mid 值时，不要马上返回，
     * 2、向 mid 索引值的左边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
     * 3、向 mid 索引值 的右边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
     * 4、 将 ArrayList 返回即可
     * */
    /*
     * @param array 需要查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 如果找到就返回下标，如果没有找到，就返回 -1
     * */
    public static ArrayList<Integer> binarySearchAllElement(int[] array, int left, int right, int findVal) {
        System.out.println("此句话每打印几次，说明调用查询方法几次");
        /*
         * 当 left > left 时，说明递归整个数组，但是没有找到，
         * */
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = array[mid];

        if (findVal > midVal) { // 向右递归
            return binarySearchAllElement(array, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearchAllElement(array, left, mid - 1, findVal);
        } else {
            /*
             * 代码走到这里时，为 findVal == midVal ， 原来是 直接返回  下标 mid 即可。现在是有可能是重复了几个值，
             * 所以这里要对这个 mid下标 的左右两边进行遍历，比较
             *
             * 思路分析
             * 1、在找到 mid 值时，不要马上返回，
             * 2、向 mid 索引值的左边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
             * 3、向 mid 索引值 的右边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
             * 4、 将 ArrayList 返回即可
             * */
            ArrayList<Integer> resIndexList = new ArrayList<>();

            // 2、向 mid 索引值的左边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || array[temp] != findVal) { // 退出
                    break;
                }
                // 否则 ，就把 temp 放入到 resIndexList 集合中
                resIndexList.add(temp);
                temp -= 1; // temp 左移
            }
            // 别忘了 把 mid 这个下标添加到 集合 中
            resIndexList.add(mid);

            //3、向 mid 索引值 的右边扫描，将所有满足 1000  的元素的下标，加入到集合 ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > array.length - 1 || array[temp] != findVal) { // 退出
                    break;
                }
                // 否则 ，就把 temp 放入到 resIndexList 集合中
                resIndexList.add(temp);
                temp += 1; // temp 右移
            }
            return resIndexList;
        }
    }
}
