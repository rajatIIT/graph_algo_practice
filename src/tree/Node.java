package tree;

public class Node {

    protected Node(int nodeID) {
        right = null;
        left = null;
        data = 0;
        this.nodeID = nodeID;
    }

    protected Node(Node left, Node right, int nodeID) {
        this.left = left;
        this.right = right;
        data = 0;
        this.nodeID = nodeID;
    }
    
    protected int nodeID;

    protected int getNodeID() {
        return nodeID;
    }

    protected void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    protected Node right;

    protected Node getRight() {
        return right;
    }

    protected void setRight(Node right) {
        this.right = right;
    }

    protected Node getLeft() {
        return left;
    }

    protected void setLeft(Node left) {
        this.left = left;
    }

    protected int getData() {
        return data;
    }

    protected void setData(int data) {
        this.data = data;
    }

    protected Node left;
    protected int data;

}
