import java.util.*;

public class Connect_Customers {

    private static class Edge {

        public int start;
        public int end;
        public int weight;

        public Edge(int u, int v, int w) {
            this.start = u;
            this.end = v;
            this.weight = w;
        }

        public int getWeight() {
            return this.weight;
        }

    }

    private static final Set<Integer> connected = new HashSet<>();
    private static final List<Edge> edges = new ArrayList<>();
    public static void main(String[] args) {

        Scanner scanner  = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int nodesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length > 3) {
                connected.add(Integer.parseInt(input[0]));
                connected.add(Integer.parseInt(input[1]));
            } else {
                edges.add(new Edge(Integer.parseInt(input[0]),Integer.parseInt(input[1]),Integer.parseInt(input[2])));
            }

        }

        edges.sort(Comparator.comparingInt(Edge::getWeight));

        int budgetUsed = 0;
        int i = 0;

        while (budgetUsed >= 0 && i < nodesCount) {

            for (Edge edge : edges) {

                if (connected.contains(edge.start)^connected.contains(edge.end)
                        && budgetUsed + edge.weight <= budget) {

                    if (connected.contains(edge.end)) {
                        connected.add(edge.start);
                    } else {
                        connected.add(edge.end);
                    }
                    budgetUsed += edge.getWeight();

                    break;

                }

            }
            i++;

        }

        System.out.println("Budget used: " + budgetUsed);

    }

}
