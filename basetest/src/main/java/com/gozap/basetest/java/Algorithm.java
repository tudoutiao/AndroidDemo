package com.gozap.basetest.java;

import io.reactivex.Observable;

/**
 * Create by liuxue on 2020/8/31 0031.
 * description:算法
 */
class Algorithm {
    public static void main(String[] args) {
        //快速排序
        int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        quickSort(arr, 0, arr.length - 1);
        for (int element : arr) {
            System.out.print(element + ",");
        }
        //堆排序
//        heapSort();
        //希尔排序
//        testShellSort();
        //
        testkmp();

    }

    /**
     * 希尔排序
     * https://www.cnblogs.com/chengxiao/p/6104371.html
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一
     * O(n^1.3)
     */
    public static void testShellSort() {
        int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        int d = arr.length / 2;
        while (d > 0) {
            shellSort(arr, d);
            d /= 2;
        }
    }

    public static void shellSort(int arr[], int gap) {
        //从第gap个元素，逐个对其所在组进行直接插入排序操作
        for (int i = gap; i < arr.length; i++) {
            int j = i;
            while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                //插入排序采用交换法
                swap(arr, j, j - gap);
                j -= gap;
            }
        }
    }

    //=====================================================================
    public static void heapSort() {
        int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        //1、建立大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从最后一个非叶子节点从下至上，从右至左调整结构
            heapAdjust(arr, i, arr.length - 1);
        }
        //2、调整结构，+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);//将堆顶元素与末尾元素进行交换
            heapAdjust(arr, 0, i);
        }

        for (int element : arr) {
            System.out.print(element + ",");
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 堆排序的基本思想：
     * 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
     * 将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
     * 如此反复执行，便能得到一个有序序列了
     * 调整大堆顶（升序）
     * O(nlogn)
     * https://www.cnblogs.com/chengxiao/p/6129630.html
     *
     * @param arr    待调整数组
     * @param i      起始指针
     * @param length 结束指针
     */
    public static void heapAdjust(int[] arr, int i, int length) {
        int temp = arr[i];//取出最后一个非叶子节点元素
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {//从i结点的左子结点开始，也就是2i+1处开始,遍历i节点下所有子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }


    /**
     * 快速排序
     * https://www.e-learn.cn/content/java/1545532
     * O(nlgn)
     */
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //基准位
        temp = arr[low];
        while (i < j) {
            //先看右边，依次左移，获取比基数小的数据
            while (temp <= arr[j] && i < j) {
                j--;
            }
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //i检索到比基数大的数据  j检索到比基数小的数据  将两个数据交换位置
            if (i < j && i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //当i==j时，将指向元素和基数做交换
        arr[low] = arr[i];
        arr[i] = temp;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }


    /**
     * 插入排序:取出i+1和前面的每个元素作比较，小于(i+1)元素位置后插入
     * https://www.cnblogs.com/coding-996/p/12275710.html
     * 复杂度O(n^2)
     */
    public static void insertSort() {
        int[] array = {84, 6, 34, 0, 19};
        for (int i = 0; i < array.length - 1; i++) {
            int pre = i;
            int value = array[pre + 1];//待插入数据
            while (pre >= 0 && array[pre] > value) {
                array[pre + 1] = array[pre];
                pre--;
            }
            array[pre + 1] = value;
        }
        for (int element : array) {
            System.out.print(element + ",");
        }
    }

    /**
     * 选择排序：将最小值放到前面
     * 复杂度O(n^2)
     */
    public static void selectSort() {
        int[] array = {84, 6, 34, 0, 19};
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }

        for (int element : array) {
            System.out.print(element + ",");
        }
    }

    /**
     * 冒泡算法：与相邻元素比较
     * 复杂度O(n^2)
     */
    public static void bubblingSortAsc() {
        int[] array = {84, 6, 34, 0, 19};
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }


    //===============================================================

    /**
     * 二分查寻 非递归（数组需要有序排列）
     * O(log n)
     */
    private static void testBinarySearch() {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        if (index != -1) {
            System.out.println("找到了，下标为：" + index);
        } else {
            System.out.println("没有找到--");
        }
    }

    /**
     * @param arr
     * @param target
     * @return
     */
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1; // 向左找
            } else {
                left = mid + 1; // 向右找
            }
        }
        return -1;
    }

    //================KMP算法
    private static void testkmp() {
        String a = "12345656789";
        String b = "567";
        int index = strStrByViolence(a, b);
        System.out.println(index);

        findMatchPosition();
    }

    /**
     * 暴力匹配
     *
     * @param str  文本串
     * @param dest 模式串
     * @return
     */
    public static int strStrByViolence(String str, String dest) {
        int index = -1;
        if (str.length() <= 0 || dest.length() <= 0 || dest.length() > str.length())
            return index;

        int i = 0;
        int j = 0;
        while (i <= (str.length() - dest.length())) {
            if (str.charAt(i) == dest.charAt(j)) {
                if (j == dest.length() - 1) {
                    return i - j;
                } else {
                    i++;
                    j++;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return index;
    }

    public static void findMatchPosition() {
        String str = "BBC ABCDAB ABCDABDABDE";
        String matchStr = "ABCDABD";
        int[] next = initNext(matchStr);
        for (int element : next) {
            System.out.print(element + ",");
        }

        int sIndex = 0;
        int mIndex = 0;
        while (sIndex < str.length() && mIndex < matchStr.length()) {
            if (str.charAt(sIndex) == matchStr.charAt(mIndex)) {
                sIndex += 1;
                mIndex += 1;
            } else if (next[mIndex] == -1) {
                sIndex++;
            } else {
                mIndex = next[mIndex];
            }
        }
        int result = mIndex == matchStr.length() ? (sIndex - mIndex) : -1;
        System.out.print("result :" + result);
    }

    /**
     * 初始化部分匹配数组
     *
     * @param matchStr
     * @return
     */
    public static int[] initNext(String matchStr) {
        int[] next = {-1};
        if (strIsValid(matchStr)) {
            int matchLen = matchStr.length();
            next = new int[matchLen];
            next[0] = -1;
            int head = 0;
            int tail = -1;
            while (head < matchLen - 1) {
                if (tail == -1 || matchStr.charAt(head) == matchStr.charAt(tail)) {
                    tail += 1;
                    head += 1;
                    next[head] = tail;
                } else {
                    tail = next[tail];
                }
            }
        }
        return next;
    }

    public static boolean strIsValid(String str) {
        return str != null && !str.trim().isEmpty();
    }


}
