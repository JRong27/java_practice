package java_0327;

import java.util.Stack;

public class QueueBy2Stacks {
    // A 用来入队列，栈顶充当队尾
    private Stack<Integer> A = new Stack<>();
    // B 用来出队列，栈顶充当队首
    private Stack<Integer> B = new Stack<>();

    /** Push element x onto stack. */
    public void push(int x) {
        //1. 把 B 中元素全部移到 A 中，类似初始化
        // 因为不能保证元素在哪个栈中，所以要避免之后影响出队列
        // 这个 isEmpty() 方法是 Collection 接口的
        while (!B.isEmpty()) {
            int tmp = B.pop();
            A.push(tmp);
        }
        //2. B 为空则对 A 入栈，实现入队列
        // 这个 push() 方法是栈的入栈方法
        A.push(x);
    }


    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
        //1. 若为空栈，则直接返回，不操作
        // 谁用“.”调用 pop() 方法，则对谁判空
        if (empty()) {
            return null;
        }
        //2. 若 A 非空，则把元素都给 B，用于出队列
        while (!A.isEmpty()) {
            int tmp = A.pop();
            B.push(tmp);
        }
        //3. 然后针对 B 出栈，实现出队列
        // 这个 pop() 是栈的出栈方法。
        return B.pop();
    }


    /** Get the top element. */
    public Integer peek() {
        // 跟 pop 方法基本一致
        //1. 若为空栈，则直接返回，不操作
        // 谁用“.”调用 pop() 方法，则对谁判空
        if (empty()) {
            return null;
        }
        //2. 若 A 非空，则把元素都给 B，用于出队列
        while (!A.isEmpty()) {
            int tmp = A.pop();
            B.push(tmp);
        }
        //3. 然后针对 B 出栈，实现出队列
        // 这个 peek() 是栈的取栈顶元素方法。
        return B.peek();
    }


    /** Returns whether the stack is empty. */
    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }

}
