class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

class AVLTree {

    int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key > node.key)
            node.left = insert(node.left, key);
        else if (key < node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key > node.left.key)
            return rightRotate(node);

        if (balance < -1 && key < node.right.key)
            return leftRotate(node);

        if (balance > 1 && key < node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    int countNodes(Node root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

public class SpotifyAVL {
    public static void main(String[] args) {

        int[] timestamps = {
            32400, 28800, 36000, 25200, 39600,
            21600, 43200, 18000, 46800, 14400, 50400
        };

        AVLTree tree = new AVLTree();
        Node root = null;

        for (int ts : timestamps) {
            root = tree.insert(root, ts);
        }

        System.out.println("Spotify Recently Played Index");
        System.out.println("\\nTimestamps in Descending Order:");
        tree.inorder(root);

        System.out.println("\\n");
        System.out.println("Root Node : " + root.key);
        System.out.println("Total Timestamps : " + tree.countNodes(root));
        System.out.println("Tree Height : " + root.height);
    }
}
