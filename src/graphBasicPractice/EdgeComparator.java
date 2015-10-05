package graphBasicPractice;

import java.util.Comparator;

public class EdgeComparator implements Comparator<GraphEdge> {

    @Override
    public int compare(GraphEdge o1, GraphEdge o2) {
        if (o1.getWeight() > o2.getWeight())
            return 1;
        else if (o1.getWeight() < o2.getWeight())
            return -1;
        else
            return 0;
    }

}
