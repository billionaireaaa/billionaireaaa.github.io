package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//FileInputStream中的常用方法
// int read（此方法可以有参数也可以不用参数，但是两者返回值的内容不一样虽然返回的都是int型）方法用于读取文件中的内容
// int available（）返回流（即文件）中剩余的没有读到的字节数
//long skip（long n）跳过几个字节不读，从跳过后的字节开始读
//在win操作系统中字符占1个字节中文占两个字节，都是Java中char型占两个字节
public class fileinputstream {
    public static void main(String[] args) throws IOException {
        //从硬盘到内存，负责读
        //读取文件中的数据
        FileInputStream fis=new FileInputStream("C:\\Users\\一些杂物\\test.txt");
        //此方法是读取的是字节
        /* int readData;
        while((readData= fis.read())!=-1)    //read方法返回的是int型的值，值是字符的ASCII码，每次只读一个字节，若已经读完全部内容还要继续读下去则返回值是-1
        {
            System.out.println(readData);
        }

        //常用的方法读文件内容
        //单次去读文件的内容
        byte[] bytes=new byte[3];
        int readCount=fis.read(bytes);      //一次能读取数组长度个字节，返回的是读取到的字节个数
        System.out.println(readCount);
        System.out.println(new String(bytes,0,readCount)); //string类中的一个方法能把字节型数组中的内容转换为字符串

        readCount= fis.read(bytes);
        System.out.println(readCount);
        String s=new String(bytes,0,readCount);
        System.out.println(s);*/

        //***用循环的方式去读文件的内容****
        byte[] bytes=new byte[3];
        int rc;
        while((rc= fis.read(bytes))!=-1)
        {
            System.out.print(new String(bytes,0,rc));
        }

        if(fis!=null)
            fis.close();
    }

}
