import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int option = -1;
        int amount;

        while (option != 0) {
            printMenu();

            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    amount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, amount);
                    System.out.println("存款成功");
                    printBalance(balance);
                    break;

                case 3:
                    amount = readPositiveAmount(sc, "請輸入提款金額：");

                    if (amount > balance) {
                        System.out.println("提款失敗，餘額不足");
                    } else {
                        balance = withdraw(balance, amount);
                        System.out.println("提款成功");
                    }

                    printBalance(balance);
                    break;

                case 0:
                    System.out.println("離開 ATM 系統");
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();

        while (amount <= 0) {
            System.out.print("金額必須大於 0，請重新輸入：");
            amount = sc.nextInt();
        }

        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額：" + balance);
    }
}