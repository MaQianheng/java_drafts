package interviews.ChuangLin;

import java.util.ArrayList;

public class t20210103 {
    public static void main(String[] args) {
        /**
         * 写一个程序给保龄球比赛计分的程序
         * 输入： 描述保龄球比赛成绩的字符串 (描述见下面)
         * 输出： 整数分
         * <p>
         * 计数规则
         * 每一局保龄球比赛，也就是我们这里的一行， 都有 10 格。格被称之为“frame”。 在每一格里， 玩家有两次机会试图击倒全部 10 个瓶。
         * 如果第一次就击倒了全部 10 个瓶，这称之为“strike”。这一格就结束了。 这一格的分数等于 10 加上接下来两球击倒的瓶数的总和。+2
         * 如果一格中的第二个球击倒了全部 10 个瓶，称之为“spare”。这一格就结束了。 这一格的分数等于 10 加上接下来一个球击倒的瓶数。+1
         * 如果两个球之后，仍然有瓶子没有被击倒，那么这一格的分数就是两次击倒的瓶数的总和。+0
         * 如果你在最后一格（第 10 格）拿到一个 spare，你就会得到一次额外的发球机会。 如果你在最后一格拿到了 strike，你就会得到两次额外的机会。 如果额外的机会击倒了全部的球，不会重复之前的流程，额外机会的分数只用来计算最后一格的分数。
         * 游戏的分数为所有格分数的总和。
         * <p>
         * 举例
         * X 表示一个 strike
         * / 表示一个 spare
         * - 表示一个 miss
         * | 表示一格的分界线
         * || 之后的字符表示最后一格的额外机会
         * <p>
         * 实例 1
         * 输入：XXXXXXXXXXXX
         * 输出：300
         * <p>
         * 10 格 10 个 strike，两个额外机会都是 strike。
         * 每格的分数 = 10 + 接下来两球的分数 = 10 + 10 + 10 = 30
         * 总共的分数 = 10 格 X 30 = 300
         * <p>
         * 实例 2
         * 输入：9-9-9-9-9-9-9-9-9-9-
         * 输出：90
         * 每格第一个球击倒了 9 个瓶，第二个球一个没击倒，全都 miss 了。 没有额外机会。
         * 每一格 9 分
         * 总共的分数 = 10 格 X 9 = 90 分
         * <p>
         * 实例 3
         * 输入：5/5/5/5/5/5/5/5/5/5/5
         * 输出：150
         * 每格第一个球击倒了 5 个瓶，第二个球击倒了剩下的 5 个，得到了一个 spare。 一个额外机会，击倒了 5 个瓶。
         * 每格的分数 = 10 + 下一个球的分数 = 10 + 5 = 15
         * 总共的分数 = 10 格 * 15 = 150
         * <p>
         * 实例 4
         * 输入：X7/9-X-88/-6XXX81
         * 输出：167
         */
        String string = "X7/9-X-88/-6XXX81";
        int index = 0;
        int totalScore = 0;
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int baseScore = 0;
            int extraScore = 0;
            int score = 0;
            String firstLetter = string.substring(index, ++index);
            if (firstLetter.equals("X")) {
                baseScore = 10;
                extraScore = convertScore(string.substring(index, index + 2));
            } else {
                String secondLetter = string.substring(index, ++index);
                baseScore = convertScore(firstLetter + secondLetter);
                if (secondLetter.equals("/")) extraScore = convertScore(string.substring(index, index + 1));
            }
            score = baseScore + extraScore;
            integerArrayList.add(score);
            totalScore += score;
        }
        System.out.println(integerArrayList.toString());
        System.out.println(totalScore);
    }

    public static int convertScore (String string) {
        if (string.contains("/")) return 10;
        int extraScore = 0;
        for (int i = 0; i < string.length(); i++) {
            String letter = string.substring(i, i+1);
            switch (letter) {
                case "X":
                    extraScore += 10;
                    break;
                case "-":
                    continue;
                default:
                    extraScore += Integer.parseInt(letter);
                    break;
            }
        }
        return extraScore;
    }
}
