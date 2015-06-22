package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BinaryTree {

    boolean hasRoot;
    Node rootNode;
    ArrayList<Node> nodeList = new ArrayList<Node>();
    private int idCounter = 0;

    public BinaryTree() {
        start();
      //  TreeOperations myOP = new TreeOperations();
    }

    public void start() {

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println(" 1) Create a new binary tree.");
            System.out.println(" 2) Add/Update branch to the binary tree.");
            //System.out.println(" 3) Delete branch from the binary tree.");
            System.out.println(" 4) View the binary tree.");
            System.out.println(" 5) Exit.");
            System.out.println(" 6) Create a new binary search tree.");
            System.out.println(" 7) Insert into binary search tree.");

            int nextInput = inputScanner.nextInt();
            if (nextInput == 1) {
                System.out.println("Does tree have a root node? (y/n)");
                char userChoice = inputScanner.next().charAt(0);
                if (userChoice == 'y') {
                    rootNode = new Node(idCounter);
                    idCounter++;
                    nodeList.add(rootNode);
                }
                System.out.println("Updated/Created the tree. The root branch has node number 0.");
            } else if (nextInput == 2) {
                System.out.println("Enter the number of node where you want to add the branch ?");
                int targetBranch = inputScanner.nextInt();
                System.out.println("Do you want to add a right/left node? (Enter r/l)");
                char userSelection = inputScanner.next().charAt(0);
                if (userSelection == 'r') {
                    System.out.println("Enter the value of the right node.");
                    int inputData = inputScanner.nextInt();
                    Node newRightNode = new Node(idCounter);
                    idCounter++;
                    nodeList.add(newRightNode);
                    newRightNode.setData(inputData);
                    nodeList.get(targetBranch).setRight(newRightNode);
                } else if (userSelection == 'l') {
                    System.out.println("Enter the value of the left node.");
                    int inputData = inputScanner.nextInt();
                    Node newLeftNode = new Node(idCounter);
                    idCounter++;
                    nodeList.add(newLeftNode);
                    newLeftNode.setData(inputData);
                    nodeList.get(targetBranch).setLeft(newLeftNode);
                } else {
                    continue;
                }
            } else if (nextInput == 4) {
                System.out.println("Displaying the binary tree. in the format <Node # :Node Value>");
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
            } else if (nextInput == 5) {
                break;
            } else if (nextInput ==6) {
                // create a new binary search tree.
                
                // does not already have a root node. 
                System.out.println("Enter the value of the root node: ");
                int rootNodeVal = inputScanner.nextInt();
                
                rootNode = new Node(idCounter,rootNodeVal);
                idCounter++;
                nodeList.add(rootNode);
                System.out.println("");
                
                System.out.println("Created tree with one node. Use insert to add more nodes.");
                
            } else if (nextInput==7 ){
                System.out.println("Enter the value to be inserted: ");
                int value = inputScanner.nextInt();
                insertIntoBST(rootNode,value);
            }

        }
        inputScanner.close();
    }
    
    
    public void insertIntoBST(Node node,int data){
        
        // traverse from root to insert into BST.
        if(data>node.getData()) {
            // insert into the right side of the tree;
            if(node.getRight()==null){
                // insert the node right here left to this node. add the new node to the list of nodes. 
                Node nodeToBeInserted = new Node(idCounter,data);
                idCounter++;
                node.setRight(nodeToBeInserted);
                nodeList.add(nodeToBeInserted);
            } else
                insertIntoBST(node.getRight(), data);
           
        } else if (data<node.getData()){
            // insert into the left side of the tree;
            if(node.getLeft()==null){
                // insert the node right here: to the left
                Node nodeToBeInserted = new Node(idCounter,data);
                idCounter++;
                node.setLeft(nodeToBeInserted);
                nodeList.add(nodeToBeInserted);
            } else 
                insertIntoBST(node.getLeft(), data);
            
            
        } else if (data==node.getData()) {
            System.out.println("Data cannot be inserted since duplicate nodes are not allowed in BST.");
        }
    }
}
