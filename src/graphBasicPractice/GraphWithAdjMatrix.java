package graphBasicPractice;

import java.util.Scanner;

public class GraphWithAdjMatrix {

    int verticesCount;
    int edgesCount;
    int[][] adjacencyMatrix;
    
    public GraphWithAdjMatrix() {
        Scanner fileRead = new Scanner(System.in);

        while (true) {
            // display the menu
            System.out.println("\n" + "What do you want to do?");
            System.out.println("1) Add a vertex ");
            System.out.println("2) Delete a vertex ");
            System.out.println("3) Create a new Graph ");
            System.out.println("4) View the graph ");
            System.out.println("5) Exit ");
            int choice = fileRead.nextInt();

            if (choice == 1) {
                System.out.println("enter the starting vertex: ");
                int inputVertex1 = fileRead.nextInt();
                System.out.println("enter the ending vertex: ");
                int inputVertex2 = fileRead.nextInt();
                if (inputVertex1 <= verticesCount && inputVertex2 <= verticesCount) {
                    adjacencyMatrix[inputVertex1 - 1][inputVertex2 - 1] = 1;
                    adjacencyMatrix[inputVertex2 - 1][inputVertex1 - 1] = 1;
                    System.out.println("Vertex Removed!");
                } else {
                    System.out
                            .println("Unable to add! Number exceeds the limit of number of vertices or no graph exists.");
                }

            } else if (choice == 2) {
                System.out.println("enter the starting vertex: ");
                int inputVertex1 = fileRead.nextInt();
                System.out.println("enter the ending vertex: ");
                int inputVertex2 = fileRead.nextInt();
                if (inputVertex1 < verticesCount && inputVertex2 < verticesCount) {
                    adjacencyMatrix[inputVertex1 - 1][inputVertex2 - 1] = 0;
                    adjacencyMatrix[inputVertex2 - 1][inputVertex1 - 1] = 0;
                    System.out.println("Vertex Added!");
                } else {
                    System.out
                            .println("Unable to add! Number exceeds the limit of number of vertices or no graph exists");
                }

            } else if (choice == 3) {

                System.out.println("Enter the size (numberof vertices): ");
                verticesCount = fileRead.nextInt();
                adjacencyMatrix = new int[verticesCount][verticesCount];
                for (int i = 0; i < verticesCount; i++) {
                    for (int j = 0; j < verticesCount; j++) {
                        System.out.println("is " + (i + 1) + "vertex connected to " + (j + 1)
                                + " vertex? (y/n) ");
                        char charChoice = fileRead.next().charAt(0);
                        if (charChoice == 'y')
                            adjacencyMatrix[i][j] = 1;
                    }
                    System.out.println();
                }
            } else if (choice == 4) {
                System.out.println("View the adjacency matrix here:");
                System.out.println();
                for (int i = 0; i < verticesCount; i++) {
                    System.out.println();
                    for (int j = 0; j < verticesCount; j++) {
                        System.out.print("  ");
                        System.out.print(adjacencyMatrix[i][j]);
                    }
                }
            } else if (choice == 5) {
                break;
            }
            fileRead.close();
        }
        
    }

}
