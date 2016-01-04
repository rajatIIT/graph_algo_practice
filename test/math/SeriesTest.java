package math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SeriesTest {
    
    @Test
    public void shoudlGenerateArithmeticSeries() {
        
        Series series = new Series();
        float[] output = series.generateRandomSeriesAsArray(series.ARITHMETIC_SERIES);
        assertTrue("Generated Series is not an arithmetic series! ",testArithmeticSeries(output));
        
        
    }
    
    
    private boolean testArithmeticSeries(float[] input) {
        float diff= input[1]-input[0];
        for(int i=2;i<input.length;i++){
            float currentDiff = (input[i]- input[i-1]);
            float errorPercentage = mod((((currentDiff - diff)/diff)*100));
            if(errorPercentage>1.0f)
                return false;
        }
        return true;
    }
    
    
    private float mod(float input) {
        if(input>0)
            return input;
        else 
            return (-1)*input;
    }

}
