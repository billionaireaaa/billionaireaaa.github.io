package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileoutputstrean {
    public static void main(String[] args) throws IOException {
        //文件字节输出流，负责写
        //从内存到硬盘
        //写内容到文件中
        FileOutputStream fos = new FileOutputStream("text.txt",true);//如果文件不存在则会新建一个，存在则直接在其中写如内容
        //新建一个byte数组,内容存放的表示的是ascii值，即97表示的其实是字符也就是写入文件的a而不是97
        byte[] bytes={97,98,99,100,101,102};
        //write方法谨慎使用因为每次写进去前它是先将文件中的内容全部清空后在写入，也就是说之前文件中的内容会不见了只剩下现在写入的内容
        //若是要在原文件末尾处追加内容而不是清空后写入，则加上参数true,加在文件路径后面
        fos.write(bytes);//把bytes中的元素全部写入
        fos.write(bytes,0,2);//把bytes中的元素从下标为0开始写进去两个元素
        //每次写完之后一定要刷新
        fos.flush();

        String s="的萨拉客户端和卢卡斯";
        byte b[]=s.getBytes();//将字符串转换为byte数组
        fos.write(b);
        //每次写完之后一定要刷新
        fos.flush();
        
        if (fos != null)
        {
            fos.close();//关闭文件
        }
    }
}

