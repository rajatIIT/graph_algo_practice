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
            int nextInput = inputScanner.nextInt() ; 
            if (nextInput== 1) {
                System.out.println("Enter numbers to be inserted followed by -1: ");
                int input = inputScanner.nextInt();
                rootNode = new Node(null, input);
                lastNodePointer = rootNode;
                while (input != -1) {
                    input = inputScanner.nextInt();
                    Node nextNode = new Node(null, input);
                    lastNodePointer.setNext(nextNode);
                    lastNodePointer = nextNode;
                }
            } else if (nextInput == 2) {
                Node currentNode = rootNode;
                while (currentNode.getNext() != null) {
                    System.out.print(" -> " + currentNode.getData());
                 currentNode=currentNode.getNext();   
                }
            } else if (nextInput == 3) {
                computeMiddle();
            }

        }

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

            //while (firstPointer!=null || firstPointer.getNext() != null || firstPointer.getNext().getNext() != null) {
                while (firstPointer.getNext()!=null ) {
                    System.out.println("not null for " + firstPointer.getData() + ". Next is " + firstPointer.getNext().getData());
                firstPointer = firstPointer.getNext().getNext();
                if(firstPointer==null)
                    break;
                secondPointer = secondPointer.getNext();
                countOfElements++;
            }

            System.out.println("Total elements in list: " + (2*countOfElements));
              System.out.println("Middle element: " + secondPointer.getData());
            
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
