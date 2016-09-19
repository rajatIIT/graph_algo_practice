package tree;

import java.util.ArrayList;
import java.util.Iterator;

//=====================
// Common utilities to be operated on the 
// binary tree. 
//=====================
public class Utils {
    
    
    public static Node createRandomBST() {
       // create a random array of integers and 
       // insert it into a standard binary search
       // tree 
        return null;
    }
    
    // A visual representation of the binary tree. 
    // For each node, we have a number (which uniquely
    // identifies the node) and a data (which is the 
    // number the node stores). 
    // Strategy to "visually" show the tree: 
    // For each node:
    // 1) print the node number and data
    // 2) execute steps 1 and 2 for right node (if present)
    // 3) execute steps 1 and 2 for left node (if present).
    
    public static void printBinaryTree(ArrayList<Node> nodeList) {
        System.out.println("Displaying the binary tree. "
                		+ "in the format <Node # :Node Value>");
        Iterator<Node> nodeIterator = nodeList.iterator();
        while (nodeIterator.hasNext()) {
            Node nextNode = nodeIterator.next();
            System.out.println();
            System.out.print("(" + nextNode.getNodeID() + ":" + nextNode.getData()
                    + " ) -> ");
            if (nextNode.getLeft() != null)
                System.out.print("(" + nextNode.getLeft().getNodeID() + ":"
                        + nextNode.getLeft().getData() + ")");
            else{
            	// we print X when there is no node 
            	// (as a left or right child)
                System.out.print("X");
            }
            System.out.print(",");
            if (nextNode.getRight() != null)
                System.out.print("(" + nextNode.getRight().getNodeID() + ":"
                        + nextNode.getRight().getData() + ")");
            else
                System.out.print("X");
        }
        System.out.println();
        
    }

}
