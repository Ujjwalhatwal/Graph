import java.util.*;

public class Graph05TopoSort {
    static void dfs(int node,ArrayList<ArrayList<Integer>> adjList,boolean[] visited,Stack<Integer> stack){
        visited[node]=true;
        for(int v:adjList.get(node)){
            if(!visited[v]){
                dfs(v,adjList,visited,stack);
            }
        }
        stack.push(node);
    }
    static void KahnAlgoBFS(int v,ArrayList<ArrayList<Integer>> adjList){
        int[] indegree = new int[v];

        // calculate the indegree of nodes
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
        int idx = 0;
        int[] topo = new int[v];
        while (!q.isEmpty()){
            int node = q.poll();
            topo[idx++] = node;
            for(int adjNode : adjList.get(node)){
                indegree[adjNode]--;
                if(indegree[adjNode]==0){
                    q.add(adjNode);
                }
            }
        }
        System.out.println("topo sort using Kahn's algo");
        for(int i = 0 ; i < topo.length ; i++){
            System.out.print(topo[i]+" ");
        }
    }
    static void main() {
        int v = 6;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(0,1)));
        adjList.add(new ArrayList<>(List.of(0,2)));

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        for(int i = 0 ; i < v ; i++){
            if(!visited[i]){
                dfs(i,adjList,visited,stack);
            }
        }
        System.out.println("The topological sort is : ");

        while(!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");
        }
        System.out.println();
        KahnAlgoBFS(v,adjList);
    }
}
