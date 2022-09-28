import java.util.*;

public class Longest_Path {

    private static class Edge {

        public int start;
        public int end;
        public int weight;

        public Edge(int u, int v, int w) {
            this.start = u;
            this.end = v;
            this.weight = w;
        }

    }

    private static final Map<Integer, List<Edge>> edges = new HashMap<>();
    private static int[] distances;
    private static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int infinity = Integer.MAX_VALUE / 2;


        int vertexCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());

        distances = new int[vertexCount + 1];
        isVisited = new boolean[vertexCount + 1];
        Arrays.fill(distances, -infinity);

        for (int i = 0; i <= vertexCount; i++) {
            edges.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            int start = scanner.nextInt();
            edges.get(start).add(new Edge(start,  scanner.nextInt(), scanner.nextInt()));
        }

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        distances[start] = 0;

        List<Edge> edgesSorted = new ArrayList<>();
        bfs(edgesSorted, start);

        longestPath(edgesSorted);

        System.out.println(distances[end]);


    }

    private static void longestPath(List<Edge> edgesSorted) {

        for (Edge edge : edgesSorted) {
            int cost = distances[edge.start] + edge.weight;
            if (cost > distances[edge.end]) {
                distances[edge.end] = cost;
            }
        }

    }

    private static void bfs(List<Edge> edgesSorted, int start) {

        Deque<Integer> queue = new ArrayDeque<>();
         queue.offer(start);
         while(!queue.isEmpty()) {
             int current = queue.poll();
             isVisited[current] = true;
             for (Edge edge : edges.get(current )) {
                 edgesSorted.add(edge);
                 if(!isVisited[edge.end]) {
                     queue.offer(edge.end);
                 }
             }
         }

    }
}
