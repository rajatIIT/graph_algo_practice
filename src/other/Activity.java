package other;

public class Activity {

    private int initialTime, finalTime, activityID;

    protected int getActivityID() {
        return activityID;
    }

    protected void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public Activity(int initialTime, int finalTime) {
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }
    
    public Activity(int activityID, int initialTime, int finalTime){
        this(initialTime,finalTime);
        this.activityID = activityID;
    }

    protected int getInitialTime() {
        return initialTime;
    }

    protected void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    protected int getFinalTime() {
        return finalTime;
    }

    protected void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
    }

}
