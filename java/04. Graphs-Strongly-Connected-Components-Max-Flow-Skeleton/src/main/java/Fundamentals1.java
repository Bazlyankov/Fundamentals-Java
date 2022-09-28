
import java.util.Scanner;

public class Fundamentals1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int lossesCount = Integer.parseInt(scanner.nextLine());

        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        double expenses = (lossesCount/2) * headsetPrice + (lossesCount/3) * mousePrice
                + (lossesCount/6) * keyboardPrice + (lossesCount/12) * displayPrice;

        System.out.printf("Rage expenses: %.2f lv.", expenses);






    }

}
