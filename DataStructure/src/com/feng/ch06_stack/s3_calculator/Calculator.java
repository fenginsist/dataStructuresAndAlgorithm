package com.feng.ch06_stack.s3_calculator;

/*
* 栈实现对表达式的计算
*
*
* */
public class Calculator {
    public static void main(String[] args) {
        // 根据前面思路，完成表达式的运算
        String expression = "3+2*6-2"; // 3+2*6-2=13
        // 思考：如果处理多位数的问题？ 30+2*6-2=40 。添加 keepNum 用于拼接多位数。

        // 创建两个栈，数栈、一个符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        // 定义需要的相关变量
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到插入保存到 ch
        String keepNum = ""; // 用于拼接多位数
        // 开始循环的扫描 expression
        while (true) {
            // 依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            /*
            * 判断 ch 是什么，然后做相应的处理
            * 如果是运算符
            * */
            if (operStack.isOperation(ch)) {
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中 pop 出两个数，
                    // 在符号栈中 pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈。
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        oper = operStack.pop();
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.calculation(num1, num2, oper);
                        // 把运算的结果入数栈
                        numStack.push(res);
                        // 然后把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空，直接入栈。。
                    operStack.push(ch);
                }
            } else {// 如果是数
                /*
                * 如果是数，就直接入数栈, 这样不能满足 多位数。。。
                * // ? "1+3" '1' => 1, ch 为字符，转换为 数字要 减48。
                * 如果是个位数，用字符 char 来表示，则要减48，如果是多位数，下边变成了字符串，就直接使用包装类的方法转换成 整型
                * */
                //numStack.push(ch-48);

                /*
                 * 解决多位数的 分析思路：
                 * 1、当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                 * 2、在处理数时，需要向expression 的表达式的index 后在多看一位，如果是数就立刻扫描，如果是符号在入栈
                 * 3、因此定义一个 变量 字符串，用于拼接
                 * */

                // 处理多位数
                keepNum += ch;

                // 如果 ch 已经是expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    // 注意是看后一位，不是 index++
                    if (operStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，则入栈, keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            // 让index +1 ，并判断 是否扫描到 expression 最后
            index++;
            if (index >= expression.length()) {
                break;
            }

        }
        /*
        * 当表达式扫描完毕，就顺序的从数栈 和符号栈 中 pop 出相应的数和符号，并运行
        * */
        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calculation(num1, num2, oper);
            numStack.push(res);
        }
        // 将数栈的最后数，pop出，就是结果
        int result = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, result);
    }
}
