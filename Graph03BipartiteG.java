import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph03BipartiteG {
    static boolean checkBFS(int st,int v,ArrayList<ArrayList<Integer>> adjList,int[] color){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(st);
        color[st]=0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int it : adjList.get(node)){
                if(color[it] == -1){
                    color[it] = 1 - color[node];
                    queue.add(it);
                }
                else if(color[it] == color[node]){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean checkDFS(int node,int col,ArrayList<ArrayList<Integer>> adjList,int[] color){
        color[node] = col;
        for(int it : adjList.get(node)){
            if(color[it] == -1){
                if(!checkDFS(it,1-col,adjList,color)) return false;
            }
            else if(color[it] == col) return false;
        }
        return true;
    }
    static void main() {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(Arrays.asList(2)));
        adjList.add(new ArrayList<>(Arrays.asList(1,3,6)));
        adjList.add(new ArrayList<>(Arrays.asList(2,4)));
        adjList.add(new ArrayList<>(Arrays.asList(3,5,7)));
        adjList.add(new ArrayList<>(Arrays.asList(6,4)));
        adjList.add(new ArrayList<>(Arrays.asList(2,5)));
        adjList.add(new ArrayList<>(Arrays.asList(4,8)));
        adjList.add(new ArrayList<>(Arrays.asList(7)));

        int v = 9;
        int[] color = new int[v];
        Arrays.fill(color, -1);
        for (int i = 1; i < v; i++) {
            if(color[i] == -1) {
//                if(!checkBFS(i,v,adjList,color)){
//                    System.out.println("Cycle detected, not able to fill the color");
//                    return;
//                }
                if(!checkDFS(i,0,adjList,color)){
                    System.out.println("odd Cycle detected, not able to color");
                    return;
                }
            }
        }
    }
}
