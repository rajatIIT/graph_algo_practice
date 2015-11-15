package graphBasicPractice;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GraphUtils {

    public static GraphVertex[] createRandomGraph(int vertexBound, int minimumGraphSize, boolean isDirected) {
        
        GraphVertex[] allVertices;
        // create a random graph
        int totalEdges = 0;
        Random random = new Random();
        int weightBound = 50;

        System.out.println("Generating a random graph.");
        // decide a random size
        int graphSize = random.nextInt(vertexBound - minimumGraphSize);
        graphSize = graphSize + minimumGraphSize;
        System.out.println("Selected random size of vertices: " + graphSize);

        // graphSize is the size of vertices. For each vertex,
        // associate it to a random number of vertices (the number of
        // associations are less than 20)

        /*
         * 
         * int[] graphColor; int numberOfVertices; int[] vertexID; int[]
         * starTime, stopTime; boolean isDirected; GraphVertex[] allVertices;
         */

        allVertices = new GraphVertex[graphSize + 1];

        // isDirected = random.nextBoolean();

        isDirected = false;
        if (isDirected)
            System.out.println("Random graph is directed.");
        else
            System.out.println("Random graph is not directed.");

        int numberOfVertices = graphSize;

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

            List<Integer> assocList = getUniqueRandomSequence(numberOfAssociations, graphSize);

            // for each element in the assocList associate the current to that number of node.

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

                            System.out.print(" ( " + i + "," + nextNodeId  + "), Wt: " + randomWeight
                                    + " .");
                        } else {
                            System.out.println("Ignoring " + nextNodeId + ". Already connected.");
                        }
                    }
                }
            }

        }
        System.out.println();
        System.out.println(" Total edges in random graph: " + totalEdges);
        
        return allVertices;
                
    }
    
    
    public static DkstraVertex[] createRandomDijkstraGraph(int vertexBound, int minimumGraphSize, boolean isDirected){
        
        
        
        DkstraVertex[] allVertices;
        // create a random graph
        int totalEdges = 0;
        Random random = new Random();
        int weightBound = 50;

        System.out.println("Generating a random graph.");
        // decide a random size
        int graphSize = random.nextInt(vertexBound - minimumGraphSize);
        graphSize = graphSize + minimumGraphSize;
        System.out.println("Selected random size of vertices: " + graphSize);

        // graphSize is the size of vertices. For each vertex,
        // associate it to a random number of vertices (the number of
        // associations are less than 20)

        /*
         * 
         * int[] graphColor; int numberOfVertices; int[] vertexID; int[]
         * starTime, stopTime; boolean isDirected; GraphVertex[] allVertices;
         */

        allVertices = new DkstraVertex[graphSize + 1];

        // isDirected = random.nextBoolean();

        isDirected = false;
        if (isDirected)
            System.out.println("Random graph is directed.");
        else
            System.out.println("Random graph is not directed.");

        int numberOfVertices = graphSize;

        System.out.println("Creating nodes.");
        for (int i = 1; i < graphSize + 1; i++) {
            // create a new vertex for each node in the iteration
            // i is the node number here.

            int nextData = random.nextInt(100);
            System.out.println("Node number " + i + " has data " + nextData + " .");
            allVertices[i] = new DkstraVertex(i, nextData);
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

            List<Integer> assocList = getUniqueRandomSequence(numberOfAssociations, graphSize);

            // for each element in the assocList associate the current to that number of node.

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

                            System.out.print(" (" + i + "," +  nextNodeId + ");Wt:" + randomWeight
                                    + " .");
                        } else {
                            System.out.print(" Ignore " + nextNodeId + ".");
                        }
                    }
                }
            }

        }
        System.out.println();
        System.out.println(" Total edges in random graph: " + totalEdges);
        
        return allVertices;
    }
    

    public static List<Integer> getUniqueRandomSequence(int size, int upperBound) {

        LinkedList<Integer> ascendingList = new LinkedList<Integer>();
        for (int i = 1; i <= upperBound; i++)
            ascendingList.add(i);

        Collections.shuffle(ascendingList);

        return ascendingList.subList(0, size);

    }

}
