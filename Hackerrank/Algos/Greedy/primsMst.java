package Algos.Greedy;

/**
 * Created by mballa on 15-07-2017.
 */
public class PrimsMst {
    // Number of vertices in the graph
    private static final int V = 5;

    static class MST {

        void primMST(int graph[][]) {
            // Array to store constructed MST
            int parent[] = new int[V];

            // Key values used to pick minimum weight edge in cut
            int key[] = new int[V];

            // To represent set of vertices not yet included in MST
            Boolean mstSet[] = new Boolean[V];

            // Initialize all keys as INFINITE
            for (int i = 0; i < V; i++) {
                key[i] = Integer.MAX_VALUE;
                mstSet[i] = false;
            }

            // Always include first 1st vertex in MST.
            key[0] = 0;     // Make key 0 so that this vertex is
            // picked as first vertex
            parent[0] = -1; // First node is always root of MST

            for(int count=0;count < V-1; count++) {
                //pick min key vertex from set of vertices not in mstset
                int u = minKey(key, mstSet);
                mstSet[u] = true;
                for (int v = 0; v < V; v++) {
                    //update the keyvalue of the adjavcent vertices of tghe picked vertex
                    //consider only vertices not included in mst
                    //update the keyValue only if graph[u][v] < key[v]
                    if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                        key[v] = graph[u][v];
                        parent[v] = u;  // include in mst
                    }
                }
            }
            printMST(parent,V,graph);

        }

        void printMST(int parent[], int n, int graph[][])
        {
            System.out.println("Edge   Weight");
            for (int i = 1; i < V; i++)
                System.out.println(parent[i]+" - "+ i+"    "+
                        graph[i][parent[i]]);
        }

        private int minKey(int[] key, Boolean[] mstSet) {
            int min = Integer.MAX_VALUE;
            int min_index = -1;
            for(int i =0 ; i < V;i++){
                if(mstSet[i] == false && key[i] < min ){
                    min = key[i];
                    min_index = i;
                }
            }
            return min_index;
        }

        public static void main(String[] args) {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
            MST t = new MST();
            int graph[][] = new int[][]{{0, 2, 0, 6, 0},
                    {2, 0, 3, 8, 5},
                    {0, 3, 0, 0, 7},
                    {6, 8, 0, 0, 9},
                    {0, 5, 7, 9, 0},
            };

            //Complexity is O(V^2)  for adjacency list representation , if we use min heap
            // we can do in O((V+E)*log(V))

            // Print the solution
            t.primMST(graph);
        }
    }
}
