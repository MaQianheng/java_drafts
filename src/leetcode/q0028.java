package leetcode;

import org.junit.Test;

public class q0028 {

    public int strStr(String haystack, String needle) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        int end = haystackLength - needleLength;
        for (int i = 0; i <= end; i++) {
            if (haystack.substring(i, i+needleLength).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test () {
        System.out.println(strStr("hello", ""));
    }
}
