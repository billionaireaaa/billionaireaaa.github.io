package ProblemSet;

/*
        你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
        同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
*/
public class 打家劫舍动态规划 {
    public static void main(String[] args) {
        int nums[] = {2, 7, 9, 3, 1};
        int count = rob(nums);
        System.out.println(count);
    }

    public static int rob(int nums[]) {
          /*
        思路：由于首尾也属于相邻，因此需要分别判断，以第一家是否打劫分成两个问题
        第一家抢：最后一家一定不能抢，从第0个到len-2做动态规划
        第一家不抢：从1到len-1做动态规划
        然后比较找出最大值
        */
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        if (len == 1)
            return nums[0];
        int[] dp1 = new int[len];
        int[] dp2 = new int[len + 1];
        //第一家抢
        dp1[0] = 0;
        dp1[1] = nums[0];
        for (int i = 2; i < len; i++)
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);

        //第一家不抢
        dp2[0] = 0;
        dp2[1] = 0;
        for (int i = 2; i <= len; i++)
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i - 1]);

        return Math.max(dp1[len - 1], dp2[len]);
    }

}
