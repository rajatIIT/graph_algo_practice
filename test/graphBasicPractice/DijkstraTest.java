package graphBasicPractice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Test;

public class DijkstraTest {
    
    
    
    @Test
    public void shouldFindAtLeastOneShortestPath() {
        
        
        
        DkstraVertex[] randomGraph = GraphUtils.createRandomDijkstraGraph(8, 7, false);
        
        
        // test dijkstra vertex casting.
        
        
        
        Dijkstra dijkstraAlgorithm = new Dijkstra();
        
        int graphSize = randomGraph.length;
        Random random = new Random();
        
        
        
        for(int i=1;i<randomGraph.length;i++){
            randomGraph[i].setId(i);
        }
        
      List<Integer> shortestPath =  dijkstraAlgorithm.getShortestPath(random.nextInt(graphSize-1) + 1,random.nextInt(graphSize-1) + 1, randomGraph);
      
      System.out.println("Shortest Path: ");
      Iterator<Integer> shpIT = shortestPath.iterator();
      while(shpIT.hasNext()){
          System.out.print(shpIT.next() + " -> ");
      }
      
      
        
    }
    
    @Test
    public void priorityQueueShouldWork() {
        
        PriorityQueue<DkstraVertex> pkdvx = new PriorityQueue<>(20, new DijkstraWeightComparator());
        
        DkstraVertex dd = new DkstraVertex(1, 2);
        dd.setMinimalWeight(23);
        
        DkstraVertex dd1 = new DkstraVertex(3, 4);
        dd1.setMinimalWeight(16);
        
        DkstraVertex dd2 = new DkstraVertex(3, 4);
        dd2.setMinimalWeight(35);
        
        DkstraVertex dd3 = new DkstraVertex(3, 4);
        dd3.setMinimalWeight(26);
        
        pkdvx.add(dd);
        pkdvx.add(dd1);pkdvx.add(dd2);pkdvx.add(dd3);
        
        System.out.println(pkdvx.poll().getMinimalWeight());
    }

}
