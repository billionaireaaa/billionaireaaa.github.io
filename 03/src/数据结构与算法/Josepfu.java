package 数据结构与算法;

//约瑟夫问题（单向环形链表）
public class Josepfu {
    public static void main(String[] args) {
        CirclsSingleLinkedList c = new CirclsSingleLinkedList();
        c.addBoy(5);
        c.show();
        System.out.println();
        c.countBoy(1, 2, 5);
    }
}

class Boy//节点类
{
    private int no;//因为no和next属性被设为私有，所以只能在类内使用，如果是类外则用用不了，所以用下面四个函数来进行类似.next的操作，因为函数是公共类型的
    private Boy next;//默认为空

    public Boy(int no) {
        this.no = no;
    }

    public Boy() {
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}

class CirclsSingleLinkedList//环形链表类
{
    private Boy first = new Boy();

    public void addBoy(int nums)//添加nums个节点进入
    {
        if (nums < 1) {
            System.out.println("加入数量有误");
            return;
        }
        Boy cur = first;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1)//第一个节点的操作方法
            {
                first = boy;
                first.setNext(first);//构成环状
                cur = first;
            } else//其余节点的操作方法
            {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void show() {
        if (first == null) {
            System.out.println("此为空");
            return;
        }
        Boy temp = first;
        while (true) {
            System.out.println(temp.getNo());
            if (temp.getNext() == first)
                break;
            temp = temp.getNext();
        }
    }

    public void countBoy(int start, int countNums, int nums)//分别表示开始数的序号，数到cunntNums的出去，总共有的序号个数
    {
        if (first == null || start < 1 || start > nums || countNums < 1) {
            System.out.println("参数有误");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first)
                break;
            helper = helper.getNext();//helper指针的指向位置要是first的上一个
        }
        for (int i = 0; i < start - 1; i++) {//first指针是在1位置，现在把他移至选定的开始位置
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNums - 1; i++) {//让first和helper同时移动countNums-1个位置，因为移动这个位置之后就是要出去的那个指针,first指向的就是要出去的
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo());//最后一个出队的节点
    }
}
