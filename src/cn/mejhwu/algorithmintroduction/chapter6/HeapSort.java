package cn.mejhwu.algorithmintroduction.chapter6;

import cn.mejhwu.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-23
 * E-mail: mejhwu@163.com
 * Description:
 * 堆排序
 */

public class HeapSort {

    /**
     * 数组中交换节点
     * @param arr
     * @param i
     * @param j
     */
    public void exchange(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 维护大根堆。
     * 假设当前节点的左右子树未已经构建好的大根堆。
     * 对于当前节点，在当前节点和其左右子节点中选择最大节点替换当前节点。并递归执行。
     * @param arr 大根堆数组
     * @param i   维护子树的根节点
     */
    public void maxHeapify(int[] arr, int i, int length) {
        if (arr == null || arr.length <= 0) {
            return;
        }


        int l = i * 2 + 1;      //左子节点
        int r = i * 2 + 2;      //右子节点

        int largestIndex = i;

        //选择父节点，左子节点，右子节点中的最大值
        if (l < length && arr[l] > arr[i]) {
            largestIndex = l;
        }

        if (r < length && arr[largestIndex] < arr[r]) {
            largestIndex = r;
        }

        //如果最大值不是父节点，则交换最大节点和父节点，并递归执行。
        if (largestIndex != i) {
            exchange(arr, largestIndex, i);
            maxHeapify(arr, largestIndex, length);
        }

    }

    /**
     * 构建大根堆。
     * 从最后一个中间节点（非叶子节点）开始向根节点进行构建。
     * @param arr
     */
    public void buildMaxHeap(int[] arr) {
        if (arr == null && arr.length <= 0) {
            return;
        }

        //最后一个中间节点（非叶子节点）
        int lastIndex = (arr.length - 2) / 2;

        for (int i = lastIndex; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }

    /**
     * 堆排序。
     * 将堆顶最大值（即arr[0]）与堆数组最后一位交换，并重新构建堆
     * @param arr
     */
    public void heapSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }

        buildMaxHeap(arr);

        for (int i = arr.length - 1; i > 0; i-- ) {
            exchange(arr, i, 0);
            maxHeapify(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();

        int[] arr = Utils.randomArray(200, 1000);

        heapSort.heapSort(arr);

        Utils.printArray(arr);
    }
}
