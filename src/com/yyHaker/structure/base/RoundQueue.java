package com.yyHaker.structure.base;

/**
 * RoundQueue
 *
 * 用循环数组来实现队列
 *
 * 分析： 和栈一样，队列中插入数据项和删除数据项的时间复杂度均为O(1)。
 * @author Le Yuan
 * @date 2016/11/23
 */
public class RoundQueue {
    private long[] a;
    private int size;   //数组大小
    private int nItems; //实际存储数量
    private int front;  //头
    private int rear;   //尾

    public RoundQueue(int maxSize) {
        this.size = maxSize;
        a = new long[size];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long value) {
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear = ++rear % size;
        a[rear] = value; //尾指针满了就循环到0处,这句相当于下面注释内容
        nItems++;
/*      if(rear == size-1){
            rear = -1;
        }
        a[++rear] = value;
*/
    }

    public long remove() {
        if(isEmpty()) {
            System.out.println("队列为空！");
            return 0;
        }
        nItems--;
        front = front % size;
        return a[front++];
    }

    /**
     * 打印所有的队列元素（从队列的头开始）
     */
    public void display() {
        if(isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        int item = front;
        for(int i = 0; i < nItems; i++) {
            System.out.print(a[item++ % size] + " ");
        }
        System.out.println("");
    }

    public long peek() {
        if(isEmpty()) {
            System.out.println("队列为空！");
            return 0;
        }
        return a[front];
    }

    public boolean isFull() {
        return (nItems == size);
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public int size() {
        return nItems;
    }
}
