import java.io.File;
import java.io.FileNotFoundException;
public class MainOne {
    public static void main(String[] args) {
        File graphFile = new File("../../graph.txt");
        PrimGraph myGraph = new PrimGraph(graphFile);
        long mstCost = myGraph.mst_cost();  // Call mstCost() method
        System.out.println("Total Cost of MST: " + mstCost);
    }
}