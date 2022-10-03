package 草稿;

public class two {
    static int max=8;
    static int[] q =new int[max];
    static int count=0;
    public static void main(String[] args) {
        check(0);
        System.out.println(count);

    }
    static void pri()
    {
        for (int i = 0; i < max; i++) {
            System.out.printf("%d\t",q[i]);
        }
        System.out.println();
    }
    static boolean judge(int n)
    {
        for (int i = 0; i < n; i++) {
            if (q[i]==q[n]||Math.abs(i-n)==Math.abs(q[i]-q[n]))
                return false;
        }
        return true;
    }
    static void check(int n)
    {
        if(n==max)
        {
            count++;
            pri();
            return;
        }
        for (int i = 0; i < max; i++) {
            q[n]=i;
            if (judge(n))
            {
                check(n+1);
            }
        }
    }
}
