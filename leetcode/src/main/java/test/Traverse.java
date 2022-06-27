package test;

import java.util.Objects;

/**
 * 二叉树遍历
 *
 * @author Lee
 */
public class Traverse {

    public static void main(String[] args) {
        Node node = new Node(1,
                new Node(2,
                        new Node(5),
                        new Node(4,
                                new Node(6),
                                new Node(7)
                                )
                        ),
                new Node(3,
                        new Node(8),
                        new Node(9)
                        )
                );

        traverse(node);
        System.out.println(before.toString());
        System.out.println(inorder.toString());
        System.out.println(after.toString());
    }

    static StringBuilder before = new StringBuilder();
    static StringBuilder inorder = new StringBuilder();
    static StringBuilder after = new StringBuilder();

    static void traverse(Node node) {

        if (Objects.isNull(node))
            return;

        before.append(node.value);
        traverse(node.left);
        inorder.append(node.value);
        traverse(node.right);
        after.append(node.value);
    }


}

class Node {
    Integer value;
    Node left;
    Node right;

    public Node(Integer value) {
        this.value = value;
    }

    public Node(Integer value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
