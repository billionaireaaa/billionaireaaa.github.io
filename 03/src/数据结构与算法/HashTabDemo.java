package 数据结构与算法;

import java.util.Scanner;
//哈希表（数组加链表）
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab ht = new HashTab(7);
        String k = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.add");
            System.out.println("2.list");
            System.out.println("3.search");
            System.out.println("0.exit");
            k = scanner.next();
            switch (k) {
                case "1":
                    System.out.println("id:  ");
                    int id = scanner.nextInt();
                    System.out.println("name:  ");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    ht.add(emp);
                    break;
                case "2":
                    ht.list();
                    break;
                case "3":
                    System.out.println("id:   ");
                    id = scanner.nextInt();
                    ht.findEmpById(id);
                    break;
                case "0":
                    System.exit(1);
            }
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    private Emp head;//默认为null

    public void add(Emp emp) {//head并不是一个不存放数组的，这里的头直接就存放了第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        Emp cur = head;
        while (true) {
            if (cur.next == null) break;
            cur = cur.next;
        }
        cur.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println(no + " = null");
            return;
        }
        System.out.print(no + ":  ");
        Emp curhead = head;
        while (true) {
            if (curhead == null) break;
            System.out.print(" ==> id = " + curhead.id + "  name = " + curhead.name);
            curhead = curhead.next;
        }
        System.out.println();
    }

    public Emp findEmoById(int id) {
        if (head == null) {
            return null;
        }
        Emp cur = head;
        while (true) {
            if (cur.id == id) {
                break;
            }
            if (cur.next == null) {
                cur = null;
                break;
            }
            cur = cur.next;
        }
        return cur;
    }
}

class HashTab {
    private EmpLinkedList empLinkedListArray[];
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[this.size];
        //一点要给每一个都初始化不然默认为null就不能操作了
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empNo = HashFun(emp.id);
        empLinkedListArray[empNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        int empNo = HashFun(id);
        Emp emp1 = empLinkedListArray[empNo].findEmoById(id);
        if (emp1 != null) {
            System.out.println(empNo+" : "+emp1.id+"  "+emp1.name);
        }
        else
        {
            System.out.println("null");
        }
    }

    public int HashFun(int no) {
        return no % size;
    }
}
