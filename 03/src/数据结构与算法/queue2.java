package 数据结构与算法;

import java.util.Scanner;

//环形队列(理解为一个圆圈）
public class queue2 {

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        Queue q = new Queue(6);//队列中的有效数据最多为5个，因为rear指向设置要空出一个位置约定
        int b;
        while (true) {
            System.out.println("1.add");
            System.out.println("2.get");
            System.out.println("3.show");
            System.out.println("4.getFirst");
            System.out.println("5.exit");
            b = a.nextInt();
            switch (b) {
                case 1:
                    q.add1();
                    break;
                case 2:
                    q.get1();
                    System.out.println("chu dui");
                    break;
                case 3:
                    q.show1();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("First:"+q.getFirst1());
                    break;
                case 5:
                    System.out.println("谢谢使用再见");
                    System.exit(1);
            }
        }
    }

    static class Queue {
        private int maxsize;//队列的长度
        private int front;//队列的头位置
        private int rear;//队列的尾位置
        private int arr[];//队列实现的数组

        //初始化队列
        public Queue(int n) {
            maxsize = n;
            arr = new int[maxsize];
            front = 0;//初始是指向的队列第一个元素的位置
            rear = 0;//初始时指向队列最后一个元素的后一个位置，因为希望空出一个空间作为约定
        }

        public boolean isFull() {
            return (rear + 1) % maxsize == front;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public int size()//查看队列中有效元素的个数
        {
            return (rear + maxsize - front) % maxsize;
        }

        public void add1()//添加元素 入队
        {
            if (isFull()) {
                System.out.println("队列已满无法添加！");
                return;
            }
            System.out.println("input value：");
            Scanner scanner = new Scanner(System.in);
            int value = scanner.nextInt();
            arr[rear] = value;
            rear = (rear + 1) % maxsize;
        }

        public int get1()//出队
        {
            if (isEmpty()) {
                System.out.println("队列为空无法出队！");
                return -727;
            }
            int value = arr[front];
            front = (front + 1) % maxsize;
            return value;
        }

        public void show1()//显示出队列中的所有元素
        {
            if (isEmpty()) {
                System.out.println("队列为空！");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.print(arr[(i) % maxsize]);
            }
        }

        public int getFirst1()//查看队头元素
        {
            if (isEmpty()) {
                System.out.println("队列为空！");
                return -727;
            }
            return arr[front];
        }
    }
}



