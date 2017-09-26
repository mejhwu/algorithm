package cn.mejhwu.algorithmintroduction.chapter9;

import cn.mejhwu.utils.Utils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-23
 * E-mail: mejhwu@163.com
 * Description:
 * 选取第k大的数
 */

public class RandomizedSelect {


    public int select(int[] arr, int k) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        return select(arr, k-1, 0, arr.length - 1);
    }

    public int select(int[] arr, int k, int low, int high) {
        if (low == high) {
            return arr[low];
        }

        int pivot = partion(arr, low, high);
        if (pivot == k) {
            return arr[pivot];
        }
        if (pivot > k) {
            return select(arr, k, low, pivot - 1);
        } else {
            return select(arr, k, pivot + 1, high);
        }

    }

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
        RandomizedSelect randomizedSelect = new RandomizedSelect();
        int[] arr = Utils.randomArray(20, 300);
        int n = randomizedSelect.select(arr, 7);
        Utils.printArray(arr);
        System.out.println(n);
    }
}
