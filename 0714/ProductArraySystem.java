import java.util.Scanner;

public class ProductArraySystem {

    // 顯示主選單
    public static void showMenu() {
        System.out.println("\n===== 商品陣列管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束系統");
        System.out.print("請輸入選項：");
    }

    // 顯示全部商品
    public static void displayAllProducts(
            String[] names, int[] prices, int[] stocks) {

        System.out.println("\n===== 全部商品資料 =====");
        System.out.printf("%-8s %-15s %-10s %-10s%n",
                "編號", "商品名稱", "價格", "庫存");

        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8d %-15s %-10d %-10d%n",
                    i + 1, names[i], prices[i], stocks[i]);
        }
    }

    // 將商品編號轉換成陣列索引
    public static int getProductIndex(
            Scanner sc, String[] names) {

        System.out.print("請輸入商品編號（1～" + names.length + "）：");

        if (!sc.hasNextInt()) {
            System.out.println("輸入錯誤，商品編號必須是整數。");
            sc.next();
            return -1;
        }

        int productNumber = sc.nextInt();
        int index = productNumber - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("找不到此商品編號。");
            return -1;
        }

        return index;
    }

    // 查詢指定商品
    public static void searchProduct(
            Scanner sc, String[] names, int[] prices, int[] stocks) {

        int index = getProductIndex(sc, names);

        if (index != -1) {
            System.out.println("\n商品編號：" + (index + 1));
            System.out.println("商品名稱：" + names[index]);
            System.out.println("商品價格：" + prices[index]);
            System.out.println("商品庫存：" + stocks[index]);
        }
    }

    // 購買商品並扣除庫存
    public static void buyProduct(
            Scanner sc, String[] names, int[] prices, int[] stocks) {

        int index = getProductIndex(sc, names);

        if (index == -1) {
            return;
        }

        System.out.println("商品名稱：" + names[index]);
        System.out.println("目前庫存：" + stocks[index]);
        System.out.print("請輸入購買數量：");

        if (!sc.hasNextInt()) {
            System.out.println("輸入錯誤，購買數量必須是整數。");
            sc.next();
            return;
        }

        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("購買數量必須大於 0。");
        } else if (quantity > stocks[index]) {
            System.out.println("庫存不足，購買數量不能超過目前庫存。");
        } else {
            stocks[index] -= quantity;
            int totalPrice = prices[index] * quantity;

            System.out.println("購買成功！");
            System.out.println("購買商品：" + names[index]);
            System.out.println("購買數量：" + quantity);
            System.out.println("應付金額：" + totalPrice);
            System.out.println("剩餘庫存：" + stocks[index]);
        }
    }

    // 補充商品庫存
    public static void addStock(
            Scanner sc, String[] names, int[] stocks) {

        int index = getProductIndex(sc, names);

        if (index == -1) {
            return;
        }

        System.out.println("商品名稱：" + names[index]);
        System.out.println("目前庫存：" + stocks[index]);
        System.out.print("請輸入補貨數量：");

        if (!sc.hasNextInt()) {
            System.out.println("輸入錯誤，補貨數量必須是整數。");
            sc.next();
            return;
        }

        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("補貨數量必須大於 0。");
        } else {
            stocks[index] += quantity;

            System.out.println("補貨成功！");
            System.out.println("補貨商品：" + names[index]);
            System.out.println("補貨數量：" + quantity);
            System.out.println("目前庫存：" + stocks[index]);
        }
    }

    // 顯示低庫存商品
    public static void displayLowStock(
            String[] names, int[] prices, int[] stocks) {

        boolean found = false;

        System.out.println("\n===== 低庫存商品 =====");
        System.out.printf("%-8s %-15s %-10s %-10s%n",
                "編號", "商品名稱", "價格", "庫存");

        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-8d %-15s %-10d %-10d%n",
                        i + 1, names[i], prices[i], stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    // 計算並顯示全部庫存總價值
    public static void displayTotalInventoryValue(
            String[] names, int[] prices, int[] stocks) {

        int totalValue = 0;

        System.out.println("\n===== 商品庫存價值 =====");

        for (int i = 0; i < names.length; i++) {
            int productValue = prices[i] * stocks[i];
            totalValue += productValue;

            System.out.println(
                    names[i] + "：" +
                    prices[i] + " × " +
                    stocks[i] + " = " +
                    productValue
            );
        }

        System.out.println("全部庫存總價值：" + totalValue);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {
                "Keyboard", "Mouse", "Monitor",
                "USB Cable", "Headset"
        };

        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int option = 0;
        int operationCount = 0;

        do {
            showMenu();

            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入 1～7 的整數選項。");
                sc.next();
                continue;
            }

            option = sc.nextInt();

            switch (option) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    operationCount++;
                    break;

                case 2:
                    searchProduct(sc, names, prices, stocks);
                    operationCount++;
                    break;

                case 3:
                    buyProduct(sc, names, prices, stocks);
                    operationCount++;
                    break;

                case 4:
                    addStock(sc, names, stocks);
                    operationCount++;
                    break;

                case 5:
                    displayLowStock(names, prices, stocks);
                    operationCount++;
                    break;

                case 6:
                    displayTotalInventoryValue(names, prices, stocks);
                    operationCount++;
                    break;

                case 7:
                    System.out.println("\n系統結束。");
                    System.out.println("本次操作次數：" + operationCount);
                    break;

                default:
                    System.out.println("選項錯誤，請輸入 1～7。");
            }

        } while (option != 7);

        sc.close();
    }
}