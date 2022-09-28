import java.util.*;

public class Dijkstra {

    public static int[] prev;
    public static int[] distances;
    private static boolean[] isVisited;
    private final static Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(e -> distances[e]));

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        prev = new int[graph.length];
        distances = new int[graph.length];
        isVisited = new boolean[graph.length];
        Arrays.fill(prev, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[sourceNode]=0;
        queue.add(sourceNode);

        while(!queue.isEmpty()) {

            int current = queue.poll();
            isVisited[current] = true;

            for (int i = 0; i < graph.length; i++) {
                int cost = graph[i][current];
                if(cost > 0 && !isVisited[i]){
                    if(distances[current] + cost < distances[i]) {
                        prev[i] = current;
                        distances[i] = distances[current] + cost;
                    }
                    queue.offer(i);

                }
            }


        }

        if(prev[destinationNode]==-1){
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int current = destinationNode;
        while(current >= 0) {
            result.add(current);
            current = prev[current];
        }

        List<Integer> reverse = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            reverse.add(result.get(result.size()-1-i));
        }

        return reverse;
    }

}
