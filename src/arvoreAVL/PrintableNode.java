package arvoreAVL;

// Author: @MightyPork
// extracted from: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
public interface PrintableNode {
    PrintableNode getLeft();
    PrintableNode getRight();
    String getText();
}
