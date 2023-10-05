package com.feng.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        list.add("hello");
        list.add("hello");
        list.add("world");
        list.add(3, "gay");
        System.out.println(list.toString());
        list.remove("hello");   // 删除第一个
        list.remove(2);
        System.out.println(list.toString());


        System.out.println("-----------------------------map");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hello", "word");
    }
}
