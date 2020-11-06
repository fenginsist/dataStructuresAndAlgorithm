package com.feng.dataStructure.test;

import java.util.Scanner;

public class TestCharInt {
    public static void main(String[] args) {
        char c1 = '0';
        char c2 = '9';
        int a1 = c1;
        int a2 = c2;
        System.out.println(a1);  // 48
        System.out.println(a2);  // 57

        int  a3 = 49;
        char c3 = (char) a3;
        System.out.println(c3); // 1

    }
}
