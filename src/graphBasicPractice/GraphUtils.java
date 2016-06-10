package graphBasicPractice;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// Some classic graph utilities 
// that we will be usig in this 
// implementation. We basically 
// create a random graph here. 

public class GraphUtils {

	// creates a random graph based 
	// on maximum number of vertices, 
	// min. graph size and whether 
	// or not graph is directed. 
    public static GraphVertex[] createRandomGraph(int vertexBound, int minimumGraphSize, boolean isDirected) {
        
        GraphVertex[] allVertices;
        // create a random graph
        int totalEdges = 0;
        Random random = new Random();
        // max value of the weight possible
        // we can change this as per requirements.
        int weightBound = 50;

        System.out.println("Generating a random graph.");
        // decide a random size
        int graphSize = random.nextInt(vertexBound - minimumGraphSize);
        // increment graphsize because 
        // nextInt can return a value between
        // 0 and the (max-vertexSize) 
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

        // we can uncomment the next line 
        // and comment out the second next
        // if we want the graph to be 
        // directed.
        
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
        // connect them to each other (randomly) now.
        for (int i = 1; i < graphSize + 1; i++) {
            // for each vertex, create edges
        	int numberOfAssociations = random.nextInt(graphSize);

            // make sure that every vertex has at least one edge.
            if (numberOfAssociations == 0)
                numberOfAssociations++;

            System.out.println(" \n Now randomly connecting vertices. Making "
                    + numberOfAssociations + " edges for vertex number " + i);
            
            // random unique list of integers
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

                        // executes when the graph is not directed
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
    
    // The method for creating a Dijkstra graph is  
    // different because it consists of special 
    // "Dijkstra Vertices" (See DkstraVertex class)
    // in which each node also contains the visit status,
    // minimal path and the minimal weight.
    //
    // The create process is similar to one for creating
    // random graph but we need to make additional assignments
    // for these extra variables. 
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
        // connect the vertices using edges now.
        
        // the way we plan to show visually is by 
        // assigning a node ID to each vertex, and, 
        // for each vertex, printing a list of 
        // node IDs to which it is connected. 
        // We also take care of avoiding duplicates
        // (they will arise only in non-directed 
        // graphs)
        
        for (int i = 1; i < graphSize + 1; i++) {
            // for each vertex, create edges.

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
    

    // generate a random list.
    public static List<Integer> getUniqueRandomSequence(int size, int upperBound) {

        LinkedList<Integer> ascendingList = new LinkedList<Integer>();
        
    	// create an ascending list, like 1,2,3,4,... etc.
        for (int i = 1; i <= upperBound; i++)
            ascendingList.add(i);
        
        Collections.shuffle(ascendingList);
        
        return ascendingList.subList(0, size);

    }

}
