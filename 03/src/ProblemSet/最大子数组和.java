package ProblemSet;

import java.util.Arrays;

//1.找到第一个不为0的数开始计算
/*2.
 * */
public class 最大子数组和 {
    public static void main(String[] args) {
        int[] ints = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(ints));
    }
    public  static int maxSubArray(int[] nums) {
        int index = 0;
        if (nums.length==1)
            return nums[0];
        int m = 0;
        while (nums[index] < 0) {
            m=Math.max(m,nums[index]);
            index++;
        }
        if(index== nums.length-1)
        {
            return m;
        }
        int dp[]=new int[nums.length];
        if (index==0)
        {
            dp[0]=nums[0];
            index++;
        }
        for (int i = index; i < nums.length; i++) {
            if(dp[i-1]+nums[i]>0)
            {
                dp[i]=dp[i-1]+nums[i];
            }
            else
            {
                dp[i]=0;
            }
        }
        Arrays.sort(dp);
        return dp[nums.length-1];
    }
}
