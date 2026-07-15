import java.util.Scanner;

public class ProductManagementSystem {

    /*
     * 測試案例：
     *
     * 1. 選擇 1：
     *    應顯示初始的 5 項商品。
     *
     * 2. 選擇 2，輸入 keyboard：
     *    應忽略大小寫並找到 Keyboard。
     *
     * 3. 選擇 2，輸入前後有空白的 "  Mouse  "：
     *    應忽略前後空白並找到 Mouse。
     *
     * 4. 選擇 2，輸入 Printer：
     *    應顯示找不到商品。
     *
     * 5. 選擇 3，新增 Webcam、1590、15：
     *    應成功新增商品。
     *
     * 6. 選擇 3，再次新增 Keyboard：
     *    應顯示商品名稱重複，不能新增。
     *
     * 7. 選擇 4，出售 Monitor 2 個：
     *    原庫存 5，出售後應剩下 3。
     *
     * 8. 選擇 4，出售 Mouse 100 個：
     *    因超過庫存，應顯示出售失敗且庫存不變。
     *
     * 9. 選擇 5，替 Headset 補貨 10 個：
     *    原庫存 8，補貨後應為 18。
     *
     * 10. 選擇 6，將 USB Cable 價格改成 300：
     *     應成功修改價格。
     *
     * 11. 選擇 7：
     *     應顯示庫存小於 10 的商品。
     *
     * 12. 選擇 8：
     *     應顯示全部商品庫存總價值。
     */

    // 顯示主選單
    public static void showMenu() {
        System.out.println("\n===== 物件導向商品管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.print("請輸入選項：");
    }

    // 讀取整數並處理非整數輸入
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }

            System.out.println("輸入錯誤，請輸入整數。");
            sc.nextLine();
        }
    }

    // 讀取不可為空白的文字
    public static String readNonBlank(Scanner sc, String message) {
        String input;

        do {
            System.out.print(message);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("輸入不可為空白，請重新輸入。");
            }
        } while (input.isEmpty());

        return input;
    }

    // 建立初始商品
    public static int initializeProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);

        return 5;
    }

    // 顯示全部商品
    public static void displayAllProducts(
            Product[] products, int productCount) {

        if (productCount == 0) {
            System.out.println("目前沒有商品資料。");
            return;
        }

        System.out.println("\n========== 全部商品 ==========");
        System.out.printf(
                "%-6s %-18s %-10s %-10s %-12s%n",
                "編號", "商品名稱", "價格", "庫存", "庫存價值"
        );

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];

            System.out.printf(
                    "%-6d %-18s %-10d %-10d %-12d%n",
                    i + 1,
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getInventoryValue()
            );
        }
    }

    // 依完整名稱尋找商品索引
    public static int findProductIndex(
            Product[] products,
            int productCount,
            String name) {

        String targetName = name.trim();

        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(targetName)) {
                return i;
            }
        }

        return -1;
    }

    // 顯示單一商品
    public static void displayProduct(Product product) {
        System.out.println(product);
        System.out.println("是否低庫存："
                + (product.isLowStock() ? "是" : "否"));
        System.out.println("庫存總價值："
                + product.getInventoryValue());
    }

    // 搜尋商品
    public static void searchProduct(
            Scanner sc,
            Product[] products,
            int productCount) {

        String name = readNonBlank(sc, "請輸入完整商品名稱：");
        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("找不到商品「" + name + "」。");
            return;
        }

        System.out.println("\n找到商品：");
        displayProduct(products[index]);
    }

    // 新增商品
    public static int addProduct(
            Scanner sc,
            Product[] products,
            int productCount) {

        if (productCount >= products.length) {
            System.out.println("商品陣列已滿，不能再新增商品。");
            return productCount;
        }

        String name = readNonBlank(sc, "請輸入商品名稱：");

        if (findProductIndex(products, productCount, name) != -1) {
            System.out.println("商品名稱重複，不能新增。");
            return productCount;
        }

        int price = readInt(sc, "請輸入商品價格：");

        if (price <= 0) {
            System.out.println("商品價格必須大於 0，新增失敗。");
            return productCount;
        }

        int stock = readInt(sc, "請輸入商品庫存：");

        if (stock < 0) {
            System.out.println("商品庫存不得小於 0，新增失敗。");
            return productCount;
        }

        products[productCount] = new Product(name, price, stock);

        System.out.println("商品新增成功！");
        System.out.println(products[productCount]);

        return productCount + 1;
    }

    // 出售商品
    public static boolean sellProduct(
            Scanner sc,
            Product[] products,
            int productCount) {

        String name = readNonBlank(sc, "請輸入要出售的商品名稱：");
        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("找不到商品「" + name + "」。");
            return false;
        }

        Product product = products[index];

        System.out.println("目前庫存：" + product.getStock());

        int amount = readInt(sc, "請輸入出售數量：");
        boolean result = product.sell(amount);

        if (result) {
            System.out.println("出售成功！");
            System.out.println("剩餘庫存：" + product.getStock());
        } else {
            System.out.println(
                    "出售失敗，數量必須大於 0 且不能超過庫存。"
            );
        }

        return result;
    }

    // 補充庫存
    public static boolean restockProduct(
            Scanner sc,
            Product[] products,
            int productCount) {

        String name = readNonBlank(sc, "請輸入要補貨的商品名稱：");
        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("找不到商品「" + name + "」。");
            return false;
        }

        Product product = products[index];

        System.out.println("目前庫存：" + product.getStock());

        int amount = readInt(sc, "請輸入補貨數量：");
        boolean result = product.restock(amount);

        if (result) {
            System.out.println("補貨成功！");
            System.out.println("目前庫存：" + product.getStock());
        } else {
            System.out.println("補貨失敗，數量必須大於 0。");
        }

        return result;
    }

    // 修改商品價格
    public static boolean changeProductPrice(
            Scanner sc,
            Product[] products,
            int productCount) {

        String name = readNonBlank(sc, "請輸入要修改價格的商品名稱：");
        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("找不到商品「" + name + "」。");
            return false;
        }

        Product product = products[index];

        System.out.println("目前價格：" + product.getPrice());

        int newPrice = readInt(sc, "請輸入新價格：");
        boolean result = product.setPrice(newPrice);

        if (result) {
            System.out.println("價格修改成功！");
            System.out.println("新價格：" + product.getPrice());
        } else {
            System.out.println("價格修改失敗，價格必須大於 0。");
        }

        return result;
    }

    // 顯示低庫存商品
    public static void displayLowStockProducts(
            Product[] products,
            int productCount) {

        boolean found = false;

        System.out.println("\n========== 低庫存商品 ==========");

        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println(
                        "編號：" + (i + 1)
                                + "，" + products[i]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    // 計算全部庫存總價值
    public static int calculateTotalInventoryValue(
            Product[] products,
            int productCount) {

        int totalValue = 0;

        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }

        return totalValue;
    }

    // 顯示操作摘要
    public static void displayOperationSummary(
            int displayCount,
            int searchCount,
            int addCount,
            int sellCount,
            int restockCount,
            int priceChangeCount,
            int lowStockCount,
            int valueCount) {

        System.out.println("\n========== 操作摘要 ==========");
        System.out.println("顯示全部商品次數：" + displayCount);
        System.out.println("搜尋商品次數：" + searchCount);
        System.out.println("成功新增商品次數：" + addCount);
        System.out.println("成功出售商品次數：" + sellCount);
        System.out.println("成功補貨次數：" + restockCount);
        System.out.println("成功修改價格次數：" + priceChangeCount);
        System.out.println("查看低庫存次數：" + lowStockCount);
        System.out.println("查看庫存總價值次數：" + valueCount);

        int totalOperations =
                displayCount
                        + searchCount
                        + addCount
                        + sellCount
                        + restockCount
                        + priceChangeCount
                        + lowStockCount
                        + valueCount;

        System.out.println("總操作次數：" + totalOperations);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = new Product[10];
        int productCount = initializeProducts(products);

        int option = 0;

        int displayCount = 0;
        int searchCount = 0;
        int addCount = 0;
        int sellCount = 0;
        int restockCount = 0;
        int priceChangeCount = 0;
        int lowStockCount = 0;
        int valueCount = 0;

        do {
            showMenu();
            option = readInt(sc, "");

            switch (option) {
                case 1:
                    displayAllProducts(products, productCount);
                    displayCount++;
                    break;

                case 2:
                    searchProduct(sc, products, productCount);
                    searchCount++;
                    break;

                case 3:
                    int oldCount = productCount;

                    productCount = addProduct(
                            sc, products, productCount
                    );

                    if (productCount > oldCount) {
                        addCount++;
                    }
                    break;

                case 4:
                    if (sellProduct(sc, products, productCount)) {
                        sellCount++;
                    }
                    break;

                case 5:
                    if (restockProduct(sc, products, productCount)) {
                        restockCount++;
                    }
                    break;

                case 6:
                    if (changeProductPrice(
                            sc, products, productCount)) {
                        priceChangeCount++;
                    }
                    break;

                case 7:
                    displayLowStockProducts(products, productCount);
                    lowStockCount++;
                    break;

                case 8:
                    int totalValue = calculateTotalInventoryValue(
                            products, productCount
                    );

                    System.out.println(
                            "\n全部商品庫存總價值：" + totalValue
                    );

                    valueCount++;
                    break;

                case 9:
                    System.out.println("\n系統結束。");

                    displayOperationSummary(
                            displayCount,
                            searchCount,
                            addCount,
                            sellCount,
                            restockCount,
                            priceChangeCount,
                            lowStockCount,
                            valueCount
                    );
                    break;

                default:
                    System.out.println("選項錯誤，請輸入 1～9。");
            }

        } while (option != 9);

        sc.close();
    }
}