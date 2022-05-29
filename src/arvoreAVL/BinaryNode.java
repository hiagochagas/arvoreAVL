package arvoreAVL;

public class BinaryNode<T> {
    private T description;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private BinaryNode<T> parent;

    public BinaryNode(T description) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.description = description;
    }

    public BinaryNode(T description, BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> parent) {
        this.description = description;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public T getDescription() {
        return description;
    }

    public void setDescription(T description) {
        this.description = description;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }
}
