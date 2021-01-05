package leetcode;

import org.junit.Test;

public class q0009 {
    public boolean isPalindrome (int x) {
        StringBuilder rev = new StringBuilder(String.valueOf(x)).reverse();
        StringBuilder ori = new StringBuilder(String.valueOf(x));
        return rev.toString().equals(ori.toString());
    }

    @Test
    public void test () {
        System.out.println(isPalindrome(121));
    }
}
