package graphBasicPractice;

import java.util.ArrayList;
import java.util.Iterator;

/*
DFS is a graph search algorithm. Review concept of graph and the DFS search algorithm before this. Using wikipedia or otherwise.
*/
public class DepthFirstSearch {
    
	// Use array to represent a graph cause we work by node IDs which uses random access which is O(1) for an array. 
    GraphVertex[] allVertices;
    private int time=1;
    private boolean[] isVertexASource;
    
    // input the provided graph using constructor.
    public DepthFirstSearch(GraphVertex[] allVertices){
        this.allVertices = allVertices;
        isVertexASource = new boolean[allVertices.length];
    }
    
    // Algorithm uses (hypothetical) colors to distinguish nodes.
    // Unvisited nodes are white, visited nodes are black, while "processing" nodes are gray. 
    public void search() {
        
    	// execute dfs search over all vertices. Thus, also graphs with non-connected vertices. 
        for(int i=1;i<allVertices.length;i++){
            DFSForest forest = new DFSForest();
            if(allVertices[i].getColor()==GraphVertex.WHITE){
            	// A source of traversing if called from inside this loop (and not from method dfs_search)
                isVertexASource[i]=true;
                // the time at which the algorithm first arrives at vertex (time is number of steps). We record this. 
                System.out.println(i  +" is white. writing start time " + time);
                allVertices[i].setStartTime(time);
                time++;
                // set gray since we will now visit the vertices connected to this vertex (dfs_search essentially does this). 
                // after all are visited, we paint the node black.
                allVertices[i].setColor(GraphVertex.GRAY);
                System.out.println("Traversing " + i);
                dfs_search(i);
           }
        }
        
        // after all the nodes have been colored black. 
        System.out.println("DFS Complete.");
        // simple utility to show the times of various vertices. 
        displayTimes();
    }

    private void displayTimes() {
        for(int i=1;i<allVertices.length;i++){
            System.out.println("Vertex " + i + " . Start: " + allVertices[i].getStartTime() + ". End: " + allVertices[i].getEndTime() + " .");
        }
    }

    // the process to be executed on each node.
    private void dfs_search(int i) {
        System.out.println("Execute dfs_search on " + i);
        // get the adjacent nodes of i
        // allVertices[i] refers to the iTH node. 
        ArrayList<Integer> adjList = allVertices[i].getList();
        // iterate over all the nodes connected to this node.
        Iterator<Integer> adjIterator = adjList.iterator();
        System.out.println("Now iterating over all list.");
        // execute search on all the adjacent nodes of i
        while(adjIterator.hasNext()){
            int nextNode = adjIterator.next();
            System.out.println("Next in list: " + nextNode);
            // if any of the adjacent nodes is white, "visit" it. 
            if(allVertices[nextNode].getColor()==GraphVertex.WHITE){
                System.out.println(nextNode  +" is white. writing start time " + time);
                allVertices[nextNode].setStartTime(time);
                time++;
                allVertices[nextNode].setColor(GraphVertex.GRAY);
                dfs_search(nextNode);
                
            }
        }
        // when executed, set the final time for i and paint i black.
        System.out.println("Internal: done with " + i + ". Setting final time: " + time);
        allVertices[i].setColor(GraphVertex.BLACK);
        // the time at which the algorithm arrives at vertex for the last time. We record this. 
        allVertices[i].setEndTime(time);
        time++;
    }

}
