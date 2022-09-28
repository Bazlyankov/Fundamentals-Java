import java.util.*;

public class Bellman_Ford {

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

    private static final Set<Edge> edges = new HashSet<>();
    private static int[] distances;
    private static int[] prev;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int infinity = Integer.MAX_VALUE/2;


        int vertexCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());

        distances = new int[vertexCount + 1];
        prev = new int[vertexCount + 1];
        Arrays.fill(distances,infinity);
        Arrays.fill(prev,-1);

        for (int i = 0; i < edgeCount; i++) {
            edges.add(new Edge(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
        }

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        distances[start] = 0;

        boolean flag = false;

        for (int i = 0; i < vertexCount; i++) {

            flag = false;

            for (Edge edge : edges) {
                if(distances[edge.start] < infinity &&
                        distances[edge.start] + edge.weight < distances[edge.end]) {
                    flag = true;
                    prev[edge.end] = edge.start;
                    distances[edge.end] = distances[edge.start] + edge.weight;
                }
            }

        }

        if (flag) {
            System.out.println("Negative Cycle Detected");
            return;
        }

        List<Integer> path = new ArrayList<>();

        int current = end;

        while(current >= 0) {
            path.add(current);
            current = prev[current];
        }

        Collections.reverse(path);
        String result = new String();

        for (int node : path) {
            result += (node + " ");
        }
        System.out.println(result.trim());
        System.out.println(distances[end]);


    }

}
