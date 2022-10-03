package 数据结构与算法;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

//逆波兰式的计算器(整数）
//string类型的可以拼接char类型的
// ""+任何类型 都可以转为 String类型
public class polandNotation {
    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        // "1+((2+3)*4)-5=16"
        List<String> list = toInfixExpressionList(expression);
        List<String> list1 = parseSuffixExpression(list);
        System.out.printf("%s = %d", expression, calculate(list1));
/*
        String suffisExpression = "3 9 + 5 * 6 -";
        List<String> list = getListString(suffisExpression);
        System.out.printf("%s = %d", suffisExpression, calculate(list));*/
    }

    /*  中缀表达式转后缀表达式的思路步骤分析
  1)初始化两个栈:运算符栈s1和储存中间结果的栈s2;
  2)从左至右扫描中缀表达式;
  3)遇到操作数时，将其压s2;
  4)遇到运算符时，比较其与s1栈顶运算符的优先级:
              1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈;
  2.否则，若优先级比栈顶运算符的高，也将运算符压入s1;
  3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较;
  5)遇到括号时:
              (1)如果是左括号“(”，则直接压入s1
              (2)如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
  6)重复步骤2至5，直到表达式的最右边
  7)将s1中剩余的运算符依次弹出并压入s2
  8)依次弹出s2中的元素并输出
              结果的逆即为中缀表达式对应的后缀表达式
              因为s2栈没有出栈只有入栈的操作所以直接用ArrayList数组代替*/
    public static List<String> parseSuffixExpression(List<String> ls) {//将中缀表达式转化后缀表达式
        Stack<String> s1 = new Stack<String>();//符号栈
        List<String> s2 = new ArrayList<String>();
        for (String item : ls) {
            if (item.matches("\\d+"))//正则表达式匹配多位数
            {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//弹出（，把小括号完整消除
            } else {
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpressionList(String s) {//将中缀表达式存放进arraylist数组中
        List<String> ls = new ArrayList<String>();
        int i = 0;//相当于一个指针的作用，只能赋初值为0，因为charat读取时参数为0是字符串的第一个字符
        String str;//用于对多位数拼接的
        char ch;//接收每次遍历得到的字符
        do {
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57)//代表符号，阿斯克码在这个范围之外的就不是数字
            {
                ls.add("" + ch);//把char类型转化为string类型才能加入
                i++;
            } else//代表数字
            {
                str = "";//每次要置空不能提前存有内容
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {//此处判断条件的顺序一定要先判断i是否小于字符串的长度不然就会出现越界
                    str += ch;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> getListString(String suffisExpression)//把一个逆波兰表达式依次放入arraylist中，ArrayList 类是一个可以动态修改的数组
    {//就算不存入List数组也可以，用String数组就可以了，转城List数组是为了便于操作
        String[] split = suffisExpression.split(" ");//遇到空格就切割开，（不要空格）最后形成一个string的数组
        List<String> list = new ArrayList<String>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> ls)//计算结果，此计算方式是根据逆波兰式的运算方法来编写的
    {
        Stack<String> stack = new Stack<String>();//后缀表达式的计算一个栈就可以中缀需要两个，这个栈里面存的都是数没有符号
        for (String i : ls) {
            if (i.matches("\\d+"))//正则表达式，匹配的是一个多位数
            {
                stack.push(i);
            } else {
                //如果是一个运算符号则进行计算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (i)//
                {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;//注意顺序，因为是栈的操作所以是后面减去前面的
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;//注意顺序
                        break;
                }
                stack.push("" + res);//把int转为String的方法
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation//符号类
{
    private static int add = 1;//加
    private static int sub = 1;//减
    private static int mul = 2;//乘
    private static int div = 2;//除

    public static int getValue(String operation)//用于判断运算符的优先级
    {
        int res = 0;
        switch (operation) {
            case "+":
                res = add;
                break;
            case "-":
                res = sub;
                break;
            case "*":
                res = mul;
                break;
            case "/":
                res = div;
                break;
            default:
                System.out.println("()");
        }
        return res;
    }
}
