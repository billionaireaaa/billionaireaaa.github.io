package ProblemSet;

/*给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

        数组中的每个元素代表你在该位置可以跳跃的最大长度。

        判断你是否能够到达最后一个下标。*/

public class 跳跃游戏 {
    public static void main(String[] args) {
        int nums[] = {2,3,0,1,4};
        System.out.println(jump(nums));
    }

    public static boolean canJump(int[] nums) {
        int reach = 0;//最远可以到达的位置
        for (int i = 0; i < nums.length ; i++) {
            if (i > reach)
                return false;

            reach = Math.max(nums[i] + i, reach);

            if (reach >= nums.length - 1)
                return true;
        }
        return false;
    }
                                              //      0,1,2,3,4
    public static int jump(int nums[])//int nums[] = {2,3,0,1,4};
    {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;

    }
}
