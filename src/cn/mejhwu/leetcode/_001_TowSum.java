package cn.mejhwu.leetcode;

import cn.mejhwu.utils.Utils;

import java.util.*;

/**
 * Given an array of integers, return indices of the
 * two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class _001_TowSum {

    static class Tuple{
        public int value;
        public int index;

        public Tuple(int value, int index) {
            this.value = value;
            this.index = index;
        }

    }

    /**
     * 解题思路：
     * 先保存值和下标，然后按值排序，利用两个指针，一个从头到尾，一个从尾到头进行遍历，
     * 两个指针和比target大，则尾指针减一，和比target小，则头指针加一.
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        int start = 0;
        int end = nums.length - 1;
        List<Tuple> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(new Tuple(nums[i], i));
        }
        list.sort((t1, t2) -> {
            if (t1.value > t2.value) {
                return 1;
            } else if (t1.value < t2.value) {
                return -1;
            } else {
                return 0;
            }
        });
        while (start < end) {
            if (list.get(start).value + list.get(end).value == target) {
                res[0] = list.get(start).index;
                res[1] = list.get(end).index;
                break;
            } else if (list.get(start).value + list.get(end).value > target) {
                end -= 1;
            } else {
                start += 1;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 利用一个hashmap存储数组值和数组下标，
     * 如果数组中存在相同的值，大的下标会覆盖小的下标，
     * 对数组进行遍历，查找在hashmap中是否存在target-num[i]的key值，
     * 以及hashmap中key值对于的value值是否等于i，如果存在且不等于，
     * 则成功找到所求结果。
     * 在对数组进行遍历查找时，下标是从小到大进行遍历的，
     * 在target等于数组中两个相同值的和的情况下，在hashmap中存储的下标
     * 会是大的下标。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] res1 = twoSum2(nums1, target1);
        Utils.printArray(res1);
        int[] nums2 = {3, 3};
        int target2 = 6;
        int[] res2 = twoSum2(nums2, target2);
        Utils.printArray(res2);
        int[] nums3 = {1, 4, 3, 3};
        int target3 = 6;
        int[] res3 = twoSum2(nums3, target3);
        Utils.printArray(res3);
        int[] nums4 = {1, 4, 3};
        int target4 = 6;
        int[] res4 = twoSum2(nums4, target4);
        Utils.printArray(res4);
    }
}