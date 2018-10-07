package cn.mejhwu.algorithmintroduction.charter2;

import cn.mejhwu.utils.Utils;

import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   7/19/17
 * E-mail: mejhwu@163.com
 * Description:
 */

public class _2_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return ;
        }

        mergeSort(arr, 0, arr.length -1);
    }

    /**
     * 归并排序
     * @param arr    数组
     * @param start  数组排序开始下标
     * @param end    数组排序结束下标
     */
    private static void mergeSort(int[] arr, int start, int end) {
        if(end - start <= 0) {
            return;
        }

        int div = (start + end) / 2;
        mergeSort(arr, start, div);
        mergeSort(arr, div + 1, end);

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


    public static void main(String[] args) {
        int[] arr = Utils.randomArray(2000000, 10000000);
        Instant start = Instant.now();
        mergeSort(arr);
        Instant end = Instant.now();
        System.out.println(end.getNano() - start.getNano());
    }

}
