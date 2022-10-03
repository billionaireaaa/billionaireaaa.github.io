package ProblemSet;

import java.util.Scanner;

public class 数组扩容 {
    public static void main(String[] args) {
        int arr[]={1,2,3};
        Scanner scanner=new Scanner(System.in);
        while (true)
        {
            int arrNew[]=new int[arr.length+1];
            for (int i = 0; i < arr.length; i++) {
                arrNew[i]=arr[i];
            }
            System.out.println("请输入需要新加入的元素： ");
            int a=scanner.nextInt();
            arrNew[arr.length]=a;
            arr=arrNew;
            System.out.println("是1否0继续添加： ");
            if((a= scanner.nextInt())==0)
                break;
        }
        for (int b:arr
             ) {
            System.out.println(b);

        }
    }
}
