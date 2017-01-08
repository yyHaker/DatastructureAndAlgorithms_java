package com.yyHaker.sort.base;

/**
 * QuickSort
 *
 * @author Le Yuan
 * @date 2016/11/23
 */
public class QuickSort {

    /**
     * 快速排序
     * 思想:快速排序本质上通过一个数组划分为两个子数组，然后递归地调用自身为每一个子数组进行快速排序来实现的，即算法分为三步：
     *      1. 把数组或者子数组划分为左边（较小的关键字）的一组和右边（较大的关键字）的一组；
     *     2. 调用自身对左边的一组进行排序；
     *     3. 调用自身对右边的一组进行排序。
     *    经过一次划分之后，所有在左边子数组的数据项都小于在右边子数组的数据项，只要对左边子数组和右边子数组分别进行排序，
     *    整个数组就是有序的了。
     *         快速排序需要划分数组，这就用到了划分算法。划分算法是由两个指针（这里是指数组数据项，非C++中所说的指针）开始工作，两个指针
     *    分别指向数组的两头，左边的指针leftPtr向右移动，右边的指针rightPtr向左移动。当leftPtr遇到比枢纽（待比较的数据项，比其小的在
     *    其左边，比其大的在其右边，下面均称之为“枢纽”）小的数据项时继续右移，当遇到比枢纽大的数据项时就停下来；类似的rightPtr想反
     *    。两边都停下后，leftPtr和rightPtr都指在数组的错误一方的位置的数据项，交换这两个数据项。交换后继续移动这两个指针。
     *
     * 算法分析:快速排序是一种不稳定的排序方法，其平均时间复杂度为O(NlogN)，最坏的情况下退化成插入排序了，为O(N2)。
     *     （最坏的情况例如8 7 6 5 4 3 2 1，以8作为划分点）
     *       在理想状态下应该选择被排序的数据项的中值数据项作为枢纽（上面程序中是用数组的最后一项作为枢纽的）。也就是说，
     *       应该有一半的数据项大于枢纽，一般的数据项小于枢纽。这会使数组被划分成两个大小相等的子数组。对于快速排序算法来说，
     *       拥有两个大小相等的子数组是最优的情况，最坏的情况就是一个子数组只有一个数据项，另一个子数组含有N-1个数据项。
     *       所以上面的算法中如果最右边的数据是最小的或者最大的，那就可能导致最坏的情况出现。
     *
     * 稳定性： 快速排序是不稳定的，当a=b>pivot且a在b前面的时候，由于从后面开始遍历，故b会先于a被替换到pivot的前面，
     *     这样，b就变成了在a的前面，也就是说，ab位置对调，故该排序算法不稳定
     * 空间复杂度：空间复杂度平均为O(logN)，空间复杂度主要是由于递归造成的。
     * @param a
     */
    public void quickSort(int[] a) {
        recQuickSort(a,0, a.length-1);
    }

    public void recQuickSort(int[] a, int left, int right) {
        if(right-left <= 0) {
            return;
        }
        else {
            int pivot = a[right]; //保存最右边的值，以这个值作为划分点
            int partition = partitionIt(a,left, right, pivot);//将数组划分两部分，并将划分点的值放在正确位置，并返回该位置
            recQuickSort(a, left, partition-1);//调用自身对左边进行排序
            recQuickSort(a, partition+1, right);//调用自身对右边进行排序
        }
    }

    public int partitionIt(int[] a, int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while(true) {
            while(a[++leftPtr] < pivot){} //往上找
            while(rightPtr > 0 && a[--rightPtr] > pivot){} //往下找
            if(leftPtr >= rightPtr) break;
            else swap(a,leftPtr, rightPtr);
        }
        swap(a,leftPtr, right);//将划分放在正确的位置
        return leftPtr;//返回划分点，用于再次小范围划分
    }

    /**
     * 交换数据
     * @param source
     * @param m
     * @param n
     */
    public void swap(int [] source,int m,int n){
        int temp=source[m];
        source[m]=source[n];
        source[n]=temp;
    }
}

class quickQuicksort2{
    /**
     * 快速排序
     * 思想：改进上面的算法，使用“三数据项取中”划分：找到数组里的第一个、最后一个以及中间位置数据项的值，
     *     将三个中处在中间大小的数据项作为枢纽，且将三个数排好序。
     *
     * 算法分析:三数据项取中法除了对选择枢纽更为有效外，还有另一个好处：可以对第二个内部while循环中取消rightPtr>left（即rightPtr>0）
     *     的测试，以略微提高算法的执行速度。因为在选择的过程中使用三数据项取中法不仅选择了枢纽，而且对这三个数据项进行了排序，
     *    所以就可以保证数组最左端的数据项小于或者等于枢纽，最右端的数据项大于或者等于枢纽，所以就可以省去rightPtr<0的检测了，
     *    leftPtr和rightPtr不会分别越过数组的最右端或者最左端。
     *        三数据项取中还有一个小的好处是，对左端、中间以及右端的数据项排序后，划分过程就不需要再考虑这三个数据项了，所以上面的程
     *   序中左端真正是从left+1处开始的，右端真正是从right-2处开始的（因为right处存的是比枢纽大的数据项，right-1处存的是枢纽）。
     *            如果使用三数据项取中划分方法，则必须要遵循快速排序算法不能执行三个或者少于三个项的划分规则。在这种情况下，数字3被称为
     *   切割点(cutoff)。在上面的例子中，我们用一段代码手动对两个或三个数据项的子数组来排序的，但是这不是最好的方法。
     *
     * @param a
     */
    public void quickSort2(int[] a) {
        recQuickSort2(a, 0, a.length-1);
    }

    public void recQuickSort2(int[] a, int left, int right) {
        int size = right - left + 1;
        if(size <= 3) {
            manualSort(a, left, right);//数据项小于等于3个，直接排
        } else {
            long median = medianOf3(a, left, right);//取左边、右边和中间三个数中中等大小的数作为枢纽
            int partition = partitionIt2(a, left, right, median);//将枢纽放到正确的位置
            recQuickSort2(a, left, partition-1);//调用自身对左边进行排序
            recQuickSort2(a, partition+1, right);//调用自身对右边进行排序
        }
    }
    private void manualSort(int[] a, int left, int right) {
        int size = right - left + 1;
        if(size <= 1) {
            return; //1个不用排
        }
        if(size == 2) {
            if(a[left] > a[right]) { //2个很好排
                swap(a,left, right);
            }
            return;
        }
        else { //3个比较下就可以排好了
            int center = right - 1;
            if(a[left] > a[center]) {
                swap(a,left, center);
            }
            if(a[left] > a[right]) {
                swap(a,left, right);
            }
            if(a[center] > a[right]) {
                swap(a,center, right);
            }
        }
    }

    private long medianOf3(int[] a, int left, int right) {
        int center = (left + right) / 2;
        if(a[left] > a[center]) {
            swap(a,left, center);
        }
        if(a[left] > a[right]) {
            swap(a,left, right);
        }
        if(a[center] > a[right]) {
            swap(a,center, right);
        }//已经将三个排好序
        swap(a,center, right - 1); //然后将枢纽保存在right-1位置
        return a[right-1];//这保证了首位置比枢纽值小，最末尾位置比枢纽值大
    }

    public int partitionIt2(int[] a, int left, int right, long pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while(true) {
            while(a[++leftPtr] < pivot){} //往上找
            while(a[--rightPtr] > pivot){} //往下找
            if(leftPtr >= rightPtr) break;
            else swap(a,leftPtr, rightPtr);
        }
        swap(a,leftPtr, right-1);//把right-1处存放的枢纽放到正确位置
        return leftPtr;//返回划分点，用于再次小范围划分
    }

    /**
     * 交换数据
     * @param source
     * @param m
     * @param n
     */
    public void swap(int [] source,int m,int n){
        int temp=source[m];
        source[m]=source[n];
        source[n]=temp;
    }


    /**
     * 结合插入排序和快速排序、三项取中法
     * 思想：处理小划分的另一个选择是使用插入排序。当使用插入排序的时候，不以限制3为切割点，可以把界限定位10、20或者其他任何数，
     *    试验不同切割点的值找到最好的执行效率是很有意义的。最好的选择值取决于计算机、操作系统、编译器等。这里使用9作为切割点。
     *    也就是说，当待比较的数小于等于9时，我们使用插入排序，大于9时我们使用快速排序法。
     * @param a
     */
    public void quickSort3(int[] a) {
        recQuickSort3(a, 0, a.length-1);
    }

    public void recQuickSort3(int[] a, int left, int right) {
        int size = right - left + 1;
        if(size < 10) {
            insertionSort(a, left, right);//小于10项使用插入排序
        }
        else { //大于10项使用快速排序
            long median = medianOf3(a, left, right);
            int partition = partitionIt2(a, left, right, median);//上面的partionIt2方法
            recQuickSort3(a, left, partition-1);
            recQuickSort3(a, partition+1, right);
        }
    }
    private void insertionSort(int[] a, int left, int right) {
        for(int i = left + 1; i <= right; i++) {
            for(int j = i; (j > left) && (a[j] < a[j-1]); j--) {
                swap(a,j, j-1);
            }
        }
    }

}
