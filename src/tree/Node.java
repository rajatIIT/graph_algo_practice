package tree;

public class Node {

    protected int height;
    protected int nodeID;
    protected Node right;
    protected Node left;
    protected int data;
    
    protected Node(int nodeID) {
        right = null;
        left = null;
        data = 0;
        this.nodeID = nodeID;
    }
    
    protected Node(int nodeID,int data){
        this.data=data;
        right = null;
        left = null;
        this.nodeID = nodeID;
    }

    protected Node(Node left, Node right, int nodeID) {
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
    
    protected void updateHeight() {
        height = 1 + Math.max(right.height, left.height);
    }
    
    protected boolean isLeaf(){
        if(right==null && left==null)
            return true;
        else
            return false;
        
    }

}
