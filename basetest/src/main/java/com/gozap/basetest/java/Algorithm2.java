package com.gozap.basetest.java;


/**
 * Create by liuxue on 2020/9/2 0002.
 * description: 算法
 * https://www.cnblogs.com/ice-line/p/11753852.html
 */
class Algorithm2 {
    public static void main(String[] args) {
        testNodeReverse();
    }


    /**
     * 单链表反转
     * https://www.cnblogs.com/keeya/p/9218352.html
     */
    public static void testNodeReverse() {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        Node n = head;
        while (n != null) {
            System.out.print(n.value + "->");
            n = n.next;
        }
        System.out.println("");
        head = reverse(head);
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
    }

    /**
     * 递归
     * 从最后一个node开始，在弹栈的过程中将指针顺序置换
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = head.next;
        Node newHead = reverse(temp);
        temp.next = head;
        head.next = null;
        return newHead;//反转后新链表的头节点
    }

    /**
     * 遍历
     * 在链表遍历的过程中将指针顺序置换
     *
     * @param node
     * @return
     */
    public static Node reverseList(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }


}
