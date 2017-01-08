package com.yyHaker.structure.high;

/**
 * Heap
 *
 *
 * 堆的数组实现
 * 用数组实现的完全二叉树中，节点的索引有如下特点（设该节点的索引为x）：
 *它的父节点的索引为 (x-1) / 2；
 *它的左子节点索引为 2*x + 1；
 *它的右子节点索引为 2*x + 2。
 * @author Le Yuan
 * @date 2016/11/25
 */
public class Heap {

    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize==0;
    }

    public boolean isFull() {
        return currentSize==maxSize;
    }

    public boolean insert(int key) {
        if(isFull()) {
            return false;
        }
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    //用数组实现的完全二叉树中，节点的索引有如下特点（设该节点的索引为x）：
    //它的父节点的索引为 (x-1) / 2；
    //它的左子节点索引为 2*x + 1；
    //它的右子节点索引为 2*x + 2。

    //向上调整
    public void trickleUp(int index) {
        int parent = (index - 1) / 2; //父节点的索引
        Node bottom = heapArray[index]; //将新加的尾节点存在bottom中
        while(index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    /**
     *  从堆中删除数据与插入不同，删除时永远删除根节点的数据，因为根节点的数据最大，删除完后，
     *  将最后一个叶节点移到根的位置，然后从根开始，逐级向下调整，直到满足堆中节点关键字的条件为止。
     * @return
     */
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    //向下调整
    public void trickleDown(int index) {
        Node top = heapArray[index];
        int largeChildIndex;
        while(index < currentSize/2) { //while node has at least one child
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //find larger child
            if(rightChildIndex < currentSize &&  //rightChild exists?
                    heapArray[leftChildIndex].getKey() < heapArray[rightChildIndex].getKey()) {
                largeChildIndex = rightChildIndex;
            } else {
                largeChildIndex = leftChildIndex;
            }
            if(top.getKey() >= heapArray[largeChildIndex].getKey()) {
                break;
            }
            heapArray[index] = heapArray[largeChildIndex];
            index = largeChildIndex;
        }
        heapArray[index] = top;
    }

    //根据索引改变堆中某个数据
    public boolean change(int index, int newValue) {
        if(index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if(oldValue < newValue) {
            trickleUp(index);
        }
        else {
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap() {
        System.out.println("heapArray(array format): ");
        for(int i = 0; i < currentSize; i++) {
            if(heapArray[i] != null) {
                System.out.print(heapArray[i].getKey() + " ");
            }
            else {
                System.out.print("--");
            }
        }
    }
}
class Node {
    private int iData;
    public Node(int key) {
        iData = key;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int key) {
        iData = key;
    }
}