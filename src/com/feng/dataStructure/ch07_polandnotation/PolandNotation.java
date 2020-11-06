package com.feng.dataStructure.ch07_polandnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 中缀表达式 转 后缀表达式 再到 计算结果的流程
 *
 * 1、中缀表达式字符串 放到 list 集合中
 * 2、中缀表达式集合 =》 后缀表达式集合
 * 3、后缀表达式集合 => 进行计算，得出结果
 * */
public class PolandNotation {
    public static void main(String[] args) {
        /*
         * 完成将一个中缀表达式 转换成 后缀表达式的功能
         * 说明：
         * 1、1+((2+3)×4)-5 => 1 2 3 + 4 × + 5 –
         * 2、因为 直接对str 进行操作，不方便，因此，先将 "1+((2+3)×4)-5"  =>  中缀表达式对应 的list
         *    即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),×,4,),-,5]
         * */
        String expression = "1+((2+3)*4)-5";
        List<String> strlist = toInfixExpressionList(expression);
        System.out.println("中缀表达式 转 ArrayList集合：" + strlist); //[1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
        System.out.println();

        List<String> suffixExpressionList = parseSuffixExpressionList(strlist);
        System.out.println("中缀表达式 转 后缀表达式：" + suffixExpressionList); //[1, 2, 3, +, 4, *, +, 5, -]
        System.out.println();

        int calculate = calculate(suffixExpressionList);
        System.out.println("后缀表达式 的计算结果：" + calculate); //  16
        System.out.println();

        /*
         * 先定义逆波兰表达式(后缀表达式)
         * （3+4）x5-6 => 3 4 + 5 x 6 - == 29
         * 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / + ==76
         * 说明为了方便，逆波兰表达式的数字和符号使用空格隔开
         *
         * */
//        String suffixExpression = "3 4 + 5 * 6 -";  //29
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        /*
         * 思路：
         * 1、现将 "3 4 5 + 5 x 6 -" =》 放到 ArrayList 中
         * 2、将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成计算
         * */
//        List<String> listString = getListString(suffixExpression);
//        System.out.println("spnList:" + listString);

//        int res = calculate(listString);
        //System.out.println("（3+4）x5-6="+res); //29
//        System.out.println("4 * 5 - 8 + 60 + 8 / 2 =" + res);
    }

    /*
     * 第一步：方法：将中缀表达式  转成 对应的 list
     * s =  "1+((2+3)×4)-5"
     * 转化为： [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
     *
     * charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。
     * */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List ，存放中缀表达式 对应的内容
        List<String> list = new ArrayList<>();
        int i = 0; // 这是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; //每遍历到一个字符，就放入c

        do {
            c = s.charAt(i);
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {  // 为非数字： + - * / （  ）
                list.add("" + c);
                i++; // i需要后移
            } else { // 如果是一个数，需要考虑多位数
                str = ""; // 现将str 置成 ""，'0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && c <= 57) { // 判断是否为数字， 并且后移继续判断 是否为 多位数
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    /*
     * 第二步：方法： 将中缀表达式对应的 list =》 后缀表达式
     * 即 ArrayList [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
     * */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        /*
         * 定义两个栈说明：因为 S2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出 因此比较麻烦，这里我们就不用 Stack<String>
         * 直接使用 List<String> s2 Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
         * */
        Stack<String> s1 = new Stack(); // 符号栈
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2

        for (String item : list) {
            // 如果是数字，直接入s2
            if (item.matches("\\d+")) { // 遇到 数字时，直接入 结果栈
                s2.add(item);
            } else if ("(".equals(item)) {     // 遇到 左括号，直接入 符号栈
                // 如果是左括号， 直接入s2
                s1.add(item);
            } else if (")".equals(item)) {     // 遇到 右括号，则依次弹出 s1 栈顶的运算符，并压入s2，直到遇到左括号为止， 此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //遇到左括号时，将 ( 弹出 s1栈， 消除小左括号
                s1.pop();
            } else {                            // 遇到 运算符时，当符号栈为空，item入结果栈， 如果 item < s1.peek 则  item 入结果栈  item ,如果 item > s1.peek 则 s1.peek 入结果栈，如果
                /*
                 * 当 item 的优先级小于等于 s1 栈顶运算符，将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到 (4.1) 与 s1 中新的栈顶运算符相比较
                 * 问题：我们缺少一个比较优先级高低的方法
                 * */
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈
                s1.push(item);
            }
        }
        /*
         * 当 list 集合中 遍历完后
         * 如果 s1 中有值，将s1中剩余的运算符依次弹出并加入s2
         * */
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
        return s2;
    }


    /*
     * 第三步：将一个后缀表达式（逆波兰表达式），依次将数据和运算符 放到 ArrayList 中
     * */
    public static List<String> getListString(String suffixExpression) {
        String[] splits = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        for (String ele : splits) {
            list.add(ele);
        }
        return list;
    }

    /*
     * 第三步：计算后缀表达式的值
     * 1、从左至右扫描，将3和4压入堆栈；
     * 2、遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3、将5入栈；
     * 4、接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5、将6入栈；
     * 6、最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     * */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();

        for (String item : list) {
            // 使用正则表达式 匹配是否为多位数
            if (item.matches("\\d+")) { //匹配多位数
                // 入栈
                stack.push(item);
            } else { // 为符号
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num2 + num1;
                } else if ("-".equals(item)) {
                    res = num2 - num1;
                } else if ("*".equals(item)) {
                    res = num2 * num1;
                } else if ("/".equals(item)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 计算后，得到数据，放到栈中
                stack.push("" + res);  // 整型转变为 字符串 最快的方式
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
