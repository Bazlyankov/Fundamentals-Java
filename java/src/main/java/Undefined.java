import java.util.*;

public class Undefined {

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

    private final static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final int INFINITY = Integer.MAX_VALUE;

        int nodesCount = Integer.parseInt(scanner.nextLine());
        int[] distances = new int[nodesCount + 1];
        Arrays.fill(distances,Integer.MAX_VALUE);
        int[] previous = new int[nodesCount + 1];
        Arrays.fill(previous,0);
        int edgesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edgesCount; i++) {

            int[] input = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            edges.add(new Edge(input[0],input[1],input[2]));

        }

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        boolean flag = false;

        distances[start] = 0;

        for (int i = 1; i < nodesCount + 3; i++) {

            flag = false;

            for (Edge edge : edges) {
                if (distances[edge.start] < INFINITY && distances[edge.end] > distances[edge.start] + edge.weight) {

                    distances[edge.end] = distances[edge.start] + edge.weight;
                    previous[edge.end] = edge.start;
                    flag = true;
                    break;

                }
            }

        }

        if (flag) {
            System.out.println("Undefined");
            return;
        }

        Deque<Integer> stack =new ArrayDeque<>();

        int current = end;

        while (current > 0) {
            stack.push(current);
            current = previous[current];
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int node : stack) {
            stringBuilder.append(node).append(" ");
        }

        System.out.println(stringBuilder.toString().trim());
        System.out.println(distances[end]);





    }

}
