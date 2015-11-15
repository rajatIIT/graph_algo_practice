package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;

public class Vertex {
    
    
    protected int id, data;
    protected ArrayList<Integer> list;
    
    protected ArrayList<Integer> getList() {
        return list;
    }
    
    protected HashMap<Integer,Integer> weightMap;
    protected HashMap<Integer,Integer> getWeightMap() {
        return weightMap;
    }
    
    protected void addVertex(int newVertexId) {
        list.add(newVertexId);
    }

    protected void setList(ArrayList<Integer> list) {
        this.list = list;
    }
    
    protected boolean isListEmpty() {
        if (list.isEmpty())
            return true;
        else
            return false;
    }
    
    public Vertex() {
    }
    
    public Vertex(int id, int data) {
        this.id=id;
        this.data=data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
    
    public int getData() {
        return data;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
