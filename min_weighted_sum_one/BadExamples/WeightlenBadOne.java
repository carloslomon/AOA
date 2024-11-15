import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class WeightlenBadOne {
   static class TimeComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] arr1, int[] arr2) {
            return Integer.compare(arr2[2], arr1[2]); // Sort in descending order of (w_i - l_i)
        }
   }
   static class TimeComparator2 implements Comparator<double[]>{
        @Override
        public int compare(double[] arr1, double[] arr2) {
            return Double.compare(arr2[2], arr1[2]); // Sort in descending order of (w_i/l_i)
        }
    }
    public static void main(String[] args) {
        try{
            int idx = 0;
            File mywl = new File("wlen.txt");
            Scanner myReader = new Scanner(mywl);
            int jobNum = Integer.parseInt(myReader.nextLine().split(" ")[0]);
            double[][] schedule = new double[jobNum][3];
            double[][] schedule2 = new double[jobNum][3]; 
            while (myReader.hasNextLine()) {
                String[] arr = myReader.nextLine().split(" ");
                double w_i = Double.parseDouble(arr[0]);
                double l_i = Double.parseDouble(arr[1]);
                schedule[idx] = new double[]{w_i, l_i, (w_i*1.0 - l_i*1.0)};
                schedule2[idx] = new double[]{w_i, l_i, (w_i*1.0)/(l_i*1.0)}; 
                //System.out.println("w: " + schedule[idx][0] + "   l: " + schedule[idx][1]);
                idx++;
            }
        
            System.out.println(idx);
            myReader.close();
            Arrays.sort(schedule, new TimeComparator2());
            Arrays.sort(schedule2, new TimeComparator2());
            //System.out.println("schedule[0] = " + schedule[0][2] + " schedule[1] = " + schedule[1][2] + " schedule[999] = " + schedule[999][2]); 
            long cost = 0;
            long comp = 0;
            long cost2 = 0;
            long comp2 = 0;
            for(int i =0; i < jobNum; i++){
                comp += schedule[i][1];
                cost += (comp * schedule[i][0]);
                comp2 += schedule2[i][1];
                cost2 += (comp2 *  schedule2[i][0]);

            }
            System.out.println(cost);
            System.out.println(cost2);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
  }
}


