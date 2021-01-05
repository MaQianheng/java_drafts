package leetcode;

import org.junit.Test;

public class q0007 {
    public int reverse (int x) {
        String str = String.valueOf(x);
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            String tmp = str.substring(i, i + 1);
            if (tmp.equals("-")) {
                res.insert(0, tmp);
                continue;
            }
            res.append(tmp);
        }
        try {
            return Integer.parseInt(res.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Test
    public void test () {
//        String str = String.valueOf(123);
//        for (int i = str.length() - 1; i >= 0; i--) {
//            String tmp = str.substring(i, i + 1);
//            System.out.println(tmp);
//        }
//        System.out.println(9646324351 > Math.pow(2, 31));
//        System.out.println(Math.pow(2, 31));
        System.out.println(reverse(1534236469));
    }
}
