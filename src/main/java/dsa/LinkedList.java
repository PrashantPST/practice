package dsa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList middleNode(LinkedList head) {
        LinkedList slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * O(n) time and O(1) space, where n is the number of nodes in the LL
    */
    public LinkedList detectCycle(LinkedList head) {
        LinkedList tortoise = head;
        LinkedList hare = head;
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
    public static LinkedList mergeTwoLinkedLists(LinkedList l1, LinkedList l2) {
        LinkedList dummyHead = new LinkedList(0, null);
        LinkedList current = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.getValue() < l2.getValue()) {
                current.next = l1;
                l1 = l1.getNext();
            } else {
                current.next = l2;
                l2 = l2.getNext();
            }
            current = current.next;
        }
        // Attach the remaining part of l1 or l2
        current.next = (l1 != null) ? l1 : l2;
        return dummyHead.next;
    }

    /**
     * given an array of k linked-lists, each linked-list is sorted in ascending order
     * Merge all the linked-lists into one sorted linked-list
    */
    public LinkedList mergeKLists(LinkedList[] lists) {
        return mergeKLinkedLists(lists, 0);
    }

    private LinkedList mergeKLinkedLists(LinkedList[] lists, int i) {
        if (lists.length == 0) {
            return null;
        }
        if (i == lists.length - 1) {
            return lists[i];
        }
        LinkedList l1 = lists[i];
        LinkedList l2 = mergeKLinkedLists(lists, i + 1);
        return mergeTwoLinkedLists(l1, l2);
    }


    /*
    O(max(m,n)) m and n represent the length of l1 and l2 respectively
     */
    public LinkedList addTwoNumbersLinkedList(LinkedList l1, LinkedList l2) {
        LinkedList dummyHead = new LinkedList(0, null);
        LinkedList curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.getValue() : 0;
            int y = (l2 != null) ? l2.getValue() : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.setNext(new LinkedList(sum % 10, null));
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

    public static LinkedList reverseLinkedList(LinkedList head) {
        LinkedList previousNode = null;
        LinkedList currentNode = head;
        while (currentNode != null) {
            LinkedList nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }

    /*
       reverse the nodes of the list k at a time, 1 <= k<= length of the linked list
       If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    */
    public LinkedList reverseKGroup(LinkedList head, int k) {
        if (head == null) {
            return head;
        }
        LinkedList prev = null;
        int k2 = k;
        LinkedList current = head;
        while (current != null && k-- > 0) {
            LinkedList nodeNext = current.next;
            current.next = prev;
            prev = current;
            current = nodeNext;
        }
        if (k == 0) {
            head.next = reverseKGroup(current, k2);
        } else if (k > 0) {
            LinkedList previousNode = null;
            LinkedList currentNode = prev;
            while (currentNode != null) {
                LinkedList nextNode = currentNode.next;
                currentNode.next = previousNode;
                previousNode = currentNode;
                currentNode = nextNode;
            }
            return previousNode;
        }
        return prev;
    }

    /**
     * Pointer to head node is not given - O(1) SC and O(1) TC
     * */
    public void deleteNode(LinkedList node) {
        LinkedList nextNode = node.next;
        node.value = nextNode.value;
        node.next = nextNode.next;
        nextNode.next = null;
    }

    /* You are given the head of a singly linked-list.
   The list can be represented as
   L0 → L1 → … → Ln - 1 → Ln
   Reorder the list to be on the following form:
   L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    */
    public void reorderList(LinkedList head) {
        // find the middle of linked list
        LinkedList slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // reverse the second part of the list in-place
        LinkedList prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        LinkedList first = head, second = prev;
        while (second.getNext() != null) {
            tmp = first.getNext();
            first.setNext(second);
            first = tmp;
            tmp = second.getNext();
            second.setNext(first);
            second = tmp;
        }
    }
}
