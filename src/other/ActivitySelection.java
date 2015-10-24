package other;

import java.util.HashSet;
import java.util.TreeSet;

public class ActivitySelection {
        
    public ActivitySelection(int[] startTime, int[] finalTime) {
        
        if(startTime.length!=finalTime.length){
            System.out.println("Error! Length of initial and final time must be equal.");
        }
        // get a list of activities 
        
        // while 
        // select a new activity
        // add the activity to the schedule 
        
        // Activity is defined as : (initial Time, final Time)
        
        TreeSet<Activity> finalTimeOrderSet = new TreeSet<Activity>(new ActivityComparator());
        
        int activityCount=0;
        for(int i=0;i<startTime.length;i++){
                finalTimeOrderSet.add(new Activity(activityCount,startTime[i],finalTime[i]));
                activityCount++;
        }
        
        java.util.LinkedList<Activity> schedule = new java.util.LinkedList<>();
        
        // while the activity set is nonEmpty
        Activity firstActivity = finalTimeOrderSet.first();
        
        int lastFinalTime = firstActivity.getFinalTime();
        
        while(!finalTimeOrderSet.isEmpty()){
            // get next distinct activity
              Activity nextActivity = getNextNonOverlappingActivity(lastFinalTime,finalTimeOrderSet);
            
            schedule.add(nextActivity);
        }
        
        
        // how to print schedule?
        
        
        
        
        
    }

    private Activity getNextNonOverlappingActivity(int lastFinalTime, TreeSet<Activity> finalTimeOrderSet) {
        Activity current = finalTimeOrderSet.first();
        
        while(current.getInitialTime()<lastFinalTime){
            current = finalTimeOrderSet.first();
            if(current==null)
                return null;
        }
        return current;
    }
    
    
    
    
}
