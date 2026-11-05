import java.util.*;

public class Graph08PrintShortestPath {
    public static List<Integer> getShortestPathUsingDA(int[][] edges, int src, int V) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        for(int i = 0 ; i < V ;i++){
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adjList.get(u).add(new int[]{v, wt});
            adjList.get(v).add(new int[]{u, wt});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);

        int[] distance = new int[V+1];
        int[] parent = new int[V+1];
        for(int i = 1 ; i <= V ; i++){
            distance[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }

        distance[src] = 0;
        pq.add(new int[]{src,0});

        while(!pq.isEmpty()){
            int dist = pq.peek()[1];
            int node = pq.peek()[0];
            pq.remove();

            for(int[] it : adjList.get(node)){
                int edgeWeight = it[1];
                int adjNode = it[0];

                if(edgeWeight + dist < distance[adjNode]){
                    distance[adjNode] = edgeWeight + dist;
                    pq.add(new int[]{adjNode,distance[adjNode]});
                    parent[adjNode] = node;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        if(distance[src] == Integer.MAX_VALUE) return path;
        int node = V;
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        path.add(src);
        Collections.reverse(path);
        return path;
    }
}
