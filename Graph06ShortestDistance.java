import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Graph06ShortestDistance {

    static void dfs(ArrayList<ArrayList<int[]>> adj, int[] vis, Stack<Integer> st, int node) {
        vis[node] = 1;
        for (int[] it : adj.get(node)) {
            int curr = it[0];
            if (vis[curr] != 1) {
                dfs(adj, vis, st, curr);
            }
        }
        st.add(node);
    }

    static int[] shortestPath(int V, int E, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adjList.get(u).add(new int[]{v, wt});
        }

        int[] visited = new int[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (visited[i] != 1) {
                dfs(adjList, visited, st, i);
            }
        }

        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int source = 6;
        distance[source] = 0;

        while (!st.isEmpty()) {
            int node = st.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (int[] it : adjList.get(node)) {
                    int v = it[0];
                    int wt = it[1];
                    if (distance[node] + wt < distance[v]) {
                        distance[v] = distance[node] + wt;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int V = 7;
        int E = 8;
        int[][] edges = {
                {4, 0, 3},
                {0, 1, 2},
                {1, 3, 1},
                {6, 4, 2},
                {6, 5, 3},
                {5, 4, 1},
                {4, 2, 1},
                {2, 3, 3}
        };

        int[] ans = shortestPath(V, E, edges);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
