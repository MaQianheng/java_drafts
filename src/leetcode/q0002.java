package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class q0002 {
    /*
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位数字。

    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/add-two-numbers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Integer integer1 = func(l1);
        Integer integer2 = func(l2);
        String res = Integer.toString(integer1 + integer2);
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        for (int i = res.length() - 1; i >= 0; i--) {
            Integer integer = Integer.parseInt(String.valueOf(res.charAt(i)));

            ListNode tmp = new ListNode(integer);
            cursor.next = tmp;
            cursor = tmp;
        }
        return root.next;
    }

    public Integer func (ListNode listNode) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        ListNode nextListNode = listNode;
        while (true) {
            Integer num;
            if (isFirst) {
                num = listNode.val;
                isFirst = false;
                nextListNode = listNode.next;
            } else {
                num = nextListNode.val;
                nextListNode = nextListNode.next;
            }
            stringBuilder.append(num);
            if (nextListNode == null) break;
            if (nextListNode.next == null) {
                stringBuilder.append(nextListNode.val);
                break;
            }
        }
        return Integer.parseInt(stringBuilder.reverse().toString());
    }

    @Test
    public void test() {
//        ListNode listNode = addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))), new ListNode(5, new ListNode(6, new ListNode(4))));
//        ListNode listNode = addTwoNumbers(new ListNode(0), new ListNode(0));
        System.out.println(Integer.parseInt("9999999991"));
    }
}

class ListNode {
    Integer val;
    ListNode next;

    ListNode() {}

    ListNode(Integer val) {
        this.val = val;
    }

    ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
