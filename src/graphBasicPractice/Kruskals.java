package graphBasicPractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Kruskals {

    GraphVertex[] originalGraph;
    GraphVertex[] kruskalsCopy;
    HashSet<Integer> alreadyIncludedVertices;
    HashMap<Integer, HashSet<Integer>> forest;

    /**
     * Execute kruskals algorithm to return a minimum spanning tree for a
     * weighted graph.
     * 
     * 
     * Executing Kruskals:
     * 
     * A set of vertices of the original graph. Containing edges initially
     * alloted. We iterate from 1 to last.
     * 
     * A set of vertices of the kruskals graph.
     * 
     */
    public Kruskals(int vertexSize, GraphVertex[] originalGraph) {

        vertexSize = originalGraph.length;
        alreadyIncludedVertices = new HashSet<Integer>();

        this.originalGraph = originalGraph;
        kruskalsCopy = new GraphVertex[vertexSize];
        forest = new HashMap<Integer, HashSet<Integer>>();

        // the indices in array that contain vertices : 1 to N
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
        while (!isTreeSpanningNew()) {
            System.out.println();
            System.out.println("Tree is not spanning yet. Include next minimum edge.");

            GraphEdge minimumWeightEdge = getMinimumWeightEdge(weightMap);
            if (minimumWeightEdge == null)
                break;

            while (!isAValidEdge(minimumWeightEdge)) {

                System.out.println("Not adding next edge from "
                        + minimumWeightEdge.getInitialNodeId() + " to "
                        + minimumWeightEdge.getFinalNodeId() + " with weight "
                        + minimumWeightEdge.getWeight());

                if (weightMap.size() == 0) {
                    System.out
                            .println("We ran out of vertices on this graph. Spanning tree not possible on this graph. ");
                    break;
                }
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

            // TODO: Do we need to maintain a copy of included vertices?
            alreadyIncludedVertices.add(minimumWeightEdge.getInitialNodeId());
            alreadyIncludedVertices.add(minimumWeightEdge.getFinalNodeId());
            System.out.println("");
        }

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

        if (treeForVertex1 == treeForVertex2)
            return false;
        else
            return true;
    }

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
