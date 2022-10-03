package 草稿;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class a {
    public static void main(String[] args) {
        int nums[] = {3, -1, 4};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0, sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (count == 0) {
                    max = Math.max(max, sum);
                    if (sum != 0) {
                        sum *= nums[i];
                    } else
                        sum = (sum + 1) * nums[i];
                    count++;
                } else {
                    sum *= nums[i];
                    count = 0;
                }
            } else if (nums[i] == 0) {
                count = 0;
                max = Math.max(max, sum);
                sum = 0;
            } else {
                if (sum == 0) {
                    sum = (sum + 1) * nums[i];
                } else {
                    if (sum < 0 && i == nums.length - 1) {
                        max=Math.max(max,nums[i]);
                    }
                    else
                    sum *= nums[i];
                }
            }
        }
        max = Math.max(max, sum);
        return max;
    }
}






