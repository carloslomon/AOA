import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PrimGraphPQueue{
    // Instance variables
    private File graph;
    private int[][] edges;
    private Set<Integer> v = new HashSet<>();
    private Set<Integer> mst = new HashSet<>();
    private List<int[]> mstEdges = new ArrayList<>();
    private int numNodes;
    private int numEdges;

    static class EdgeComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] arr1, int[] arr2) {
            return Integer.compare(arr1[2], arr2[2]);
        }
    }

    public PrimGraphPQueue(File graph) {
        this.graph = graph;
        int idx = 0;
        try (Scanner graphReader = new Scanner(this.graph)) {
            while (graphReader.hasNextLine()) {
                String[] str = graphReader.nextLine().split(" ");
                if (str.length < 3) {
                    this.numNodes = Integer.parseInt(str[0]);
                    this.numEdges = Integer.parseInt(str[1]);
                    this.edges = new int[this.numEdges][3];
                } else {
                    int w = Integer.parseInt(str[0]);
                    int u = Integer.parseInt(str[1]);
                    int cost = Integer.parseInt(str[2]);
                    this.edges[idx++] = new int[]{w, u, cost};
                    this.v.add(w);
                    this.v.add(u);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        Arrays.sort(this.edges, new EdgeComparator());
    }

    public int mstCost() {
        int totalCost = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new EdgeComparator());
        Set<Integer> visited = new HashSet<>();
        
        // Start from an arbitrary node (first in set)
        Integer start = v.iterator().next();
        visited.add(start);
        
        // Add all edges from the start node
        for (int[] edge : edges) {
            if (edge[0] == start || edge[1] == start) {
                pq.add(edge);
            }
        }

        while (!pq.isEmpty() && visited.size() < numNodes) {
            int[] edge = pq.poll();
            int w = edge[0], u = edge[1], cost = edge[2];

            if (visited.contains(w) && visited.contains(u)) {
                continue; // Ignore if both nodes are already in the MST
            }

            // Add the edge to MST
            mstEdges.add(edge);
            totalCost += cost;

            // Add new node to visited set
            int newVertex = visited.contains(w) ? u : w;
            visited.add(newVertex);

            // Add all new edges from the new vertex
            for (int[] nextEdge : edges) {
                if ((nextEdge[0] == newVertex && !visited.contains(nextEdge[1])) ||
                    (nextEdge[1] == newVertex && !visited.contains(nextEdge[0]))) {
                    pq.add(nextEdge);
                }
            }
        }
        
        return visited.size() == numNodes ? totalCost : -1;
    }

    public static void main(String[] args) {
        
        File graphFile = new File("graph.txt");
        PrimGraphPQueue myGraph = new PrimGraphPQueue(graphFile);
        int mstCost = myGraph.mstCost();
        System.out.println("Total Cost of MST: " + mstCost);
    }
}
