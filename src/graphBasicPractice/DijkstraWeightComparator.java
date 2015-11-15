package graphBasicPractice;

import java.util.Comparator;

public class DijkstraWeightComparator implements Comparator<DkstraVertex> {
    
    @Override
    public int compare(DkstraVertex o1, DkstraVertex o2) {
        if (o1.getMinimalWeight() > o2.getMinimalWeight())
            return 1;
        else if (o1.getMinimalWeight() < o2.getMinimalWeight())
            return -1;
        else
            return 0;
    }

}
