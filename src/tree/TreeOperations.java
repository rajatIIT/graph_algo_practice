package tree;

public class TreeOperations {

    public TreeOperations() {
        testInOrderTraversal();
    }

     //=======================
     // A simple test method. 
     //=======================
    private void testInOrderTraversal() {
        System.out.println("In Order traversal");
        Node rootNode = new Node(0, 3);
        Node rightRoot = new Node(1, 2);
        Node leftRoot = new Node(2, 1);
        Node rightRightNode = new Node(3, 5);
        Node leftRighNode = new Node(4, 4);
        rootNode.setLeft(leftRoot);
        rootNode.setRight(rightRoot);
        rightRoot.setRight(rightRightNode);
        rightRoot.setLeft(leftRighNode);
        InOrderTraversal(rootNode);
    }

    public void InOrderTraversal(Node rootNode) {
        // display the contents of the tree in inOrder traversal.
        if (rootNode.getLeft() != null)
            InOrderTraversal(rootNode.getLeft());
        System.out.print(" -> " + rootNode.getData());
        if (rootNode.getRight() != null)
            InOrderTraversal(rootNode.getRight());
    }

    public void PreOrderTraversal(Node rootNode) {
        // PreOrder : root, left, right
        System.out.print(" -> " + rootNode.getData());
        if (rootNode.getLeft() != null)
            PreOrderTraversal(rootNode.getLeft());
        if (rootNode.getRight() != null)
            PreOrderTraversal(rootNode.getRight());
    }

    public void postOrderTraversal(Node rootNode) {
        if (rootNode.getLeft() != null)
            postOrderTraversal(rootNode.getLeft());
        if (rootNode.right != null)
            postOrderTraversal(rootNode.getRight());
        System.out.println(" -> " + rootNode.getData());
    }
}
