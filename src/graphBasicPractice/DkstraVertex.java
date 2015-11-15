package graphBasicPractice;

import java.util.List;



public class DkstraVertex extends GraphVertex{

    public static final int VISITED=0, NOT_VISITED=1;
    protected int visitStatus;
    protected int minimalWeight;
    public static final int INFINITY=100000000;
    
    public DkstraVertex(int nodeID, int data){
        super(nodeID,data);
        minimalWeight=INFINITY;
    }
   
    protected int getMinimalWeight() {
        return minimalWeight;
    }

    protected void setMinimalWeight(int minimalWeight) {
        this.minimalWeight = minimalWeight;
    }

    protected List<Integer> getMinimalPath() {
        return minimalPath;
    }

    protected void setMinimalPath(List<Integer> minimalPath) {
        this.minimalPath = minimalPath;
    }

    protected List<Integer> minimalPath;
    
    public void setVisitStatus(int visitStatus) {
        this.visitStatus=visitStatus;
    }
    
    
    
}
