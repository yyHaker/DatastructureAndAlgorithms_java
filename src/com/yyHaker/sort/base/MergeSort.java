package com.yyHaker.sort.base;

/**
 * MergeSort
 *
 * @author Le Yuan
 * @date 2016/11/23
 */

public class MergeSort {

    /**
     * 归并排序
     * 思想：归并排序的思想是把一个数组分成两半，排序每一半。然后用merge方法将数组的两半归并成一个有序的数组。
     *          被分的每一半使用递归，再次划分排序，直到得到的子数组只含有一个数据项为止。正如上面所说的，归并排序需要
     *          额外的一个和AB两个数组总和相等的空间，如果初始数组几乎沾满了整个存储器，那么归并排序就不能工作了。
     *
     * 算法分析：归并排序的运行时间最差、最好和平均都是O(NlogN)，但是它需要额外的存储空间，这在某些内存紧张的机器
     *      上会受到限制。归并算法是由分割和归并两部分组成的，对于分各部分，如果我们使用二分查找，时间是O(NlogN)，在最后
     *      归并的时候时间是O(N)，所以总时间是O(NlogN)。空间复杂度为O(N)。
     * 稳定性：归并排序是稳定的，由于没有发生数据交换，所有当a=b的时候，a一开始如果在b前面，则其每一次合并后仍然在b前面，
     *       故该排序算法是稳定的。
     * 空间复杂度：空间复杂度为O(N)。
     * @param source
     */
    public void mergeSort(int[] source) {
        int[] workSpace = new int[source.length];
        recMergeSort(source,workSpace, 0, source.length-1);
    }

    private void recMergeSort(int[] source, int[] workSpace, int lowerBound, int upperBound) {
        if(lowerBound == upperBound) {
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(source, workSpace, lowerBound, mid); //左边排
            recMergeSort(source, workSpace, mid+1, upperBound); //右边排
            merge(source, workSpace, lowerBound, mid+1, upperBound);//归并
        }
    }

    private void merge(int[] a, int[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while(lowPtr <= mid && highPtr <= upperBound) {
            if(a[lowPtr] < a[highPtr]) {
                workSpace[j++] = a[lowPtr++];
            }
            else {
                workSpace[j++] = a[highPtr++];
            }
        }

        while(lowPtr <= mid) {
            workSpace[j++] = a[lowPtr++];
        }

        while(highPtr <= upperBound) {
            workSpace[j++] = a[highPtr++];
        }

        for(j = 0; j < n; j++) {
            a[lowerBound + j] = workSpace[j];
        }

    }


}
