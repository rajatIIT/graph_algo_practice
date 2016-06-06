package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import org.omg.PortableServer.RequestProcessingPolicyValue;

public class HuffmanEncoding {

    public HuffmanEncoding() {

    }

    public List<String> encode(char[] input) {

        // BUILD THE FREQUENCY MAP
        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();

        for (char each : input) {
            if (!frequency.containsKey(each)) {
                frequency.put(each, 1);
            } else {
                frequency.put(each, frequency.get(each) + 1);
            }

        }
        
    //    System.out.println(frequency.toString()); 

        // now build a treeSet using this map.

        PriorityQueue<HuffmanNode> elementSet = new PriorityQueue<HuffmanNode>(new NodeComparator());

        Iterator<Character> frequencyIterator = frequency.keySet().iterator();

        while (frequencyIterator.hasNext()) {
            
            char nextElement = frequencyIterator.next();
            int freqValue = frequency.get(nextElement);
      //      System.out.println("Add to eleset: " + nextElement + " , " + freqValue + ".");
            elementSet.add(new HuffmanNode("" + nextElement, freqValue));
        //    System.out.println(elementSet.toString());
        }

        
        
        // now Iteratively construct the Tree.
        while (!(elementSet.size() == 1)) {

            // POP THE TWO MINIMUM ELEMENTS
      //      System.out.println("Set Size: " + elementSet.size());

            HuffmanNode small1 = elementSet.poll();
            HuffmanNode small2 = elementSet.poll();
            
          //  System.out.println("pop: (" + small1.getChar() + small1.getFrequency() + "), (" + small2.getChar() + small2.getFrequency() + ") ");

            int newFrequency = small1.getFrequency() + small2.getFrequency();
            String newC = small1.getChar() + small2.getChar();

            HuffmanNode newNode = new HuffmanNode(newC, newFrequency, small1, small2);
            elementSet.add(newNode);

        }

        String s = "";
        return buildList(new ArrayList<String>(), s, elementSet.poll());

    }

    public List<String> buildList(List<String> currList, String currStr, HuffmanNode currNode) {

        if (currNode.left == null && currNode.right == null) {
            currList.add(currStr +" : " + currNode.getChar());
        } else {

            // update list with left tree
            if (currNode.left != null)
                buildList(currList, currStr + "1", currNode.left);
            // update list with right tree
            if (currNode.right != null)
                buildList(currList, currStr + "0", currNode.right);
        }

        return currList;
    }
}
