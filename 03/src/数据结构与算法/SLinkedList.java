package 数据结构与算法;

import java.util.Stack;

//单链表
public class SLinkedList {
    public static void main(String[] args) {
        SingleLinkList L = new SingleLinkList();

        Node a = new Node(1, "a");
        Node b = new Node(2, "b");
        Node c = new Node(3, "c");
        // Node aa=new Node(1,"aa");

        L.addon(b);
        L.addon(a);
        L.addon(c);
        // SingleLinkList.show();
        L.show();//等同于 SingleLinkList.show()
        //   L.del(1);
        // L.show();
        // System.out.println(findLastindexNode(SingleLinkList.getHead(),1));

        //reverse(SingleLinkList.getHead());
        //  SingleLinkList.show();
        // Node r=L.findLastindexNode(1);
        // L.search(b);
        reversePrint(SingleLinkList.getHead());
    }

    //方法：
    public static int getLength(Node head)//获取有效节点的个数 头节点不算
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return 0;
        }
        Node cur = head.next;
        int count = 0;

        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    public static Node findLastindexNode(Node head, int index)//查找链表中倒数第index个节点
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return null;
        }
        int length = getLength(head);
        if (index <= 0 || index > length) {
            return null;
        }
        Node cur = head.next;
        for (int i = 0; i < length - index; i++) {//遍历这个次数后找到的节点就是倒数第index个节点
            cur = cur.next;
        }
        return cur;
    }

    public static void reverse(Node head)//反转链表
    //思路：1.定义一个新的反转的链表的头节点
    //2.遍历取出原链表的每一个节点，取出一个则放置一个在反转的链表中（用头插法放入）
    {
        if (head.next == null || head.next.next == null)//只有一个节点或者没有节点的情况
        {
            System.out.println("不需要反转");
            return;
        }
        Node cur = new Node();
        Node temp = head.next;
        while (temp != null) {
            Node a = temp.next;
            temp.next = cur.next;
            cur.next = temp;
            temp = a;
        }
        head.next = cur.next;
    }

    public static void reversePrint(Node head)//逆序打印链表，利用栈来完成操作
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Node temp = head.next;
        while (temp != null) {
            stack.push(temp);//入栈的操作
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());//出栈的操作
        }
    }
}


class Node//节点类
{
    public int no;
    public String name;
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {//重写toString方法  快捷键为alt+insert
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public Node() {
    }
}

class SingleLinkList {//单链表类
    private static Node head = new Node();//创建好一个头节点，位置是固定不动的

    public static Node getHead() {
        return head;
    }

    public void add(Node node)//添加新的节点
    {
        Node temp = head;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp.next != null)//循环之后辅助指针指向链表中的最后一个节点
        {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addon(Node node)//按顺序添加，从小到大
    {
        Node temp = head;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        boolean flag = false;//标识现在要添加的编号是否已经存在
        while (temp.next != null) {
            if (temp.next.no > node.no)
                break;
            else if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag)//判断是否存在此编号
        {
            System.out.println("此编号已经存在");
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    public void update(Node node)//修改节点的值 根据no值来判断
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        Node temp = head.next;
        boolean flag = false;//标识是否找到节点
        while (temp != null) {
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
        } else
            System.out.println("不存在该节点");
    }

    public static void show()//显示链表
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        Node temp = head.next;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void del(int n)//删除节点
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        boolean flag = false;
        Node temp = head;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp.next != null) {
            if (temp.next.no == n) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;//删除后的节点会被垃圾回收机制回收
            System.out.println("删除成功！");
        } else
            System.out.println("删除失败");
    }

    public void search(Node node) {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        boolean flag = false;
        Node temp = head.next;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp != null) {
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("true");
        } else
            System.out.println("false");
    }
}

