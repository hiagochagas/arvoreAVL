package arvoreAVL;

public class Main {
    public static void main(String[] args) {
        BinaryNode<Integer> node50 = new BinaryNode<>(50);
        BinaryNode<Integer> node17 = new BinaryNode<>(17);
        BinaryNode<Integer> node76 = new BinaryNode<>(76);
        BinaryNode<Integer> node9 = new BinaryNode<>(9);
        BinaryNode<Integer> node23 = new BinaryNode<>(23);
        BinaryNode<Integer> node54 = new BinaryNode<>(54);
        BinaryNode<Integer> node14 = new BinaryNode<>(14);
        BinaryNode<Integer> node19 = new BinaryNode<>(19);
        BinaryNode<Integer> node72 = new BinaryNode<>(72);
        BinaryNode<Integer> node12 = new BinaryNode<>(12);
        BinaryNode<Integer> node67 = new BinaryNode<>(67);
        BinaryTree<Integer> tree = new BinaryTree<>(node50);
        tree.addNode(node17, node50, BinaryPosition.left);
        tree.addNode(node76, node50, BinaryPosition.right);
        tree.addNode(node9, node17, BinaryPosition.left);
        tree.addNode(node23, node17, BinaryPosition.right);
        tree.addNode(node54, node76, BinaryPosition.left);
        tree.addNode(node14, node9, BinaryPosition.right);
        tree.addNode(node19, node23, BinaryPosition.left);
        tree.addNode(node72, node54, BinaryPosition.right);
        tree.addNode(node12, node14, BinaryPosition.left);
        tree.addNode(node67, node72, BinaryPosition.left);

        tree.print();
        tree.avlBalance();
        tree.print();
    }
}
