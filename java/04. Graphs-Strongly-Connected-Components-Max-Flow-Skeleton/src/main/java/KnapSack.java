import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapSack {


    private final static List<Integer> weights = new ArrayList<>();
    private final static List<Integer> values = new ArrayList<>();
    private static int dp[][];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());


        String input = scanner.nextLine();
        while (!input.equals("end")) {
            int[] tokens = Arrays.stream(input.split(" ")).skip(1).mapToInt(Integer::parseInt)
                    .toArray();
            weights.add(tokens[0]);
            values.add(tokens[1]);
            input = scanner.nextLine();
        }

        dp = new int[values.size() + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],-1);
        }

        System.out.println( recursion(0,0,capacity));


    }

    public static int recursion (int valueIndex, int weightIndex, int capacity) {

        if(weightIndex >= weights.size() || weights.get(weightIndex) > capacity) {
            return 0;
        }

        if(dp[valueIndex][capacity] >= 0) {
            return dp[valueIndex][capacity];
        }

        return dp[valueIndex][capacity] = Math.max(recursion(valueIndex + 1, weightIndex + 1,
                        capacity - weights.get(weightIndex)) + values.get(valueIndex),
                recursion(valueIndex + 1, weightIndex + 1, capacity));
    }


}
