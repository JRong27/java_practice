package java_0329_0330;

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


public class InterviewTree {

    //NO.1 先序遍历二叉树（这个遍历是通过加入到链表中,而不是打印）
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            // 返回一个空的 List，元素个数为 0，但不是 null
            return result;
        }
        // 访问根节点，访问操作的结果就是把元素 add 到 List
        result.add(root.val);
        // 递归遍历左子树， 把结果加到 List
        result.addAll(preOrderTraversal(root.left));
        // 递归遍历右子树， 把结果加到 List
        result.addAll(preOrderTraversal(root.right));
        return result;
    }



    //NO.2 中序遍历二叉树
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            // 返回一个空的 List，元素个数为 0，但不是 null（连表都没有）
            return result;
        }
        // 访问根节点，访问操作的结果就是把元素 add 到 List
        // 递归遍历左子树， 把结果加到 List
        result.addAll(inOrderTraversal(root.left));
        result.add(root.val);
        // 递归遍历右子树， 把结果加到 List
        result.addAll(inOrderTraversal(root.right));
        return result;
    }



    //NO.3 后序遍历二叉树
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            // 返回一个空的 List，元素个数为 0，但不是 null
            return result;
        }
        // 访问根节点，访问操作的结果就是把元素 add 到 List
        // 递归遍历左子树， 把结果加到 List
        result.addAll(postOrderTraversal(root.left));
        // 递归遍历右子树， 把结果加到 List
        result.addAll(postOrderTraversal(root.right));
        result.add(root.val);
        return result;
    }



    //NO.4 检查两棵树是否相同(结点&结构)
    // 拆分：根节点相同 && A.left B.left相同 && A.right B.right相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 情况一：p q 均为null
        if (p == null && q == null) {
            return true;
        }
        // 情况二： p q 中一个为 null，另一个 不为 null
        // 上一个逻辑判断已经过滤了 p q 同时为 null 的情况，其他情况才能进入后面的判断
        // 所以可简化（p == null && q != null) || (p != null && q == null)
        if (p == null || q == null) {
            return false;
        }
        // 情况三： p q 均不为 null
        if (p.val != q.val) {
            return false;
        }
        // 根节点值相同，再向下面的子树继续判断
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }



    //NO.5 判断一棵树是否是另一棵树的子树
    // 是遍历 + 递归拆分的问题,基于上一题的思路
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        boolean ret = false;
        // 下面三个判断是逻辑或的关系
        // 若根节点相同，则递归判断左右子树是否相同
        if (s.val == t.val) {
            ret = isSameTree(s, t);
        }
        // 若 ret 为 false，即s,t不同，或根节点的左右子树不同
        // 则先递归判断 s.left 是否包含 t
//        if (!ret) {
//            ret = isSubtree(s.left, t);
//        }
//        // 若 ret 为 false，则再递归判断 s.right 是否包含 t
//        if (!ret) {
//            ret = isSubtree(s.right, t);
//        }
//        return  ret;
        // 上面两个判断可简化如下： 可短路求值
        return ret || isSubtree(s.left, t) || isSubtree(s.right, t);
    }



    //NO.6 求树的深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 简化 maxDepth(root.left) > maxDepth(root.right)
        //      ? maxDepth(root.left) : maxDepth(root.right);
        // 让三次递归变为两次递归
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + (Math.max(leftDepth, rightDepth));
    }



    //NO.7 判断一棵树是否是平衡二叉树
    // 即，根节点的左右子树高度差 <= 1 && 左子树平衡 && 右子树平衡
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        // 判断当前节点的子树是否平衡
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth - rightDepth > 1 || leftDepth - rightDepth < -1) {
            return false;
        }
        // 若当前节点的左右子树高度符合平衡条件，则继续向下层判断是否平衡
        // 
        return isBalanced(root.left) && isBalanced(root.right);
    }



    //NO.8 判定一棵树是否对称
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isMirror(root.left, root.right);
    }
    // 不一定要将所有逻辑写在一个方法里，可以对方法进行适当的拆分
    public boolean isMirror(TreeNode n1, TreeNode n2) {
        // 若都为空节点
        if (n1 == null && n2 == null) {
            return true;
        }
        // 若只有一个为空节点
        if (n1 == null || n2 == null) {
            return false;
        }
        // 若两个非空节点值不相等
        if (n1.val != n2.val) {
            return false;
        }
        // 两个非空节点值相等
        return isMirror(n1.left, n2.right) && isMirror(n2.left, n1.right);
    }
    // 注意不可在第二步直接判断如下语句
    // if (n1.val == n2.val)
    // return isMirror(n1.left, n1.right) && isMirror(n2.left, n2.right);
    // 因为不能排除 n1  n2 有一个是 null 的情况 若是 null 则不能用“.”引用



    //NO.9 二叉树的层序遍历（非递归，可借助队列返回，边存边打印）
    public void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 若队列非空，则访问元素（即打印）
            TreeNode cur = queue.poll();
            System.out.println(cur.val + " ");
            // 打印后继续判断，是否需要向队列中添加元素
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }



    public static TreeNode build() {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(5);
        TreeNode F = new TreeNode(6);
        TreeNode G = new TreeNode(7);
        TreeNode H = new TreeNode(8);
        TreeNode I = new TreeNode(9);
        TreeNode J = new TreeNode(10);
        TreeNode K = new TreeNode(11);
        TreeNode L = new TreeNode(12);
        TreeNode Z = new TreeNode(26);

        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        D.left = F;
        D.right = G;
        F.left = Z;
        E.left = H;
        E.right = I;
        C.left = K;
        C.right = L;
        K.left = J;
        return A;
    }

    public static void main(String[] args) {
        // 因为 build() 是静态方法，所以可以直接调用
        TreeNode root = build();
        // levelOderTraversal(root) 是非静态方法，所以要 new 一个引用来调用
        InterviewTree interviewTree = new InterviewTree();
        // NO.7
         boolean ret = interviewTree.isBalanced(root);
         System.out.println(ret);
        // NO.9
        interviewTree.levelOrderTraversal(root);
        System.out.println();  // 即 输出一空行换行

    }
}
