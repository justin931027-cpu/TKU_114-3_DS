import java.util.Scanner;

public class SalesMatrix {

    // 輸入三項商品四天的銷售量
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int product = 0; product < sales.length; product++) {
            for (int day = 0; day < sales[product].length; day++) {

                while (true) {
                    System.out.print(
                        "請輸入商品 " + (product + 1)
                        + " 第 " + (day + 1) + " 天的銷售量："
                    );

                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();

                        if (value >= 0) {
                            sales[product][day] = value;
                            break;
                        } else {
                            System.out.println("輸入錯誤，銷售量不得小於 0。");
                        }
                    } else {
                        System.out.println("輸入錯誤，請輸入整數。");
                        sc.next();
                    }
                }
            }
        }
    }

    // 以表格形式顯示銷售資料
    public static void printSalesTable(int[][] sales) {
        System.out.println("\n銷售矩陣報表：");
        System.out.printf("%-10s", "商品");

        for (int day = 0; day < sales[0].length; day++) {
            System.out.printf("%8s", "第" + (day + 1) + "天");
        }

        System.out.println();

        for (int product = 0; product < sales.length; product++) {
            System.out.printf("%-10s", "商品" + (product + 1));

            for (int day = 0; day < sales[product].length; day++) {
                System.out.printf("%8d", sales[product][day]);
            }

            System.out.println();
        }
    }

    // 計算指定商品的銷售總量
    public static int calculateProductTotal(int[][] sales, int product) {
        int total = 0;

        for (int day = 0; day < sales[product].length; day++) {
            total += sales[product][day];
        }

        return total;
    }

    // 顯示各項商品的銷售總量
    public static void printProductTotals(int[][] sales) {
        System.out.println("\n各商品銷售總量：");

        for (int product = 0; product < sales.length; product++) {
            int total = calculateProductTotal(sales, product);

            System.out.println(
                "商品 " + (product + 1)
                + " 的銷售總量：" + total
            );
        }
    }

    // 計算指定日期全部商品的銷售總量
    public static int calculateDayTotal(int[][] sales, int day) {
        int total = 0;

        for (int product = 0; product < sales.length; product++) {
            total += sales[product][day];
        }

        return total;
    }

    // 顯示每天全部商品的銷售總量
    public static void printDayTotals(int[][] sales) {
        System.out.println("\n每天銷售總量：");

        for (int day = 0; day < sales[0].length; day++) {
            int total = calculateDayTotal(sales, day);

            System.out.println(
                "第 " + (day + 1)
                + " 天的銷售總量：" + total
            );
        }
    }

    // 找出銷售總量最高的商品索引
    public static int findBestProduct(int[][] sales) {
        int bestProduct = 0;
        int highestTotal = calculateProductTotal(sales, 0);

        for (int product = 1; product < sales.length; product++) {
            int total = calculateProductTotal(sales, product);

            if (total > highestTotal) {
                highestTotal = total;
                bestProduct = product;
            }
        }

        return bestProduct;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 3 列代表三項商品，4 欄代表四天
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printSalesTable(sales);
        printProductTotals(sales);
        printDayTotals(sales);

        int bestProduct = findBestProduct(sales);
        int bestTotal = calculateProductTotal(sales, bestProduct);

        System.out.println("\n銷售總量最高的商品：");
        System.out.println(
            "商品 " + (bestProduct + 1)
            + "，銷售總量為 " + bestTotal
        );

        sc.close();
    }
}