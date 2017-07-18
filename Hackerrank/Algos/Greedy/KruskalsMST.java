package Algos.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by mballa on 18-07-2017.
 * Algo Description:
 * 1) sort all edges in incresing order
 * 2) pick smallest edge and see if it forms a cycle with spanning tree so far , if so discard it else include it
 * 3) report 2 until v-1 edges in spanning tree
 */
public class KruskalsMST {
    static class Graph{
        int V , E ;
        Edge[] edge;
        Graph(int V,int E){
            this.V=V;
            this.E=E;
            edge = new Edge[E];
            for(int i=0;i<E;i++){
                edge[i]=new Edge();
            }
        }
    }

    static class Edge{
        int src,dest,wt;
    }

    static class Subset{
        int parent;
        int rank;
    }
    //method to find the parent of i    O(LOGN)
    static int find(Subset[] subset , int i){
        if(subset[i].parent != i){
            //path compression - make this as parent of i
            subset[i].parent = find(subset,subset[i].parent);
        }
        return subset[i].parent;
    }

    static void union(Subset[] subset , int x , int y){
        int xSet = find(subset,x);
        int ySet = find(subset,y);
        //union by rank - attach the lower rank tree to higer rank tree
        if(subset[xSet].rank < subset[ySet].rank){
            subset[xSet].parent = ySet;
        }else if(subset[xSet].rank > subset[ySet].rank){
            subset[ySet].parent = xSet;
        }else{
            subset[xSet].parent = ySet;
            subset[xSet].rank++;
        }
    }

    static void KruskalsMST(Graph graph){
        int V = graph.V;
        Edge[] edge = graph.edge;
        //sort in increasing order .
        Arrays.sort(edge, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.wt > o2.wt)return 1;
                if(o1.wt < o2.wt)return -1;
                return 0;
            }
        });

        //MST set
        // Allocate memory for creating V ssubsets
        Subset subsets[] = new Subset[V];
        for(int i=0; i<V; ++i)
            subsets[i]=new Subset();

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        Edge[] mstEdge = new Edge[graph.V]; // all result edges will store here
        for (int i=0; i<graph.V; i++){
            mstEdge[i] = new Edge();
        }

        int e=0;
        int i=0;
        while (e < V -1 ){
            //pick smallest edge
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            //see if including this edge causes a cycle
            int x = find(subsets,next_edge.src);
            int y = find(subsets,next_edge.dest);
            if(x!=y){ // no cycle
                mstEdge[e++] = next_edge;
                union(subsets,x,y);
            }
            //discard the edge if it forms a cycle.
        }
        // print the contents of result[] to display the built MST
        System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(mstEdge[i].src+" -- "+mstEdge[i].dest+" == "+
                    mstEdge[i].wt);
    }

    // Driver Program
    public static void main (String[] args)
    {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].wt = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].wt = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].wt = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].wt = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].wt = 4;

        KruskalsMST(graph);
    }
}



