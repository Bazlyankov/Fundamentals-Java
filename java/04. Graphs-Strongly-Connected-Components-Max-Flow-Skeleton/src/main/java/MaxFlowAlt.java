import java.util.*;
import java.util.stream.Collectors;

public class MaxFlowAlt {

    private static int[][] adjacency;

    private static boolean[] isVisited;
    private static int start;
    private static int finish;

    private final static Map<Character,Integer> relations = new TreeMap<>();

    private static int[] previous;

    private static int tasksCount;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int peopleCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        tasksCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        int vertexCount = peopleCount + tasksCount + 2;
        isVisited = new boolean[vertexCount];
        start = vertexCount - 2;
        finish = vertexCount - 1;
        previous = new int[vertexCount];
        Arrays.fill(previous,-1);

        adjacency = new int[vertexCount][vertexCount];
        for (int i = 0; i < peopleCount; i++) {
            char[] input = scanner.nextLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                if (input[j] == 'Y') {
                    adjacency[tasksCount + i][j] = 1;
                }
            }
        }

        for (int j = 0; j < peopleCount; j++) {
            adjacency[vertexCount - 2][j + tasksCount] = 1;
        }

        for (int j = 0; j < tasksCount; j++) {
            adjacency[j][finish] = 1;
        }

        bfs();
        for(Map.Entry<Character,Integer> entry : relations.entrySet()) {
            System.out.println(entry.getKey() + "-" +entry.getValue());
        }


    }

    private static void bfs() {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        isVisited[start] = true;

        List<Integer> currentSorted = adjacencySorted();
        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == finish) {
                backtrack();
                return;
            }


            for (int i = 0; i <= finish; i++) {
                int index = currentSorted.get(i);
                if (adjacency[current][index] > 0 && !isVisited[index]) {
                    isVisited[index] = true;
                    previous[index] = current;
                    queue.offer(index);
                }
            }

        }

    }

    private static void backtrack() {

        isVisited = new boolean[finish + 1];
        int[] path = {start,previous[previous[finish]],previous[finish],finish};
        for (int i = 0; i < 3; i++) {
            adjacency[path[i]][path[i + 1]] = 0;
        }
        Arrays.fill(adjacency[path[1]],0);
        for (int i = 0; i < finish + 1; i++) {
            adjacency[i][path[2]] =  0;
        }
        relations.put((char)( 65 + path[1] - tasksCount),path[2] + 1);
        bfs();

    }
    
    public static List<Integer> adjacencySorted() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < finish + 1; i++) {
            list.add(i);
        }
        return list.stream().sorted(Comparator.comparing(MaxFlowAlt::sumColumn))
                .collect(Collectors.toList());



        
    }

    private static int sumColumn(int index) {
        int sum = 0;
        for (int i = 0; i <= finish; i++) {
            sum += adjacency[i][index];
        }
        return sum;
    }

}
