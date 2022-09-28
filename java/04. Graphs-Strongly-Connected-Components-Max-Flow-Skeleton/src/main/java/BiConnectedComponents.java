
import java.util.*;

public class BiConnectedComponents {

    private static boolean[][] adjacency;
    private static boolean[] isVisited;
    private static boolean[] isVisited2;
    private static int[] depths;
    private static int[] children;
    private static int[] parents;
    private static int[] lowPoints;
    private final static Set<Integer> artPoints = new HashSet<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nodeCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgeCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        adjacency = new boolean[nodeCount][nodeCount];
        isVisited = new boolean[nodeCount];
        depths = new int[nodeCount];
        Arrays.fill(depths,Integer.MIN_VALUE);
        children = new int[nodeCount];
        parents = new int[nodeCount];
        lowPoints = new int[nodeCount];
        Arrays.fill(parents,-1);
        depths = new int[nodeCount];
        Arrays.fill(depths,Integer.MAX_VALUE);
        Arrays.fill(lowPoints,Integer.MAX_VALUE);
        for (int i = 0; i < edgeCount; i++) {
            int[] edge = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            adjacency[edge[0]][edge[1]] = adjacency[edge[1]][edge[0]] = true;
        }

        dfs(0,0);




        if(children[0] > 1) {
            artPoints.add(0);
        }

        for (int i = 1; i < nodeCount; i++) {
            isVisited = new boolean[nodeCount];
            if(findLowPoints(i) >= depths[parents[i]]){
                artPoints.add(parents[i]);
            }
        }


        int childrenTotal = 0;
        int coupledGraphs = 0;

        for (int artPoint : artPoints) {
            isVisited = new boolean[nodeCount];
            isVisited2 = new boolean[nodeCount];
            dfs(artPoint,0);

            for (int i = 0; i < nodeCount; i++) {
                if(parents[i] == artPoint ) {
                    childrenTotal++;

                    if(containsArtPoints(i)) {
                        coupledGraphs++;
                    }
                }
            }

        }

        System.out.println("Number of bi-connected components: " + (childrenTotal-coupledGraphs/2));


    }

    private static int findLowPoints(int node) {

        if(lowPoints[node] < depths[node] || depths[node] == 0) {
            return lowPoints[node];
        }

        boolean[] temp = adjacency[parents[node]];
        adjacency[parents[node]] = new boolean[adjacency.length];
        for (int i = 0; i < adjacency.length; i++) {
            adjacency[i][parents[node]] = adjacency[parents[node]][i]  = false;
        }

        for (int i = 0; i < adjacency.length; i++) {
            if (adjacency[node][i] && !isVisited[i]) {
                isVisited[i] = true;
                lowPoints[node] = Math.min(lowPoints[node],findLowPoints(i));
            }
        }

        for (int i = 0; i < adjacency.length; i++) {
            adjacency[i][parents[node]] = adjacency[parents[node]][i]  = temp[i];
        }

        return lowPoints[node];


    }

    private static void dfs(int node, int depth) {

        isVisited[node] = true;
        depths[node] = depth;
        lowPoints[node] = depth;
        for (int i = 0; i < adjacency.length; i++) {
            if(adjacency[node][i] && !isVisited[i]) {
                parents[i] = node;
                children[node]++;
                dfs(i,depth + 1);
            }
        }

    }

    private static boolean containsArtPoints(int node) {

        if(artPoints.contains(node)) {
            return true;
        }

        boolean flag = false;

        boolean[] temp = adjacency[parents[node]];
        adjacency[parents[node]] = new boolean[adjacency.length];
        for (int i = 0; i < adjacency.length; i++) {
            adjacency[i][parents[node]] = adjacency[parents[node]][i]  = false;
        }

        for (int i = 0; i < adjacency.length; i++) {
            if (adjacency[node][i] && !isVisited2[i]) {
                isVisited2[i] = true;
                if(containsArtPoints(i)) {
                    flag = true;
                }
            }
        }

        for (int i = 0; i < adjacency.length; i++) {
            adjacency[i][parents[node]] = adjacency[parents[node]][i]  = temp[i];
        }

        return flag;


    }


}
