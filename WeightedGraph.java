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

    //shortest path using Dijkstra Algo(only valid for +ve weights)
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
    
    //BellmanFord algo for shortest path-->valid for both +ve and -ve weights
public static void bellmanFord(ArrayList<Edge> graph[], int src) {
int dist[] = new int[graph.length];
for(int i=0; i<dist.length; i++) {
if(i != src)
dist[i] = Integer.MAX_VALUE;
}
//O(V)
for(int i=0; i<graph.length-1; i++) {
//edges - O(E)
for(int j=0; j<graph.length; j++) {
for(int k=0; k<graph[j].size(); k++) {
Edge e = graph[j].get(k);
int u = e.src;
int v = e.dest;
int wt = e.wt;
if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]) {
dist[v] = dist[u] + wt;
}
}
}
}
    
    
    //Detecting Negative Weight Cycle in Bellman(for which the algo fails)
for(int j=0; j<graph.length; j++) {
for(int k=0; k<graph[j].size(); k++) {
Edge e = graph[j].get(k);
int u = e.src;
int v = e.dest;
int wt = e.wt;
if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]) {
System.out.println("negative weight cycle exists");
break;
}
}
}
    
for(int i=0; i<dist.length; i++) {
System.out.print(dist[i]+" ");
}
System.out.println();
}
    
    
    //Prim's algo for MST--> complexity=O(ElogE)
public static void primAlgo(ArrayList<Edge> graph[]) {
boolean vis[] = new boolean[graph.length];
PriorityQueue<Pair> pq = new PriorityQueue<>();
pq.add(new Pair(0, 0));
int cost = 0;
while(!pq.isEmpty()) {
Pair curr = pq.remove();
if(!vis[curr.v]) {
vis[curr.v] = true;
cost += curr.wt;
for(int i=0; i<graph[curr.v].size(); i++) {
Edge e = graph[curr.v].get(i);
if(!vis[e.dest]) {
pq.add(new Pair(e.dest, e.wt));
}
}
}
}
System.out.println(cost);
}

    public static void main(String[] args){
        int v=6;
        ArrayList<Edge> gr[]=new ArrayList[v];
        create(gr);
        DijkstraAlgo(gr,0,v);
        
        int src = 0;
        bellmanFord(gr, src);
        
        primAlgo(gr);

    }
}
