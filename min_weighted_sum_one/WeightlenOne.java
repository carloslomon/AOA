/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class JobSchedule<T extends Number>{
    private List<T> jobWeights;
    private List<T> jobLengths;
    private List<Double[]> jobSchedule;
    private long cost;
    private long completionTime;

    public JobSchedule(List<T> jobWeights, List<T> jobLengths) {
        this.jobWeights = jobWeights;
        this.jobLengths = jobLengths;
        this.jobSchedule = new ArrayList<>();
        this.cost = 0;
        this.completionTime = 0;
    }
    

    public void minusRule() {
        jobSchedule.clear();
        cost = 0;
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < jobWeights.size(); i++) {
            int value = (int)(jobWeights.get(i)) - (int)(jobLengths.get(i));
            values.add(value);
            jobSchedule.add(new int[]{jobWeights.get(i), jobLengths.get(i), value});
        }

        jobSchedule.sort((a, b) -> {
            int cmp = Integer.compare((int)b[2], (int)a[2]);
            if (cmp == 0) {
                return Integer.compare((int)b[0], (int)a[0]); // Tie-breaking by weight
            }
            return cmp;
        });
    }

    public void ratioRule() {
        jobSchedule.clear();
        cost = 0;

        for (int i = 0; i < jobWeights.size(); i++) {
            double ratio = (Double) jobWeights.get(i) / jobLengths.get(i);
            jobSchedule.add(new Double[]{jobWeights.get(i), jobLengths.get(i), ratio});
        }

        jobSchedule.sort(Comparator.comparingDouble(a -> -((double) a[2])));
    }

    public void calcCost() {
        completionTime = 0;
        cost = 0;

        for (Double[] job : jobSchedule) {
            long weight = (long)job[0];
            long length = (long)job[1];
            completionTime += length;
            cost += weight * completionTime;
        }
    }

    public long getCost() {
        return cost;
    }
}

public class WeightlenOne{
    public static void main(String[] args) {
        List<Double> jobWeights = new ArrayList<>();
        List<Double> jobLengths = new ArrayList<>();
        
        try {
            File file = new File("wlen.txt");
            Scanner scanner = new Scanner(file);

            // Skip the first line (job number)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read weights and lengths
            while (scanner.hasNextLine()) {
                String[] nums = scanner.nextLine().split(" ");
                jobWeights.add(Double.parseDouble(nums[0]));
                jobLengths.add(Double.parseDouble(nums[1]));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return;
        }

        JobSchedule jobScheduleTest = new JobSchedule(jobWeights, jobLengths);
        
        // Calculate cost using the minus rule
        jobScheduleTest.minusRule();
        jobScheduleTest.calcCost();
        System.out.println("Minus rule total cost is: " + jobScheduleTest.getCost());
        
        // Calculate cost using the ratio rule
        jobScheduleTest.ratioRule();
        jobScheduleTest.calcCost();
        System.out.println("Ratio rule total cost is: " + jobScheduleTest.getCost());
    }
}*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class JobSchedule<T extends Number> {
    private List<T> jobWeights;
    private List<T> jobLengths;
    private List<double[]> jobSchedule;
    private long cost;
    private long completionTime;

    public JobSchedule(List<T> jobWeights, List<T> jobLengths) {
        this.jobWeights = jobWeights;
        this.jobLengths = jobLengths;
        this.jobSchedule = new ArrayList<>();
        this.cost = 0;
        this.completionTime = 0;
    }

    public void minusRule() {
        jobSchedule.clear();
        cost = 0;

        for (int i = 0; i < jobWeights.size(); i++) {
            double weight = jobWeights.get(i).doubleValue();
            double length = jobLengths.get(i).doubleValue();
            double value = weight - length;
            jobSchedule.add(new double[]{weight, length, value});
        }

        jobSchedule.sort((a, b) -> {
            int cmp = Double.compare(b[2], a[2]);
            if (cmp == 0) {
                return Double.compare(b[0], a[0]); // Tie-breaking by weight
            }
            return cmp;
        });
    }

    public void ratioRule() {
        jobSchedule.clear();
        cost = 0;

        for (int i = 0; i < jobWeights.size(); i++) {
            double weight = jobWeights.get(i).doubleValue();
            double length = jobLengths.get(i).doubleValue();
            double ratio = weight / length;
            jobSchedule.add(new double[]{weight, length, ratio});
        }

        jobSchedule.sort(Comparator.comparingDouble(a -> -a[2]));
    }

    public void calcCost() {
        completionTime = 0;
        cost = 0;

        for (double[] job : jobSchedule) {
            long weight = (long) job[0];
            long length = (long) job[1];
            completionTime += length;
            cost += weight * completionTime;
        }
    }

    public long getCost() {
        return cost;
    }
}

public class WeightlenOne {
    public static void main(String[] args) {
        List<Double> jobWeights = new ArrayList<>();
        List<Double> jobLengths = new ArrayList<>();

        try {
            File file = new File("wlen.txt");
            Scanner scanner = new Scanner(file);

            // Skip the first line (job number)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read weights and lengths
            while (scanner.hasNextLine()) {
                String[] nums = scanner.nextLine().split(" ");
                jobWeights.add(Double.parseDouble(nums[0]));
                jobLengths.add(Double.parseDouble(nums[1]));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return;
        }

        JobSchedule<Double> jobScheduleTest = new JobSchedule<>(jobWeights, jobLengths);

        // Calculate cost using the minus rule
        jobScheduleTest.minusRule();
        jobScheduleTest.calcCost();
        System.out.println("Minus rule total cost is: " + jobScheduleTest.getCost());

        // Calculate cost using the ratio rule
        jobScheduleTest.ratioRule();
        jobScheduleTest.calcCost();
        System.out.println("Ratio rule total cost is: " + jobScheduleTest.getCost());
    }
}

