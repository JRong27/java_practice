package java_0401;

import java.util.Scanner;

public class BuildTreeDemo {
    // 创建静态内部类 因为这个包中已经有了一个节点类 直接创建普通类会报错
    static class TreeNode {
        public char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 因为 OJ 一般都是多组数据输入，所以用循环
        while (scanner.hasNext()) {
            // 输入字符串，严格注意输出格式，不能多空少空
            String line = scanner.next();
            TreeNode root = build(line);
            inOrder(root);
            System.out.println(); // 换行多空一行的作用
        }
    }

    // 中序遍历打印已经还原好的树
    private static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val + " ");
        inOrder(root.right);
    }


    //为了在先序遍历，递归创建树的过程中，能随时知道当前访问到哪个元素，用静态变量记录
    private static int index = 0;
    private static TreeNode build(String line) {
        return createTreePreOrder(line);
    }
    // 用辅助方法完成递归
    private static TreeNode createTreePreOrder(String line) {
        // 获取当前处理的节点
        char cur = line.charAt(index);
        if (cur == '#') {
            return null;
        }
        // 当前字符非 # 则创建一个节点
        TreeNode root = new TreeNode(cur);
        index++;
        // 准备处理下一个节点，此后就是当前 root 左子树的遍历结果。
        root.left = createTreePreOrder(line);
        index++;
        // 此后就是当前 root 右子树的遍历结果。
        root.right = createTreePreOrder(line);
        return root;
    }

}
