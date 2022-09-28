import java.util.*;

public class EdmondsKarp {

    private static int[][] adjacency;
    private static int[] parents;
    private static boolean[] isVisited;
    private static int n;

    private static int maxFlow;
    public static int findMaxFlow(int[][] targetGraph) {

        n = targetGraph.length;
        maxFlow = 0;
        adjacency = targetGraph;
        isVisited = new boolean[n];
        parents = new int[n];
        Arrays.fill(parents,-1);

        bfs();

        return maxFlow;
    }

    private static void bfs() {


        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == n - 1) {
                backtrack();
                bfs();
                return;
            }



            for (int i = 0; i < n; i++) {
                if (adjacency[current][i] > 0 && !isVisited[i]) {
                    parents[i] = current;
                    isVisited[i] = true;
                    queue.offer(i);
                }
            }
        }

    }

    private static void backtrack() {

        List<Integer> path = new ArrayList<>();

        int current = n - 1;

        while (current >= 0) {
            path.add(current);
            current = parents[current];
        }

        Collections.reverse(path);

        int pathFlow = Integer.MAX_VALUE;

        for (int i = 0; i < path.size() - 1; i++) {
            int currentFlow = adjacency[path.get(i)][path.get(i+1)];
            if (currentFlow < pathFlow) {
                pathFlow = currentFlow;
            }
        }

        for (int i = 0; i < path.size() - 1; i++) {
            adjacency[path.get(i)][path.get(i+1)] -= pathFlow;

        }

        maxFlow += pathFlow;
        isVisited = new boolean[n];

    }
}
