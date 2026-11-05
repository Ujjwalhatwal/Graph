import java.util.ArrayList;

public class Graph00Input {
    public static void main(String[] args) {
        int n = 3,m = 3;

        // 1. adjacency matrix : space complexity is n*n
//        int[][] adj = new int[n+1][n+1];
//
//        adj[1][2] = 1;
//        adj[2][1] = 1;
//
//        adj[1][3] = 1;
//        adj[3][1] = 1;
//
//        adj[2][3] = 1;
//        adj[3][2] = 1;

        //2. adjacency list : space is : 2*e for undirected , e for directed

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n ; i++) {
            adjList.add(new ArrayList<>());
        }

        // edge 1 -- 2
        adjList.get(1).add(2);
        adjList.get(2).add(1);

        // edge 2 -- 3
        adjList.get(2).add(3);
        adjList.get(3).add(2);

        //edge 1 -- 3
        adjList.get(1).add(3);
        adjList.get(3).add(1);

        for (int i = 1; i < n ; i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }
}
