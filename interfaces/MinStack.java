package java_0327;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> A = new Stack<>();
    private Stack<Integer> B = new Stack<>();

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            A.push(x);
            //  当 B 栈空，则默认第一个元素为最小值
            if (B.isEmpty()) {
                B.push(x);
                return;  // 返回结束本方法
            }
            // 当 B 栈非空，则比较 B 栈顶元素与当前压入 A 的元素
            // 保证 B 栈顶始终是 A 栈最小值
            int min = B.peek();
            if (x < min) {
                min = x;
            }
            B.push(min);
        }

        public Integer pop() {
            if (A.isEmpty()) {
                return null;  // 为了情况可见，返回null，把void 改为 Integer
            }
            B.pop();
            return A.pop();
        }

        public Integer top() {
            if (A.isEmpty()) {
                return null;
            }
            return A.peek();
        }

        public Integer getMin() {
            if (B.isEmpty()) {
                return null;
            }
            return B.peek();
        }

}
