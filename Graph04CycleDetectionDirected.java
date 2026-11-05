import java.util.*;

public class Graph04CycleDetectionDirected {
    static boolean dfs(int node,boolean[] visited,int[] path,ArrayList<ArrayList<Integer>> adjList){
        visited[node]=true;
        path[node]=1;
        for(int item:adjList.get(node)){
            if(!visited[item]){
                if(dfs(item,visited,path,adjList)) return true;
            }
            else if(path[item]==1) return true;
        }
        path[node]=0;
        return false;
    }

    static  boolean usingKahnAlgo(int v,ArrayList<ArrayList<Integer>> adjList){
        int[] indegree = new int[v];
        for(int i=0;i<v;i++){
            for(int node :adjList.get(i)){
                indegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        int count = 0;
        while (!q.isEmpty()){
            int node = q.poll();
            count++;
            for(int adjNode : adjList.get(node)){
                indegree[adjNode]--;
                if(indegree[adjNode]==0){
                    q.add(adjNode);
                }
            }
        }
        return count!=v;
    }
    static void main() {

        int v = 11;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(4,7)));
        adjList.add(new ArrayList<>(List.of(5)));
        adjList.add(new ArrayList<>(List.of(6)));
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(5)));
        adjList.add(new ArrayList<>(List.of(9)));
        adjList.add(new ArrayList<>(List.of(10)));
        adjList.add(new ArrayList<>(List.of(8)));

        boolean[] visited = new boolean[v];
        int[] path =  new int[v];
        boolean check = false;
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                if(dfs(i,visited,path,adjList)){
                    check = true;
                    System.out.println("cycle detected");
                }
            }
        }
        if(!check) System.out.println("cycle not detected");

        if(usingKahnAlgo(v,adjList)){
            System.out.println("cycle detected using Kahn's algo");
        }
         else System.out.println("cycle not detected using Kahn's algo");
    }
}


// for eventual safe state just make the list of node which are not part of cycle or leading to cycle
// for that make a check[] , and in dfs call just mark as check[node] = 1 just before path[node] = 0;