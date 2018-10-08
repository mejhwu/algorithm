package cn.mejhwu.leetcode;

import cn.mejhwu.utils.Utils;

/**
 * @author mejhwu
 * @version 1.0
 * @date 2018/10/07
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 */

public class _007_ReverseInteger {

    public int reverse1(int x) {
        String prefix = "";
        if (x < 0) {
            prefix = "-";
            x *= -1;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        sb.reverse();
        String str = sb.toString();
        str = str.replaceFirst("^0?", "");
        int res = 0;
        try {
            res = Integer.parseInt(prefix + str);
        } catch (Exception e) {}
        return res;
    }

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (x > 0 && res <= (Integer.MAX_VALUE - x % 10) / 10) {
                res = res * 10 + x % 10;
                x = x / 10;
            } else if (x < 0 && res >= (Integer.MIN_VALUE - x % 10) / 10 ) {
                res = res * 10 + x % 10;
                x = x / 10;
            } else {
                return 0;
            }
        }
        return res;
    }

    /**
     * leetcode 给出的答案
     * @param x
     * @return
     */
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        _007_ReverseInteger o = new _007_ReverseInteger();
//        System.out.println(o.reverse(123));
//        System.out.println(o.reverse(-123));
//        System.out.println(o.reverse(120));
//        System.out.println(o.reverse(1200));
//        System.out.println(o.reverse(-1200));
//        System.out.println(o.reverse(-1));
//        System.out.println(o.reverse(2147483647));
//        System.out.println(o.reverse(-2147483648));
        System.out.println(o.reverse(1463847412));
    }
}
