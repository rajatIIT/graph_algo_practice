package other;

import java.util.Comparator;

public class ActivityComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity o1, Activity o2) {
        // o1 has a higher priority if o1 has a lesser finish time.
        if (o1.getFinalTime() < o2.getFinalTime())
            return 1;
        else if (o1.getFinalTime() > o2.getFinalTime())
            return -1;
        else
            return 0;
    }

}
