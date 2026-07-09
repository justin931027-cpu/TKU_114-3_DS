import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = -1;
        int quantity = 0;
        int subtotal = 0;

        int totalQuantity = 0;
        int totalAmount = 0;

        while (option != 0) {
            System.out.println("=== Drink Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入商品選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("請輸入數量：");
                    quantity = sc.nextInt();

                    while (quantity <= 0) {
                        System.out.print("數量必須大於 0，請重新輸入：");
                        quantity = sc.nextInt();
                    }

                    subtotal = quantity * 30;
                    totalQuantity += quantity;
                    totalAmount += subtotal;

                    System.out.println("本次小計：" + subtotal);
                    break;

                case 2:
                    System.out.print("請輸入數量：");
                    quantity = sc.nextInt();

                    while (quantity <= 0) {
                        System.out.print("數量必須大於 0，請重新輸入：");
                        quantity = sc.nextInt();
                    }

                    subtotal = quantity * 35;
                    totalQuantity += quantity;
                    totalAmount += subtotal;

                    System.out.println("本次小計：" + subtotal);
                    break;

                case 3:
                    System.out.print("請輸入數量：");
                    quantity = sc.nextInt();

                    while (quantity <= 0) {
                        System.out.print("數量必須大於 0，請重新輸入：");
                        quantity = sc.nextInt();
                    }

                    subtotal = quantity * 50;
                    totalQuantity += quantity;
                    totalAmount += subtotal;

                    System.out.println("本次小計：" + subtotal);
                    break;

                case 0:
                    System.out.println("=== Checkout ===");
                    System.out.println("Total quantity: " + totalQuantity);
                    System.out.println("Total amount: " + totalAmount);
                    break;

                default:
                    System.out.println("Unknown option");
            }

            System.out.println();
        }

        sc.close();
    }
}