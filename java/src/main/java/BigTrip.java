import java.util.*;

public class BigTrip {

    private static int[][] adjMatrix;
    private static int nodesCount;

    private static final int INFINITY = Integer.MAX_VALUE;
    private static int[] distances;

    private static int[] previous;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        nodesCount = Integer.parseInt(scanner.nextLine());
        distances = new int[nodesCount + 1];
        adjMatrix = new int[nodesCount + 1][nodesCount + 1];
        for (int i = 0; i < nodesCount + 1; i++) {
            Arrays.fill(adjMatrix[i],-INFINITY);
        }
        Arrays.fill(distances,-INFINITY);
        previous = new int[nodesCount + 1];
        Arrays.fill(previous,0);
        int edgesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edgesCount; i++) {

            int[] input = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            adjMatrix[input[0]][input[1]] = input[2];

        }

        Set<Integer> sorted = new HashSet<>();

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        distances[start] = 0;
        bfs(start,sorted);

        longestPath(sorted);

        List<Integer> path = new ArrayList<>();

        int current = end;

        while (current > 0) {
            path.add(current);
            current = previous[current];
        }

        System.out.println(distances[end]);
        Collections.reverse(path);
        for (int node : path) {
            System.out.print(node + " ");
        }

    }

    private static void longestPath(Set<Integer> sorted) {

        for (int node : sorted) {
            for (int j = 1; j <= nodesCount; j++) {
                int newDist = distances[node] + adjMatrix[node][j];
                if (adjMatrix[node][j] > -INFINITY && distances[j] < newDist) {
                    distances[j] = newDist;
                    previous[j] = node;
                }
            }
        }
    }






    private static void bfs(int start, Set<Integer> sorted) {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sorted.add(current);
            for (int i = 1; i <= nodesCount; i++) {
                if (adjMatrix[current][i] > -INFINITY) {
                    queue.offer(i);
                }
            }
        }



    }

}
