package com.feng.ch11_hashtab;


/*
 * 创建 HashTable 管理多条链表
 * */
public class HashTable {

    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size; // 表示共有多少条链表

    // 构造器
    public HashTable(int size) {
        this.size = size;
        // 初始化 employeeLinkedListArray
        this.employeeLinkedListArray = new EmployeeLinkedList[size];
        // 留一个坑，这样能不能用这个链表数组？
        // 这时不要忘记分别初始化每个链表
        // 好好想一想
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    // 添加雇员
    public void add(Employee employee) {
        // 根据员工的 id 得到该员工应当添加到 哪条链表
        int employeeLinkedListNo = hashFun(employee.getId());
        // 将 employee 添加到对应的链表中
        employeeLinkedListArray[employeeLinkedListNo].addEmployee(employee);
    }

    // 遍历所有的 链表
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    /*
     * 根据输入的id ，查找雇员
     * */
    public void findEmployeeByid(int id) {
        // 使用散列函数确定到哪条链表查找
        int employeeLinkedListNo = hashFun(id);
        Employee employee = employeeLinkedListArray[employeeLinkedListNo].findEmployeeById(id);
        if (employee != null) {
            System.out.printf("在第%d条链表中找到 雇员 id=%d\n", (employeeLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }


    /*
     * 编写一个散列函数
     * 有很多方法，这里使用 简单取模法
     *
     * */
    public int hashFun(int id) {
        return id % size;
    }


}
