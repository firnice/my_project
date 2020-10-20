package com.cornerstone.leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode curNode = head;
        int count = 1;


        do {
            if(curNode.next == null){
                break;
            }
            curNode = curNode.next;
            count++;
        }while (true);

        if(count == n){
           return head.next;
        }

            //末尾
        curNode= head;
        for (int i = 1; i < (count - n); i++) {
            curNode = curNode.next;
        }
        if(count == 1){
            head = null;
        }else {
            curNode.next = curNode.next.next;
        }
        return head;
    }

    
    
    public static void main(String[] args) {
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode4 = new ListNode(4,listNode5);
//        ListNode listNode3 = new ListNode(3,listNode4);
//        ListNode listNode2 = new ListNode(2,listNode3);
//        ListNode listNode1 = new ListNode(1,listNode2);
//        ListNode listNode = removeNthFromEnd(listNode1, 2);

//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode = removeNthFromEnd(listNode1,1);

        ListNode listNode2 = new ListNode(2,null);
        ListNode listNode1 = new ListNode(1,listNode2);


//
        ListNode listNode = removeNthFromEnd(listNode1, 2);
        
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
            if(listNode == null){
                break;
            }
        }while (true);
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