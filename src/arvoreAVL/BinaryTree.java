package arvoreAVL;

public class BinaryTree<T> {
    private final BinaryNode<T> root;

    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public void addNode(BinaryNode<T> nodeToAdd, BinaryNode<T> parentNode, BinaryPosition position) {
        switch (position) {
            case left:
                parentNode.setLeft(nodeToAdd);
                break;
            case right:
                parentNode.setRight(nodeToAdd);
                break;
        }
    }

    public void removeNode(T nodeDescription) {
        BinaryNode<T> node = searchNode(nodeDescription, root);
        if (node != null) {
            BinaryNode<T> parentNode = node.getParent();
            if (parentNode.getLeft() == node) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        }
    }

    public BinaryNode<T> searchNode(T nodeDescription) {
        return searchNode(nodeDescription, root);
    }

    private BinaryNode<T> searchNode(T nodeDescription, BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        if (nodeDescription == node.getDescription()) {
            return node;
        }
        BinaryNode<T> left = searchNode(nodeDescription, node.getLeft());
        BinaryNode<T> right = searchNode(nodeDescription, node.getRight());
        if(left != null) {
            return left;
        } else {
            return right;
        }
    }
}
