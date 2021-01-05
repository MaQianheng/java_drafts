package leetcode;

import org.junit.Test;

public class q0058 {
    public int lengthOfLastWord (String s) {
//        String[] strings = s.split(" ");
//        return strings[strings.length - 1].length();
        s = s.trim();
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            length += 1;
            if (s.charAt(i) == ' ') {
                return length - 1;
            }
            if (i == 0) return length;
        }
        return 0;
    }

    @Test
    public void test () {
        System.out.println(lengthOfLastWord(""));
    }
}
