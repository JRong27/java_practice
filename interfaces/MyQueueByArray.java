package java_0325;

public class MyQueueByArray {
    //数组方式建立
    // 普通数组出队列要搬运，效率低
    // 用循环队列来实现
    // head 始终是队首元素，tail 始终是队尾的下一个元素
    // 有效元素的范围就是 [head, tail)，tail 可能在 head 之前
    // 当 head 和 tail 重合，则可能是空队列，也可能是满队列
    // 可以引入 size 变量来标记判断 空 or 满
    private int[] array = new int[100];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    // 入队列
    public void offer(int val) {
        if (size == array.length) {
            // 返回空，说明队列已满，无法插入
            // 其实也可以不作操作，为空语句？
            return;
        }
        // 保证下标不越界
        array[tail] = val;
        tail++;
        // 当 tail++ 超出有效范围，则从头开始
        if (tail >= array.length) {
            tail = 0;
        }
        size++;
    }

    // 出队列
    public Integer poll() {
        if (size == 0) {
            return null;
        }
        Integer ret = array[head];
        head++;
        if (head >= array.length) {
            head = 0;
        }
        size--;
        return ret;
    }


    //取队首元素
    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return array[head];
    }
}
