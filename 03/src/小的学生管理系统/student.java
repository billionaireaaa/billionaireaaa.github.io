package 小的学生管理系统;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class student {
    static class Stu {
        private String sn;
        private String name;
        private double grade;

        Stu(){};

        Stu(String sn, String name, double grade) {
            this.sn = sn;
            this.name = name;
            this.grade = grade;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }


    }

    public static void addStudent(ArrayList<Stu> arr)//用于添加学生信息
    {

        Scanner sc = new Scanner(System.in);
        String sid;

        while (true) {
            System.out.println("请输入学生学号：");
            sid = sc.nextLine();
            boolean flag = isUsed(sid, arr);
            if (flag) {
                System.out.println("你输入的学号已经被占用，请重新输入");
            } else {
                break;
            }
        }

        System.out.println("请输入姓名：");
        String a = sc.nextLine();
        System.out.println("请输入成绩：");
        double c = sc.nextDouble();

        Stu s = new Stu();
        s.setName(a);
        s.setSn(sid);
        s.setGrade(c);

        arr.add(s);
        System.out.println("添加成功");
    }

    public static boolean isUsed(String s, ArrayList<Stu> array)//判断学号是否被占用
    {
        boolean flag = false;
        for (int i = 0; i < array.size(); i++) {
            Stu s1 = array.get(i);
            if (s1.getSn().equals(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void deleteStudent(ArrayList<Stu> array) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的学生的学号： ");
        String id = sc.nextLine();
        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            Stu temp = array.get(i);
            if (temp.getSn().equals(id)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("不存在此同学");
        } else {
            array.remove(index);
            System.out.println("删除成功");
        }
    }

    public static void updateStudent(ArrayList<Stu> array) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你要修改的学生的学号");
        String sid = sc.nextLine();

        //键盘录入要修改的学生信息
        System.out.println("请输入学生新姓名");
        String name = sc.nextLine();
        System.out.println("请输入学生新成绩");
        Double score = sc.nextDouble();


        //创建学生对象
        Stu s = new Stu();
        s.setSn(sid);
        s.setName(name);
        s.setGrade(score);


        //遍历集合修改对应的学生信息
        for (int i = 0; i < array.size(); i++) {
            Stu student = array.get(i);
            if (student.getSn().equals(sid)) {
                array.set(i, s);
                break;
            }
        }

        //给出修改成功提示
        System.out.println("修改学生成功");

    }

    public static void findAllStudent(ArrayList<Stu> array) {
        for (int i = 0; i < array.size(); i++) {
            Stu temp = array.get(i);
            System.out.print("学号为： ");
            System.out.println(temp.getSn());
            System.out.print("姓名为： ");
            System.out.println(temp.getName());
            System.out.print("成绩为： ");
            System.out.println(temp.getGrade()) ;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<Stu> array = new ArrayList<Stu>();
        System.out.println("--------欢迎来到学生管理系统--------");
        Stu s=new Stu("123","dsa",98);
        array.add(s);


        while (true) {
            //主界面

            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有信息");
            System.out.println("5 退出");
            System.out.println("请输入你的选择");

            Scanner sc = new Scanner(System.in);
            String key = sc.nextLine();

            switch (key) {
                case "1":
                    //System.out.println("添加学生");
                    addStudent(array);
                    break;
                case "2":
                    //System.out.println("删除学生");
                    deleteStudent(array);
                    break;
                case "3":
                    //System.out.println("修改学生");
                    updateStudent(array);
                    break;
                case "4":
                    //System.out.println("查看所有信息");
                    findAllStudent(array);

                    break;
                case "5":
                    System.out.println("谢谢使用");
                    //break;
                    System.exit(0);  //JVM退出
            }
        }

    }


    }


