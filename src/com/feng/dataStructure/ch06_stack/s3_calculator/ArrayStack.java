package com.feng.dataStructure.ch06_stack.s3_calculator;

/*
 * 先创建一个栈，直接使用前面创建好的，
 * 需要扩展功能：增加
 * 定义一个 ArrayStack 表示栈
 * */
public class ArrayStack {

    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在数组中。
    private int top = -1; // top 表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法添加~");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈，将栈顶的数据返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，无法获取数据\n");
        }
        int num = stack[top];
        top--;
        return num;
    }

    // 显示栈的情况【遍历栈】,遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.printf("栈空，无数据\n");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /*
     * 查看栈顶的 数据, 不是真正出栈
     * */
    public int peek() {
        return stack[top];
    }

    /*
     * 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
     * 数字越大，则优先级越高。
     * */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有 + - * /
        }
    }

    /*
     * 判断是不是一个云算法
     * */
    public boolean isOperation(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /*
     * 计算方法
     * @param num1
     * @param num2
     * @param oper
     * @return
     * */
    public int calculation(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }
}
