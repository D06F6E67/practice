package test;

import java.util.Objects;

/**
 * 最大深度
 *
 * @author Lee
 */
public class maxDepth {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(
                new TreeNode(
                        new TreeNode(
                                new TreeNode(
                                        null,
                                        new TreeNode()
                                ),
                                null
                        ),
                        new TreeNode()
                ),
                new TreeNode(
                        new TreeNode(),
                        new TreeNode()
                )
        );

        traverse(a);
        System.out.println(res);

    }

    // 最大深度
    static Integer res = 0;
    // 当前深度
    static Integer depth = 0;

    static void traverse(TreeNode node) {
        if (Objects.isNull(node)) {
            // 到达叶子节点
            res = Math.max(res, depth);
            return;
        }

        // 前序遍历位置
        depth ++;
        traverse(node.left);
        traverse(node.right);
        // 后序遍历位置
        depth --;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;

    public TreeNode(){}

    public TreeNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }
}
