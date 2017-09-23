package cn.mejhwu.algorithmintroduction.charter2;

import cn.mejhwu.utils.Utils;

import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   7/19/17
 * E-mail: mejhwu@163.com
 * Description:
 * 在归并排序中对小数组采用插入排序，
 * 小数组定义为 k <= lg(n)/2
 */

public class _2_1_MergeSortOptimize {

    public static void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return ;
        }

        int bound = (int)(Math.log(arr.length) / 5);
        mergeSort(arr, 0, arr.length -1, bound);
    }

    /**
     * 归并排序
     * @param arr    数组
     * @param start  数组排序开始下标
     * @param end    数组排序结束下标
     * @param bound  采用插入排序的小数组的大小
     */
    private static void mergeSort(int[] arr, int start, int end, int bound) {
        if(end - start <= 0) {
            return;
        }


        //小于小数组的定义值，采用插入排序
        if (end - start < bound) {
            injectionSort(arr, start, end);
        } else {
            int div = (start + end) / 2;
            mergeSort(arr, start, div, bound);
            mergeSort(arr, div + 1, end, bound);
            merge(arr, start, div, end);
        }

    }

    /**
     * 归并过程
     * @param arr    数组
     * @param start  开始下标
     * @param div    中间下标
     * @param end    结束下标
     */
    private static void merge(int[] arr, int start, int div, int end) {

        int[] copy = new int[end - start + 1];

        int i = start;
        int j = div + 1;
        int k = 0;
        while (i <= div && j <= end) {
            if (arr[i] < arr[j]) {
                copy[k++] = arr[i++];
            } else {
                copy[k++] = arr[j++];
            }
        }

        while (i <= div) {
            copy[k++] = arr[i++];
        }

        while (j <= end) {
            copy[k++] = arr[j++];
        }

        System.arraycopy(copy, 0, arr, start, copy.length);
    }

    /**
     * 插入排序
     * @param arr    数组
     * @param start  排序位置开始下标
     * @param end    排序位置结束下标
     */
    private static void injectionSort(int[] arr, int start, int end) {
        int key = 0;
        for (int i = start + 1; i <= end; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >=start && key < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = Utils.randomArray(2000000, 10000000);
        Instant start = Instant.now();
        mergeSort(arr);
        Instant end = Instant.now();
        System.out.println(end.getNano() - start.getNano());
    }
}
