package k_way_merge;

import java.util.List;

public class MergeKSortedLinkedLists {

    private static ListNode mergeTwoListsUtil(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode now = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                now.next = list1;
                list1 = list1.next;

            } else {
                now.next = list2;
                list2 = list2.next;
            }

            now = now.next;
        }

        while (list1 != null) {
            now.next = list1;
            list1 = list1.next;
            now = now.next;
        }

        while (list2 != null) {
            now.next = list2;
            list2 = list2.next;
            now = now.next;
        }

        return dummy.next;

    }

    private static void mergeKListsUtil(ListNode[] lists, int start, int end) {

        if (end - start <= 1) {
            return;
        }

        if (end - start == 2) {
            lists[start] = mergeTwoListsUtil(lists[start], lists[start + 1]);
            return;
        }

        int mid = (start + end) / 2;
        mergeKListsUtil(lists, start, mid);
        mergeKListsUtil(lists, mid, end);
        lists[start] = mergeTwoListsUtil(lists[start], lists[mid]);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        mergeKListsUtil(lists, 0, lists.length);
        return lists[0];
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static ListNode createLinkedList(List<Integer> list) {
        ListNode head = new ListNode(list.get(0));
        ListNode now = head;

        for (int i = 1; i < list.size(); i++) {
            now.next = new ListNode(list.get(i));
            now = now.next;
        }

        return head;
    }

    private static String listToString(ListNode head) {
        StringBuffer sb = new StringBuffer();
        while (head != null) {
            sb.append(head.val + " ");
            head = head.next;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode list1 = createLinkedList(List.of(2, 4, 6, 8));
        ListNode list2 = createLinkedList(List.of(1, 3, 5, 7));

        System.out.println(listToString(mergeKLists(new ListNode[] { list1, list2 })));
    }
}
