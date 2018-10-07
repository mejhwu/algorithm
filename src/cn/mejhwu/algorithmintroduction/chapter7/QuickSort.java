package cn.mejhwu.algorithmintroduction.chapter7;

import cn.mejhwu.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-23
 * E-mail: mejhwu@163.com
 * Description:
 * 快排
 */

public class QuickSort {

    public void quickSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }

        quickSort(arr, 0, arr.length-1);
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        //一趟快排
        int pivot = partion(arr, low, high);

        //左右部分递归快排
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);

    }

    /**
     * 一趟快排。
     * 选择支点，将大于支点的数移动到数组右边，将小于支点的数移动到数组左边。
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public int partion(int[] arr, int low, int high) {


        int i = low;

        /**
         * i为小于支点数的下标，
         * j为大于支点数的下标
         * 选取数组最右边值作为支点
         * 将j从数组左至右遍历，如果出现值小于支点，则将下标j与下标i交换，即将大于支点的移动到数组左边，
         * i始终为第一个大于支点数的下标。
         * 遍历结束后交换i与支点。
         */
        for (int j = low; j < high; j++) {
            if (arr[j] < arr[high]) {
                exchange(arr, i, j);
                i++;
            }
        }

        exchange(arr, i, high);

        return i;
    }

    public void exchange(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        int[] arr = Utils.randomArray(100, 2000);
        quickSort.quickSort(arr);
        Utils.printArray(arr);
    }
}
