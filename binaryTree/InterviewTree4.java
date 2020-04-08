package java_0405;

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class InterviewTree4 {
    public static TreeNode build() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = g;
        c.right = f;
        return a;
    }


    //NO.1 非递归先序遍历二叉树，栈辅助完成
    public static void preOrderByLoop(TreeNode root) {
        if (root == null) {
            return;
        }
        // 准备一个栈,起到辅助效果
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            // 通过打印来访问元素
            System.out.print(top.val + " ");
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }


    //NO.2 非递归中序遍历二叉树
    public static void inOrderByLoop(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (true) {
            // 1. 循环往左, 遇到的节点都入栈，直到节点为null暂停寻找
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 2. 如果当前栈为空, 遍历就结束了
            // 不可以把该条件放第一个while，
            // 因为遍历过程中有空栈的时候。
            if (stack.isEmpty()) {
                break;
            }
            // 3. 取栈顶元素并访问
            TreeNode top = stack.pop();
            System.out.print(top.val + " ");
            // 4. 从当前节点的右子树再出发继续刚才的过程
            cur = top.right;
        }
    }


    //NO.3 非递归后序遍历二叉树
    public static void postOrderByLoop(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // cur 记录每次作为根节点的当前节点
        TreeNode cur = root;
        // prev 记录了当前已经访问过的节点中的最后一个节点.
        // 即将被访问的元素的前一个节点
        TreeNode prev = null;
        while (true) {
            // 1. 从 cur 出发，循环向左找，非空节点都入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // n. cur 为空且栈空，则说明遍历完了所有元素
            if (stack.isEmpty()) {
                break;
            }
            // 3. 拿出栈顶元素的值, 看看是否符合可访问条件 b)
            TreeNode top = stack.peek();
            // 4. 若栈顶元素 a)没有右子树 或 b)右子树已访问，则该元素可访问
            if (top.right == null || prev == top.right) {
                System.out.print(top.val + " ");
                stack.pop();
                prev = top;  // 时刻维护好 prev 指向已经遍历完元素的最后一个.
            }
            // 5. 否则从栈顶元素的右子树出发，开始循环
            else {
                cur = top.right;
            }
        }
    }


    public static void main(String[] args) {
        TreeNode root = build();
        preOrderByLoop(root);
        System.out.println();
        inOrderByLoop(root);
        System.out.println();
        postOrderByLoop(root);
    }
}
