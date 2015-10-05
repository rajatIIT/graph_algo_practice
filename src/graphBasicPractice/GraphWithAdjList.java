package graphBasicPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
            System.out.println("7) Create a Random Weighted graph ");
            System.out.println("8) Execute Kruskals. ");
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
            } else if (choice == 5) {
                System.exit(1);
            } else if (choice == 6) {
                DepthFirstSearch myDFS = new DepthFirstSearch(allVertices);
                myDFS.search();
            } else if (choice == 7) {

                // create a random graph
                int totalEdges = 0;
                Random random = new Random();
                int weightBound = 50;

                System.out.println("Generating a random graph.");
                // decide a random size
                int graphSize = random.nextInt(5);
                graphSize = graphSize + 5;
                System.out.println("Selected random size of vertices: " + graphSize);

                // graphSize is the size of vertices. For each vertex,
                // associate it to a random number of vertices (the number of
                // associations are less than 20)

                /*
                 * 
                 * int[] graphColor; int numberOfVertices; int[] vertexID; int[]
                 * starTime, stopTime; boolean isDirected; GraphVertex[]
                 * allVertices;
                 */

                allVertices = new GraphVertex[graphSize + 1];

                // isDirected = random.nextBoolean();

                isDirected = false;
                if (isDirected)
                    System.out.println("Random graph is directed.");
                else
                    System.out.println("Random graph is not directed.");

                numberOfVertices = graphSize;

                System.out.println("Creating nodes.");
                for (int i = 1; i < graphSize + 1; i++) {
                    // create a new vertex for each node in the iteration
                    // i is the node number here.

                    int nextData = random.nextInt(100);
                    System.out.println("Node number " + i + " has data " + nextData + " .");
                    allVertices[i] = new GraphVertex(i, nextData);
                }

                // we now have the list of vertices ready.
                // create the associations now.
                for (int i = 1; i < graphSize + 1; i++) {
                    // for each vertex, create associations

                    int numberOfAssociations = random.nextInt(graphSize);

                    // make sure that every vertex has at least one edge.
                    if (numberOfAssociations == 0)
                        numberOfAssociations++;

                    System.out.println(" \n Now randomly connecting vertices. Making "
                            + numberOfAssociations + " edges for vertex number " + i);

                    List<Integer> assocList = getUniqueRandomSequence(numberOfAssociations,
                            graphSize);

                    // for each element in the assocList associate the current
                    // to that number of node.

                    Iterator<Integer> assocListIt = assocList.iterator();

                    while (assocListIt.hasNext()) {

                        int nextNodeId = assocListIt.next();

                        // associate a random weight, too.
                        int randomWeight = random.nextInt(weightBound);

                        if (i != nextNodeId) {
                            if (isDirected) {
                                totalEdges++;
                                allVertices[i].addVertex(nextNodeId);
                                allVertices[i].getWeightMap().put(nextNodeId, randomWeight);
                            } else if (!isDirected) {

                                // executes when the node is not directed
                                // we are trying to associate i to nextNodeId.
                                // if I is already associated to nextNodeId,
                                // neither
                                // disturb I
                                // nor nextNodeId, else connect both

                                if (!allVertices[i].getList().contains(nextNodeId)) {

                                    totalEdges++;
                                    allVertices[nextNodeId].addVertex(i);
                                    allVertices[nextNodeId].getWeightMap().put(i, randomWeight);

                                    allVertices[i].addVertex(nextNodeId);
                                    allVertices[i].getWeightMap().put(nextNodeId, randomWeight);

                                    System.out.print(" Connect " + nextNodeId + " Weight: "
                                            + randomWeight + " .");
                                } else {
                                    // the vertices are already connected.
                                    System.out.println("Ignoring " + nextNodeId
                                            + ". Already connected.");
                                }
                            }
                        }
                    }

                }
                System.out.println();
                System.out.println(" Total edges in random graph: " + totalEdges);

            } else if (choice == 8) {
                Kruskals kruskalExecuter = new Kruskals(numberOfVertices, allVertices);
            }

        }
    }

    public List<Integer> getUniqueRandomSequence(int size, int upperBound) {

        LinkedList<Integer> ascendingList = new LinkedList<Integer>();
        for (int i = 1; i <= upperBound; i++)
            ascendingList.add(i);

        Collections.shuffle(ascendingList);

        return ascendingList.subList(0, size);

    }

}
