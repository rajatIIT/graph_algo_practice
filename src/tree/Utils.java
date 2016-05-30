package tree;

import java.util.ArrayList;
import java.util.Iterator;

//=====================
// common utilities.
//=====================
public class Utils {
    
    
    public static Node createRandomBST() {
       // create a random array of integers and 
       // insert it into a standard binary search
       // tree 
        return null;
    }
    
    
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
            else
                System.out.print("X");
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
