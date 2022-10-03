package 数据结构与算法;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class 稀疏数组 {

    public static void main(String[] args) throws IOException {


        //二维数组转稀疏数组
        //先创建一个二维数组，在有意义的位置上赋值
        int [][] chessArr1=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;

        //用增强型for循环去打印出二维数组

        for(int[] row: chessArr1)
        {
            for(int data : row)
            {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //1.用sum去记录二维数组中有效值的个数（即不为0的数）
        int sum=0;
        for(int i=0;i<chessArr1.length;i++)//行数
        {
            for(int j=0;j<chessArr1[0].length;j++)//列数
            {
                if(chessArr1[i][j]!=0)
                    sum++;
            }
        }
        //2.去创建稀疏数组，行数为有效值的个数加1，列数都为3
        // 稀疏数组中第一行中第一个元素表示转化来的二维数组共几行，第二个元素表示共几列，第三个元素表示有效值的个数，其余的每一行中三个元素分别表示有效值所在的行，列，有效值的大小
        int sparseArr[][]=new int[sum+1][3];
        //3.给稀疏数组赋值
        //第一行单独赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //给剩下的几行赋值
        int count=0;//用count来表示记录稀疏数组中行数
        for(int i=0;i<chessArr1.length;i++)
        {
            for(int j=0;j<chessArr1[0].length;j++)
            {
                if(chessArr1[i][j]!=0)
                {
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //打印稀疏数组
        System.out.println();
        for (int i=0;i<sparseArr.length;i++)
        {
            System.out.printf("%d\t%d\t%d\t",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            System.out.println();
        }


        //稀疏数组转为二维数组

        //1.创建一个二维数组
        int [][]chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.从稀疏数组中读取有效值的位置并赋值到二维数组中
        for(int i=1;i<sparseArr.length;i++)
        {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //打印二维数组
        System.out.println();
        for(int[] row: chessArr2)
        {
            for(int data : row)
            {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


     //   System.out.println(Arrays.deepToString(chessArr1));

        FileOutputStream fos=new FileOutputStream("text.txt",true);
        String s=Arrays.deepToString(chessArr1);//把数组转换为字符串
        byte[] bytes=s.getBytes();//把字符串转化为byte数组
        fos.write(bytes);//把byte数组中的内容写入文件夹内
        fos.flush();//刷新
        if(fos!=null)
        {
            fos.close();//关闭文件夹
        }

    }
}
