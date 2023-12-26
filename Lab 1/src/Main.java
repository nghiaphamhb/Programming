//variant 21352
import java.util.Random;
import java.util.Arrays;

public class Main{
    private static final int maxArray1=16;
    private static final int minArray1=2;

    private static final float maxArray2=5.0f;  //float need to declare -f
    private static final float minArray2=-3.0f;

    private static final int columnsArray3=17;
    private static final int rowsArray3=15;

    private static final int rounding=5;

    public static void main (String[] args){
        int []c= new int [maxArray1-minArray1+1 ];
        c=makeArray1(c, maxArray1, minArray1);
        System.out.println("First array:\n  "+ Arrays.toString(c)+ "\n");   //Arrays.toString()

        float []x= new float[17];
        x= makeArray2(x, maxArray2, minArray2);
        printArray2(x);

        double [][]a= new double [rowsArray3][columnsArray3];
        a=makeArray3(a, c, x);
        printArray3(a);

    }

    private static int[] makeArray1 (int []c, int maxArray1, int minArray1){
        int n=maxArray1;
        for(int i=0; i< maxArray1-minArray1+1; i++){
            c[i]=n;
            n--;
        }
        return c;
    }

    private static float[] makeArray2 (float []x, float maxArray2, float minArray2){
        Random random = new Random();
        for(int i=0; i<17; i++){
            x[i]=minArray2+random.nextFloat(maxArray2-minArray2+1);
        }
        return x;
    }

    private static double[][] makeArray3 (double [][]a, int[] c, float []x){
        for(int i=0; i<rowsArray3; i++){
            for(int j=0; j<columnsArray3; j++){
                float x1=x[j];
                switch(c[i]){
                    case 13:
                        a[i][j]=Math.pow(Math.PI-Math.pow(Math.pow(x1,(x1-1)/x1),4*(Math.log(Math.abs(x1))-1)),2);
                        break;
                    case 2,3,6,8,11,12,16:
                        a[i][j]=Math.atan(Math.cos(Math.pow(3/(4*(Math.sin(x1)-1)) ,Math.pow(x1/4,3))));
                        break;
                    default:
                        a[i][j]=Math.exp(Math.atan(Math.sin(Math.tan(Math.exp(x1)))));
                        break;
                }
            }
        }
        return a;
    }

    private static void printArray2 (float []x){
        System.out.println("Second array:");
        for(float d: x){
            System.out.format("%.5f ", d);   //Print the resulting array in five decimal places
        }
        System.out.println("\n");
    }

    private static void printArray3 (double [][]a){
        System.out.println("Third array: ");
        for(double []a_i: a){   				//a 2-d array
            for(double a_j: a_i){				//a_i 1-d array
                System.out.format("%.5f  ", a_j);
            }
            System.out.println("");
        }
    }
}