package sort;

public class SelectionSort implements Sort{
    
    
    public SelectionSort() {
        
    }

    @Override
    public void sort(int[] input) {
        
        for(int i=0;i<input.length-1;i++){
            // iterate till last to find the last element and replace the 
            // minimum element with the first element (i)
            int max=i;
            for(int j=i;j<input.length;j++){
                if(input[j]<input[max])
                    max=j;
            }
            SortUtils.swap(input, i, max);
        }
        
        
        
        
    }

}
