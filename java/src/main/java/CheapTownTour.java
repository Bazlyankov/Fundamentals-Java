import java.util.*;

public class CheapTownTour {


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

    private final static List<Edge> edges = new ArrayList<>();
    private final static List<Edge> mst = new ArrayList<>();

    private final static Set<Set<Integer>> edgeSets =new HashSet<>();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nodesCount = Integer.parseInt(scanner.nextLine());
        int edgesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edgesCount; i++) {

            int[] input = Arrays.stream(scanner.nextLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            edges.add(new Edge(input[0],input[1],input[2]));

        }

        edges.sort(Comparator.comparingInt(Edge::getWeight));

        findMST();

        int totalCost = 0;

        for (Edge edge : mst) {
            totalCost += edge.weight;
        }

        System.out.printf("Total cost: %d", totalCost);


    }

    private static void findMST() {

        for (Edge edge : edges) {
            Set<Integer> first = null;
            Set<Integer> second = null;

            for (Set<Integer> set : edgeSets) {
                if(set.contains(edge.start)) {
                    first = set;
                }
                if(set.contains(edge.end)) {
                    second = set;
                }
            }

            mst.add(edge);

            if (first == null && second == null) {
                Set<Integer> toAdd = new HashSet<>();
                toAdd.add(edge.start);
                toAdd.add(edge.end);
                edgeSets.add(toAdd);

            } else if (first == null) {
                second.add(edge.start);
            } else if (second == null) {
                first.add(edge.end);
            } else if (first != second){
                first.addAll(second);
                edgeSets.remove(second);
            } else {
                mst.remove(edge);
            }
        }



    }

}
