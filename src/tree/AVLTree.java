package tree;

import java.util.ArrayList;

/**
 * 
 * Create an AVL tree which automatically balances itself in the wake of random
 * additions and deletions.
 * 
 * Standard Operations : Insert and delete. 
 * Internal Operations : Balance. 
 * 
 * @author rajatpawar
 *
 */
public class AVLTree extends BinarySearchTree {

    private AVLNode rootNode;

    ArrayList<AVLNode> nodeList;

    ArrayList<AVLNode> preInsertNodeQueue;

    public AVLTree() {

    }

    public void insert(int data) {

        // perform insert
        insertIntoBST(rootNode, data);
        
        for(AVLNode eachNode: preInsertNodeQueue){
            eachNode.computeBalanceFactor();
        }

        // check the last four nodes to see if they are balanced.
        for(int i=1;i<3;i++){
            if(preInsertNodeQueue.get(i).isUnbalanced()){
                balanceAVLNode(preInsertNodeQueue.get(i-1), preInsertNodeQueue.get(i));
                break;
            }
        }
        
    }

    public void insertIntoBST(AVLNode node, int data) {

        preInsertNodeQueue.add(node);
        if (preInsertNodeQueue.size() >= 4) {
            preInsertNodeQueue.remove(0);
        }

        if (rootNode == null) {
            rootNode = new AVLNode(idCounter, data);
            idCounter++;
            nodeList.add(rootNode);
        } else {

            // traverse from root to insert into BST.
            if (data > node.getData()) {
                // insert into the right side of the tree;
                if (node.getRight() == null) {

                    // Execute insertion
                    // insert the node right here left to this node. add the new
                    // node to the list of nodes.
                    AVLNode nodeToBeInserted = new AVLNode(idCounter, data);
                    idCounter++;
                    node.setRight(nodeToBeInserted);
                    nodeList.add(nodeToBeInserted);

                    // TODO : rebalance

                } else
                    insertIntoBST(node.getRight(), data);

            } else if (data < node.getData()) {
                // insert into the left side of the tree;
                if (node.getLeft() == null) {
                    // insert the node right here: to the left
                    AVLNode nodeToBeInserted = new AVLNode(idCounter, data);
                    idCounter++;
                    node.setLeft(nodeToBeInserted);
                    nodeList.add(nodeToBeInserted);

                    // TODO : rebalance

                } else
                    insertIntoBST(node.getLeft(), data);

            } else if (data == node.getData()) {
                System.out
                        .println("Data cannot be inserted since duplicate nodes are not allowed in BST.");
            }

        }

    }

    public void balanceAVLNode(AVLNode parent, AVLNode node) {

        // the balance factor of this node is not right.

        if (node.getBalanceFactor() < 2 && node.getBalanceFactor() > -2) {
            // node is already balanced
        } else {

            String situation;

            if (node.getRight().isLeaf() && node.getLeft().getLeft().isLeaf()) {
                situation = "left_right_case";
                
                // convert to left_left
                
                // save nodes
                AVLNode nodeB = node.getLeft().getRight().getLeft();
                AVLNode node4 = node.getLeft().getRight();
                AVLNode node3 = node.getLeft();
                
                //make rotation
                node.setLeft(node4);
                node4.setLeft(node3);
                node3.setRight(nodeB);
                
                
            } else if (node.getRight().isLeaf() && node.getLeft().getRight().isLeaf()) {
                situation = "left_left_case";
                balanceFromLeftLeft(parent, node);
            } else if (node.getLeft().isLeaf() && node.getRight().getRight().isLeaf() && node.getRight().getLeft().getRight().isLeaf()) {
                situation = "right_left_case";
                
                // convert to right right case
                AVLNode nodeC = node.getRight().getLeft().getRight();
                AVLNode node4 = node.getRight().getLeft();
                AVLNode node5 = node.getRight();
                
                node.getRight().setLeft(nodeC);
                node.setRight(node4);
                node4.setRight(node5);
                
                node.setRight(node.getRight().getLeft());
                balanceFromLeftLeft(parent, node);
             } else if (node.getLeft().isLeaf() && node.getRight().getLeft().isLeaf() && node.getRight().getRight().getRight().isLeaf()) {
                 balanceFromRightRight(parent, node);
             }

        }

    }
    
    
    public void balanceFromLeftLeft(AVLNode parent, AVLNode node) {
        
        AVLNode nodeC = node.getLeft().getRight();
        
        node.getLeft().setRight(node);
        node.setLeft(nodeC);
        
        if(parent.getLeft()==node){
            parent.setLeft(node.getLeft());
        } else if (parent.getRight()==node){
            parent.setRight(node.getLeft());
        }
        
    }
    
    public void balanceFromRightRight(AVLNode parent, AVLNode node) {
        
        AVLNode nodeB = node.getRight().getLeft();
        AVLNode node4 = node.getRight();
        
        node.setRight(nodeB);
        node4.setLeft(node);
        
        if(parent.getLeft()==node){
            parent.setLeft(node.getRight());
        } else if (parent.getRight()==node){
            parent.setRight(node.getRight());
        }
        
    }
}
