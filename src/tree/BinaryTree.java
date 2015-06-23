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
        // TreeOperations myOP = new TreeOperations();
    }

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
                System.out
                        .println("Displaying the binary tree. in the format <Node # :Node Value>");
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
            } else if (nextInput == 6) {
                // create a new binary search tree.

                // does not already have a root node.
                System.out.println("Enter the value of the root node: ");
                int rootNodeVal = inputScanner.nextInt();

                rootNode = new Node(idCounter, rootNodeVal);
                idCounter++;
                nodeList.add(rootNode);
                System.out.println("");

                System.out.println("Created tree with one node. Use insert to add more nodes.");

            } else if (nextInput == 7) {
                System.out.println("Enter the values to be inserted (and -1 when you are done): ");
                int value = inputScanner.nextInt();
                while (value != -1) {
                    insertIntoBST(rootNode, value);
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

    /**
     * is tree height balanced?
     * 
     * find the height of all leaf nodes. if height of even one is different,
     * return false.
     * 
     * checkTreeHeight(Node n)
     * 
     * if n is leaf and height!=lastUpdatedHeight print not balanced.
     * 
     * else
     * 
     * checkTreeHeight(left) checkTreeheight(right)
     * 
     * @param rootNode
     * @return
     */
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

    public void computeTreeDepth(int height, Node rootNode) {

        rootNode.setHeight(height);

        if (rootNode.getLeft() != null)
            computeTreeDepth(height + 1, rootNode.getLeft());

        if (rootNode.getRight() != null)
            computeTreeDepth(height + 1, rootNode.getRight());

    }

    public void insertIntoBST(Node node, int data) {

        // traverse from root to insert into BST.
        if (data > node.getData()) {
            // insert into the right side of the tree;
            if (node.getRight() == null) {
                // insert the node right here left to this node. add the new
                // node to the list of nodes.
                Node nodeToBeInserted = new Node(idCounter, data);
                idCounter++;
                node.setRight(nodeToBeInserted);
                nodeList.add(nodeToBeInserted);
            } else
                insertIntoBST(node.getRight(), data);

        } else if (data < node.getData()) {
            // insert into the left side of the tree;
            if (node.getLeft() == null) {
                // insert the node right here: to the left
                Node nodeToBeInserted = new Node(idCounter, data);
                idCounter++;
                node.setLeft(nodeToBeInserted);
                nodeList.add(nodeToBeInserted);
            } else
                insertIntoBST(node.getLeft(), data);

        } else if (data == node.getData()) {
            System.out
                    .println("Data cannot be inserted since duplicate nodes are not allowed in BST.");
        }
    }

    public void inOrderSuccessor(Node inputNode) {

        if (inputNode.getLeft() == null) {
            System.out.println(" (" + inputNode.getNodeID() + ", " + inputNode.getData()
                    + ") is successor.");
        } else
            inOrderSuccessor(inputNode.getLeft());
    }

    public void findStatic(int number, Node rootNode) {

        // find the numberTH smallest number in the tree.

        // traverse left tree

        // traverse root

        // traverse right tree

    }

    int countOfSort = 0;

    public void returnkthStaticstic(int k, Node rootNode) {
        if (rootNode.getLeft() != null)
            returnkthStaticstic(k, rootNode.getLeft());

        countOfSort++;
        if (countOfSort == k)
            System.out.println(k + " th item is " + rootNode.getData());

        if (rootNode.getRight() != null)
            returnkthStaticstic(k, rootNode.getRight());

    }

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
