package smallchangesys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class SmallChangeSys {
    public static void main(String[] args) {
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);
        String key="";
        String details="=========零钱通明细=========";
        double balance=0;//表示余额
        double money=0;//表示收益
        Date date=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//用于转换时间输出的格式
        String note="";
        while(loop)
        {
            System.out.println("\n=========零钱通菜单=========");
            System.out.println("\t\t1 零钱通明细");
            System.out.println("\t\t2 收益入账");
            System.out.println("\t\t3 消费");
            System.out.println("\t\t4 退     出");

            System.out.println("请选择： ");
            key=scanner.next();
            switch (key)
            {
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.print("请输入收益金额: ");
                    money=scanner.nextDouble();
                    date=new Date();//获取当前的时间
                    if(money<=0)
                    {
                        System.out.println("输入的收益有误请从新输入正确的收益金额：");
                        money=scanner.nextDouble();
                    }
                    balance+=money;
                    details+="\n收益入账\t+"+money+"\t"+sdf.format(date)+"\t余额\t"+balance;
                    break;
                case "3":
                    System.out.print("消费金额： ");
                    money=scanner.nextDouble();
                    if(money>balance||money<=0)
                    {
                        System.out.println("消费金额大于余额，不能完成消费");
                        break;
                    }
                    System.out.print("消费说明： ");
                    note=scanner.next();
                    balance-=money;
                    date=new Date();
                    details+="\n"+note+"\t-"+money+"\t"+sdf.format(date)+"\t余额\t"+balance;
                    break;
                case "4":
                    System.out.println("是否真的要退出？y/n");
                    String choice= "";
                    while(true)
                    {
                        choice=scanner.next();
                        if ("y".equals(choice)||"n".equals(choice))
                            break;
                        System.out.println("请输入y或n");
                    }
                    if ("y".equals(choice))
                        loop=false;
                    break;
                default:
                    System.out.println("输入有误请从新输入！");
                    break;
            }
        }
        System.out.println("========欢迎下次使用=======");
    }
}
