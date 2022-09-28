import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    private static boolean[][] adjacency;
    private static boolean[] isVisited;
    private static int[] parent;
    private static int[] children;
    private static int[] depth;
    private static int[] lowPoint;
    private static int n;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {

        n = targetGraph.length;
        adjacency = new boolean[n][n];
        isVisited = new boolean[n];
        parent = new int[n];
        lowPoint = new int[n];
        Arrays.fill(parent,-1);
        children = new int[n];
        depth = new int[n];
        Arrays.fill(depth,Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j : targetGraph[i]) {
                ArticulationPoints.adjacency[i][j] = adjacency[j][i] = true;
            }
        }

        List<Integer> result = new ArrayList<>();

        dfs(0,0);

        if (n >= 0) System.arraycopy(depth, 0, lowPoint, 0, n);

        isVisited = new boolean[n];
        for (int i = 1; i < n; i++) {

            calculateLowPoint(i);
        }

        if(children[0] > 1) {
            result.add(0);
        }

        for (int i = 1; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if(parent[j] == i && lowPoint[j] >= depth[i]) { //check index
                    flag = true;
                    break;
                }

            }

            if(flag) {
                result.add(i);
            }
        }

        return result;
    }

    private static void dfs(int node, int currentDepth) {

        if (isVisited[node]) {
            return;
        }

        isVisited[node] = true;
        depth[node] = currentDepth;

        for (int i = 0; i < n; i++) {
            if(adjacency[node][i] && !isVisited[i]) {
                parent[i] = node;
                children[node]++;
                dfs(i,currentDepth + 1);
            }
        }

    }

    private static int calculateLowPoint(int node) {
        if(lowPoint[node] < depth[node] || isVisited[node] || node == 0) {
            return lowPoint[node];
        }

        isVisited[node] = true;

        int result = depth[node];

        adjacency[node][parent[node]]= adjacency[parent[node]][node] = false;

        for (int i = 0; i < n; i++) {
            if(adjacency[node][i] && calculateLowPoint(i) < result) {
                result = calculateLowPoint(i);
            }
        }

        lowPoint[node] = result;
        adjacency[node][parent[node]]= adjacency[parent[node]][node] = true;

        return result;
    }
}
