package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphVertex {

    private int color, startTime, endTime, id, branchType, data;
    private ArrayList<Integer> list;
    private HashMap<Integer,Integer> weightMap;
    public static final int WHITE = 0, GRAY = 1, BLACK = 2;
    public static final int TREE = 0, FORWARD = 1, BACK = 2, CROSS = 3;

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
    
    protected HashMap<Integer,Integer> getWeightMap() {
        return weightMap;
    }
    
    protected boolean isListEmpty() {
        if (list.isEmpty())
            return true;
        else
            return false;
    }
    
    protected int getData() {
        return data;
    }
    
    protected void setData(int data) {
        this.data = data;
    }

    protected int getBranchType() {
        return branchType;
    }

    protected void setBranchType(int branchType) {
        this.branchType = branchType;
    }

    protected ArrayList<Integer> getList() {
        return list;
    }

    protected void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
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

    protected void addVertex(int newVertexId) {
        list.add(newVertexId);
    }

}
