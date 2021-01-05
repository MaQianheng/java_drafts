package leetcode;

import org.junit.Test;

public class q0035 {

    public int searchInsert(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == target) return i;
            try {
                if (target > nums[i - 1] && target < nums[i]) {
                    return i;
                }
            } catch (Exception e) {
                return i + 1;
            }
        }
        return nums.length;
    }

    @Test
    public void test() {
        System.out.println(searchInsert(new int[]{1, 3, 5}, 4));
    }

}
