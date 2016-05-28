package graphBasicPractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author rajatpawar
 *
 */
public class Kruskals {

	
    GraphVertex[] originalGraph;
	// Create an empty copy of output tree. 
    GraphVertex[] kruskalsCopy;
    //===============================================
  	//  Create a data structure to keep track of the vertices we have already included. 
    //  Note that we use Hashmap because it provides O(1) lookup performance.
  	//=============================================== 
    HashSet<Integer> alreadyIncludedVertices;
    //===============================================
  	//  Forest, as it is called in the kruskals' parlance, 
    // 	basically represents an "incomplete tree".
  	//===============================================
    HashMap<Integer, HashSet<Integer>> forest;
    
    

    /*
      ================================================================
      The kruskal's algorithm is a weighted "graph" algorithm. Make sure you have 
      familiarity with the concept of graphs: weights, edges and vertices.
      A simple example is here: .   
      
      A spanning tree is a (sub) tree of a graph whose vertices set contains
      vertices of the entire graph. Note, though, that the same may not be 
      true for edges of the graph. 
      
      There can be multiple spanning trees for a weighted graph. The idea
      is to compute a spanning tree such that the sum of the weights of 
      the edges of spanning tree is minimum (of all the possible trees).
      
      Kruskal's algorithm Implementation: A well known algorithm to compute
      a minimum spanning tree for a weighted graph.
      
      Graph may contain a lot of edges between various vertices. However, we want 
      to include as less edges in the tree as possible. Graph is usually dense
      and tree is usually sparse. 
      
      Input is a graph, and output should be a tree. We start by creating an 
      empty tree, then start including edges from input graph into tree 
      one by one, starting with the edge with minimum weight (marking it as included,
      then including the minimum edge from the remaining set and so on). We perform 
      this process iteratively until we have a tree which can be used to "reach" every 
      vertex of the graph. 
      
      Per wikipedia, steps: 
      1) create a forest F (a set of trees), where each vertex in the graph is a separate tree
      2) create a set S containing all the edges in the graph
	  3) while S is nonempty and F is not yet spanning
             ->remove an edge with minimum weight from S
			 ->if the removed edge connects two different trees then add it to the forest F, combining two trees into a single tree
	  4) At the termination of the algorithm, the forest forms a minimum spanning forest of the graph. If the graph is connected, the forest has a single component and forms a minimum spanning tree.
      
      REFERRED CONCEPTS: TREE, GRAPH, TIME COMPLEXITY
      ================================================================
      */
     
    public Kruskals(int vertexSize, GraphVertex[] originalGraph) {

    	
    	// we got a weighted graph. we need to compute a minimum spanning tree. 
        vertexSize = originalGraph.length;
        alreadyIncludedVertices = new HashSet<Integer>();

        //===============================================
    	  /* 
          Create a copy of the input graph.
          Why Array to represent a graph ? 
          Because we need random access on the graph data structure (random access takes O(1) time on array)
          It takes O(1) time to access a list of edges a graph is attached to.
           
          Each vertex is referenced in one index of the graph. And,
          every vertex contains a list of edges it is attached to.
          Refer to GraphVertex class to see the contents of GraphVertex. 
          */ 
    	//===============================================
        // 2) create a set S containing all the edges in the graph
        this.originalGraph = originalGraph;
        
        
        //===============================================
  	    /* 
        Create a copy of the input graph which excludes edges. 
        We plan to include edges which form a part of the minimum spanning tree as 
        algorithm progresses, so that this copy will finally contain the required tree. 
        
        Read the reason above about why we use array to represent a graph.
        */ 
  	   //===============================================
        
        kruskalsCopy = new GraphVertex[vertexSize];
        
		//===============================================
  	    // 1) create a forest F (a set of trees), where each vertex in the graph is a separate tree.
        //=============================================== 
        
        forest = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 1; i < vertexSize; i++) {

            kruskalsCopy[i] = new GraphVertex(originalGraph[i].getId(), originalGraph[i].getData());
            HashSet<Integer> tempSet = new HashSet<Integer>();
            tempSet.add(i);
            forest.put(i, tempSet);
            // this vertex currently has no connections.
        }
        // FOREST BUILT.

        System.out.println("Total number of vertices in graph are " + (kruskalsCopy.length - 1));

        printGraph();
        
      //===============================================
  	    /* 
        WeightMap : Data Structure used to retrieve the next minimum edge from 
        the graph. It retrieves and removes. Thus, the weightmap contains
        "remaining" edges. 
        
        Why TreeMap? The primary use of Data Structure is to retrieve the minimum
        edge. TreeMap does this in O(1)[constant] time.TreeMap is a unique Data Structure
        which is a combination of Binary Tree and a HashMap. 
        */ 
  	   //===============================================
        TreeMap<Integer, List<GraphEdge>> weightMap = new TreeMap<Integer, List<GraphEdge>>(
                new WeightComparator());

        // Use graph to create a list of Edges O(|E|) )
        for (int i = 1; i < vertexSize; i++) {

            // list of the edges to which this node is connected.
            LinkedList<Integer> completeList = new LinkedList<Integer>(originalGraph[i].getList());
            Iterator<Integer> completListIt = completeList.iterator();

            while (completListIt.hasNext()) {
                int nextNodeId = completListIt.next();
                if (nextNodeId > i) {
                    // check if this weight value exists in the hashmap.
                    int weightForThisEdge = originalGraph[i].getWeightMap().get(nextNodeId);
                    // if it exists, append the list creating a new GraphEdge,

                    if (weightMap.containsKey(weightForThisEdge)) {
                        List<GraphEdge> listForThisWeight = weightMap.get(weightForThisEdge);
                        listForThisWeight.add(new GraphEdge(i, nextNodeId, weightForThisEdge));
                    } else {

                        // if not, create a new entry with this weight that
                        // contains one edge: this one.
                        LinkedList<GraphEdge> temp = (new LinkedList<GraphEdge>());
                        temp.add(new GraphEdge(i, nextNodeId, weightForThisEdge));
                        weightMap.put(weightForThisEdge, temp);
                    }
                }
            }

            if (weightMap.isEmpty()) {
                System.out.println("Unable to construct a spanning tree!");
                break;
            }

        }

        // the weightSet now contains all the weights done with constructing the
        // edgeSet, now we try to construct the spanning tree.

        System.out.println("---------------------------------------");
        System.out.println("BUILDING SPANNING TREE");
        System.out.println("---------------------------------------");
        
        //===============================================
  	    // 3) while S is nonempty and F is not yet spanning
        //===============================================
        while (!isTreeSpanningNew()) {
            System.out.println();
            System.out.println("Tree is not spanning yet. Include next minimum edge.");

            GraphEdge minimumWeightEdge = getMinimumWeightEdge(weightMap);
            if (minimumWeightEdge == null)
                break;
          //===============================================
      	    /* 
            Concept of valid edge : Consider three vertices A, B and C in
            the graph. Suppose, while in the process of forming the spanning tree, 
            we are at a step where we already have the following edges : 
            1) connecting A and B.
            2) connecting B and C. 
            
            It does not make any sense to include the edge connecting A and C
            because it does not bring any new vertex to the tree (and only adds
            weight to the graph which is something we don't want).
            
            A valid edge is one which adds new vertices to the graph. 
            */ 
      	   //===============================================
            
            // -> if the removed edge connects two different trees then add it to the forest F, combining two trees into a single tree
            while (!isAValidEdge(minimumWeightEdge)) {

                System.out.println("Not adding next edge from "
                        + minimumWeightEdge.getInitialNodeId() + " to "
                        + minimumWeightEdge.getFinalNodeId() + " with weight "
                        + minimumWeightEdge.getWeight());
                
                //===============================================
          	    /* 
                We quit if the graph is way too sparse. Like,
                100 vertices and only 5 edges. (Yes, such graphs are possible and do exist)
                It is clear that no spanning tree is possible.
                */ 
          	   //===============================================

                if (weightMap.size() == 0) {
                    System.out
                            .println("We ran out of vertices on this graph. Spanning tree not possible on this graph. ");
                    break;
                }
                
                // -> remove an edge with minimum weight from S
                minimumWeightEdge = getMinimumWeightEdge(weightMap);
            }

            // NOW WE ARE INCLUDING A NEW EDGE INTO THE FOREST
            System.out.println("Including vertex that connects vertex "
                    + minimumWeightEdge.getInitialNodeId() + " and "
                    + minimumWeightEdge.getFinalNodeId() + " using the weight "
                    + minimumWeightEdge.getWeight());

            // select the edge with minimum weight and add it to the kruskal's
            // copy
            kruskalsCopy[minimumWeightEdge.getInitialNodeId()].addVertex(minimumWeightEdge
                    .getFinalNodeId());

            // UPDATE THIS EDGE IN THE FOREST.
            merge(treeNo1, treeNo2);

            
            alreadyIncludedVertices.add(minimumWeightEdge.getInitialNodeId());
            alreadyIncludedVertices.add(minimumWeightEdge.getFinalNodeId());
            System.out.println("");
        }

        // 4) At the termination of the algorithm, the forest forms a minimum spanning forest of the graph. If the graph is connected, the forest has a single component and forms a minimum spanning tree.
        if (isTreeSpanningNew()) {
            System.out.println("---------------------------------------");
            System.out.println("A minimum spanning tree is generated: ");
            System.out.println("---------------------------------------");
            printGraph();
        }
    }

    private void merge(int firstTree, int secondTree) {
        // MERGE THESE TWO TREES IN THE FOREST
        forest.get(firstTree).addAll(forest.get(secondTree));
        forest.remove(secondTree);
    }

    public boolean isTreeSpanningNew() {
        System.out.println("Size of Forest: " + forest.size());
        if (forest.size() == 1)
            return true;
        else
            return false;
    }

    public void printGraph() {
        // runs in O(|V|*|E|)

        boolean[] alreadyPrinted = new boolean[kruskalsCopy.length];
        for (boolean eachEntry : alreadyPrinted)
            eachEntry = false;

        for (int i = 1; i < kruskalsCopy.length; i++) {
            alreadyPrinted[i] = true;
            System.out.println();

            Iterator<Integer> currentListIt = kruskalsCopy[i].getList().iterator();

            if (currentListIt.hasNext()) {
                System.out.println("Vertex " + i + " is connected to ");
                while (currentListIt.hasNext()) {
                    int nextVertex = currentListIt.next();
                    if (!alreadyPrinted[nextVertex])
                        System.out.println(nextVertex + " ");
                }
            }
        }

    }

    Integer treeForVertex1, treeForVertex2;
    int treeNo1, treeNo2;

    //
    /*
    Valid Edge check : if the removed edge connects two different trees then add it to the forest F, combining two trees into a single tree
    */
    //
    public boolean isAValidEdge(GraphEdge edge) {

        // runs in O(number of forests) <= O(number of edges) = O(|E|)
        // if two vertices lie in different trees, this is a valid edge.

        int init = edge.getInitialNodeId();
        int fnl = edge.getFinalNodeId();
        int wt = edge.getWeight();

        System.out.println("Checking if (" + init + "," + fnl + ") : " + wt + " is good.");

        Iterator<Integer> forestIterator = forest.keySet().iterator();
        while (forestIterator.hasNext()) {

            Integer nextKey = forestIterator.next();
            if (forest.get(nextKey).contains(init)) {
                treeForVertex1 = nextKey;
                treeNo1 = treeForVertex1;
            }
            if (forest.get(nextKey).contains(fnl)) {
                treeForVertex2 = nextKey;
                treeNo2 = treeForVertex2;
            }
        }
        
        // if the removed edge connects two different trees
        if (treeForVertex1 == treeForVertex2)
            return false;
        else
            return true;
    }

    //===============================================
    //  remove an edge with minimum weight from the set of minimum edges
   //===============================================
    public GraphEdge getMinimumWeightEdge(TreeMap<Integer, List<GraphEdge>> weightMap) {

        // O(1)
        if (weightMap.size() == 0)
            return null;

        int minimumWeight = weightMap.firstKey();

        GraphEdge minimumWeightEdge;

        if (weightMap.get(minimumWeight).size() == 1) {

            minimumWeightEdge = weightMap.get(minimumWeight).get(0);
            weightMap.remove(minimumWeight);

        } else {

            // there are more than one elements for this weight.
            minimumWeightEdge = weightMap.get(minimumWeight).get(0);
            weightMap.get(minimumWeight).remove(0);
        }

        return minimumWeightEdge;
    }

}
