package leetcode;

import org.junit.Test;

public class q0053 {
    public int maxSubArray (int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num: nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

    @Test
    public void test () {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4}));
    }
}
