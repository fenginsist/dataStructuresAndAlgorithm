package com.feng.dataStructure.test;

import java.util.TreeSet;

public class SetTest {


    public static void main(String[] args) {

        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(1);  // 只会输出一个 1
        treeSet.add(9);

        for (Integer i : treeSet) {
            System.out.println(i);
        }

    }
}
