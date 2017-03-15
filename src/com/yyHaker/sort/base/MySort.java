package com.yyHaker.sort.base;

/**
 * @Description: 自己实现的快速排序算法
 * @Author: LeYuan
 * @Date: ${time}  ${date}
 * @Problem 注意对于排序有相同的数时，容易出现栈溢出的问题，例如你排序·[17,17,17],你会发现递归停不下来;
 *           后来采用在数组中找一个位置存放基准，partrition结束之前将base放到指定位置，保证左边的值小于base，
 *           右边的值大于base，最后再递归调用QuickSort(num,i ,k-1);QuickSort(num,k+1,j);还有就是注意数组越界的问题
 * @conclusion 编写递归算法时注意什么时候递归结束，防止栈溢出
 */
public class MySort {
    public static void main(String []args) {
        int[] num2 = {1, 0, 23, 17, 29, 34, 2, 17, 23, 59};
        //int num2[]={0,1,2,17,17};
        System.out.println("After sort the Array is:");

        //调用相关方法排序
        MyBaseSort.QuickSort(num2,0,num2.length-1);
        for (int i = 0; i < num2.length; i++) {
            System.out.print(num2[i]+" ");
        }
    }
}

 class MyBaseSort{
    /**
     * 快速排序
     * @param num
     * @param i
     * @param j
     */
    public static void QuickSort(int num[],int i,int j){
        if(i>=j){
            return;
        } else{
            //设置一个基准
            int base =num[i];
            int k=partrition(num,i,j,base);
            QuickSort(num,i ,k-1);
            QuickSort(num,k+1,j);
        }

    }


    public static int partrition(int num[],int i,int j,int base){
        //数组最左边num[i]存放基准base
        int low=i+1,high=j;
        int temp;
        while(low<high){
            //swap(num[low],num[high]);
            temp=num[high];
            num[high]=num[low];
            num[low]=temp;

            while(num[low]<base&&low<j){
                low++;
            }
            while(num[high]>base&&high>i){
                high--;
            }
        }
        //将基准放到指定位置swap(num[i],num[high])
           temp=num[i];
           num[i]=num[high];
           num[high]=temp;
        return high;
    }
}