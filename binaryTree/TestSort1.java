package java_0408;

import java.util.Arrays;

public class TestSort1 {
//【插入类排序】
    // 【直接插入排序·升序排序】
    public static void insertSort(int[] array) {
        // [0, bound) 已排序区间
        // [bound, size) 待排序区间
        // bound 从 1 开始，则默认第一个元素已排序
        // 外循环更新待排序区间边界
        for (int bound = 1; bound < array.length; bound++) {
            int v = array[bound];  // 存放边界元素，在内循环排序中保持不变
            int cur = bound - 1;   // 已排序区间的最后一个元素下标
            // 内循环对已排序区间的元素，从后向前进行升序排序
            for (; cur >= 0; cur--) {
                // 注意!!!! if 条件若写成 >= , 插入排序就不稳定了
                // v 始终是当前外层循环的下标 bound 对应元素值
                if (array[cur] > v) {
                    // 不用交换三连，因为有 v 一直存放 cur + 1 对应的值
                    array[cur + 1] = array[cur];
                } else {
                    // 此时说明，cur 在已排序区间中已经找到了合适的位置
                    break;
                }
            }
            // cur + 1 是补偿内循环最后一次判断合法后执行的 cur--
            // 把当初的 bound 对应值放在比较后合适的位置
            array[cur + 1] = v;
        }
    }


    // 【希尔排序·升序】
    public static void shellSort(int[] array) {
        // 更新分组跨度
        int gap = array.length / 2;
        while (gap > 1) {
            // 需要循环进行分组插排
            insertSortGap(array, gap);
            gap = gap / 2;
        }
        // gap == 1 时进行最后一次插排
        insertSortGap(array, 1);
    }
    private static void insertSortGap(int[] array, int gap) {
        // 通过 bound 来划分出两个区间
        // [0, bound) 已排序区间
        // [bound, size) 待排序区间
        // 当吧 gap 替换成 1 的时候, 理论上这个代码就和前面的插排代码一模一样.
        // 处理顺序是：每组第二个元素处理完后，才处理每组第三个元素，以此类推
        for (int bound = gap; bound < array.length; bound++) {
            int v = array[bound];
            int cur = bound - gap;  // 这个操作是在找同组中相邻的上一个元素
            for (; cur >= 0; cur = cur - gap) {
                if (array[cur] > v) {
                    array[cur + gap] = array[cur];
                } else {
                    break;
                }
            }
            array[cur + gap] = v;
        }
    }


//【选择类排序】
    //【简单排序·降序】（逻辑很简单，操作很多次）
    public static void selectSort(int[] array) {
        for (int bound = 0; bound < array.length; bound++) {
            // 以 bound 位置的元素作为擂主. 循环从待排序区间中取出元素和擂主进行比较
            // 如果打擂成功, 就和擂主交换.
            for (int cur = bound + 1; cur < array.length; cur++) {
                if (array[cur] < array[bound]) {
                    swap(array, cur, bound);
                }
            }
        }
    }

    // 交换元素的方法
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    //【堆排序·大堆升序/小堆降序】
    public static void heapSort(int[] array) {
        // 先建初堆, 建议单独写一个建堆方法.
        createHeap(array);
        // 循环把堆顶元素交换到最后. 并进行调整堆
        // 循环此时是 length - 1. 当堆中只剩一个元素的时候, 也就一定是有序的了.
        for (int i = 0; i < array.length - 1; i++) {
            // 当前堆的元素个数，
            // 相当于 array.length - i（因为删除最后一个元素后，堆变小）
            int heapSize = array.length - i;
            // 交换 堆顶元素 和 堆的最后一个元素
            // 堆的最后一个元素下标 array.length - i - 1
            // 取堆的最后一个元素（交换后当前堆的最大值）
            swap(array, 0, heapSize - 1);   //
            // 把最后一个元素从堆中（待排序的部分）删除. 堆的 size 就 --
            heapSize--;
            // 此后堆的长度就又进一步缩水了 array.length - i - 1
            // [0, array.length - i - 1) 待排序区间
            // [array.length - i - 1, array.length) 已排序区间
            // [注意!!!!] 代码中的边界条件特别容易搞错
            //            -1 还是 不减 还是 +1, 最好代入数值来验证.
            // 例如可以验证 i = 0 的时候, 逻辑是否合理.
            shiftDown(array, heapSize, 0);

            /*等价于上边的四条语句
              swap(array, 0, array.length - i - 1);
              shiftDown(array, array.length - i - 1, 0);
             */
        }
    }
    // 建初堆的方法（大根堆）
    private static void createHeap(int[] array) {
        // 从最后一个非叶子节点出发向前循环, 依次进行向下调整
        for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }
    }
    // 向下调整
    private static void shiftDown(int[] array, int heapLength, int index) {
        // 这里是升序排序, 建立的是大堆.
        // 大堆就需要找出左右子树中的较大值, 再和根节点比较
        int parent = index;
        int child = 2 * parent + 1;
        while (child < heapLength) {
            if (child + 1 < heapLength && array[child + 1] > array[child]) {
                child = child + 1;
            }
            // 条件结束意味着, child 就已经是左右子树比较大的值的下标了
            if (array[child] > array[parent]) {
                // 需要交换两个元素
                swap(array, child, parent);
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }


//【交换类排序】
    //【冒泡排序】
    public static void bubbleSort(int[] array) {
        // bound 记录趟数
        for (int bound = 0; bound < array.length; bound++) {
            //方式一： 按照每次找最小的方式来进行排序. (从后往前比较交换，找最小上冒)
            // [0, bound) 已排序区间
            // [bound, size) 待排序区间
            // cur > bound 而不是 >= ,
            // 当 bound 为 0 的时候, 如果 >= , cur 也为 0, cur - 1 下标越界
            for (int cur = array.length - 1; cur > bound; cur--) {
                // 此处 cur - 1 是因为 cur 初始值是 array.length - 1.
                // 如果取 cur + 1 下标的元素, 就越上界
                // 此处的条件如果写成 >= 同样无法保证稳定性
                if (array[cur - 1] > array[cur]) {
                    swap(array, cur - 1, cur);
                }
            }
            //方式2： 按照每次找最大的方式来进行排序. （从前往后比较交换，找最大下沉）
            for (int cur = 0; cur < array.length - 1; cur++) {
                // 此处 cur + 1 是因为每次 cur 初始值是 0
                // 如果取 cur - 1 下标的元素, 就越下界
                // 此处的条件如果写成 >= 同样无法保证稳定性
                if (array[cur] > array[cur + 1]) {
                    swap(array, cur + 1, cur);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 5, 2, 7, 3, 6, 8};
        // insertSort(arr);
        // shellSort(arr);
        // selectSort(arr);
        heapSort(arr);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
