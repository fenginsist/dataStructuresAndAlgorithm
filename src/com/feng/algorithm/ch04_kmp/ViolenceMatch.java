package com.feng.algorithm.ch04_kmp;

/*
 * 暴力匹配算法
 * */
public class ViolenceMatch {
    public static void main(String[] args) {

        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int i = violenceMatch(str1, str2);
        System.out.println("index:" + i); // 15

    }

    public static int violenceMatch(String str1, String str2) {

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i 索引指向 s1
        int j = 0; // j 索引指向 s2

        while (i < s1Len && j < s2Len) { // 保证匹配时， 不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else { // 没有匹配成功
                // 如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
