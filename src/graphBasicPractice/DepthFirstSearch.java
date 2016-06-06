package graphBasicPractice;

import java.util.ArrayList;
import java.util.Iterator;

/*
DFS is a graph search algorithm. 
Review the following before going ahead :

https://en.wikipedia.org/wiki/Depth-first_search   (Introduction Part)
https://en.wikipedia.org/wiki/Depth-first_search#Output_of_a_depth-first_search
https://en.wikipedia.org/wiki/Depth-first_search#Pseudocode

*/
public class DepthFirstSearch {
    
    //===========================================
	//
	// For simplicity and readability, we divide the
	// implementation into various classes. This is
	// one of the most important concepts of Object 
	// Oriented Programming, one of the key areas of 
	// testing in tech interviews. Interviewer 
	// usually observes :
	// -> the number of classes structured for the situation
	// -> Which variables and methods are placed in a particular class 
	// -> the access level of variables/methods (public/private etc.)
	// -> relationships between classes. 
	//
	// The key concept that should be kept in
	// mind while designing the classes is that
	// they should (as nearly as possible) mimic
	// the real world concept. For example, while
	// creating the graphVertex class, we use variables
	// color, startTime, endTime which are all 
	// information associated with a Graph Vertex.
	// Some simple methods include setBranchType etc. 
	// A class contains of information (represented 
	// as variables) and operations (represented as 
	// methods).
	//
	// Proper naming is also a very important and 
	// fundamental concept in OOP. IT substantially
	// enhances readability and make code more maintainable.
	// (think of a situation where some other person
	// is adding more code to make your repo better? )
	
	//===========================================
	// We use an array of graphVertex to represent 
	// a graph because of the simple reason that 
	// a graph is simply a collection of vertices. 
	//
	// And, as far as DFS is concerned all the vertices
	// have certain properties, most important of which
	// is the edges a certain vertex is connected to. 
	//
	// Note that in our implementation, the GraphVertex
	// "extends" a Vertex which contains a list of edges
	// as one of the variables in its class. The concept of 
	// extension is also a fundamental one in OOP and 
	// interviewers frequently grill candidates on this 
	// concept called inheritance. A class which "extends"
	// a certain class inherits (which means already has)
	// the variables and methods of the class it is entending. 
	// Thus, note that, even though we don't have a list 
	// of edges in GraphVertex class, we can access
	// the list (because it is present in Vertex from 
	// which the GraphVertex class inherits). 
    //===========================================
    GraphVertex[] allVertices;
    private int time=1;
    // stores info about a particular vertex
    // being a source. 
    private boolean[] isVertexASource;
    
    //===========================================
    // input the provided graph using constructor.
    //===========================================
    public DepthFirstSearch(GraphVertex[] allVertices){
        this.allVertices = allVertices;
        isVertexASource = new boolean[allVertices.length];
    }
    
    //===========================================
    // Algorithm uses (hypothetical) colors to distinguish 
    // nodes. Unvisited nodes are white, visited nodes 
    // are black, while "processing" nodes are gray.
    //
    // In accordance with the standard algorithm
    // described in Wikipedia and in cormen, we 
    // implement an exhaustive search, meaning, 
    // we search all the nodes (whether they 
    // equal to the item we are looking or not).
    // We do it this way because, for illustrative
    // purposes, we want to discover all the types
    // of edges. 
    //===========================================
    public void search() {
        
    	// execute dfs search over all vertices. Thus, also graphs with non-connected vertices. 
        for(int i=1;i<allVertices.length;i++){
            DFSForest forest = new DFSForest();
            // Usually the graph is connected 
            // which means that there will be only
            // one source vertex (any of all the vertices).
            //
            // But, for non-connected graphs: 
            // initially all the vertices are white
            // Now, when we first visit each vertex, all
            // the vertices connected to it (directly
            // or indirectly like somewhere down the 
            // line) are visited and so are painted
            // either black or gray. Since only
            // disconnected vertices are white, we 
            // are absolutely sure that we are 
            // visiting disconnected and distinct 
            // parts of the graph.  
            
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

    //===========================================
    // the process to be executed on each node.
    //===========================================
    private void dfs_search(int i) {
        System.out.println("Execute dfs_search on " + i);
        // get the list of nodes to which node i is connected.
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
                // color nodes that are being visited 
                // (but not yet completely visited) as
                // gray. This is in accordance with the 
                // steps of the algorithm. 
                allVertices[nextNode].setColor(GraphVertex.GRAY);
                dfs_search(nextNode);
                
            }
        }
        // when executed, set the final time for i and paint i black.
        System.out.println("Internal: done with " + i + ". Setting final time: " + time);
        // color the node whose all the vertices have been
        // visited as black, like being stated in the algorithm. 
        allVertices[i].setColor(GraphVertex.BLACK);
        // the time at which the algorithm arrives
        // at vertex for the last time. We record this. 
        allVertices[i].setEndTime(time);
        time++;
    }

}
