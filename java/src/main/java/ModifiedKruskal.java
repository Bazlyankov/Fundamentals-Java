import java.util.*;

public class ModifiedKruskal {

    private static class Node {
        public int index;
        public Node parent;
        public List<Node> children = new ArrayList<>();

        public Node(int index) {
            this.index = index;
            this.parent = this;
        }

    }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight && Objects.equals(start, edge.start) && Objects.equals(end, edge.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, weight);
        }
    }

    private static final List<Edge> edges  = new ArrayList<>();
    private static final List<Node> nodes  = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        for (int i = 0; i <= nodesCount; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < edgesCount; i++) {
            edges.add(new Edge(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
        }

        edges.sort(Comparator.comparingInt(Edge::getWeight));

        List<Edge> mst = new ArrayList<>();

        int totalWeight = 0;

        for (Edge edge : edges) {
            if (isUsefulEdge(edge)) {
                mergeSets(nodes.get(edge.start).parent,nodes.get(edge.end).parent);
                totalWeight += edge.weight;
                mst.add(edge);
            }
        }
        System.out.println("Minimum spanning forest weight: " + totalWeight);

//        for (Edge edge : mst) {
//            System.out.printf("(%d %d) -> %d%n",edge.start,edge.end,edge.weight);
//        }
    }

    private static void mergeSets(Node first, Node second) {

        first.children.addAll(second.children);
        for (Node child : second.children) {
            child.parent = first;
        }
        first.children.add(second);
        second.parent.children.clear();
        second.parent = first;

    }

    private static boolean isUsefulEdge(Edge edge) {
        return !(nodes.get(edge.start).parent.equals(nodes.get(edge.end).parent));
    }

}
