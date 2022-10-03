package 数据结构与算法;

import java.util.Scanner;
//普通队列(理解为一条直线）
public class queue1 {
    public static void main(String[] args) {
        Scanner a =new Scanner(System.in);
        int b = 0;
        int count=0;
        QUeue q=new QUeue(6);//队列中的有效数据最多为5个，因为rear指向设置要空出一个位置约定
        System.out.println("input:");
        while ((b=a.nextInt())!=-727)
        {


            q.add(b);
            count++;
            System.out.println("input:");
        }
        q.show(count);


    }
}
//顺序存储（数组的形式）的队列
class   QUeue{
    private int maxsize;//队列的长度
    private int front;//队列的头位置
    private int rear;//队列的尾位置
    private int arr[];//队列实现的数组
    //初始化队列
    public QUeue(int n)
    {
        maxsize=n;
        arr=new int[maxsize];
        front=-1;//初始是指向的是头部的前一个位置
        rear=-1;//初始时首尾指向是相同的位置
    }

    public boolean isFull()
    {
        return rear==maxsize-1;
    }

    public boolean isEmpty()
    {
        return front==rear;
    }

    public void add(int n)//添加元素 入队
    {
        if(isFull())
        {
            System.out.println("队列已满无法添加！");
            return;
        }
        rear++;
        arr[rear]=n;
    }

    public int get()//出队
    {
        if(isEmpty())
        {
            System.out.println("队列为空无法出队！");
            return -727;
        }
        return arr[++front];
    }

    public void show(int x)//显示出队列中的所有元素
    {
        if(isEmpty())
        {
            System.out.println("队列为空！");
            return;
        }
        for(int i=0;i<x;i++)
        {
            System.out.print(arr[i]);
        }
    }

    public int getFirst()//查看队头元素
    {
        if(isEmpty())
        {
            System.out.println("队列为空！");
            return -727;
        }
        return arr[front+1];
    }

}


