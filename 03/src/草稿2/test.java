package 草稿2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("all")
public class test {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\一些杂物\\test.txt";
        String path1 = "C:\\Users\\一些杂物\\test1.txt";
        int readLen=0;
        byte[] bytes = new byte[8];
        String s="中国";
        FileOutputStream fileOutputStream = new FileOutputStream(path1,true);
        FileInputStream fileInputStream = new FileInputStream(path);
        fileOutputStream.write(s.getBytes());
    }
}


