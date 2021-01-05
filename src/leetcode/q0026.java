package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class q0026 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println(Arrays.toString(nums));
        return i + 1;
    }

    @Test
    public void test () {
        removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
    }
}
