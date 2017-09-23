package cn.mejhwu.algorithmintroduction.charter2;

import cn.mejhwu.utils.Utils;

/**
 * Author: jhwu
 * E-mail: mejhwu@163.com
 * Date: 16-12-16
 * Time: 上午9:49.
 *
 */
public class _1_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = Utils.randomArray(100, 300);
        Utils.printArray(arr);
        insertionSort(arr);
        Utils.printArray(arr);
    }
}
