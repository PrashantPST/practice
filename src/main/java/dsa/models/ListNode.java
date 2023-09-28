package dsa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListNode {
    private int value;
    private ListNode next;

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /*
   O(n) time and O(1) space, where n is the number of nodes in the LL
    */
    public ListNode detectCycle(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;
        do {
            if (hare == null || hare.getNext() == null) {
                return null;
            }
            tortoise = tortoise.getNext();
            hare = hare.getNext().getNext();
        }
        while (tortoise != hare);
        tortoise = head;
        while (tortoise != hare) {
            tortoise = tortoise.getNext();
            hare = hare.getNext();
        }
        return hare;
    }

    /*
    O(n + m) TC & O(1) SC
     */
    public static ListNode mergeTwoLinkedLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0, null);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.getValue() < l2.getValue()) {
                curr.setNext(l1);
                l1 = l1.getNext();
            } else {
                curr.setNext(l2);
                l2 = l2.getNext();
            }
            curr = curr.getNext();
        }
        if (l1 != null) {
            curr.setNext(l1);
        }
        if (l2 != null) {
            curr.setNext(l2);
        }
        return dummyHead.getNext();
    }

    /*
   given an array of k linked-lists, each linked-list is sorted in ascending order
   Merge all the linked-lists into one sorted linked-list
    */
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLinkedLists(lists, 0);
    }

    private ListNode mergeKLinkedLists(ListNode[] lists, int i) {
        if (lists.length == 0) {
            return null;
        }
        if (i == lists.length - 1) {
            return lists[i];
        }
        ListNode l1 = lists[i];
        ListNode l2 = mergeKLinkedLists(lists, i + 1);
        return mergeTwoLinkedLists(l1, l2);
    }


    /*
    O(max(m,n)) m and n represent the length of l1 and l2 respectively
     */
    public ListNode addTwoNumbersLinkedList(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0, null);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.getValue() : 0;
            int y = (l2 != null) ? l2.getValue() : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.setNext(new ListNode(sum % 10, null));
            curr = curr.getNext();
            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }
        }
        return dummyHead.getNext();
    }

    public static ListNode reverseLinkedList(ListNode head) {
        ListNode previousNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }
}
