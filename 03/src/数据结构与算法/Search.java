package 数据结构与算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {
    static int maxsize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6};
        System.out.println(fibSearch(arr, 3));
    }

    public static int insertValueSearch(int arr[], int left, int right, int findvalue)//插值查找算法，也需要数组有序
    {
        if (left > right || findvalue > arr[right] || findvalue < arr[left])//遍历完了数组但是没有找到需要查找的数
        {
            return -1;
        }
        int mid = left + (right - left) * (findvalue - arr[left]) / (arr[right] - arr[left]);//一种自适应的计算公式
        if (arr[mid] > findvalue) {
            return insertValueSearch(arr, mid + 1, right, findvalue);
        } else if (arr[mid] < findvalue) {
            return insertValueSearch(arr, left, mid - 1, findvalue);
        } else {
            return mid;
        }
    }

    public static List<Integer> BinarySearch(int arr[], int left, int right, int findvalue)//二分查找的数组必须是有序的（且次代码的顺序是从小到大）
    {
        if (left > right)//遍历完了数组但是没有找到需要查找的数
        {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if (arr[mid] > findvalue) {
            return BinarySearch(arr, mid + 1, right, findvalue);
        } else if (arr[mid] < findvalue) {
            return BinarySearch(arr, left, mid - 1, findvalue);
        } else {
            List<Integer> a = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true)//向左扫描看是否有存在 相同的数
            {
                if (temp < 0 || arr[temp] != findvalue)
                    break;
                a.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (true)//向右扫描看是否有存在 相同的数
            {
                if (temp >= arr.length || arr[temp] != findvalue) {
                    break;
                }
                a.add(temp);
                temp++;
            }
            a.add(mid);
            return a;

        }
    }

    public static int[] fib() {
        int f[] = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int a[], int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契数值的分割线
        int mid = 0;
        int f[] = fib();
        //获取斐波那契分割线的下标(固定的一个方法）
        while (high > f[k] - 1) {
            k++;
        }
        int temp[] = Arrays.copyOf(a, f[k]);//拷贝数组，因为f【k】的值可能大于数组a的长度所以不足的位置会被填充0
        //但是不想被0填充，需要被数组a中最后一个数填充所以
        for (int i = high + 1; i < a.length; i++) {
            temp[i] = a[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (low <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }
}
