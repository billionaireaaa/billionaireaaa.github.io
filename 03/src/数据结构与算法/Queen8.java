package 数据结构与算法;

import javax.swing.*;

//8皇后问题(8*8的棋盘）
public class Queen8 {
    //皇后的个数
    static int max = 8;
    static int count = 0;//记录总共存在多少种方法
    static int[] array = new int[max];//用来表示每个皇后所在的位置，下标表示的是第几个皇后同时也表示第几行，数组中的值表示的是对应下标的皇后所在的列

    public static void main(String[] args) {
        check(0);
        System.out.println(count);
    }

    public static void pri() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }

    public static boolean judge(int n)//判断第n个皇后是否与前面放置好的皇后位置有冲突
    {
        for (int i = 0; i < n; i++) {
            /*array[i] == array[n]判断的是是否有皇后与第n个皇后在同一列
             *后一个条件判断的是是否有同一斜线
             * 不用判断是否在用一行，因为数组的下标表示的就是行循环遍历时i不会等于n所以不用判断 */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //放置第n个皇后
    public static void check(int n) {
        if (n == max)//表示开始放第9个皇后了，说明前八个皇后已经放置完了，所以递归的出口在这里
        {
            count++;
            pri();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {//判断是否冲突如果不冲突就放下一个皇后
                check(n + 1);
            }
        }
    }
}
