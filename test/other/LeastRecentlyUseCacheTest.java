package other;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;


public class LeastRecentlyUseCacheTest {
	
	
	@Test
	public void shouldWorkForSomeTrivialCases() {
		
		LeastRecentlyUseCache myCache = new LeastRecentlyUseCache();
		
		
		// generate a random string Array
		
		int array_size = 100;
		
		String[] randomStr = new String[array_size];
		
		
		Random randomInt = new Random();
		
		int maxSTringSize = 15;
		
		for(int i=0;i<array_size;i++){
			randomStr[i] = getARandomString(randomInt.nextInt(maxSTringSize));
		}
		
		myCache.setItems(randomStr);
		
		
		// throw some items to the cache, check to see if the items are there in the cache.
		
		int testSize=10;
		ArrayList<Integer> simpleList = new ArrayList<>();
		for(int i=0;i<testSize;i++){
			int nextRand = randomInt.nextInt(array_size);
			simpleList.add(nextRand);
			myCache.lookupitem(nextRand);
		}
		
		
		for(int each : simpleList){
			System.out.println(myCache.lookupitem(each));
		}
		
	
		
	}
	
	
	
	public String getARandomString(int size) {
			Random r = new Random();
			StringBuilder builder = new StringBuilder();
			
			for(int i=0;i<size;i++){
				builder.append((char)r.nextInt(128));
			}
			
			return builder.toString();
	}
	
	
	
}

