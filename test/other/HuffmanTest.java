package other;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

public class HuffmanTest {
    
    @Test
    public void shouldGenerateHuffmanEncoding() {
        
        // genrate a random character array 
        
        
        TreeMap<Character,Integer> map = new TreeMap<>();
        
        map.put('a', 3);map.put('b', 3);map.put('c', 3);
        
        
        
        int charArraySize = 70;
        
        char[] charArray = new char[charArraySize];
        
        Random r = new Random();
        
        for(int i=0;i<charArraySize;i++) {
        charArray[i]= (char)(97 + r.nextInt(26));    
        }
        
        HuffmanEncoding encoding = new HuffmanEncoding();
        
        System.out.println(Arrays.toString(charArray));
        List<String> output = encoding.encode(charArray);
        for(String s : output){
            System.out.println(s);
        }
        
        
    }

}
