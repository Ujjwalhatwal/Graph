import java.util.*;

public class Graph07DijkstraImplementation {

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
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

        int[] distance = new int[V];
        Arrays.fill(distance,Integer.MAX_VALUE);

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
                }
            }
        }

        return distance;
    }
    // shortest path in undirected graph with unit distance
    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0 ; i < V; i++)
            adjList.add(new ArrayList<>());

        // adjList generation
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // distance array
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.add(src);

        while(!q.isEmpty()){
            int node = q.poll();
            for(int it : adjList.get(node)){
                if((dist[node] + 1) < dist[it]){
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }

        for(int i = 0 ; i < V ; i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;
    }
}
