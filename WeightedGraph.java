import java.util.ArrayList;
import java.util.PriorityQueue;

public class WeightedGraph {
    public static class Pair implements Comparable<Pair>{
        int node;
        int dist;
        Pair(int node,int dist){
            this.node=node;
            this.dist=dist;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dist-p2.dist;
        }
    }

    public static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int src,int dest, int wt){
            this.src=src;
            this.dest=dest;
            this.wt=wt;
        }
    }

    public static void create(ArrayList<Edge> gr[]){
        for(int i=0;i<gr.length;i++){
            gr[i]=new ArrayList<>();
        }

        gr[0].add(new Edge(0,1,2));
        gr[0].add(new Edge(0,2,4));
        gr[1].add(new Edge(1,2,1));
        gr[1].add(new Edge(1,3,7));
        gr[2].add(new Edge(2,4,3));
        gr[3].add(new Edge(3,5,1));
        gr[4].add(new Edge(4,3,2));
        gr[4].add(new Edge(4,5,5));
    }

    //shortest path
    public static void DijkstraAlgo(ArrayList<Edge> gr[], int src, int v){

        PriorityQueue<Pair> pq=new PriorityQueue<>();
        int dist[]=new int[v];
        for(int i=0;i<v;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        boolean vis[]=new boolean[v];

        pq.add(new Pair(0,0));

        while(!pq.isEmpty()){
            Pair curr=pq.remove();
            if(!vis[curr.node]){
                vis[curr.node]=true;
                for(int i=0;i<gr[curr.node].size();i++){
                    Edge e=gr[curr.node].get(i);
                    int u=e.src;
                    int vi=e.dest;
                    if(dist[u]+e.wt<dist[vi]){
                        dist[vi]=dist[u]+e.wt;
                        pq.add(new Pair(vi, dist[vi]));
                    }

                }
            }
        }

        for(int i=0;i<v;i++){
            System.out.println(dist[i]);
        }
    }

    public static void main(String[] args){
        int v=6;
        ArrayList<Edge> gr[]=new ArrayList[v];
        create(gr);
        DijkstraAlgo(gr,0,v);

    }
}
