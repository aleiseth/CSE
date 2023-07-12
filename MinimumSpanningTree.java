import java.util.*;

public class MinimumSpanningTree {
    
    public static MinimumSpanningTree t = new MinimumSpanningTree();
    private static final int V = 9;

    public static void main(String[] args) {
        // adjacency matrix
        int graph[][] = new int[][] {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }};
        
        System.out.println("Graph Data: ");
        for(int i = 0; i < V; i++) {
            System.out.println(i);
         }
        t.prims(graph);
    }
    // function to find minimum distance from the MST set 
    // not yet included in the MST
    public int minDistance(int dist[], boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        
        return min_index;
    }

    public void printSolution(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            if (parent[i] < i) {
                System.out.println(parent[i] + " " + i + "\t" + graph[i][parent[i]]);
            } else {
                System.out.println(i + " " + parent[i] + "\t" + graph[i][parent[i]]);
            }
        }
    }

    public void prims (int graph[][]) {
        // add "parent" array to create edges which require a start & end
        int parent[] = new int[V];
        int dist[] = new int[V];
        boolean mstSet[] = new boolean[V];
        // initialize all disatnces as infinite & the boolean MST set as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        // node 0 is the picked node & source from itself is always 0
        dist[0] = 0;
        parent[0] = -1;
        // find shortest path for each vertex
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, mstSet);
            
            //mark picked vertex as processed
            mstSet[u] = true;
            // Update dist & parent value of adjacent vertices
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < dist[v]) {
                    parent[v] = u;
                    dist[v] = graph[u][v];
                }
            }
        }
        // print MST
        printSolution(parent, graph);
    }
}
// This code is contributed by Aakash Hasija 
// & modified by Annika Leiseth to implement Prim's