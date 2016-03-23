package other;

import java.util.Random;

import org.junit.Test;

import other.MatrixMultiplication.Matrix;

public class MatrixMultiplicationTest {
    
    
    @Test
    public void shouldFindMinimumScalarMultiplicationsForTrivialCases(){
        
        Random r = new Random();
        int size = 3;
        Matrix[] m = new Matrix[size];
        MatrixMultiplication solver = new MatrixMultiplication();
        
        System.out.println("Solve matrix multiplication problem for: ");
        
        int old = r.nextInt(100);
        
        for(int i=0;i<size;i++){
            
           // int columns =r.nextInt(100);
            int rows =old;
            int columns =r.nextInt(100);
            old=columns;
            
            m[i] = solver.new Matrix(rows, columns);
            
            System.out.print( "A"+ i + "[" +rows + "," + columns+ "]");
            System.out.print(" ");
        }
        System.out.println();
        
        solver.computeMinimumScalerMultiplicationsUsingDynamic(m,true);
        solver.computeMinimumScalerMultiplicationsUsingDynamic(m,false);
        
    }

}
