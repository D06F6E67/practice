package bTree.topic;

/**
 * @author lee
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode nodeHead = new ListNode();
        ListNode nextNode = new ListNode();
        nodeHead = nextNode;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                nextNode.next = list1;
                list1 = list1.next;
            } else {
                nextNode.next = list2;
                list2 = list2.next;
            }
            nextNode = nextNode.next;
        }
        if (list1 != null) nextNode.next = list1;
        if (list2 != null) nextNode.next = list2;

        return nodeHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}