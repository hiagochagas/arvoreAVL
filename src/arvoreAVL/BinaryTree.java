package arvoreAVL;

public class BinaryTree<T> {
    private final BinaryNode<T> root;

    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public void addNode(BinaryNode<T> nodeToAdd, BinaryNode<T> parentNode, BinaryPosition position) {
        nodeToAdd.setParent(parentNode);
        switch (position) {
            case left:
                parentNode.setLeft(nodeToAdd);
                break;
            case right:
                parentNode.setRight(nodeToAdd);
                break;
        }
    }
    
    public void addNode(BinaryNode<T> nodeToAdd) {
    	addNode(nodeToAdd,root);
    }
    
    private void addNode(BinaryNode<T> nodeToAdd, BinaryNode<T> parentNode) {
    	if(parentNode.getRight() == null) {
    		addNode(nodeToAdd,parentNode, BinaryPosition.right);
    	} else {    		
    		addNode(nodeToAdd,parentNode.getRight());
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

    public boolean isAVLBalanced() {
        return areAllChildrenNodesBalanced(root);
    }

    public BinaryNode<T> findUnbalacedNode() {
        return findUnbalacedNode(root);
    }

    private BinaryNode<T> findUnbalacedNode(BinaryNode<T> node) {
        if (node == null || !isSubtreeBalanced(node)) {
            return node;
        }
        BinaryNode<T> left = findUnbalacedNode(node.getLeft());
        BinaryNode<T> right = findUnbalacedNode(node.getRight());
        if(left != null) {
            return left;
        } else {
            return right;
        }
    }

    private boolean areAllChildrenNodesBalanced(BinaryNode<T> node) {
        if (node == null) {
            return true;
        }
        return isSubtreeBalanced(node) && areAllChildrenNodesBalanced(node.getLeft()) && areAllChildrenNodesBalanced(node.getRight());
    }

    private boolean isSubtreeBalanced(BinaryNode<T> root) {
        int height = subtreeAVLHeight(root);
        if (height < -1 || height > 1) {
            return false;
        }
        return true;
    }

    private int subtreeAVLHeight(BinaryNode<T> root) {
        return maximumHeightFromNode(root.getRight()) - maximumHeightFromNode(root.getLeft());
    }

    private int maximumHeightFromNode(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maximumHeightFromNode(node.getLeft()), maximumHeightFromNode(node.getRight()));
    }
}
