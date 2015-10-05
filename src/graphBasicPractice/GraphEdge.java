package graphBasicPractice;

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
