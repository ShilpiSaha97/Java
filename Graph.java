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
    
    
        public static void printAllPaths(ArrayList<Edge> gr[], int source, int target, String str,boolean vis[]){
        if(source==target){
            System.out.println(str);
            return;
        }

        for(int i=0;i<gr[source].size();i++){
            Edge e=gr[source].get(i);
            if(!vis[e.dest]){
                vis[source]=true;
                printAllPaths(gr,e.dest,target,str+e.dest,vis);
                vis[source]=false;
            }
        }

    }
    
    

    public static boolean isCycleDirected(ArrayList<Edge> gr[], boolean[] vis, int src, boolean[] recursionStack){
        vis[src]=true;
        recursionStack[src]=true;

        for(int i=0;i<gr[src].size();i++){
            Edge e=gr[src].get(i);
            //cycle condition
            if(recursionStack[e.dest]==true)
                return true;

            else if(vis[e.dest]==false){
                if(isCycleDirected(gr,vis,e.dest, recursionStack)){
                    return true;
                }
            }
        }
        recursionStack[src]=false;
        return false;
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
        
        //print all path from src to dest
        boolean[] vis=new boolean[v];
        printAllPaths(gr,0,3,"0",vis);
        
        //is cyclic directed graph
        boolean[] vis=new boolean[v];
        boolean[] rec=new boolean[v];
        for(int i=0;i<v;i++){
            if(!vis[i]){
                boolean iscycle=isCycleDirected(gr,vis,0,rec);
                if(iscycle){
                    System.out.println(iscycle);
                    break;
                }
            }
        }
        
    }
}
