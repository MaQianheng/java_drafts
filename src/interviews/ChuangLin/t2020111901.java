package interviews.ChuangLin;

import java.util.Arrays;
import java.util.Scanner;

/**
    小明想买东西，但是他钱不多。他想用有限的钱去买尽量更多件数的物品。写程序帮助他实现。
    第一行输入小明的预算。第二行输入可购买商品的价格，以空格隔开。
    最后输出可买的商品的总价格。
    例1：
        输入：
            200
            100 20 40 50 10 20 60
        输出：
            200
        解释：最多可购买6件商品：20 40 50 10 20 60，总价为200。
    例2：
        输入：
            150
            100 12 33 25 26 11 210
        输出：
            107
        解释：最多可购买6件商品：12 33 25 26 11，总价为107。
 */

public class t2020111901 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入预算");
        String strBalance = scanner.nextLine();
        int intBalance;
        try {
            intBalance = Integer.parseInt(strBalance);
        } catch (Exception e) {
            System.out.println("发生错误：" + e);
            return;
        }
        String strItemPrice = scanner.nextLine();
        String[] arrStrItemPrice = strItemPrice.split(" ");
        int[] arrIntItemPrice = new int[arrStrItemPrice.length];
        for (int i = 0; i < arrStrItemPrice.length; i++) {
            String tmp = arrStrItemPrice[i];
            try {
                int num = Integer.parseInt(tmp);
                arrIntItemPrice[i] = num;
            } catch (Exception e) {
                System.out.println("发生错误：" + e);
                return;
            }
        }
        Arrays.sort(arrIntItemPrice);
        int intCurrentPrice = 0;
        for (int j : arrIntItemPrice) {
            if ((intCurrentPrice + j) > intBalance) {
                System.out.println(intCurrentPrice);
                return;
            }
            intCurrentPrice += j;
        }
    }
}
