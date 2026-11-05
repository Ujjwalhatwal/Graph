import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph01BFS {

    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adjList){
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            Integer node = queue.poll();
            ans.add(node);

            for(int i : adjList.get(node)){
                if(!visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return ans;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adjList,
                           boolean[] visited,ArrayList<Integer> ans,int node){
        visited[node] = true;
        ans.add(node);
        for(int it : adjList.get(node)){
            if(!visited[it]){
                dfs(adjList,visited,ans, it);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            adjList.add(new ArrayList<>());
        }
        adjList.get(1).add(2);
        adjList.get(1).add(6);

        adjList.get(2).add(1);
        adjList.get(2).add(3);
        adjList.get(2).add(4);

        adjList.get(3).add(2);

        adjList.get(4).add(2);
        adjList.get(4).add(5);

        adjList.get(5).add(4);
        adjList.get(5).add(7);

        adjList.get(6).add(1);
        adjList.get(6).add(7);
        adjList.get(6).add(8);

        adjList.get(7).add(6);
        adjList.get(7).add(5);

        adjList.get(8).add(6);

        System.out.println("The adjacency list is : ");
        for(int i = 0; i < 9; i++){
            System.out.println(i + " : " +  adjList.get(i));
        }

        System.out.println("The BFS traversal is : ");
        ArrayList<Integer> ans = bfs(adjList);
        System.out.println(ans);

        System.out.println("The DFS traversal is : ");
        boolean[] visited = new boolean[9];
        ArrayList<Integer> ans1 = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            if(!visited[i]){
                dfs(adjList,visited,ans1,i);
            }
        }
        System.out.println(ans1);
    }

}
