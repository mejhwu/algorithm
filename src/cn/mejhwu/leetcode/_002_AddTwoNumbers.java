package cn.mejhwu.leetcode;

import cn.mejhwu.utils.ListNode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author mejhwu
 * @version 1.0
 * @date 2018/10/08
 * You are given two non-empty linked lists representing
 * two non-negative integers. The digits are stored
 * in reverse order and each of their nodes contain
 * a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any
 * leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */

public class _002_AddTwoNumbers {

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int c = 0;
        int temp = 0;
        ListNode head = null;
        ListNode pre = null;
        while (l1 != null && l2 != null) {
            temp = l1.val + l2.val + c;
            c = temp / 10;
            temp = temp % 10;
            if (head == null) {
                head = new ListNode(temp);
                pre = head;
            } else {
                ListNode node = new ListNode(temp);
                pre.next = node;
                pre = node;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            temp = l1.val + c;
            c = temp / 10;
            temp = temp % 10;
            if (head == null) {
                head = new ListNode(temp);
                pre = head;
            } else {
                ListNode node = new ListNode(temp);
                pre.next = node;
                pre = node;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            temp = l2.val + c;
            c = temp / 10;
            temp = temp % 10;
            if (head == null) {
                head = new ListNode(temp);
                pre = head;
            } else {
                ListNode node = new ListNode(temp);
                pre.next = node;
                pre = node;
            }
            l2 = l2.next;
        }
        if (c != 0) {
            ListNode node = new ListNode(c);
            pre.next = node;
        }
        return head;
    }

    /**
     * 改进
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        int temp = 0;
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (l1 != null || l2 != null) {
            temp = c;
            if (l1 != null) {
                temp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                temp += l2.val;
                l2 = l2.next;
            }
            c = temp / 10;
            temp = temp % 10;
            pre.next = new ListNode(temp);
            pre = pre.next;
        }

        if (c != 0) {
            pre.next = new ListNode(c);
        }
        return head.next;
    }
    @Test
    public void testAddTwoNumbers() {

    }

}
