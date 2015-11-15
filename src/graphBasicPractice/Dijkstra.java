package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    DkstraVertex[] dijkstraVerticesSet;
    HashSet<DkstraVertex> unvisitedVertices;
    PriorityQueue<DkstraVertex> unvisitedVerticesWithWeight;
    HashSet<DkstraVertex> visitedVertices;

    public Dijkstra() {

    }

    // find the shortest path

    public List<Integer> getShortestPath(int initialVertex, int finalVertex, DkstraVertex[] dijkstraVerticesSet) {
        this.dijkstraVerticesSet = dijkstraVerticesSet;
        System.out.println("---------------------------------");
        System.out.println("Try to find shortest distance between " + initialVertex + " and " +  finalVertex);
        System.out.println("---------------------------------");
        // 2) Assign a current node.

        // 3) for each node connected to the current node, visit the node.

        // visit() {

        // update visit status
        //

        // create a new DkstraVertex for allVertices.
       
        /*
        dijkstraVerticesSet = new DkstraVertex[allVertices.length];

        for (int i = 1; i < allVertices.length; i++) {
            dijkstraVerticesSet[i] = (DkstraVertex) allVertices[i];
                    
                    new DkstraVertex(allVertices[i].getId(),
                    allVertices[i].getData());
            dijkstraVerticesSet[i].setList((ArrayList<Integer>)allVertices[i].getList().clone());
            HashMap<Integer, Integer> map = dijkstraVerticesSet[i].getWeightMap();
            
            map = (HashMap<Integer, Integer>) allVertices[i].getWeightMap().clone();
            
            System.out.println("Set" + i + " index with nodeid " + allVertices[i].getId());
            System.out.println(dijkstraVerticesSet[i].getId());
        }
        
        */
        // set initial node as 0
        dijkstraVerticesSet[initialVertex].setMinimalWeight(0);

        // 1) Create an unvisited set : Set because of fast delete performance. 
        unvisitedVertices = new HashSet<>();

        //for (DkstraVertex eachVertex : dijkstraVerticesSet) {
        for (int i=1;i<dijkstraVerticesSet.length;i++) {
          //  System.out.println("add " + eachVertex.getId() + " to unvisited vertices.");
            unvisitedVertices.add(dijkstraVerticesSet[i]);
        }
        unvisitedVertices.remove(dijkstraVerticesSet[initialVertex]);
     //   dijkstraVerticesSet[initialVertex].setList(new ArrayList<Integer>());

        
        // set initial node as current
        int current = initialVertex;

        // PriorityQueue because we want fast getMaxPriorityElement performance. 
        unvisitedVerticesWithWeight = new PriorityQueue<DkstraVertex>(20,
                new DijkstraWeightComparator());

        visitedVertices = new HashSet<>();
        printVisitStatus();

        while (!(unvisitedVertices.isEmpty() && unvisitedVerticesWithWeight.isEmpty()) ) {
            
            System.out.println(" Visit " + current + " node.");

            
            
            Iterator<Integer> listIT = dijkstraVerticesSet[current].getList().iterator();
            
            System.out.println( current + " connected to ");
            while(listIT.hasNext()){
                System.out.print(listIT.next() + " ");
            }
            System.out.println();
            
            int currentWeight = dijkstraVerticesSet[current].getMinimalWeight();
            
            List<Integer> verticesConnectedToCurrent = dijkstraVerticesSet[current].getList();
            // visit each vertex and update the weight and the list as
            // necessary.

            Iterator<Integer> verticesIterator = verticesConnectedToCurrent.iterator();

            while (verticesIterator.hasNext()) {

                // for each vertex visit and update the weight as necessary.
                int nextVertex = verticesIterator.next();
                
                int currentAddedWeight = dijkstraVerticesSet[current].getWeightMap().get(nextVertex);
                currentWeight = currentWeight + currentAddedWeight;
                //CHANGED THIS: visitNode(dijkstraVerticesSet[current].getList(), currentWeight, nextVertex); 
                visitNode(dijkstraVerticesSet[current].getMinimalPath(), currentWeight, nextVertex);
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
        // check if the weight is minimum, if yes, update the list and the
        // minimal weight.
        
        System.out.println("Examine node " + nodeToBeVisited);
        if (currWeight < dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight()) {
            
            System.out.println("Current Weight  " + currWeight + " less than minimal weight " + dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight() + "for current node. Reassign Weight.");

            if (dijkstraVerticesSet[nodeToBeVisited].getMinimalWeight() == DkstraVertex.INFINITY) {
                System.out.println("Visiting " + nodeToBeVisited + " for the first time. Move to visited with weight.");
                unvisitedVertices.remove(dijkstraVerticesSet[nodeToBeVisited]);
                unvisitedVerticesWithWeight.add(dijkstraVerticesSet[nodeToBeVisited]);
            }

            dijkstraVerticesSet[nodeToBeVisited].setMinimalWeight(currWeight);
            
            ArrayList<Integer> perpareNewList = new ArrayList<Integer>();
            perpareNewList.addAll(list);
            perpareNewList.add(nodeToBeVisited);
            
            dijkstraVerticesSet[nodeToBeVisited].setMinimalPath(new ArrayList<Integer>(perpareNewList));
            unvisitedVerticesWithWeight.remove(dijkstraVerticesSet[nodeToBeVisited]);
            unvisitedVerticesWithWeight.add(dijkstraVerticesSet[nodeToBeVisited]);
            
            // if we are updating the weight of the vertex, remove the vertex
            // from the
            // unvisited (infinite) graph list and add it to the
            // unvivited(finite) graph
            // list .
        }
        
        // remove thevertex from the not visited list and add it to the visited list. 
        
    }
    
    
    public void printVisitStatus() {
        
        // print unvisitedVertices 
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
