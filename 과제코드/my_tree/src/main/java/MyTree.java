import java.util.LinkedList;
import java.util.Queue;

public class MyTree {
    static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insertNode(root, value);
    }
    private Node insertNode(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = insertNode(node.left, value);
        else if (value > node.value) node.right = insertNode(node.right, value);
        return node;
    }

    public void preOrder() {
        System.out.print("전위: ");
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        System.out.print("중위: ");
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    public void postOrder() {
        System.out.print("후위: ");
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    public void levelOrder() {
        System.out.print("후위: ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.value + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        System.out.println("\n깊이: " + maxDepth(root));
        System.out.println("잎 개수: " + leaves(root));
    }

    private int maxDepth(Node node) {
        if (node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    private int leaves(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leaves(node.left) + leaves(node.right);
    }

    public boolean search(int value) {
        return searchNode(root, value);
    }
    private boolean searchNode(Node node, int value) {
        if (node == null) return false;
        if (value < node.value) return searchNode(node.left, value);
        if (value > node.value) return searchNode(node.right, value);
        return true;
    }

    public void printTree() {
        printTree(root, 0);
    }
    private void printTree(Node node, int depth) {
        if (node == null) return;

        printTree(node.left, depth + 1);
        System.out.println("    ".repeat(depth) +  node.value);
        printTree(node.right,  depth + 1);
    }
}
