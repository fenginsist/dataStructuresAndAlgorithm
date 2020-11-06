package com.feng.dataStructure.ch11_hashtab;

/*
 * 表示链表
 * */
public class EmployeeLinkedList {


    public static void main(String[] args) {
        EmployeeLinkedList employeeLinkedList = new EmployeeLinkedList();
        Employee employee01 = new Employee(1, "小刘", "男", 12);

        employeeLinkedList.addEmployee(employee01);

        employeeLinkedList.list(1);
    }

    // 头指针，执行第一个 Employee ，因此我们这个链表的 head 是直接指向第一个 Employee
    private Employee head;

    public Employee getHead() {
        return head;
    }

    /*
     * 添加
     * 说明：
     * 1、假定当添加雇员时，id 是自增的，即 id的分配总是从小到大
     * 2、因此我们将该雇员直接加入到本链表的最后即可。
     * */
    public void addEmployee(Employee employee) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Employee temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        // 添加雇员
        temp.setNext(employee);
    }

    /*
     * 遍历
     * */
    public void list(int no) {
        if (head == null) { // 说明链表为空
            System.out.println("第" + (no+1) + "条链表为空");
            return;
        }
        // 使用辅助指针 帮助遍历
        Employee temp = head;
        System.out.print("第" + (no+1) + "条链表信息为：");
        while (true) {
            System.out.printf("=>id=%d name=%s sex=%s age=%d\t", temp.getId(), temp.getName(), temp.getSex(), temp.getAge());
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        System.out.println();
    }

    /*
    * 根究 id 查找雇员
    * 如果查找到，就返回 employee ，如果没有找到，就返回 null
    * */
    public Employee findEmployeeById(int id){
        // 判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Employee temp = head;
        while (true){
            if (temp.getId() == id){ // 找到
                break;
            }
            if (temp.getNext() == null){ // 没有找到
                temp = null;
                break;
            }
            temp = temp.getNext();
        }
        return temp;
    }


}


