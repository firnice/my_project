package com.cornerstone.leetcode;

public class MergeTwoLists {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode listNode = new ListNode();
        ListNode head = listNode;

        while (l1 != null && l2 != null) {
            ListNode node = new ListNode();
            if (l1.val < l2.val) {
                node.val = l1.val;
                l1 = l1.next;
            } else {
                node.val = l2.val;
                l2 = l2.next;
            }
            head.next = node;
            head = head.next;

        }

        if (l1 != null) {
            head.next = l1;
        }

        if (l2 != null) {
            head.next = l2;
        }
        return listNode.next;
    }


    public static void main(String[] args) {

        ListNode listNode13 = new ListNode(4);
        ListNode listNode12 = new ListNode(2,listNode13);
        ListNode listNode11 = new ListNode(1,listNode12);

        ListNode listNode23 = new ListNode(4);
        ListNode listNode22 = new ListNode(3,listNode23);
        ListNode listNode21 = new ListNode(1,listNode22);


        ListNode listNode = mergeTwoLists(listNode11, listNode21);
        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
            if(listNode == null){
                break;
            }
        }while (true);

    }
}
