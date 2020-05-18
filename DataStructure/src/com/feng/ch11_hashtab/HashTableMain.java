package com.feng.ch11_hashtab;

import java.util.Scanner;

/*
 * 创建 HashTable 管理多条链表
 * */
public class HashTableMain {

    public static void main(String[] args) {
        // 创建一个 哈希表
        HashTable hashTable = new HashTable(7); // 说明哈希表的数组 长度为7，下标从 0->6

        // 写一个简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("exit：退出系统");
            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    System.out.println("输入性别");
                    String sex = scanner.next();
                    System.out.println("输入年龄");
                    int age = scanner.nextInt();
                    // 创建雇员
                    Employee employee = new Employee(id, name, sex, age);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmployeeByid(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
