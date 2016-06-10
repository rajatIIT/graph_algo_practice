package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;

// represents a graph vertex. 
// notice how we use minimalistic 
// design here. 
//
// One of the (very) crucial aspects
// of Object Oriented Programming 
// is to design in a way as close to real
// world as possible. Notice how we declare
// only three variables which could uniquely 
// describe a vertex in the graph: 
// The id, the data and the list of 
// vertices to which it is connected.
//
// Also notice how we designed the methods.
// The number of methods and the simplicity 
// of implementation is very important. 
// it should closely describe the real world 
// procedure associated with the method.
//
// Also, notice the use of access modifiers:
// public, private and protected. A trick to 
// remember is simple : public means all, 
// private means (really private!) only to 
// the current class, no modifier means 
// access to (the) package of the class 
// (and not to subclass),
// and protected means access to package 
// (of the class) and also to subclasses.
// 
// We use protected for variables we 
// expect will be used in the subclasses.
// Note that DkstraVertex is a subclass 
// and they are used there! Also, note that 
// we keep the methods public because 
// we want users to access methods (and
// not variables!) Why ? Because, we want to 
// provide the flexibility of implementation. 
// What if a situation arises where we want
// only the positive data? We can implement 
// the check in the method. 
// 
// trick to remember (notice patterns of 
// Ys and Ns, taken from oracle official 
// docs):

//Modifier		Class	Package	Subclass	World
//public		Y		Y		Y			Y
//protected		Y		Y		Y			N
//no modifier	Y		Y		N			N
//private		Y		N		N			N

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
