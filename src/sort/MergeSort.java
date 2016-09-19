package sort;

import java.util.Arrays;

//
// Popular, efficient, useful
// and common sorting algorithm.
// 
// The input array is divided 
// into sublists and which are
// individually sorted (using 
// mergesort, again!), and 
// after they are sorted,
// we finally merge them. The result
// is sorted.


// the way we have implemented it 
// here is to divide the input 
// list (the one to be sorted)
// into two parts, sort the parts
// individually, and then merge
// the sorted parts. 


 

public class MergeSort {

    public MergeSort() {

    }

    public void sort(int[] input) {
    	// Uses Recursion : one of the most
    	// important the defining characteristics
    	// of Divide and Conquer- The concept
    	// behind the MergeSort algorithm.
        mergeSort(input, 0, input.length - 1);
        System.out.println("After Sort: " + Arrays.toString(input));
    }

    // The recursive procedure mergeSort sorts the input 
    // array from start to end. 
    //
    // Note that almost all instances of mergeSort
    // (different instances are defined by different
    // start and end points) use results from other
    // instances of mergeSort (which are defined by
    // different indices).
    public void mergeSort(int[] input, int start, int end) {

    	
        if (start == end) {
            // do nothing
        	// because array is trivially sorted
        	// from i to i.
        } else if (end == start + 1) {
            // swap if not in the correct order.
        	// this is called the "base case" of recursion.
        	// Simple but important to get for most
        	// recursive procedures.
            if (input[start] > input[end])
                SortUtils.swap(input, start, end);
        } else if (end == start + 2) {

            // size is 3
        	// call mergeSort on two small index
        	// ranges of size 2. In both calls,
        	// the case above of size 2 is 
        	// applicable.
        	mergeSort(input, start, start + 1);
            mergeSort(input, start + 2, start + 2);
            if (input[start + 2] < input[start]) {
                SortUtils.swap(input, start + 1, start + 2);
                SortUtils.swap(input, start, start + 1);
            } else if (input[start] <= input[start + 2] && input[start + 2] <= input[start + 1]) {
                SortUtils.swap(input, start + 1, start + 2);
            }

        } else {
            System.out.println(Arrays.toString(input));
            
            // first mergeSort the middle ones
        	// compute the element on which 
        	// we will divide the input.
        	int middle = (end - start) / 2;
        	
        	// sort the first part of the list
            System.out.println("Merge (" + start + "," + (start + middle) + ") and ("
                    + (start + middle + 1) + "," + end + ")");
            mergeSort(input, start, start + middle);
            
            // sort the second part of the list 
            System.out.println("After first merge: " + Arrays.toString(input));
            mergeSort(input, start + middle + 1, end);
            
            System.out.println("After second merge: " + Arrays.toString(input));

            // note that while merging we need a separate
            // output array. However, in certain situations
            // (in most of them as storage devices are far
            // less cheaper these days as compared to 
            // processing power) when lots of storage 
            // is available, mergeSort can be extremely 
            // useful. 
            //
            // There is also External Merge Sort, which
            // sorts data on secondary memory (disk drives,
            // as it is called in common language).
            //
            // In case you are not aware, the arrays 
            // and variables that we use in these programs 
            // are created and stored in primary memory. 
            // This is a very important concept to understand
            // and use if you are preparing for tech/coding
            // interviews. Compiler/Interpreter is a 
            // program/application which runs on the 
            // top of Operating System. Both compiler 
            // and the Operating Systems are basically 
            // programs and we will get to that in a 
            // little while. Basically it is this 
            // compiler that understands the special
            // keywords (for, while etc..) and takes
            // the necessary actions. It is "programmed"
            // to understand all this. Operating
            // system is the last level of software 
            // which basically runs on the top of the 
            // physical hardware that we see in real life. 
            
            
            // Primary memory (also known as RAM)
            // is usually substantially less in amount as compared
            // to hard drives (secondary) because it is very expensive. 
            // Consequently, its fast, too. Also, primary loses 
            // the data when disconnected from power but
            // secondary retains it. Like I explained earlier, 
            // don't forget that primary memory is extremely
            // fast. Like, they can achieve speeds to the north
            // of 17000 mbps while the typical hard drive 
            // is more like 100-200 mbps.
            
          
            
            int[] mergedOutput = new int[end - start + 1];
            int mergeOutPointer = 0;

            int pointer1 = start;
            int pointer2 = start + middle + 1;

 
            // Merging process begins here :
            // we assume that the two lists 
            // that were partitioned earlier
            // are sorted. Now we initiate
            // two pointers which are basically
            // indices which we initialize to the
            // beginning of the sorted lists.
            
            // Read Carefully:
            // The element in whichever pointer
            // is found small is extracted and pushed
            // to the output stream. The pointer, is 
            // then advanced and this process is 
            // repeated until we have reached to the end
            // of both the pointers. Basically,
            // the MERGE process. 
            
            // Note that if wither of the pointer
            // reaches the end, it makes sense to 
            // push rest of the other array to 
            // output stream becase that is the
            // only input left to merge. 
            // We perform this check at every 
            // step of the merge.
            
            
            // This step of merging concludes the merge sort
            // for the particular instance.
            
            while (true) {
                    if (input[pointer1] <= input[pointer2]) {
                        System.out.println("Pointer 1 small. Take " + input[pointer1]);
                        mergedOutput[mergeOutPointer] = input[pointer1];
                        if (pointer1 == start + middle) {
                            // pointer 1 reached the end. include the current
                            // pointer2,
                            // and exhaust it until the end.

                            System.out.println("Exhaust 2");
                            mergeOutPointer++;
                            mergedOutput[mergeOutPointer] = input[pointer2];
                            
                            
                            while (pointer2 != end) {
                                pointer2++;
                                System.out.println("include " + input[pointer2]);
                                mergeOutPointer++;
                                mergedOutput[mergeOutPointer] = input[pointer2];
                                
                            }
                            break;
                        }
                        pointer1++;
                    } else {
                        System.out.println("Pointer 2 small. Take " + input[pointer2]);
                        mergedOutput[mergeOutPointer] = input[pointer2];
                        if (pointer2 == end) {
                            // include pointer1 and exhaust it till the end.
                            System.out.println("Exhaust 1");
                            System.out.println("include " + input[pointer1]);
                            mergeOutPointer++;
                            mergedOutput[mergeOutPointer] = input[pointer1];
                            
                            while (pointer1 != start+middle) {
                                pointer1++;
                                System.out.println("include " + input[pointer1]);
                                mergeOutPointer++;
                                mergedOutput[mergeOutPointer] = input[pointer1];
                            }
                            break;
                        }
                        pointer2++;
                }
                mergeOutPointer++;
            }
            // copy mergeOutput into the array
            System.out.println("Combined Array: " + Arrays.toString(mergedOutput));
            for (int c = 0; c < mergedOutput.length; c++) {
                input[start + c] = mergedOutput[c];
            }
            
        }

    }
}
