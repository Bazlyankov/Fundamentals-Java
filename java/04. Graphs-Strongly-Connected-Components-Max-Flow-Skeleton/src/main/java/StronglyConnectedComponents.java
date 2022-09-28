import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StronglyConnectedComponents {

    private static boolean[] isVisited;
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static boolean[][] adjacency;
    private static int n;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {

        n = targetGraph.length;
        isVisited = new boolean[n];
        adjacency = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j : targetGraph[i]) {
                adjacency[i][j] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            dfs(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        isVisited = new boolean[n];

        while (!stack.isEmpty()) {
            int node = stack.pop();
            List<Integer> scc = findComponent(node, new ArrayList<>());
            if (scc != null) {
                result.add(scc);
            }
        }

        return result;
    }

    private static List<Integer> findComponent(int node, List<Integer> result) {

        if(isVisited[node]) return null;

        isVisited[node] = true;

        for (int i = 0; i < n; i++) {
            if (adjacency[i][node] && !isVisited[i]) {
                findComponent(i, result);
            }
        }

        result.add(node);

        return result;

    }

    private static void dfs(int node) {

        if (isVisited[node]) {
            return;
        }

        isVisited[node] = true;

        for (int i = 0; i < n; i++) {
            if(adjacency[node][i]) {
                dfs(i);
            }
        }

        stack.push(node);

    }
}
