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
            }

        }
        inputScanner.close();
    }
}
