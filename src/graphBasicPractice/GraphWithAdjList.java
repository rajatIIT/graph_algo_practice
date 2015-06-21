package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class GraphWithAdjList {

    // 0: white, 1 :gray, 2:black
    int[] graphColor;
    int numberOfVertices;
    int[] vertexID;
    int[] starTime, stopTime;
    boolean isDirected;
    GraphVertex[] allVertices;

    public GraphWithAdjList() {

        // implement DFS :

        Scanner fileRead = new Scanner(System.in);

        while (true) {

            // Menu-Driven Program
            System.out.println("\n" + "What do you want to do?");
            System.out.println("1) Add a vertex ");
            System.out.println("2) Delete a vertex ");
            System.out.println("3) Create a new Graph ");
            System.out.println("4) View the graph ");
            System.out.println("5) Exit ");
            System.out.println("6) Execute Depth First Search ");
            int choice = fileRead.nextInt();

            if (choice == 3) {
                // create a new graph

                // input the number of vertices

                // for each vertice, ask the number of vertices it is connected
                // to.

                System.out.println("Enter the number of vertices: ");
                numberOfVertices = fileRead.nextInt();
                allVertices = new GraphVertex[numberOfVertices + 1];

                System.out.println("Is the graph Directed?(y/n)");
                char userChoice = fileRead.next().charAt(0);
                if (userChoice == 'y')
                    isDirected = true;
                else if (userChoice == 'n')
                    isDirected = false;
                for (int i = 1; i <= numberOfVertices; i++) {
                    GraphVertex newVertex = new GraphVertex();
                    newVertex.setId(i);
                    allVertices[i] = newVertex;
                }
                for (int i = 1; i <= numberOfVertices; i++) {

                    // create a adjacency list for every vertex.

                    System.out
                            .println("Enter the vertices to which vertex "
                                    + i
                                    + " is connected followed by enter. Enter -1 when you are finished entering.");
                    int nextInt = fileRead.nextInt();
                    while (nextInt != -1) {
                        if (!allVertices[i].getList().contains(nextInt))
                            allVertices[i].getList().add(nextInt);
                        if (!isDirected) {
                            if (!allVertices[nextInt].getList().contains(i))
                                allVertices[nextInt].getList().add(i);
                        }
                        nextInt = fileRead.nextInt();
                    }

                }
            } else if (choice == 4) {

                // display the graph
                for (int i = 1; i <= numberOfVertices; i++) {

                    System.out.println();
                    System.out.println("Vertex " + (i) + " is connected to :");
                    ArrayList<Integer> currentList = allVertices[i].getList();
                    Iterator currentListIt = currentList.iterator();
                    while (currentListIt.hasNext())
                        System.out.print(((Integer) currentListIt.next()) + "   ");
                }
            } else if (choice==5){
                System.exit(1);
            } else if (choice==6){
                DepthFirstSearch myDFS = new DepthFirstSearch(allVertices);
                myDFS.search();
            }
        }
    }

}
