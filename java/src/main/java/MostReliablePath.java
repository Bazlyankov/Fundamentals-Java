import java.util.*;

public class MostReliablePath {


    private static double[][] paths;
    private static double[] reliability;
    private static int[] previous;

    private static int nodesCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        nodesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        String[] path = scanner.nextLine().split(" ");
        int start = Integer.parseInt(path[1]);
        int end = Integer.parseInt(path[3]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        paths = new double[nodesCount][nodesCount];

        for (int i = 0; i < edgesCount; i++) {
            int ind1 = scanner.nextInt();
            int ind2 = scanner.nextInt();
            paths[ind1][ind2] = paths[ind2][ind1] = 0.01*scanner.nextInt();
        }

        reliability = new double[nodesCount];
        reliability[start] = 1;

        previous = new int[nodesCount];
        Arrays.fill(previous, -1);
        newDijkstra(start);

        Deque<Integer> stack = new ArrayDeque<>();

        int current = end;
        while (current >= 0) {
            stack.push(current);
            current = previous[current];
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Most reliable path reliability: %.2f%%%n",100 * reliability[end]));
        stringBuilder.append(stack.pop());
        while (!stack.isEmpty()) {
            stringBuilder.append(" -> ").append(stack.pop());
        }
        System.out.println(stringBuilder);
    }

    private static void newDijkstra(int start) {

        Deque<Integer> queue =new ArrayDeque<>();

        queue.offer(start);

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < nodesCount; i++) {
                double reliable = paths[current][i];
                if (reliable > 0 && reliability[i] < reliability[current] * reliable) {
                    reliability[i] = reliability[current] * reliable;
                    previous[i] = current;
                    queue.offer(i);
                }
            }
        }

    }

}
