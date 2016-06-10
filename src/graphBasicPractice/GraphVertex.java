package graphBasicPractice;

import java.util.ArrayList;
import java.util.HashMap;

// Class for a graph vertex.
// Notice how we do the naming 
// for readability (just served the 
// purpose!). 
// Also, notice the use of Integer constants.
// We don't use string variables because 
// compiler is faster at handling integers.
// On of the most critical tasks of a software 
// engineer is to make code fast (others are to 
// make code robust and maintainable). It is 
// simple to see how much we hate slow softwares 
// (remember any slow/buggy app?).
//
// Making the code fast typically involves 
// taking care of the following things: 
// 1) Making the right design decisions (data structures)
// 2) not re-implementing standard data structures provided
// by the language (unless you know what you are doing) 
// 3) reading the documentation (in case of Java, there
// are notes on performance in the documentation)
// 4) Minimalistic design : Remember code executes on the 
// processor and the more cycles it takes to execute a code
// the higher the response time will be. Hence, try to minimize
// the processing power use as much as possible.(Unless, the result
// is faster by using more processing power).
// 5) Remember that reading and writing to main memory is extremly
// fast, as compared to the secondary memory. Take care and 
// make sensible decisions! 
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
