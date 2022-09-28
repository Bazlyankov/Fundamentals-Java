import java.util.*;

public class KruskalAlgorithm {

    private static final Map<Integer, List<Edge>> localEdges = new HashMap<>();
    private static boolean[] isVisited;
    private static final Queue<Edge> queue = new PriorityQueue<>();

    private static final List<Edge> result = new ArrayList<>();

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {

        isVisited = new boolean[numberOfVertices];


        edges.sort(compareEdge);
        for (Edge edge : edges) {

            localEdges.putIfAbsent(edge.getStartNode(),new ArrayList<>());
            localEdges.putIfAbsent(edge.getEndNode(),new ArrayList<>());
            localEdges.get(edge.getStartNode()).add(edge);
            localEdges.get(edge.getEndNode()).add(edge);

        }


        for (int i = 0; i < numberOfVertices; i++) {
            prim(i);
        }



        result.sort(compareEdge);


        return result;
    }

    public static void prim(int current) {

        if(isVisited[current]) {
            return;
        }

        isVisited[current] = true;

        for (Edge edge : localEdges.get(current)) {

            if(usefulEdge(edge)) {
                queue.offer(edge);
            }

        }

        if(queue.isEmpty()){
            return;
        }

        Edge currentEdge;
        do {
            currentEdge = queue.poll();
        }while(!usefulEdge(currentEdge) && !queue.isEmpty());

        if(usefulEdge(currentEdge)) {
            result.add(currentEdge);
            prim( currentEdge.getStartNode());
            prim( currentEdge.getEndNode());
        }

    }



    private static boolean usefulEdge(Edge edge) {
        return !(isVisited[edge.getStartNode()] && isVisited[edge.getEndNode()]);
    }

    private static final Comparator<Edge> compareEdge = (e1, e2) -> {
        if(e1.compareTo(e2) != 0) {
            return e1.compareTo(e2);

        } else {
            return Integer.compare(e1.getStartNode(),e2.getStartNode());
        }
    };
}
