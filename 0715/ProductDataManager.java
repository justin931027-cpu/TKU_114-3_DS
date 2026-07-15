import java.util.Scanner;

public class ProductDataManager {

    /*
     * 測試案例：
     *
     * 1. 輸入 Keyboard
     *    預期：完整名稱搜尋到 Keyboard。
     *
     * 2. 輸入 keyboard
     *    預期：忽略大小寫，搜尋到 Keyboard。
     *
     * 3. 輸入 o
     *    預期：部分名稱搜尋到 Keyboard、Mouse、Monitor。
     *
     * 4. 輸入 cable
     *    預期：部分名稱搜尋到 USB Cable。
     *
     * 5. 輸入 Printer
     *    預期：顯示找不到商品。
     *
     * 6. 新增資料：Webcam,1590,15
     *    預期：成功新增商品。
     *
     * 7. 新增資料：Webcam,abc,15
     *    預期：顯示價格必須是整數，程式不會中止。
     *
     * 8. 新增資料：Webcam,1590,-3
     *    預期：顯示庫存不得小於 0，程式不會中止。
     *
     * 9. 新增資料：Webcam,1590
     *    預期：顯示資料必須包含名稱、價格、庫存。
     *
     * 10. 新增資料：,1590,15
     *     預期：顯示商品名稱不可為空白。
     */

    // 解析原始文字資料，並存入三個對應陣列
    public static int parseRecords(
            String[] records,
            String[] names,
            int[] prices,
            int[] stocks) {

        int validCount = 0;

        for (String record : records) {
            String[] parts = record.split(",", -1);

            if (parts.length != 3) {
                System.out.println("資料格式錯誤，跳過：" + record);
                continue;
            }

            String name = parts[0].trim();
            String priceText = parts[1].trim();
            String stockText = parts[2].trim();

            if (name.isEmpty()) {
                System.out.println("商品名稱不可為空白，跳過：" + record);
                continue;
            }

            try {
                int price = Integer.parseInt(priceText);
                int stock = Integer.parseInt(stockText);

                if (price < 0) {
                    System.out.println("商品價格不得小於 0，跳過：" + record);
                    continue;
                }

                if (stock < 0) {
                    System.out.println("商品庫存不得小於 0，跳過：" + record);
                    continue;
                }

                names[validCount] = name;
                prices[validCount] = price;
                stocks[validCount] = stock;
                validCount++;

            } catch (NumberFormatException e) {
                System.out.println(
                        "價格或庫存必須是整數，跳過：" + record
                );
            }
        }

        return validCount;
    }

    // 顯示商品表格
    public static void displayProducts(
            String[] names,
            int[] prices,
            int[] stocks,
            int count) {

        System.out.println("\n========== 商品資料表 ==========");
        System.out.printf(
                "%-6s %-18s %-10s %-10s%n",
                "編號", "商品名稱", "價格", "庫存"
        );
        System.out.println("---------------------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.printf(
                    "%-6d %-18s %-10d %-10d%n",
                    i + 1,
                    names[i],
                    prices[i],
                    stocks[i]
            );
        }
    }

    // 讀取不可為空白的文字
    public static String readNonBlank(
            Scanner sc, String message) {

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

    // 完整名稱或部分名稱搜尋
    public static void searchProducts(
            String[] names,
            int[] prices,
            int[] stocks,
            int count,
            String keyword) {

        boolean found = false;
        String lowerKeyword = keyword.toLowerCase();

        System.out.println("\n搜尋結果：");

        // 先檢查是否有完整名稱相同的商品
        for (int i = 0; i < count; i++) {
            if (names[i].equalsIgnoreCase(keyword)) {
                displayOneProduct(i, names, prices, stocks);
                return;
            }
        }

        // 沒有完整名稱時，改為部分名稱搜尋
        for (int i = 0; i < count; i++) {
            if (names[i].toLowerCase().contains(lowerKeyword)) {
                displayOneProduct(i, names, prices, stocks);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "找不到名稱包含「" + keyword + "」的商品。"
            );
        }
    }

    // 顯示單一商品
    public static void displayOneProduct(
            int index,
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println(
                "編號：" + (index + 1)
                        + "，名稱：" + names[index]
                        + "，價格：" + prices[index]
                        + "，庫存：" + stocks[index]
        );
    }

    // 顯示低庫存商品
    public static void displayLowStockProducts(
            String[] names,
            int[] prices,
            int[] stocks,
            int count) {

        boolean found = false;

        System.out.println("\n========== 低庫存商品 ==========");

        for (int i = 0; i < count; i++) {
            if (stocks[i] < 10) {
                displayOneProduct(i, names, prices, stocks);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    // 計算庫存總價值
    public static int calculateTotalInventoryValue(
            int[] prices,
            int[] stocks,
            int count) {

        int totalValue = 0;

        for (int i = 0; i < count; i++) {
            totalValue += prices[i] * stocks[i];
        }

        return totalValue;
    }

    // 驗證並新增一筆文字格式商品資料
    public static boolean addNewRecord(
            String record,
            String[] names,
            int[] prices,
            int[] stocks,
            int index) {

        String[] parts = record.split(",", -1);

        if (parts.length != 3) {
            System.out.println(
                    "格式錯誤：資料必須包含商品名稱、價格、庫存。"
            );
            System.out.println("正確格式範例：Webcam,1590,15");
            return false;
        }

        String name = parts[0].trim();
        String priceText = parts[1].trim();
        String stockText = parts[2].trim();

        if (name.isEmpty()) {
            System.out.println("格式錯誤：商品名稱不可為空白。");
            return false;
        }

        if (priceText.isEmpty()) {
            System.out.println("格式錯誤：商品價格不可為空白。");
            return false;
        }

        if (stockText.isEmpty()) {
            System.out.println("格式錯誤：商品庫存不可為空白。");
            return false;
        }

        try {
            int price = Integer.parseInt(priceText);
            int stock = Integer.parseInt(stockText);

            if (price < 0) {
                System.out.println("資料錯誤：商品價格不得小於 0。");
                return false;
            }

            if (stock < 0) {
                System.out.println("資料錯誤：商品庫存不得小於 0。");
                return false;
            }

            names[index] = name;
            prices[index] = price;
            stocks[index] = stock;

            System.out.println("商品資料新增成功！");
            return true;

        } catch (NumberFormatException e) {
            System.out.println(
                    "數字轉換錯誤：價格與庫存必須是整數。"
            );
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
                "Keyboard,890,12",
                "Mouse,490,20",
                "Monitor,5200,5",
                "USB Cable,250,30",
                "Headset,1290,8"
        };

        // 多保留一格，用來存放使用者新增的商品
        String[] names = new String[records.length + 1];
        int[] prices = new int[records.length + 1];
        int[] stocks = new int[records.length + 1];

        int productCount = parseRecords(
                records, names, prices, stocks
        );

        // 顯示解析後的商品表格
        displayProducts(
                names, prices, stocks, productCount
        );

        // 完整名稱或部分名稱搜尋
        String keyword = readNonBlank(
                sc, "\n請輸入要搜尋的商品名稱或關鍵字："
        );

        searchProducts(
                names,
                prices,
                stocks,
                productCount,
                keyword
        );

        // 顯示低庫存商品
        displayLowStockProducts(
                names, prices, stocks, productCount
        );

        // 顯示庫存總價值
        int totalValue = calculateTotalInventoryValue(
                prices, stocks, productCount
        );

        System.out.println(
                "\n目前全部庫存總價值：" + totalValue
        );

        // 輸入一筆新文字資料並驗證
        boolean added = false;

        while (!added) {
            System.out.println(
                    "\n請新增一筆商品資料，格式為：名稱,價格,庫存"
            );
            System.out.print("請輸入：");

            String newRecord = sc.nextLine();

            added = addNewRecord(
                    newRecord,
                    names,
                    prices,
                    stocks,
                    productCount
            );
        }

        productCount++;

        // 顯示新增後的完整商品表格
        displayProducts(
                names, prices, stocks, productCount
        );

        int newTotalValue = calculateTotalInventoryValue(
                prices, stocks, productCount
        );

        System.out.println(
                "\n新增後全部庫存總價值：" + newTotalValue
        );

        sc.close();
    }
}