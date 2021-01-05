package leetcode;

import org.junit.Test;

import java.util.ArrayList;

public class q0021 {
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ArrayList<Integer> listNode2ArrayList (ListNode listNode) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        while (true) {
            integerArrayList.add(listNode.val);
            if (listNode.next == null) break;
            listNode = listNode.next;
        }
        return integerArrayList;
    }

//    public ListNode arrayList2ListNode (ArrayList<Integer> integerArrayList) {
//        ListNode listNode = new ListNode(integerArrayList.get(0));
//        for (int i = 1; i < integerArrayList.size(); i++) {
//            ListNode listNode1 = new ListNode(integerArrayList.get(i));
//            listNode.next = listNode1;
//        }
//    }

    @Test
    public void test () {}
}