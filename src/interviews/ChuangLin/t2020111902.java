package interviews.ChuangLin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class t2020111902 {
    /**
    两个同学想要传纸条，但是不想被别人知道内容是什么。编写程序根据输入日期转换文本。
     例1：
       输入：
           2 2
           ok
       输出：
           15 11
       解释：
           2 2：当天日期2月2日
           原文本排列：{"ABCDEFGHI", "JKLMNOPQR", "STUVWXYZ*"}
           转换后排列：{"KLMNOPQRJ", "TUVWXYZ"*S, "BCDEFGHIA"}
           月份2代表每组文本左移1次，日期2代表每组内数据左移1次
           15 11：输入文本"ok"转换后对应的每个字母的下标，15表示第1组数据的第5个字母，11表示第1组数据的第1个字母

      例2：
       输入：
           4 6
           thank you
       输出：
           36 13 15 29 26 34 32 21 37
       解释：
           4 6：当天日期4月6日
           原文本排列：{"ABCDEFGHI", "JKLMNOPQR", "STUVWXYZ*"}
           转换后排列：{"FGHIABCDE", "OPQRJKLMN", "XYZ*STUVW"}
           月份4代表每组文本左移3次，日期6代表每组内数据左移5次
           36 13 15 29 26 34 32 21 37：输入文本"thank you"转换后对应的每个字母的下标，36表示第3组数据的第6个字母...。其中空格对应"*"，34表示第3组数据的第4个字母。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strDate = scanner.nextLine();
        String strUserInput = scanner.nextLine();
        String[] arrStrDate = strDate.split(" ");
        if (arrStrDate.length != 2) {
            System.out.println("输入格式有误");
            return;
        }
        String[] arrStrContent = {
                "ABCDEFGHI",
                "JKLMNOPQR",
                "STUVWXYZ*"
        };
        int intSituation;
        try {
            intSituation = Integer.parseInt(arrStrDate[0]) % 3;
        } catch (Exception e) {
            System.out.println("发生错误：" + e);
            return;
        }
//        System.out.println(intSituation);
        String tmp;
        switch (intSituation) {
            // 不动
            // case 1:
            // 左移1次
            case 2:
                tmp = arrStrContent[0];
                arrStrContent[0] = arrStrContent[1];
                arrStrContent[1] = arrStrContent[2];
                arrStrContent[2] = tmp;
                break;
            // 左移2次
            case 0:
                tmp = arrStrContent[2];
                arrStrContent[2] = arrStrContent[1];
                arrStrContent[1] = arrStrContent[0];
                arrStrContent[0] = tmp;
                break;
            default:
                break;
        }

//        System.out.println(arrStrContent[0]);

        HashMap<String, String> mapTmp = new HashMap<String, String>();

        for (int i = 0; i < arrStrContent.length; i++) {
            String tmp2;
            String tmp3;
            int interceptLength = Integer.parseInt(arrStrDate[1]) % 9 - 1;
            if (interceptLength == -1) {
                interceptLength = 8;
            }
            tmp2 = arrStrContent[i].substring(0, interceptLength);
            tmp3 = arrStrContent[i].substring(interceptLength);

            arrStrContent[i] = tmp3 + tmp2;
//            System.out.println(arrStrContent[i]);
            for (int j = 0; j < arrStrContent[i].length(); j++) {
                String strContent = arrStrContent[i];
                String strIndex = (i + 1) + "" + (j + 1);
                mapTmp.put(strContent.substring(j, j + 1), strIndex);
            }
        }

        System.out.println(Arrays.toString(arrStrContent));

//        System.out.println(mapTmp);
        StringBuilder strResult = new StringBuilder();

        strUserInput = strUserInput.replace(" ", "*");
        for (int i = 0; i < strUserInput.length(); i++) {
//            System.out.println(strUserInput.substring(i, i+1));
            String v = mapTmp.get(strUserInput.substring(i, i+1).toUpperCase());
            strResult.append(" ").append(v);
        }
        System.out.println(strResult);

    }
}
