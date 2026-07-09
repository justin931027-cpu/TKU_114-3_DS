package week1_practice;

import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = -1;

        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu();

            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                case 2:
                case 3:
                case 4:
                    int price = getPrice(option);
                    String itemName = getItemName(option);

                    int quantity = readValidQuantity(sc);
                    int subtotal = calculateSubtotal(price, quantity);

                    if (option == 1) {
                        blackTeaCount += quantity;
                    } else if (option == 2) {
                        greenTeaCount += quantity;
                    } else if (option == 3) {
                        milkTeaCount += quantity;
                    } else if (option == 4) {
                        coffeeCount += quantity;
                    }

                    totalItems += quantity;
                    totalAmount += subtotal;

                    System.out.println(itemName + " x " + quantity);
                    System.out.println("Subtotal: " + subtotal);
                    break;

                case 0:
                    int discountTotal = calculateDiscountedTotal(totalAmount);
                    printReceipt(
                        blackTeaCount,
                        greenTeaCount,
                        milkTeaCount,
                        coffeeCount,
                        totalItems,
                        totalAmount,
                        discountTotal
                    );
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 45;
            case 4:
                return 50;
            default:
                return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Milk tea";
            case 4:
                return "Coffee";
            default:
                return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
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

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int)(totalAmount * 0.9);
        } else {
            return totalAmount;
        }
    }

    public static void printReceipt(
        int blackTeaCount,
        int greenTeaCount,
        int milkTeaCount,
        int coffeeCount,
        int totalItems,
        int totalAmount,
        int discountTotal
    ) {
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);

        if (totalAmount >= 300) {
            System.out.println("Discount: Yes");
        } else {
            System.out.println("Discount: No");
        }

        System.out.println("Final amount: " + discountTotal);
    }
}