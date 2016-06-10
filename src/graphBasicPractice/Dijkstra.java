package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

// implement the Dijkstra shortest path algorithm
// find the hosrtest path between two vertices. 
// Fixes one vertex as a source vertex. 
//
// (MUST) Read the following before proceeding : 
// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm (Intro part)
// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Algorithm
//
// Important : Dijkstra algorithm works on 
// "weighted" graphs. Weight is a central concept
// here since we want to find a minimal weight 
// path from source vertex to the destination vertex.
// 
public class Dijkstra {

	// Implement the input graph as an
	// array of Dijkstra Vertices. 
	// Note that we created special
	// class for a Dijkstra vertex 
	// because it is different from 
	// a regular vertex. 
	//
	// This class, has, some fields
	// in addition to the regular graph vertex:
	// visitStatus and minimal weight. 
	// These fields are in addition 
	// to the regular fields a graph 
	// has. Note, in particular, how 
	// frequently (and practically)
	// the concept of inheritance 
	// from OOP is used. 
	// [Read about inheritance from 
	// the Depth First Search algorithm]
	//
    // Note that as far as the concept of 
    // a "Dijkstra Vertex" is concerned, 
	// we have two related conepts : 
	// Visiting the Node and allocating
	// the minimal weight. 
	// Every vertex is moved from the 
	// unvisited set to the visited 
	// one for the first time it is visited.
	// It is never visited again. 
	// However, if algorithm considers 
	// some path where it comes to the particular
	// node again, a minimum weight may
	// be allocated. 
	
	
	// Priority Queue is a very interesting
	// data structure which is used here. 
	// One of the favorites of the interviewer,
	// it is used here in practice. 
	//
	// Basically we use a priority queue to 
	// store the unvisited vertices. From the 
	// algorithm in Wikipedia, we observe that
	// there are two sets: visited and unvisited
	// vertices. 
	
    DkstraVertex[] dijkstraVerticesSet;
     
    HashSet<DkstraVertex> unvisitedVertices;
    
    // Priority queue allows for O(log n)
    // insertion time and O(1) removal 
    // time (and some time for maintenance
    // after that)
    // Priority queue works on top of a heap
    // which is actually implemented using 
    // either a heap or an array. Remember,
    // we love arrays in situations where 
    // the only operation on the Data Structure 
    // is random access because they are fast. 
    PriorityQueue<DkstraVertex> unvisitedVerticesWithWeight;
    
    // we use a HashSet because we will 
    // often check to see if a vertex has
    // been visited in the past or not. 
    // This happens a lot of times in the
    // dense graphs (graph that have a
    // lot of edges compared to the vertices)
    // HashSet gives O(1) performance in 
    // such cases. 
    // Compare to list/array where we would
    // have to perform a search and it would 
    // take O(n) time to execute the search.
    HashSet<DkstraVertex> visitedVertices;
    
    // One of the most critical decisions 
    // while scripting solution to any problem 
    // is identifying the right data structures. 
    // Note that different data structures have
    // different performance considerations 
    // and this could fundamentally affect the 
    // time taken by algorithm to execute.
    //
    // Thus, it is very important to understand the 
    // working of various data structures for 
    // devising an algorithm for optimal performance.


    public Dijkstra() {

    }


    // we assume user to create 
    // the array representing the graph beforehand 
    // and pass to this method. Because of the
    // flexibility that OOP provides in class structures
    // ,the user can represent any graph using 
    // the structures and pass it to this method.
    //
    // Note how each node is represented with an
    // "integer" and we return a list of integer
    // which represents the order of vertices
    // user needs to visit to traverse the shortest
    // path. 
    
    public List<Integer> getShortestPath(int initialVertex, int finalVertex, DkstraVertex[] dijkstraVerticesSet) {
        // create a local reference to the 
    	// input graph
    	this.dijkstraVerticesSet = dijkstraVerticesSet;
        System.out.println("---------------------------------");
        System.out.println("Try to find shortest distance between " + initialVertex + " and " +  finalVertex);
        System.out.println("---------------------------------");
        
        // Each vertex has a "minimial weight" as per 
        // the algorithm and our task is to find this 
        // value for the destination vertex. 
        // As stated in the first step of the algorithm,
        // we assign a minimal weight of 0 to the 
        // source vertex and infinity to rest of the vertices. 
        dijkstraVerticesSet[initialVertex].setMinimalWeight(0);

        // 1) Create an unvisited set : Set because of fast delete performance. 
        unvisitedVertices = new HashSet<>();

        //for (DkstraVertex eachVertex : dijkstraVerticesSet) {
        for (int i=1;i<dijkstraVerticesSet.length;i++) {
          //  System.out.println("add " + eachVertex.getId() + " to unvisited vertices.");
            unvisitedVertices.add(dijkstraVerticesSet[i]);
        }
        // initially all the vertices except the source vertex
        // are unvisited. 
            unvisitedVertices.remove(dijkstraVerticesSet[initialVertex]);
     //   dijkstraVerticesSet[initialVertex].setList(new ArrayList<Integer>());

        
        // set initial node as current
        int current = initialVertex;

        // PriorityQueue because we want fast getMaxPriorityElement performance. 
        unvisitedVerticesWithWeight = new PriorityQueue<DkstraVertex>(20,
                new DijkstraWeightComparator());

        visitedVertices = new HashSet<>();
        printVisitStatus();

        // these sets get empty when whole of 
        // the graph has been visited. 
        // (as per the algorithm).
        // This also means that the 
        // minimal distance to all the 
        // vertices from source vertex 
        // has been calculated.
        
        while (!(unvisitedVertices.isEmpty() && unvisitedVerticesWithWeight.isEmpty()) ) {
            
            System.out.println(" Visit " + current + " node.");
    
            Iterator<Integer> listIT = dijkstraVerticesSet[current].getList().iterator();
            
            // just print the connected vertices.
            System.out.println( current + " connected to ");
            while(listIT.hasNext()){
                System.out.print(listIT.next() + " ");
            }
            System.out.println();
            
            int currentWeight = dijkstraVerticesSet[current].getMinimalWeight();
            
            List<Integer> verticesConnectedToCurrent = dijkstraVerticesSet[current].getList();
            Iterator<Integer> verticesIterator = verticesConnectedToCurrent.iterator();
            // iterate over all the vertices connected to 
            // current vertex. 
            while (verticesIterator.hasNext()) {

                // for each vertex visit and update the weight as necessary.
                int nextVertex = verticesIterator.next();
                
                int currentAddedWeight = dijkstraVerticesSet[current].getWeightMap().get(nextVertex);
                // notice how we use currentWeight:
                // whenever we explore a possible path,
                // we keep on adding the weight of vertices
                // we visit one after the other. 
                // this is why we are adding the weights here. 
                currentWeight = currentWeight + currentAddedWeight;
                // Then, we visit the node and assign the minimum
                // weight to the node as required.
                visitNode(dijkstraVerticesSet[current].getMinimalPath(), currentWeight, nextVertex);
                // Finally, we backtrack to explore the next 
                // unvisited vertex in the list. While
                // backtracking, we have to subtrct the weight for
                // this vertex.
                currentWeight=currentWeight-currentAddedWeight;
            }
            
            System.out.println("Mark " + current + " as visited.");
            dijkstraVerticesSet[current].setVisitStatus(DkstraVertex.VISITED);
            unvisitedVertices.remove(dijkstraVerticesSet[current]);
            unvisitedVerticesWithWeight.remove(dijkstraVerticesSet[current]);
            visitedVertices.add(dijkstraVerticesSet[current]);
            printVisitStatus();
            
            

            if (!unvisitedVerticesWithWeight.isEmpty()){
                current = unvisitedVerticesWithWeight.poll().getId();
            System.out.println("Out of unvisited vertices, next polled vertex is " + current);
            
            }
            

        }
        //print shortest path to all vertices
        printMinimalWeights(dijkstraVerticesSet);
        return dijkstraVerticesSet[finalVertex].getMinimalPath();
  }

    private void printMinimalWeights(DkstraVertex[] vertices) {
        
        for(int i=1;i<vertices.length;i++){
            System.out.println("For " + vertices[i].getId() + " the min. weight is " + vertices[i].getMinimalWeight());
            System.out.print("The minimal path is: ");
            List<Integer> minimalPathList = vertices[i].getMinimalPath();
            for(Integer eachInt: minimalPathList){
                System.out.print(eachInt + "-> ");
            }
            
            
        }
        
    }

    public void visitNode(List<Integer> list, int currWeight, int nodeToBeVisited) {
       
        System.out.println("Examine node " + nodeToBeVisited);
        // currWeight is the computed weight. 
        if (currWeight < dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight()) {
            
            System.out.println("Current Weight  " + currWeight + 
            		" less than minimal weight " + dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight() 
            		+ "for current node. Reassign Weight.");
            
            // allocating the minimum distance for the 
            // first time
            if (dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight() == DkstraVertex.INFINITY) {
                System.out.println("Visiting " + nodeToBeVisited + " for the first time. Move to visited with weight.");
                unvisitedVertices.remove(dijkstraVerticesSet[nodeToBeVisited]);
                unvisitedVerticesWithWeight.add(dijkstraVerticesSet[nodeToBeVisited]);
            }

            dijkstraVerticesSet[nodeToBeVisited].setMinimalWeight(currWeight);
            
            // if the minimum weight of vertex is updated, 
            // then the minimal path is updated, too! 
            // We store this path in the current vertex. 
            // 
            // So that, in the end, we can just query 
            // the minimal path for the destination
            // vertex to get the shortest path. 
            ArrayList<Integer> perpareNewList = new ArrayList<Integer>();
            perpareNewList.addAll(list);
            perpareNewList.add(nodeToBeVisited);
            dijkstraVerticesSet[nodeToBeVisited].setMinimalPath(new ArrayList<Integer>(perpareNewList));
            unvisitedVerticesWithWeight.remove(dijkstraVerticesSet[nodeToBeVisited]);
            unvisitedVerticesWithWeight.add(dijkstraVerticesSet[nodeToBeVisited]);
            
        }
        
    }
    
    
    public void printVisitStatus() {
        
        // Just print the various lists.
        System.out.print("Unvisited Infinite Vertices: ");
        Iterator<DkstraVertex> unvIt = unvisitedVertices.iterator();
        while(unvIt.hasNext())
            System.out.print(unvIt.next().getId() + " -> ");
        System.out.println();
        
        
        System.out.print("Unvisited Weighted Vertices: ");
        Iterator<DkstraVertex> unvWtIt = unvisitedVerticesWithWeight.iterator();
        while(unvWtIt.hasNext())
            System.out.print(unvWtIt.next().getId() + " -> ");
        System.out.println();
        
        
        System.out.print("Visited Vertices: ");
        Iterator<DkstraVertex> viIterator = visitedVertices.iterator();
        while(viIterator.hasNext())
            System.out.print(viIterator.next().getId() + " -> ");
        System.out.println();
    }

}
