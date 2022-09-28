import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MaxFlowExercise {

    private static int start;
    private static int finish;
    private static int[] previous;
    private static int[][] adjacency;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int peopleCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int tasksCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        int vertexCount = peopleCount + tasksCount + 2;
        start = vertexCount - 2;
        finish = vertexCount - 1;
        previous = new int[vertexCount];
        Arrays.fill(previous,-1);

        adjacency = new int[vertexCount][vertexCount];
        for (int i = 0; i < peopleCount; i++) {

            char[] input = scanner.nextLine().toCharArray();
            for (int j = 0; j < tasksCount; j++) {
                if (input[j] == 'Y') {
                    adjacency[i][peopleCount + j] = 1;
                }
            }
        }

        for (int j = 0; j < peopleCount; j++) {
            adjacency[start][j] = 1;
        }

        for (int i = 0; i < tasksCount; i++) {
            adjacency[peopleCount + i][finish] = 1;
        }

        while (bfs()) {
            int current = finish;
            while (current != start) {
                adjacency[current][previous[current]] = 1;
                adjacency[previous[current]][current] = 0;
                current = previous[current];
            }
        }

        for (int i = 0; i < peopleCount; i++) {
            for (int j = 0; j < tasksCount; j++) {
             if (adjacency[peopleCount + j][i] > 0) {

                 System.out.println((char)(65 + i) + "-" + (j+1));

             }

            }

        }




    }

    private static boolean bfs() {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] isVisited = new boolean[finish + 1];
        isVisited[start] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();


            for (int i = finish; i >= 0; i--) {
                if(adjacency[current][i] > 0 && !isVisited[i]) {
                    isVisited[i] = true;
                    previous[i] = current;
                    queue.offer(i);
                }
            }
        }

        return isVisited[finish];

    }

}
