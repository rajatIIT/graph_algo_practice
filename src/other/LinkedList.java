package other;

import java.util.Scanner;

public class LinkedList {

    private Node rootNode;
    private Node lastNodePointer;

    public LinkedList() {
        start();
    }

    public void start() {

        while (true) {
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("\n" + "Press 1 to create a new linked list. ");
            System.out.println("Press 2 to view the linked list. ");
            System.out.println("Press 3 to view the middle element of the linked list. ");
            System.out.println("Press 4 to view the Nth element from last. ");
            System.out.println("Press 5 to reverse the linked list.  ");
            int nextInput = inputScanner.nextInt();
            if (nextInput == 1) {
                System.out.println("Enter numbers to be inserted followed by -1: ");
                int input = inputScanner.nextInt();
                rootNode = new Node(null, input);
                lastNodePointer = rootNode;
                while (input != -1) {
                    input = inputScanner.nextInt();
                    if (input != -1) {
                        Node nextNode = new Node(null, input);
                        lastNodePointer.setNext(nextNode);
                        lastNodePointer = nextNode;
                    }
                }
            } else if (nextInput == 2) {
                Node currentNode = rootNode;
                while (currentNode != null) {
                    System.out.print(" -> " + currentNode.getData());
                    currentNode = currentNode.getNext();
                }
            } else if (nextInput == 3) {
                computeMiddle();
            } else if (nextInput == 4) {
                getNfromLast(0);
            } else if (nextInput == 5) {
                reverseList();
            }

        }

    }

    public void reverseList() {
        if (rootNode.equals(null) || rootNode.getNext().equals(null)) {
            System.out.println("The list has only one or two elements.");
        } else {
            Node topPointer = rootNode;
            Node bottomPointer = rootNode.getNext();
            rootNode.setNext(null);
            while (bottomPointer.getNext() != null) {
                // first invert
                Node nextPointer = bottomPointer.getNext();
                bottomPointer.setNext(topPointer);
                // then update both the pointers
                topPointer = bottomPointer;
                bottomPointer = nextPointer;
            }
            bottomPointer.setNext(topPointer);
            rootNode = bottomPointer;
        }
    }

    public void getNfromLast(int n) {

        // getElementCount

        // getElementFromStart

        // implement getMiddleElementIndex() : if equal to elementFromStart,
        // return the element else recursively continue;

        // implement getMiddleElement ;
        System.out.println(getMiddleElement(rootNode, rootNode.getNext()));
        System.out.println("Index: " + middleElementIndex);
    }

    private int elementCount;
    private int middleElementIndex;
    private Node startElement;

    public Node getMiddleElement(Node startElement, Node finalElement) {
        Node firstPointer = startElement;
        Node secondPointer = startElement;
        middleElementIndex = 0;

        System.out.println("firstpointer: " + firstPointer.getData() + " , secondPointer: "
                + secondPointer.getData());
        while (!firstPointer.equals(finalElement) || !firstPointer.equals(finalElement)) {
            System.out.println("firstpointer: " + firstPointer.getData() + " , secondPointer: "
                    + secondPointer.getData());
            middleElementIndex++;
            firstPointer = firstPointer.getNext().getNext();
            secondPointer = secondPointer.getNext();
        }
        return secondPointer;
    }

    /**
     * Get the middle element of the list.
     * 
     * 
     */
    public void computeMiddle() {
        if (rootNode != null) {

            Node firstPointer = rootNode;
            Node secondPointer = rootNode;
            int countOfElements = 0;

            // while (firstPointer!=null || firstPointer.getNext() != null ||
            // firstPointer.getNext().getNext() != null) {
            while (firstPointer.getNext().getNext() != null || firstPointer.getNext() != null) {
                System.out.println("not null for " + firstPointer.getData() + ". Next is "
                        + firstPointer.getNext().getData());
                firstPointer = firstPointer.getNext().getNext();
                if (firstPointer == null)
                    break;
                secondPointer = secondPointer.getNext();
                countOfElements++;
            }

            System.out.println("Total elements in list: " + (2 * (countOfElements + 1)));
            System.out.println("Middle element: " + secondPointer.getData());
            System.out.println("Index of Middle element: " + countOfElements);

        } else {
            System.out.println("This is a null list! ");
        }
    }

    public class Node {

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }

        Node next;

        protected Node getNext() {
            return next;
        }

        protected void setNext(Node next) {
            this.next = next;
        }

        protected int getData() {
            return data;
        }

        protected void setData(int data) {
            this.data = data;
        }

        int data;
    }

}
