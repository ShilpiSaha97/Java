import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    static class Edge{
        int src;
        int dest;

        public Edge(int src,int dest){
            this.src=src;
            this.dest=dest;
        }

    }

    public static void construct(ArrayList<Edge> gr[]){
        for(int i=0;i<gr.length;i++){
            gr[i]=new ArrayList<>();
        }

        gr[0].add(new Edge(0,2));

        gr[1].add(new Edge(1,2));
        gr[1].add(new Edge(1,3));

        gr[2].add(new Edge(2,0));
        gr[2].add(new Edge(2,1));
        gr[2].add(new Edge(2,3));

        gr[3].add(new Edge(3,1));
        gr[3].add(new Edge(3,2));
    }

    public static void bfs(ArrayList<Edge> grp[], int v, boolean vis[], int start){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int curr=queue.remove();
            if(vis[curr]==false){
                System.out.print(curr+" ");
                vis[curr]=true;
                for(int i=0;i<grp[curr].size();i++){
                    Edge edge=grp[curr].get(i);
                    queue.add(edge.dest);
                }

            }
        }
    }

    public static void dfs(ArrayList<Edge> gr[], int curr, boolean vis[]){

        System.out.print(curr+" ");
        vis[curr]=true;
        for(int i=0;i<gr[curr].size();i++){
            Edge e=gr[curr].get(i);
            if(vis[e.dest]==false)
            dfs(gr,e.dest,vis);
        }
    }




    public static void main(String[] args){
        int v=4;

        ArrayList<Edge> gr[]=new ArrayList[v];
        construct(gr);

        //print neighbours of 2
        for(int i=0;i<gr[2].size();i++){
            Edge edge=gr[2].get(i);
            System.out.println(edge.dest);
        }

        //bfs
        boolean vis[]=new boolean[v];
        for(int i=0;i<v;i++){
            if(vis[i]==false){
                bfs(gr,v,vis, i);
            }
        }

        //dfs
        boolean vis1[]=new boolean[v];
        System.out.println();
        for(int i=0;i<v;i++){
            if(vis1[i]==false){
            dfs(gr, i, vis1);
            }
        }
    }
}
