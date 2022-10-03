package 数据结构与算法;
//简单栈的实现（用的是数组）

import java.util.Scanner;

public class ArrayStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("maxsize:");
        int w = scanner.nextInt();
        Stack2 s = new Stack2(w);
        int k;
        while (true) {
            System.out.println("1.push");
            System.out.println("2.pop");
            System.out.println("3.show");
            System.out.println("4.exit");
            switch (k = scanner.nextInt()) {
                case 1:
                    System.out.println("add:");
                    int z = scanner.nextInt();
                    s.push(z);
                    break;
                case 2:
                    System.out.println(s.pop());
                    break;
                case 3:
                    s.show();
                    break;
                case 4:
                    System.exit(1);
            }

        }

    }
}

class Stack2 {
    private int maxsize;//栈的大小
    private int[] stack;//存放栈的数据的数组
    private int top = -1;//栈顶的指针默认为-1

    public Stack2(int maxsize)//初始化栈
    {
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value)//入栈
    {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop()//出栈
    {
        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void show() {//显示栈中的内容
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

