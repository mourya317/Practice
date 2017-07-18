package Algos.Greedy;

/**
 * Created by mballa on 18-07-2017.
 * this will take O(N2)
 */
public class unionFindAlgo {

    static class Graph{
        int V , E;
        Edge[] edge;
        class Edge{
            int src,dest;
        }
        Graph(int V , int E){
            this.V=V;
            this.E=E;
            edge = new Edge[E];
            for(int i=0;i<V;i++){
                edge[i]=new Edge();
            }

        }


        //http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
        //Find -->   using union by rank technique
        //Union -->  using path compression technique

        //instead of parent[] we have a struct parant and rank

        class Subset{
            int parent;
            int rank;
        }

        int find(Subset[] subset,int i){
            //find root and make it parent of i (path compression)
            if(subset[i].parent != i){
                subset[i].parent = find(subset,subset[i].parent);
            }
            return subset[i].parent;
        }

        //union of 2 sets using union by rank  //O(LOGN)
        void union(Subset[] subset,int x,int y){
            int xSet = find(subset,x);
            int ySet = find(subset,y);
            //attach smaller rank treees under higher rank trees
            if(subset[xSet].rank < subset[ySet].rank){
                subset[xSet].parent=ySet;
            }else if(subset[xSet].rank > subset[ySet].rank){
                subset[ySet].parent=xSet;
            }else{
               //if both have same ranks , make anyone as root and incremrnt its rank
                subset[xSet].parent = ySet;
                subset[xSet].rank++;
            }
        }

        int find(int[] parent,int i){
            if(parent[i] == -1){
                return i;
            }
            return find(parent,parent[i]);
        }

        void union(int[] parent,int x,int y){
            int xSet = find(parent,x);
            int ySet = find(parent,y);
            parent[xSet] = ySet;
        }

        int isCycle(Graph graph){
            int[] parent = new int[graph.V];
            for(int i=0;i<V;i++){
                parent[i]=-1;
            }
            for(int i=0;i<graph.E;i++){
                int x = find(parent,graph.edge[i].src);
                int y = find(parent,graph.edge[i].dest);
                if(x!=y)return 1;
                graph.union(parent,x,y);
            }
            return 0;
        }
    }

    public static void main(String[] args){
         /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */
        int V = 3, E = 3;
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph)==1)
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }

    }



