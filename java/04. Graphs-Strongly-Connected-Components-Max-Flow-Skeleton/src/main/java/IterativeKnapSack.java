import java.util.*;
import java.util.stream.Collectors;

public class IterativeKnapSack {

    private static class Item {
        String name;
        int value;
        int weight;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

    private static int[][] dp;
    private static boolean[][] dpAdd;

    private static final List<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            int[] tokens = Arrays.stream(input.split(" "))
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<String> name = Arrays.stream(input.split(" ")).limit(1).collect(Collectors.toList());
            items.add(new Item(name.get(0), tokens[0], tokens[1]));
            input = scanner.nextLine();
        }

        dp = new int[items.size() + 1][capacity + 1];
        dpAdd = new boolean[items.size() + 1][capacity + 1];

        for (int i = 1; i <= items.size(); i++) {
            Item item = items.get(i - 1);
            for (int j = 1; j <= capacity; j++) {
                if (j >= item.weight) {
                    if (item.value + dp[i - 1][j - item.weight] > dp[i - 1][j]) {
                        dpAdd[i][j] = true;
                        dp[i][j] = item.value + dp[i - 1][j - item.weight];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        Deque<Item> stack = new ArrayDeque<>();
        Set<String> set = new TreeSet<>();

        int i = items.size();
        int j = capacity;
        int totalWeight = 0;


        while (i >= 0) {
            if(dpAdd[i][j]) {
                Item item = items.get(i - 1);
                stack.push(item);
                set.add(item.name);
                j -= item.weight;
                totalWeight += item.weight;
            }
            i--;
        }

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Total Value: " + dp[items.size()][capacity]);

        for (String name : set) {
            System.out.println(name);
        }


    }
}
