package java_0327;

import java.util.LinkedList;
import java.util.Queue;

public class StackBy2Queues {
    // 因为LinkedList也实现了Queue接口，所以可以向上转型
    private Queue<Integer> A = new LinkedList<>();
    private Queue<Integer> B = new LinkedList<>();

    // 入栈
    public void push(int x) {
        A.offer(x);
    }

    // 出栈
    public Integer pop(){
        if (empty()) {
            return null;
        }
        // 把 A 中元素转移到 B 中
        while (A.size() > 1) {
            Integer front = A.poll();
            B.offer(front);
        }
        // 循环结束，A 中应有 1 个元素，即应被出栈的元素
        // 队尾元素充当栈底元素
        int ret = A.poll();
        // 交换A B身份（地址），A 还是那个“栈”，B 被自动回收
        swapAB();
        return ret;
    }

    private void swapAB() {
        // 实际是引用（地址）相互赋值
        Queue<Integer> tmp = A;
        A = B;
        B = tmp;
    }

    // 取栈顶元素
    public Integer top() {
        if (empty()) {
            return null;
        }
        // 把 A 中元素转移到 B 中
        while (A.size() > 1) {
            Integer front = A.poll();
            B.offer(front);
        }
        // 循环结束，A 中应有 1 个元素，即应被出栈的元素
        int ret = A.poll();
        // 唯一与 pop 和 top唯一不同之处，要把栈顶元素继续留在栈中
        B.offer(ret);
        // 交换A B身份，即地址
        swapAB();
        return ret;
    }

    // 判断栈空
    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}
