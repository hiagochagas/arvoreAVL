package arvoreAVL;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {
    private BinaryNode<T> root;

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

    public void avlBalance() {
        while (!isAVLBalanced()) {
            balance();
        }
    }

    private void balance() {
        BinaryNode<T> unbalacedNode = findUnbalacedNode();
        int height = subtreeAVLHeight(unbalacedNode);

        if(height > 1) {
            int heightRight = subtreeAVLHeight(unbalacedNode.getRight());
            if(heightRight < 0) {
                doubleLeftRotation(unbalacedNode);
            } else {
                leftRotation(unbalacedNode);
            }
        } else {
            int heightLeft = subtreeAVLHeight(unbalacedNode.getLeft());
            if(heightLeft > 0) {
                doubleRightRotation(unbalacedNode);
            } else {
                rightRotation(unbalacedNode);
            }
        }
    }

    private void doubleLeftRotation(BinaryNode<T> node) {
        rightRotation(node.getRight());
        leftRotation(node);
    }

    private void doubleRightRotation(BinaryNode<T> node) {
        leftRotation(node.getLeft());
        rightRotation(node);
    }
    
    private void leftRotation(BinaryNode<T> node) {
    	BinaryNode<T> newRoot = node.getRight();
        this.rearrangeNodes(node, newRoot);

    	if(newRoot.getLeft() != null) {
    		newRoot.getLeft().setParent(node);
    	}
    	node.setRight(newRoot.getLeft());
    	newRoot.setLeft(node);
    }
    
    private void rightRotation(BinaryNode<T> node) {
    	BinaryNode<T> newRoot = node.getLeft();
    	this.rearrangeNodes(node, newRoot);
    	
    	if (newRoot.getRight() != null) {
    		newRoot.getRight().setParent(node);
    	}
    	node.setLeft(newRoot.getRight());
    	newRoot.setRight(node);
    }
    
    private void rearrangeNodes(BinaryNode<T> node, BinaryNode<T> newRoot) {
    	newRoot.setParent(node.getParent());
    	if(node.getParent() != null) {
    		if (node.getParent().getLeft() == node) {
            	node.getParent().setLeft(newRoot);
            } else {
            	node.getParent().setRight(newRoot);
            }
    	} else {
    		this.root = newRoot;
    	}
    	node.setParent(newRoot);
    }
    // Author: @MightyPork
    // extracted and adapted from: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
    public void print() {
        List<List<String>> lines = new ArrayList<>();
        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next = new ArrayList<>();
        level.add(root);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<String>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();
                    next.add(n.getLeft());
                    next.add(n.getRight());
                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }
            if (widest % 2 == 1) widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }
        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}
