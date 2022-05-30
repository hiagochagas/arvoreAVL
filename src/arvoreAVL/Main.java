package arvoreAVL;

public class Main {
    public static void main(String[] args) {
        BinaryNode<Integer> node1 = new BinaryNode<>(1);
        BinaryNode<Integer> node2 = new BinaryNode<>(2);
        BinaryNode<Integer> node3 = new BinaryNode<>(3);
        BinaryNode<Integer> node4 = new BinaryNode<>(4);
        BinaryNode<Integer> node5 = new BinaryNode<>(5);
        BinaryNode<Integer> node6 = new BinaryNode<>(6);
        BinaryNode<Integer> node7 = new BinaryNode<>(7);
        BinaryTree<Integer> tree = new BinaryTree<>(node1);
//        tree.addNode(node2, node1, BinaryPosition.left);
//        tree.addNode(node3, node1, BinaryPosition.right);
//
//        tree.addNode(node4, node2, BinaryPosition.left);
//        tree.addNode(node5, node2, BinaryPosition.right);
//
//        tree.addNode(node6, node3, BinaryPosition.left);
//        tree.addNode(node7, node3, BinaryPosition.right);
//        
        tree.addNode(node2);
        tree.addNode(node3);

        tree.addNode(node4);
        tree.addNode(node5);

        tree.addNode(node6);
        tree.addNode(node7);

        BinaryNode<Integer> foundNode = tree.searchNode(7);
        System.out.println(foundNode.getDescription());
    }
}
