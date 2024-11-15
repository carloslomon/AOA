
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class WeightlenTwo{
    /*public static double weighted_cost(int[]){

    }*/
   static class time_comparator implements Comparator<double[]>{
        @Override
        public int compare(double[] arr1, double[] arr2) {
            double cmp = arr1[2] - arr2[2];
            if(cmp < 0){
                return 1;
            }
            else if(cmp == 0){
                return 0;
            }
            else{
                return -1;
            }
        }
   }


    public static void main(String[] args){
        int job_num = 0;
        int idx = -1;
        double[][] schedule = null;
        try{
            File mywl = new File("wlen.txt");
            Scanner myReader = new Scanner(mywl);
            while(myReader.hasNextLine()){
                String[] arr = myReader.nextLine().split(" ");
                if(arr.length == 1){
                    job_num = Integer.valueOf(arr[0]);
                    schedule = new double[job_num][3];
                }else{
                   double w_i = Double.parseDouble(arr[0]);
                   double l_i = Double.parseDouble(arr[1]);
                   schedule[idx] = new double[]{w_i, l_i, (w_i*1.0/l_i*1.0)};
                   //System.out.println(""); 
                }
                idx++;
                
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
                System.out.println("An error occurred");
                e.printStackTrace();

        }
        //remember to close the file 
        Arrays.sort(schedule, new time_comparator());
        //System.out.println("schedule[0] = " + schedule[0][2] + " schedule[1] = " + schedule[1][2] + " schedule[999] = " + schedule[999][2]); 
        long cost = 0;
        long comp = 0;
        for(double[] job : schedule){
            comp += job[1];
            cost += comp * job[0];
        }
        System.out.println(cost);
    }
        
}
