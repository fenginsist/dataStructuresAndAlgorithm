package com.feng.ch06_stack.s1_arraystack;

/*
 * 定义一个 ArrayStack 表示栈，数组模拟 栈
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
    public boolean isFull(){
        return top == maxSize-1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈已满，无法添加~");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈，将栈顶的数据返回
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈已空，无法获取数据\n");
        }
        int num = stack[top];
        top--;
        return num;
    }

    // 显示栈的情况【遍历栈】,遍历时，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.printf("栈空，无数据\n");
        }
        for (int i = top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
