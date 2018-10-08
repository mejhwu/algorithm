package cn.mejhwu.utils;

import java.util.Random;

/**
 * Author: jhwu
 * E-mail: mejhwu@163.com
 * Date: 16-12-16
 * Time: 上午9:58.
 */
public class Utils {

    /**
     * 产生随机数组
     * @param length    数组长度
     * @param bound     数组上限
     * @return
     */
    public static int[] randomArray(int length, int bound) {
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    /**
     * 打印整型数组
     * @param arr
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(arr[arr.length-1]);
    }

    public static void printListNode(ListNode head) {
        ListNode node = head;
        while (head != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
