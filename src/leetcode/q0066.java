package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class q0066 {
    public int[] plusOne (int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            int tmp = digits[i] + 1;
            if (tmp == 10) {
                digits[i] = 0;
                continue;
            } else {
                digits[i] = tmp;
            }
            return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    @Test
    public void test () {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 8})));
    }
}
