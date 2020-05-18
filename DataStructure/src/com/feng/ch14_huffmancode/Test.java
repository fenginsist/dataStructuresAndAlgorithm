package com.feng.ch14_huffmancode;

public class Test {

    public static void main(String[] args) {

        String str = "10101000";
        System.out.println(Integer.parseInt(str, 2)); // 168
        System.out.println((byte)Integer.parseInt(str, 2)); // -88

    }
}
