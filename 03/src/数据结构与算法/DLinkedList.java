package 数据结构与算法;
//重写toString方法  快捷键为alt+insert
//双链表
public class DLinkedList {

    public static void main(String[] args) {
        DoubleLinkList d = new DoubleLinkList();
        Node2 a = new Node2(11, "a");
        Node2 b = new Node2(22, "b");
        Node2 c = new Node2(33, "c");
        Node2 aa = new Node2(11, "aa");
        d.add2(a);
        d.add2(b);
        d.add2(c);
        d.show2();
        System.out.println();

        d.update2(aa);
        d.search2(b);
        d.del2(33);
        DoubleLinkList.show2();

    }
}

class DoubleLinkList {//双向链表类
    private static Node2 head = new Node2();//创建好一个头节点，位置是固定不动的

    public static Node2 getHead() {
        return head;
    }

    public static void show2()//显示链表
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        Node2 temp = head.next;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void add2(Node2 node)//添加
    {
        Node2 temp = head;
        while (temp.next != null) {

            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    public void update2(Node2 node)//修改节点的值 根据no值来判断
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        Node2 temp = head.next;
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

    public void del2(int n)//删除节点
    {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        boolean flag = false;
        Node2 temp = head;//因为head节点是固定的，所以创建一个辅助指针来进行操作
        while (temp != null) {
            if (temp.no == n) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            if (temp.next == null)//最后一个节点的删除
            {
                temp.pre.next = null;
            }
            else//其余节点的删除
            {
                temp.next.pre = temp.pre;
                temp.pre.next = temp.next;//删除后的节点会被垃圾回收机制回收
            }

            System.out.println("删除成功！");
        } else
            System.out.println("删除失败");
    }

    public void search2(Node2 node) {
        if (head.next == null) {
            System.out.println("此为空链表");
            return;
        }
        boolean flag = false;
        Node2 temp = head.next;//因为head节点是固定的，所以创建一个辅助指针来进行操作
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

class Node2//节点类
{
    public int no;
    public String name;
    public Node2 next;//指向后一个节点，默认为null
    public Node2 pre;//指向前一个节点，默认为null

    public Node2(int no, String name) {
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

    public Node2() {
    }
}
