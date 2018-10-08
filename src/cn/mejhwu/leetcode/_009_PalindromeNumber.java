package cn.mejhwu.leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author mejhwu
 * @version 1.0
 * @date 2018/10/08
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 * Example 1:
 *
 * Input: 121
 * Output: true
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121.
 * From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */

public class _009_PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int y = x;
        int reverse = 0;
        while (y != 0) {
            reverse = reverse * 10 + y % 10;
            y = y / 10;
        }
        return x == reverse;
    }


    @Test
    public void testIsPalindrome() {
        _009_PalindromeNumber o = new _009_PalindromeNumber();
        assertEquals(true, o.isPalindrome(0));
        assertEquals(false, o.isPalindrome(-20));
        assertEquals(false, o.isPalindrome(-121));
        assertEquals(false, o.isPalindrome(20));
        assertEquals(true, o.isPalindrome(121));
        assertEquals(true, o.isPalindrome(1234321));
        assertEquals(false, o.isPalindrome(-1234321));
    }
}
