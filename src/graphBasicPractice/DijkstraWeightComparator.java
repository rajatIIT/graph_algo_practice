package graphBasicPractice;

import java.util.Comparator;

//Comparator is a classic concept in Java. 
//It is an interface using which we can implement
//our own comparator logic over some class.
//It compares two instances of the class
//and returns 1,0 or -1 based on whether 
//first is greater than, equal to or less than 
//second. These comparators can then
//be used to sort lists of instances of the class.
//Read more here : 
//https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html
//
//For example, we can implement comparator over 
//DkstraVertex which can be configured to compare
//over minimalWeight, data, length of path etc... 
 
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
