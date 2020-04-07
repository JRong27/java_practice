package java_0401;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}


public class InterviewTree2 {

    //NO.1 判定完全二叉树
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 层序遍历树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean isSecondStep = false;

        while (!queue.isEmpty()) {
            // 引入标志位，记录当前节点，用于判断属于哪个阶段
            TreeNode cur = queue.poll();
            // 对当前节点进行访问，判断是否符合完全二叉树的要求
            if (!isSecondStep) {
                // 第一阶段：（1）每个结点都有左右子树，则直接入队列
                if (cur.left != null && cur.right != null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                // （2）若左子树非空，右子树为空，则进入第二阶段（可以简化只写条件前半句）
                else if (cur.left != null && cur.right == null) {
                    isSecondStep = true;
                }
                // （3）若左子树为空，右子树非空，则一定不是完全二叉树（可以简化只写条件后半句）
                else if (cur.left == null && cur.right == null) {
                    return false;
                }
                // （4）左右子树均为空，则进入第二阶段判断
                else {
                    isSecondStep = true;
                }
            }
            // 第二阶段：此时已又经过一次poll()，cur代表上一个if中，节点的下一个右边的节点
            else if (cur.left != null || cur.right != null){
                return false;
            }
        }
        return true;
    }


    //NO.2 二叉树的分层层序遍历
    // ArrayList包含了所有层，其中每个元素又是一个List
    // result 的 0 号元素对应第 0 层；1 号元素对应第一层；……
    // result 起始为空，size() 就为 0
    static List<List<Integer>> result = new ArrayList<>();
    // 把每一层的结点放到单独的 List 中，用二维数组实现，基于递归解决问题
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }
        // 表示当前层数从 0 开始，与 List 下标一致
        helper(root, 0);
        return result;
    }
    // 辅助方法 用于递归 对树进行先序遍历
    private void helper(TreeNode root, int level) {
        // 判断当前层数是否与当前最深层数相等
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        // 把当前结点添加到 result 中合适的位置（核心操作）
        // 等价于 List<Integer> row = result.get(level);
        //        row.add(root.val);
        result.get(level).add(root.val);
        if (root.left != null) {
            helper(root.left, level + 1);  // 加 1 后表示更新到下一层进行操作
        }
        if (root.right != null) {
            helper(root.right, level + 1);
        }
    }


    public static void main(String[] args) {
        InterviewTree2 tree = new InterviewTree2();
        // 创建一棵树TreeNode root = build();
        // boolean ret = tree.isCompleteTree(root);
        // System.out.println(ret);
    }
}
