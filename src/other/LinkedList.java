package other;

import java.util.Scanner;

public class LinkedList {

    private Node rootNode;
    private Node lastNodePointer;
    private Node auxilaryList;

    public LinkedList() {
        start();

    }

    public void deleteAlternate() {

        Node currentNode = rootNode;
        while (true) {
            currentNode.setNext(currentNode.getNext().getNext());
            currentNode = currentNode.getNext();
            if (currentNode == null)
                break;
        }
    }

    public void mergeSortedLists() {

    }

    /**
     * Correct and Efficient
     * 
     */
    public void bringToFront() {
        Node currentNode = rootNode;

        while (currentNode.getNext().getNext() != null) {
            currentNode = currentNode.getNext();
        }

        Node lastSecondNode = currentNode;
        Node lastNode = currentNode.getNext();

        lastNode.setNext(rootNode);
        lastSecondNode.setNext(null);
        rootNode = lastNode;

    }

    public void testCycle() {

        Node one = new Node(null, 1);
        Node two = new Node(null, 2);
        Node three = new Node(null, 3);
        Node four = new Node(null, 4);
        Node five = new Node(null, 5);

        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(two);

        rootNode = one;
        detectLoop();

    }

    public void start() {

        while (true) {
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("\n" + "Press 1 to create a new linked list. ");
            System.out.println("Press 2 to view the linked list. ");
            System.out.println("Press 3 to view the middle element of the linked list. ");
            System.out.println("Press 4 to view the Nth element from last. ");
            System.out.println("Press 5 to reverse the linked list.  ");
            System.out.println("Press 6 to delete alternate nodes in linked list.  ");
            System.out.println("Press 7 to bring the last node to the front.");
            System.out.println("Press 8 to add an auxilary list.");
            System.out.println("Press 9 to view the auxilary list.");
            System.out
                    .println("Press 10 to take intersection of primary and auxilary list. (Both MUST be sorted)");
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
            } else if (nextInput == 6) {
                deleteAlternate();
            } else if (nextInput == 7) {
                bringToFront();
            } else if (nextInput == 8) {

                createAnotherList();

            } else if (nextInput == 9) {
                Node currentNode = auxilaryList;
                while (currentNode != null) {
                    System.out.print(" -> " + currentNode.getData());
                    currentNode = currentNode.getNext();
                }
            } else if (nextInput == 10) {
                mergeLists();
                System.out.println("The merged list is: ");
                Node currentNode = newList;
                while (currentNode != null) {
                    System.out.print(" -> " + currentNode.getData());
                    currentNode = currentNode.getNext();
                }

            }

        }

    }

    Node newList = new Node();

    /**
     * Given two lists sorted in increasing order, create and return a new list
     * representing the intersection of the two lists. The new list should be
     * made with its own memory — the original lists should not be changed. For
     * example, let the first linked list be 1->2->3->4->6 and second linked
     * list be 2->4->6->8, then your function should create and return a third
     * list as 2->4->6.
     * 
     */
    private void mergeLists() {

        Node newListCurrentNode = newList;

        Node mainListPointer = rootNode;
        Node auxilaryListPointer = auxilaryList;

        while (!(mainListPointer.getNext() == null && auxilaryListPointer.getNext() == null)) {
            if (mainListPointer.getData() < auxilaryListPointer.getData()) {
                mainListPointer = mainListPointer.getNext();
            } else if (mainListPointer.getData() > auxilaryListPointer.getData()) {
                auxilaryListPointer = auxilaryListPointer.getNext();
            } else if (mainListPointer.getData() == auxilaryListPointer.getData()) {
                Node newListNode = new Node(null, mainListPointer.getData());
                newListCurrentNode.setNext(newListNode);
                newListCurrentNode = newListNode;
                if (mainListPointer.getNext() != null)
                    mainListPointer = mainListPointer.getNext();
                if (auxilaryListPointer.getNext() != null)
                    auxilaryListPointer = auxilaryListPointer.getNext();
            }
        }
    }

    private void createAnotherList() {

        Scanner myInputScanner = new Scanner(System.in);
        System.out.println("Enter the numbers for the new list followed by -1");

        int nextInput;
        Node currentNode;

        nextInput = myInputScanner.nextInt();
        auxilaryList = new Node(null, nextInput);
        currentNode = auxilaryList;

        while (true) {
            nextInput = myInputScanner.nextInt();
            if (nextInput == -1)
                break;
            Node newNode = new Node(null, nextInput);
            currentNode.setNext(newNode);
            currentNode = newNode;
        }

    }

    public void detectLoop() {

        Node simplePointer = rootNode;
        Node alternatePointer = rootNode;
        boolean foundCycle = false;

        while (true) {

            if (alternatePointer.getNext() == null || alternatePointer.getNext().getNext() == null)
                break;
            simplePointer = simplePointer.getNext();
            alternatePointer = alternatePointer.getNext().getNext();

            if (simplePointer.equals(alternatePointer)) {
                foundCycle = true;
                break;
            }

        }

        if (foundCycle)
            System.out.println("Cycle exists.");
        else
            System.out.println("No cycle!");

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

        public Node() {
            this.next = null;
        }

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
