package graphBasicPractice;

import java.util.ArrayList;
import java.util.Iterator;

public class DepthFirstSearch {
    
    GraphVertex[] allVertices;
    private int time=1;
    private boolean[] isVertexASource;
    
    public DepthFirstSearch(GraphVertex[] allVertices){
        this.allVertices = allVertices;
        isVertexASource = new boolean[allVertices.length];
    }
    
    public void search() {
        
        for(int i=1;i<allVertices.length;i++){
            DFSForest forest = new DFSForest();
            if(allVertices[i].getColor()==GraphVertex.WHITE){
                isVertexASource[i]=true;
                System.out.println(i  +" is white. writing start time " + time);
                allVertices[i].setStartTime(time);
                time++;
                allVertices[i].setColor(GraphVertex.GRAY);
                System.out.println("Traversing " + i);
                dfs_search(i);
           }
            
            
        }
        System.out.println("DFS Complete.");
        displayTimes();
    }

    private void displayTimes() {
        for(int i=1;i<allVertices.length;i++){
            System.out.println("Vertex " + i + " . Start: " + allVertices[i].getStartTime() + ". End: " + allVertices[i].getEndTime() + " .");
        }
    }

    private void dfs_search(int i) {
        System.out.println("Execute dfs_search on " + i);
        // get the adjacent nodes of i
        ArrayList<Integer> adjList = allVertices[i].getList();
        Iterator<Integer> adjIterator = adjList.iterator();
        System.out.println("Now iterating over all list.");
        // execute all the adjacent nodes of i
        while(adjIterator.hasNext()){
            int nextNode = adjIterator.next();
            System.out.println("Next in list: " + nextNode);
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
        allVertices[i].setEndTime(time);
        time++;
    }

}
