package tree;

public class AVLNode {

    protected int height;
    protected int nodeID;
    protected AVLNode right;
    protected AVLNode left;
    protected int data;
    private int balanceFactor = 0;

    protected AVLNode(int nodeID) {
        right = null;
        left = null;
        data = 0;
        this.nodeID = nodeID;
    }

    protected AVLNode(int nodeID, int data) {
        this.data = data;
        right = null;
        left = null;
        this.nodeID = nodeID;
    }

    protected AVLNode(AVLNode left, AVLNode right, int nodeID) {
        this.left = left;
        this.right = right;
        data = 0;
        this.nodeID = nodeID;
    }

    protected int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected int getNodeID() {
        return nodeID;
    }

    protected void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    protected AVLNode getRight() {
        return right;
    }

    protected void setRight(AVLNode right) {
        this.right = right;
    }

    protected AVLNode getLeft() {
        return left;
    }

    protected void setLeft(AVLNode left) {
        this.left = left;
    }

    protected int getData() {
        return data;
    }

    protected void setData(int data) {
        this.data = data;
    }

    protected void updateHeight() {
        height = 1 + Math.max(right.height, left.height);
    }

    protected boolean isLeaf() {
        if (right == null && left == null)
            return true;
        else
            return false;

    }

    protected boolean isUnbalanced() {
        if (balanceFactor > 1 || balanceFactor < -1)
            return true;
        else
            return false;

    }

    public void computeBalanceFactor() {
        balanceFactor = right.getHeight() - left.getHeight();
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

}
