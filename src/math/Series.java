package math;

import java.awt.GraphicsEnvironment;
import java.util.Random;

/**
 * 
 * Some constructs which create common mathematical series like AP, GP, HP log
 * sum series etc. Implementing to understand the basic concepts behind them and
 * appreciate the computational complexity concepts associated with them.
 * 
 * @author rajatpawar
 *
 */
public class Series {

    public final int ARITHMETIC_SERIES = 1;
    public FloatRange initialTermRange, differenceRange;
    public IntRange sizeRange;

    public float[] generateRandomSeriesAsArray(int seriesType) {

        initialTermRange = new FloatRange();
        initialTermRange.setInitial(10);
        initialTermRange.setFinalNum(50);

        differenceRange = new FloatRange();
        differenceRange.setInitial(5);
        differenceRange.setFinalNum(10);

        sizeRange = new IntRange();
        sizeRange.setInitial(100);
        sizeRange.setFinalNum(1000);

        Float initialTerm = getNumberFromRange(initialTermRange);
        Float difference = getNumberFromRange(differenceRange);

        Random random = new Random();
        int randomSize = sizeRange.getInitial()
                + random.nextInt(sizeRange.getFinalNum() - sizeRange.getInitial());

        if (seriesType == ARITHMETIC_SERIES)
            return getArithmeticSeries(initialTerm, difference, randomSize);
        else
            return null;

    }

    private float getNumberFromRange(FloatRange inputRange) {
        Random random = new Random();
        Float randomFloat = random.nextFloat();
        Float difference = inputRange.getFinalNum() - inputRange.getInitial();
        Float output = difference * randomFloat;
        return output;
    }

    private float[] getArithmeticSeries(float term, float diff, int size) {

        // compute the series
        float[] series = new float[size];
        series[0] = term;
        for (int i = 1; i < size; i++) {
            series[i] = series[i - 1] + diff;
        }

        return series;
    }
    
    

}
