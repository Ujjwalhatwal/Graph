import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Pair{
    int first;
    int second;
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class Graph02DetectCycle {
    static boolean checkForCycleBFS(int src, int v, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        visited[src] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(src, -1));
        while (!queue.isEmpty()) {
            int node = queue.peek().first;
            int parent = queue.peek().second;
            queue.remove();
            for (int it : graph.get(node)) {
                if (!visited[it]) {
                    visited[it] = true;
                    queue.offer(new Pair(it, node));
                } else if (parent != it) {
                    return true;
                }
            }
        }
        return false;
    }
    static boolean checkForCycleDFS(int node,int parent, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for(int adjNode : graph.get(node)) {
            if (!visited[adjNode]) {
                if(checkForCycleDFS(adjNode, parent, graph, visited)) return true;
                else if (parent != adjNode) return true;
            }
        }
        return false;
    }
    static void main() {
        int v = 8;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>(Arrays.asList(2, 3)));
        adjList.add(new ArrayList<>(Arrays.asList(1, 5)));
        adjList.add(new ArrayList<>(Arrays.asList(1, 4, 6)));
        adjList.add(new ArrayList<>(Arrays.asList(3)));
        adjList.add(new ArrayList<>(Arrays.asList(2, 7)));
        adjList.add(new ArrayList<>(Arrays.asList(3, 7)));
        adjList.add(new ArrayList<>(Arrays.asList(5, 7)));

        boolean[] visited = new boolean[adjList.size()];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
//                if (checkForCycleBFS(i, v, adjList, visited)) {
//                    System.out.println("Cycle detected");
//                    return;
//                }
                if(checkForCycleDFS(i, v, adjList, visited)) {
                    System.out.println("Cycle detected");
                    return;
                }
            }
        }
    }
}
