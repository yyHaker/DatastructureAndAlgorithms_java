package com.yyHaker.structure.base;

/**
 * ArrayStack
 *
 * 栈可以用数组实现也可以用链表实现
 *
 * 栈的数组实现
 * 分析： 数据项入栈和出栈的时间复杂度均为O(1)。这也就是说，栈操作所消耗的时间不依赖于栈中数据项的个数，
 *          因此操作时间很短。栈不需要比较和移动操作。
 * @author Le Yuan
 * @date 2016/11/23
 */
public class ArrayStack {
    private long[] a;
    private int size; //栈数组的大小
    private int top; //栈顶

    public ArrayStack(int maxSize) {
        this.size = maxSize;
        this.a = new long[size];
        this.top = -1; //表示空栈
    }

    public void push(long value) {//入栈
        if(isFull()) {
            System.out.println("栈已满！");
            return;
        }
        a[++top] = value;
    }

    public long peek() {//返回栈顶内容，但不删除
        if(isEmpty()) {
            System.out.println("栈中没有数据");
            return 0;
        }
        return a[top];
    }

    public long pop() { //弹出栈顶内容，删除
        if(isEmpty()) {
            System.out.println("栈中没有数据！");
            return 0;
        }
        return a[top--];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == size -1);
    }

    public void display() {
        for(int i = top; i >= 0; i--) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }
}