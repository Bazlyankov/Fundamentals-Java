import java.util.*;
public class SupplementGraph {

    private static boolean[][] graph;


    private static boolean[] isVisited;
    private final static Deque<Integer> stack = new ArrayDeque<>();

    public static List<List<Integer>> components = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nodeCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgeCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        isVisited = new boolean[nodeCount];

        graph = new boolean[nodeCount][nodeCount];

        for (int i = 0; i < edgeCount; i++) {
            int[] edge = Arrays.stream(scanner.nextLine().split(" -> "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[edge[0]][edge[1]] = true;
            graph[edge[0]][edge[1]] = true;
        }

        for (int i = 0; i < nodeCount; i++) {
            dfsSort(i);
        }


        isVisited = new boolean[nodeCount];

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if(!isVisited[node]) {
                components.add(dfsComponents(node,new ArrayList<>()));
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        int in = 0;
        int out = 0;

        for (int i = 0; i < components.size(); i++) {
            boolean flagOut = false;
            boolean flagIn = false;
                for (int j = 0; j < components.size(); j++) {
                    if(i==j) continue;
                    List<Integer> first = components.get(i);
                    List<Integer> second = components.get(j);
                    if(connected(first,second)) {
                        flagOut = true;
                        break;
                    }
                    if(connected(second,first)) {
                        flagIn = true;
                        break;
                    }
                }

                if(flagIn) in++;
                if(!flagOut) out++;
        }



        stringBuilder.append("New edges needed: ").append(Math.max(in,out));
        System.out.println(stringBuilder);

    }

    private static void dfsSort(int node) {

        if(isVisited[node]) {
            return;
        }

        isVisited[node] = true;
        stack.push(node);

        for (int i = 0; i < graph.length; i++) {
            if(graph[node][i] && !isVisited[i]) {
                dfsSort(i);
            }
        }

        stack.push(node);
    }

    private static List<Integer> dfsComponents(int node, List<Integer> component) {

        if(isVisited[node]) {
            return null;
        }

        isVisited[node] = true;


        for (int i = 0; i < graph.length; i++) {
            if(graph[i][node] && !isVisited[i]) {
                dfsComponents(i,component);

            }
        }

        component.add(node);

        return component;

    }

    private static boolean connected(List<Integer> first, List<Integer> second) {
        for (int ind1 : first) {
            for (int ind2 : second) {
                if(graph[ind1][ind2]) {
                    return true;
                }
            }
        }
        return false;
    }

}
