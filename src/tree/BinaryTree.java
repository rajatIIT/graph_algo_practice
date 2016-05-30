package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// ======================================
// Implement the binary tree, binary search tree
// and the basic utilities around it. 
// Utilities include
// -> Create new Binary Tree.
// -> Create new Binary Search Tree.
// -> Insert/Delete integers.
// -> Find inorder successor
// -> Find kth smallest element.
// -> Print elements within a range.
// -> Check if tree is balanced.
// Familiarize yourself with inOrder traversal
// using Wikipedia or otherwise. 
//
// Trick to remember order in traversals:
// the word represents the order of visiting 
// the root
// InOrder -> root in between (L and R)
// PreOrder -> root before (L and R)
// PostOrder -> root after (L and R)
// @author rajatpawar
// ======================================
public class BinaryTree {

	// ======================================
	// The data structures used for storing the
	// data in the tree. nodeList contains 
	// all the nodes in the tree.
	// 
	// Every node can be referenced by a unique
	// number(id). This requires random access.
	// Thus, we use ArrayList. We do not know the
	// total number of nodes in the tree beforehand.
	// This makes array less preferable. ArrayList,
	// which is designed for use specifically in such 
	// situations is our choice here.
	//
	// View the Node class which represents a node of 
	// the tree. 
	// ======================================
	boolean hasRoot;
    Node rootNode;
    ArrayList<Node> nodeList = new ArrayList<Node>();
    private int idCounter = 0;
    BinarySearchTree bst;
    
    
    public BinaryTree() {
        start();
        // TreeOperations myOP = new TreeOperations();
    }

    
    // ======================================
    // An infinite loop which asks user "what to do next? ".
    // ======================================
    public void start() {

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println(" 1) Create a new binary tree.");
            System.out.println(" 2) Add/Update branch to the binary tree.");
            // System.out.println(" 3) Delete branch from the binary tree.");
            System.out.println(" 4) View the binary tree.");
            System.out.println(" 5) Exit.");
            System.out.println(" 6) Create a new binary search tree.");
            System.out.println(" 7) Insert into binary search tree.");
            System.out.println(" 8) Find inorderSuccessor of a node.");
            System.out.println(" 9) Find kth smallest element from the tree.");
            System.out.println(" 10) Print elements of tree that lie within a particular range.");
            System.out.println(" 11) check if the tree is balanced.");
            
            // depending on what number user inputs, the system makes
            // necessary changes to the tree.
            int nextInput = inputScanner.nextInt();
            if (nextInput == 1) {
            	// some trees are null trees. Just to support that feature
            	// we ask if there is a root node. 
            	// we work with node numbers. we assign 
            	// number 0 to root node. 
                System.out.println("Does tree have a root node? (y/n)");
                char userChoice = inputScanner.next().charAt(0);
                if (userChoice == 'y') {
                    rootNode = new Node(idCounter);
                    idCounter++;
                    nodeList.add(rootNode);
                }
                System.out.println("Updated/Created the tree. The root branch has node number 0.");
            } else if (nextInput == 2) {
            	// input the node number where insertion is to
            	// be made. The class shows a nice node number
            	// and value map by using the 4 option
            	// view binary search tree of this class.
            	// using this system for a nice visual representation. 
            	// usually, the tree is traversed all the way to 
            	// the end and then the node is added. 
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
            	// we have a special class 
            	// utils which contains the common code.
                if (nodeList.size() == 0)
                    Utils.printBinaryTree(bst.nodeList);
                else
                    Utils.printBinaryTree(nodeList);

            } else if (nextInput == 5) {
                break;
            } else if (nextInput == 6) {
                // create a new binary search tree.
                bst = new BinarySearchTree();
            } else if (nextInput == 7) {
                System.out.println("Enter the values to be inserted (and -1 when you are done): ");
                int value = inputScanner.nextInt();
                while (value != -1) {
                    bst.insert(value);
                    value = inputScanner.nextInt();
                }
            } else if (nextInput == 8) {
                System.out
                        .println("Enter the number of node for which inorder successor has to be found. ");
                int nodeNo = inputScanner.nextInt();

                Node inputNode = nodeList.get(nodeNo);
                if (inputNode.getRight() == null)
                    System.out.println("No successor!");
                else
                    inOrderSuccessor(inputNode.getRight());
            } else if (nextInput == 9) {
                countOfSort = 0;
                System.out.println("Enter the number of k where you want kTH order statistic:");
                int myK = inputScanner.nextInt();
                // view the method
                returnkthStaticstic(myK, rootNode);
            } else if (nextInput == 10) {
                System.out.println("Enter the initial and the final values of the range: ");
                int start = inputScanner.nextInt();
                int end = inputScanner.nextInt();
                printBetween(start, end, rootNode);
            } else if (nextInput == 11) {
                leafDepth = 0;
                isLeafDepthSet = false;
                System.out.println("*No output after this line means tree is balanced*");
                computeTreeDepth(0, rootNode);
                isTreeHeightBalanced(rootNode);
            }

        }
        inputScanner.close();
    }

    // ======================================
    //  find the height of all leaf nodes. if 
    // height of even one is different,
    //  the tree is not node balanced.
    // ======================================
    public void isTreeHeightBalanced(Node rootNode) {

        // traverse all the nodes.
        if (rootNode.getLeft() == null && rootNode.getRight() == null) {
            // the node is a leaf
            if (!isLeafDepthSet) {
                leafDepth = rootNode.getHeight();
                isLeafDepthSet = true;
            } else {
                if (rootNode.getHeight() != leafDepth) {
                    System.out.println("Not balanced.");
                }
            }
        }

        if (rootNode.getLeft() != null)
            isTreeHeightBalanced(rootNode.getLeft());
        if (rootNode.getRight() != null)
            isTreeHeightBalanced(rootNode.getRight());
    }

    boolean isBalanced;
    boolean isLeafDepthSet;
    int leafDepth;

    // simple recursive algorithm.
    // node that the node structure has a 
    // field for tree height.
    public void computeTreeDepth(int height, Node rootNode) {

        rootNode.setHeight(height);

        if (rootNode.getLeft() != null)
            computeTreeDepth(height + 1, rootNode.getLeft());

        if (rootNode.getRight() != null)
            computeTreeDepth(height + 1, rootNode.getRight());

    }
    
    // ======================================
    // * for binary search tree only *
    // go all the way down to the left in the 
    // tree, start inorder traversal, increment
    // a counter while performing the inorder 
    // traversal and return the kth element 
    // in the counter.
    // ======================================
    public void inOrderSuccessor(Node inputNode) {

        if (inputNode.getLeft() == null) {
            System.out.println(" (" + inputNode.getNodeID() + ", " + inputNode.getData()
                    + ") is successor.");
        } else
            inOrderSuccessor(inputNode.getLeft());
    }

    // ignore this.
    public void findStatic(int number, Node rootNode) {

        // find the numberTH smallest number in the tree.

        // traverse left tree

        // traverse root

        // traverse right tree

    }

    int countOfSort = 0;

   // ======================================
   // * for binary search tree only *
   // go all the way down to the left in the 
   // tree, start inorder traversal, increment
   // a counter while performing the inorder 
   // traversal and return the kth element 
   // in the counter.
   // ======================================
    public void returnkthStaticstic(int k, Node rootNode) {
        if (rootNode.getLeft() != null)
            returnkthStaticstic(k, rootNode.getLeft());

        countOfSort++;
        if (countOfSort == k)
            System.out.println(k + " th item is " + rootNode.getData());

        if (rootNode.getRight() != null)
            returnkthStaticstic(k, rootNode.getRight());

    }

    // ======================================
    // A very simple technique : perform
    // inOrder traversal and print all the elements
    // in the range. Another is to execute two 
    // two traversals from the root. One towards
    // left which prints elements in range 
    // (min,root) and other towards right which 
    // prints elements in range (root, max).
    // in the left traversal, print the right
    // subtree of a node if the min element 
    // is less than the node value. For the 
    // right traversal, print the left subtree 
    // of a node if the node value is less than
    // the max. First method is simple, while
    // second is more efficient. We implement
    // the first one. 
    // ======================================
    public void printBetween(int start, int end, Node node) {

        // perform inOrder traversal
        if (node.getLeft() != null)
            printBetween(start, end, node.getLeft());

        if (node.getData() > start && node.getData() < end)
            System.out.println(node.getData() + " lies in the range.");

        if (node.getRight() != null)
            printBetween(start, end, node.getRight());
    }

}
