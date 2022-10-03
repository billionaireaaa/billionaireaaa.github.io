package ProblemSet;
/*
        给你一个整数数组nums，你可以对它进行一些操作。

        每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。

        开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
*/


public class 删除并且获得点数动态规划 {
    public static void main(String[] args) {
        int nums[] = {2, 2, 3, 3, 3, 4};
        int a =deleteAddEarn(nums);
        System.out.println(a);

    }

    public static int deleteAddEarn(int nums[]) {
        int size = nums.length;
        if (size==0)
            return 0;
        if (size==1)
            return nums[0];
        int max=0;
        for (int i = 0; i < size; i++) {
            max=Math.max(max,nums[i]);
        }
        //下标对应的是点数
        int all[]=new int[max+1];
        for (int a:nums) {
            all[a]++;
        }
        int dp[]=new int[max+1];
        dp[0]=0;
        dp[1]=1*all[1];
        for (int i = 2; i <= max; i++) {
            dp[i]=Math.max(dp[i-1],dp[i-2]+all[i]*i);
        }
        return dp[max];
    }
}
