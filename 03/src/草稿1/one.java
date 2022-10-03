package 草稿;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class one {

    public static void main(String[] args) {

        System.out.printf("input: ");
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        List<String> list=toInfixExpressionList(s);
        List<String> list1=parseSuffixExpression(list);
        System.out.println(calculate(list1));
    }
    public static List<String> parseSuffixExpression1(List<String> ls) {//将中缀表达式转化后缀表达式
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
                while (s1.size() != 0 && Operatinon.getValue(item) <= Operatinon.getValue(s1.peek()) ){
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
    public static List<String> parseSuffixExpression(List<String> ls) {//将中缀表达式转化后缀表达式
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<String>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("("))
                    s2.add(s1.pop());
                s1.pop();
            } else {
                while (s1.size() != 0 && Operatinon.getValue(item) <= Operatinon.getValue(s1.peek()))
                    s2.add(s1.pop());
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }
   public static int calculate1(List<String> ls)//计算结果，此计算方式是根据逆波兰式的运算方法来编写的
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
    public static int calculate(List<String> ls)//计算结果，此计算方式是根据逆波兰式的运算方法来编写的
    {
        Stack<String> stack=new Stack<String>();
        int res=0;
        for (String item:ls) {
            if (item.matches("\\d+"))
            {
                stack.push(item);

            }
            else
            {
                int num1= Integer.parseInt(stack.pop());
                int num2= Integer.parseInt(stack.pop());
                switch (item)
                {
                    case "+":
                        res=num1+num2;
                        break;
                    case "-":
                        res=num2-num1;
                        break;
                    case "*":
                        res=num1*num2;
                        break;
                    case "/":
                        res=num2/num1;
                        break;
                }
                stack.push(res+"");
            }
        }
       return Integer.parseInt(stack.pop());
    }
    public static List<String> toInfixExpressionList1(String s) {//将中缀表达式存放进arraylist数组中
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
    public static List<String> toInfixExpressionList(String s) {//将中缀表达式存放进arraylist数组中
        List<String> list = new ArrayList<String>();
        String a = "";
        char ch = ' ';
        for (int i = 0; i < s.length(); ) {

            if (s.charAt(i) >= 48 && s.charAt(i) <= 57)//数字
            {

                a = "";
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {//此处判断条件的顺序一定要先判断i是否小于字符串的长度不然就会出现越界
                    a += ch;//拼接
                    i++;
                }
                list.add(a);
            } else {
                list.add(s.charAt(i) + "");
                i++;
            }
        }
        return list;
    }
}
    class Operatinon {
        private static int add = 1;
        private static int sub = 1;
        private static int mul = 2;
        private static int div = 2;

        public static int getValue(String v) {
            int res = 0;
            switch (v) {
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
            }
            return res;
        }
    }


