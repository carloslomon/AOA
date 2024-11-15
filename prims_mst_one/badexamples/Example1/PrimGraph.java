
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class PrimGraph{
    //instance variables
    private File graph;
    private double[][] edges = null;
    private Set<Double> v = new HashSet<>(); // You have to use wrapper classes
    private Set<Double> mst = new HashSet<>();
    private Set<double[]> e = new HashSet<>();
    private ArrayList<double[]> mstEdges = new ArrayList<>();
    private int num_nodes = 0;
    private int num_edges = 0;
    private long mst_cost = 0;

    static class edge_comparator implements Comparator<double[]>{
        @Override
        public int compare(double[] arr1, double[] arr2) {
            return Double.compare(arr2[2], arr1[2]);
        }
    }



    public PrimGraph(File graph){
        this.graph = graph;
        try{
            Scanner graphReader = new Scanner(this.graph);
            int idx =-1;
            while(graphReader.hasNextLine()){
                String[] str = graphReader.nextLine().split(" ");
                //System.out.println(str.length);
                if(str.length < 3){
                    this.num_nodes = Integer.parseInt(str[0]);
                    this.num_edges = Integer.parseInt(str[1]); 
                    this.edges = new double[this.num_edges][3];
                }else{
                    double w = Double.parseDouble(str[0]);
                    double u = Double.parseDouble(str[1]);
                    double cost = Double.parseDouble(str[2]);
                    this.edges[idx] = new double[]{w, u, cost};
                    double[] tmp_e = new double[]{w,u};
                    this.v.add(w);
                    this.v.add(u);
                    this.e.add(tmp_e);
                }
                idx++;
            }
            graphReader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        if(this.edges.length > 0){
           Arrays.sort(this.edges, new edge_comparator()); 
        }

    }

    public long mst_cost(){
        long totalCost = 0;
        if(this.mst.size() == 0){
            for(double v_tmp : this.v){
                for(int i = 0; i < this.num_edges; i++){
                    
                    if(v_tmp == this.edges[i][0]){
                        if(this.mst.contains(this.edges[i][1])){
                            continue;
                        }else{
                            this.mst.add(v_tmp);
                            this.mst.add(this.edges[i][1]);
                            this.mstEdges.add(this.edges[i]);
                            totalCost+=this.edges[i][2]; 
                        }
                    }
                    else if(v_tmp == this.edges[i][1]){
                        if(this.mst.contains(this.edges[i][0])){
                            continue;
                        } 
                        else{
                            this.mst.add(v_tmp);
                            this.mst.add(this.edges[i][0]);
                            this.mstEdges.add(this.edges[i]);
                            totalCost+=this.edges[i][2]; 
                        }
                    }
            }
          }
          if(this.mst.size() == this.num_nodes){
            this.mst_cost = totalCost;
            return totalCost;
          }
        }
        return this.mst_cost;

    }
}    
/*class Main{
        public static void main(String[] args){
            try{
                File graphFile = new File("graph.txt");
                PrimGraph myGraph = new PrimGraph(graphFile);
                int mst_cost = myGraph.mst_cost();
                System.out.println("Total Cost of MST: " + mst_cost ); 
            }
            catch(FileNotFoundException e){
                System.out.println("An error occurred");
                e.printStackTrace();

            }
        }
}*/

/*public class Main {
    public static void main(String[] args) {
        try {
            File graphFile = new File("graph.txt");
            PrimGraph myGraph = new PrimGraph(graphFile);
            int mstCost = myGraph.mst_cost();  // Call mstCost() method
            System.out.println("Total Cost of MST: " + mstCost);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the graph file.");
            e.printStackTrace();
        }
    }
}
*/

