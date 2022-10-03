package 数据结构与算法;
//排序

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Sort {
    public static void main(String[] args) {

        int arr[]=new int[]{4,6,8,5,9};
        HeapSort(arr);
       /* int arr[] = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//随机生成一个【0，8000000）区间内的数
        }
        int a[] = {1, 5, 14, 3, 9, 6, 4};
        //BubbleSort(arr);
        //selectSort(arr);
        //insertSort(a);
        //quickSort(a, 0, a.length - 1);
        //  radixSort(a);
        // System.out.println(Arrays.toString(a));


        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String d = simpleDateFormat.format(date1);
        System.out.println(d);
        //BubbleSort(arr);
      *//*  int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);*//*
        radixSort(arr);

        Date date2 = new Date();
        String d2 = simpleDateFormat.format(date2);
        System.out.println(d2);
*/

    }

    //冒泡排序
    public static void BubbleSort(int arr[]) {
        int temp = 0;//用于交换的一个变量
        boolean flag = false;//用来标识，优化此排序
        for (int i = 0; i < arr.length - 1; i++) {//比较几轮
            for (int j = 0; j < arr.length - 1 - i; j++) {//每轮和几个数进行比较
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag)
                break;
        }
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    public static void selectSort(int arr[]) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    k = j;
                }
            }
            if (k != i) {
                arr[k] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int arr[]) {
        System.out.println(Arrays.toString(arr));
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i != j) {//找出中轴值右边 小于于等于基准位的数的下标
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i != j) {//找出中轴值左边 大于等于基准位的数的下标
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准位与i和j相等位置的数字交换
        //可能存在a【j】>基准位的值，这个不影响 快速排序实质是确定每个数字应该在的位置，基准位应该在的位置就是当前i=j的这个位置
        arr[low] = arr[j];
        arr[j] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

    //归并算法中 合并的过程
    public static void merge(int arr[], int left, int mid, int right, int[] temp) {
        int i = left;//左边有序列的初始索引
        int j = mid + 1;//右边有序列的初始索引
        int t = 0;//temp数组的初始索引
        while (i <= mid && j <= right) {
            //把左右两边的有序数组按照大小加入到temp数组中，直到有一边的处理完才停止
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        //把存在剩余的一边中的全部元素都加入到temp数组中
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }
        //将temp数组拷贝到arr数组
        t = 0;
        int templeft = left;
        while (templeft <= right) {
            arr[templeft] = temp[t];
            t++;
            templeft++;
        }
    }

    //归并算法中 分+合的部分
    public static void mergeSort(int arr[], int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);//向左递归分解
            mergeSort(arr, mid + 1, right, temp);//向右递归分解
            merge(arr, left, mid, right, temp);
        }
    }

    //基数排序(负数不能运算）
    public static void radixSort(int arr[]) {
        //找到数组中的最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        //求出最大的数总共有几位
        int maxLength = ("" + max).length();
        //创建一个二维数组，表示10个桶，每个桶就是一个一维数组
        //二维数组中包含10个一维数组
        //为了防止溢出所以每个一位数组的大小为arr.length
        //这个二维数组中的行表示第几个桶，列存放的是对应的桶中的元素
        int bucket[][] = new int[10][arr.length];
        //这个数组表示每个对应的桶中有几个元素，数组下标对应的是桶的序号，值对应的就是桶中共有几个元素
        int buckerElementCount[] = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //把数组中的元素按照位数（先是个位，后是十位，以此类推）存入对应的桶中
                int digit = arr[j] / n % 10;
                bucket[digit][buckerElementCount[digit]] = arr[j];
                buckerElementCount[digit]++;
            }
            //把桶中的元素取出放入arr中
            int index = 0;//索引
            for (int j = 0; j < buckerElementCount.length; j++) {
                if (buckerElementCount[j] != 0) {
                    for (int k = 0; k < buckerElementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                //弄完之后要清零
                buckerElementCount[j] = 0;
            }
        }
    }

    //堆排序
    public static void adjustHeap(int arr[], int i, int length)//i为非叶子结点的索引，length表示的是长度
    {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = i * 2 + 1) {
            if (j + 1 < length && arr[j + 1] > arr[j]) {
                j++;//找到i结点下左右两结点中比较大的那一个
            }
            if(temp<arr[j])
            {
                arr[i]=arr[j];
                i=j;//移动i的下标，为了看这个新的结点下的树是否需要进行调整
            }
            else
                break;
            arr[i]=temp;//此时的arr[i]已经不是一开始的arr【i]了因为i的值已经改变了
        }
    }
    public static void HeapSort(int arr[])
    {
        //构成一个大顶堆
        for (int i = arr.length/2-1; i>=0; i--) {//获得非叶子结点，并且是倒叙得到即先是得到最后一个叶子结点
            adjustHeap(arr,i,arr.length);
        }
        //把大顶堆中最大的数（也就是数组中的第一个数）和数组中最后一个数交换位置，重复执行，相当于每次把一个大顶堆中最大的数选出
        for (int i = arr.length-1; i >0 ; i--) {
            int temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,i);
        }

        System.out.println(Arrays.toString(arr));
    }
}
