package other; 

import java.util.Comparator;



public class NodeComparator implements Comparator<HuffmanNode>{

public int compare (HuffmanNode o1, HuffmanNode o2) {
    if(o1.getFrequency()>o2.getFrequency())
    return 1;
    else if (o1.getFrequency()<o2.getFrequency())
    return -1;
    else 
    return 0;
}



} 