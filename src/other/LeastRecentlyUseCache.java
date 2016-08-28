package other;



import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author rajatpawar
 */

// assuming all the items in cache are unique
// implemented this in notepad :P
public class LeastRecentlyUseCache implements cache{
	
	HashMap<Integer,CacheItem> cacheItems = new HashMap<>();
	
	
	
	private String[] items;
	int cache_size=50;
	int counter;
	
	public LeastRecentlyUseCache() {
		counter=0;
		
		
	}
	
	public void setItems(String[] input) {
		this.items = input;
	}
	
        
        @Override
	public boolean lookupitem(int item) {
		
		
		if(cacheItems.containsKey(item)){
			
			counter++;
			// cache hit
			
			String result = (cacheItems.get(item)).value;
				cacheItems.put(item, (new CacheItem(counter,result)));
				System.out.println("Item is present in cache: " + result);
		}  else {
			
			
			if(cacheItems.size()==cache_size){
			
			// cache miss 
			
			// find the item to remove
			
			int itemToRemove = findLeastRecentlyUsedItem();
			cacheItems.remove(itemToRemove, cacheItems.get(itemToRemove));
			counter++;
			
			// read the value 
			String result = items[item];
			cacheItems.put(item, new CacheItem(counter,result));
			System.out.println("Item was not present, fetched from data store: " + result);
		} else {
			counter++;
			cacheItems.put(item, new CacheItem(counter,items[item]));
		}
		}
		
		// TODO : implement exception and null value checker to returen false
		return true;
		
	}
	
	
	
	public int findLeastRecentlyUsedItem() {
		
		Iterator<Integer> keyIT = cacheItems.keySet().iterator();
		int minItem = keyIT.next();
		int minItemCounter = cacheItems.get(minItem).count;
		
		while(keyIT.hasNext()){
			
			int nextItem = keyIT.next();
			int nextItemCounter = cacheItems.get(nextItem).count;
			if (nextItemCounter<minItemCounter){
				minItem=nextItem;
			}
		}
		return minItem;
	}

   
    
	
}
