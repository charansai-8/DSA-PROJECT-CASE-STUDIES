public class AmazonPersistentSegmentTree {

    static class Node {
        int sum;
        Node left, right;

        Node(int sum) {
            this.sum = sum;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.sum = left.sum + right.sum;
        }
    }

    static Node build(int[] arr, int start, int end) {
        if (start == end) return new Node(arr[start]);

        int mid = (start + end) / 2;
        Node left = build(arr, start, mid);
        Node right = build(arr, mid + 1, end);

        return new Node(left, right);
    }

    static Node update(Node node, int start, int end, int index, int value) {
        if (start == end) return new Node(value);

        int mid = (start + end) / 2;

        if (index <= mid) {
            Node newLeft = update(node.left, start, mid, index, value);
            return new Node(newLeft, node.right);
        } else {
            Node newRight = update(node.right, mid + 1, end, index, value);
            return new Node(node.left, newRight);
        }
    }

    static void printTree(Node node, int start, int end) {
        System.out.println("[" + start + "-" + end + "] = " + node.sum);

        if (start == end) return;

        int mid = (start + end) / 2;

        printTree(node.left, start, mid);
        printTree(node.right, mid + 1, end);
    }

    public static void main(String[] args) {

        int[] stock = {12, 7, 25, 18, 9, 14, 6, 30};

        Node v0 = build(stock, 0, 7);

        System.out.println("===== VERSION v0 =====");
        printTree(v0, 0, 7);

        stock[2] += 50;

        Node v1 = update(v0, 0, 7, 2, stock[2]);

        System.out.println("\n===== NEW NODES CREATED IN v1 =====");
        System.out.println("[2-2] = 75");
        System.out.println("[2-3] = 93");
        System.out.println("[0-3] = 112");
        System.out.println("[0-7] = 171");

        System.out.println("\n===== SHARED NODES WITH v0 =====");
        System.out.println("[0-1] = 19");
        System.out.println("[3-3] = 18");
        System.out.println("[4-7] = 59");

        System.out.println("\n===== VERSION v1 =====");
        printTree(v1, 0, 7);
    }
}
