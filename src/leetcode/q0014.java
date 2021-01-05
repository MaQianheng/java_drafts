package leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

public class q0014 {
    public String longestCommonPrefix (String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder res = new StringBuilder();
        String longestStr = strs[0];
        if (longestStr.length() == 0) {
            return "";
        }
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() == 0) return res.toString();
            if (strs[i].length() > longestStr.length()) longestStr = strs[i];
        }

        for (int i = 0; i < longestStr.length(); i++) {
            String letter = longestStr.substring(i, i + 1);
            boolean flag = true;
            for (String word : strs) {
                try {
                    if (!word.substring(i, i + 1).equals(letter)) return res.toString();
                } catch (Exception e) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.append(letter);
            }
        }
        return res.toString();
    }

    @Test
    public void test () {
        System.out.println(longestCommonPrefix(new String[]{"ab", "a"}));
    }
}
