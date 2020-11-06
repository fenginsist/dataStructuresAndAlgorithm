package com.feng.test;

public class TestArray {

    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
//        arr[3] = 3;

        for (int i = 0; i<=arr.length-1; i++){
            System.out.print(arr[i]+" ");
        }
        /*
        * 如果 添加 arr[3] = 3;，会报错，数组下标越界错误
        * */
    }
}
