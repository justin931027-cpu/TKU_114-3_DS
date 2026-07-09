import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = -1;
        int quantity;
        int subtotal;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu();

            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    quantity = readQuantity(sc);
                    subtotal = calculateSubtotal(30, quantity);
                    totalItems += quantity;
                    totalAmount += subtotal;
                    System.out.println("Subtotal: " + subtotal);
                    break;

                case 2:
                    quantity = readQuantity(sc);
                    subtotal = calculateSubtotal(35, quantity);
                    totalItems += quantity;
                    totalAmount += subtotal;
                    System.out.println("Subtotal: " + subtotal);
                    break;

                case 3:
                    quantity = readQuantity(sc);
                    subtotal = calculateSubtotal(50, quantity);
                    totalItems += quantity;
                    totalAmount += subtotal;
                    System.out.println("Subtotal: " + subtotal);
                    break;

                case 0:
                    printReceipt(totalItems, totalAmount);
                    break;

                default:
                    System.out.println("Unknown option");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int readQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();

        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            quantity = sc.nextInt();
        }

        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return 0;
        }
    }
}