package cn.mejhwu.algorithmintroduction.chapter10;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-23
 * E-mail: mejhwu@163.com
 * Description:
 * 栈
 */

public class MyStack {

    private int[] arr;
    private int top = 0;

    public MyStack(int length) {
        arr = new int[length];
    }

    public MyStack() {
        arr = new int[16];
    }

    public void push(int num) {
        if (top >= arr.length - 1) {
            int[] newArr = new int[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[++top] = num;
    }

    public int pop() throws Exception {
        if (top == -1) {
            throw new Exception("栈为空");
        }
        return arr[top--];
    }

}
