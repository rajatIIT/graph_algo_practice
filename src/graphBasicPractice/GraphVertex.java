package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphVertex extends Vertex{

    private int color, startTime, endTime, branchType;
    public static final int WHITE = 0, GRAY = 1, BLACK = 2;

    public GraphVertex() {
        list = new ArrayList<Integer>();
        this.color = this.WHITE;
        this.startTime=0;
        this.endTime=0;
        weightMap = new HashMap<Integer,Integer>();
    }
    
    public GraphVertex(int nodeID, int data) {
        this();
        this.id = nodeID;
        this.data = data;
    }

    protected int getBranchType() {
        return branchType;
    }

    protected void setBranchType(int branchType) {
        this.branchType = branchType;
    }


    protected int getColor() {
        return color;
    }

    protected void setColor(int color) {
        this.color = color;
    }

    protected int getStartTime() {
        return startTime;
    }

    protected void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    protected int getEndTime() {
        return endTime;
    }

    protected void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
