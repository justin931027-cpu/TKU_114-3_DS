import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        int number = scanner.nextInt();

        while (number != 0) {
            System.out.println(number);

            System.out.print("Enter number: ");
            number = scanner.nextInt();
        }

        scanner.close();
    }
}