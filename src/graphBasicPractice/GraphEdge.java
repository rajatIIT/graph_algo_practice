package graphBasicPractice;

// Represents a graph Edge
// Notice how the concept of graph 
// edge principally relies on 
// the concept of a vertex.
// 
// we define an edge using the 
// two vertices which it connects 
// and the weight of the edge.
public class GraphEdge {
    
    private int initialNodeId,finalNodeId,weight;
    
    protected int getInitialNodeId() {
        return initialNodeId;
    }

    protected int getFinalNodeId() {
        return finalNodeId;
    }

    protected int getWeight() {
        return weight;
    }

    public GraphEdge(int initialNode,int finalNode, int wt){
        this.initialNodeId = initialNode;
        this.finalNodeId = finalNode;
        this.weight = wt;
    }
    
    

}
