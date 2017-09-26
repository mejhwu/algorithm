package cn.mejhwu.algorithmintroduction.chapter10;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-23
 * E-mail: mejhwu@163.com
 * Description:
 * 队列
 */

public class MyQueue {

    private int[] arr;

    private int front = 0;
    private int tail = 1;

    public MyQueue() {
        arr = new int[16];
    }

    public MyQueue(int length) {
        arr = new int[length];
    }

    public void enQueue(int num) throws Exception {
        if ((tail + 1) % arr.length == front) {
            throw new Exception("队列已满");
        }
        tail = (tail + 1) % arr.length;
        arr[tail] = num;

    }

    public int deQueue() throws Exception {
        if ((tail - front + arr.length) % arr.length == 1) {
            throw new Exception("队列为空");
        }
        front = (front + 1) % arr.length;
        int num = arr[front];
        return num;
    }

    public static void main(String[] args) throws Exception {
        MyQueue q = new MyQueue();

        Random rand = new Random();

        int k = 20;
        for (int j = 0; j < k; j++) {
            q.enQueue(j);
        }
        int a = 8;
        for (int j = 0; j < a; j++) {
            q.deQueue();
        }
        k = 10;
        for (int j = 0; j < k; j++) {
            q.enQueue(j);
        }


    }

}
