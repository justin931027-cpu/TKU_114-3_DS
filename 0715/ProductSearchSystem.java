import java.util.Scanner;

public class ProductSearchSystem {

    // 顯示選單
    public static void showMenu() {
        System.out.println("\n===== 商品名稱搜尋系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與關鍵字第一次出現的位置");
        System.out.println("6. 結束");
        System.out.print("請輸入選項：");
    }

    // 顯示全部商品
    public static void displayAllProducts(
            String[] names, int[] prices, int[] stocks) {

        System.out.println("\n===== 全部商品 =====");
        System.out.printf("%-6s %-15s %-10s %-10s%n",
                "編號", "商品名稱", "價格", "庫存");

        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-6d %-15s %-10d %-10d%n",
                    i + 1, names[i], prices[i], stocks[i]);
        }
    }

    // 輸入不可為空白的關鍵字
    public static String readKeyword(Scanner sc) {
        String keyword;

        do {
            System.out.print("請輸入商品名稱或關鍵字：");
            keyword = sc.nextLine().trim();

            if (keyword.isEmpty()) {
                System.out.println("輸入不可為空白，請重新輸入。");
            }
        } while (keyword.isEmpty());

        return keyword;
    }

    // 完整名稱搜尋，忽略大小寫與前後空白
    public static int findExactProduct(
            String[] names, String keyword) {

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(keyword.trim())) {
                return i;
            }
        }

        return -1;
    }

    // 顯示完整名稱搜尋結果
    public static void searchExactProduct(
            Scanner sc, String[] names, int[] prices, int[] stocks) {

        String keyword = readKeyword(sc);
        int index = findExactProduct(names, keyword);

        if (index >= 0) {
            System.out.println("\n找到商品：");
            displayProduct(index, names, prices, stocks);
        } else {
            System.out.println("找不到完整名稱為「" + keyword + "」的商品。");
        }
    }

    // 部分名稱搜尋，可顯示多筆結果
    public static void searchPartialProduct(
            Scanner sc, String[] names, int[] prices, int[] stocks) {

        String keyword = readKeyword(sc);
        String lowerKeyword = keyword.toLowerCase();
        boolean found = false;

        System.out.println("\n搜尋結果：");

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(lowerKeyword)) {
                displayProduct(i, names, prices, stocks);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到包含「" + keyword + "」的商品。");
        }
    }

    // 顯示單項商品
    public static void displayProduct(
            int index, String[] names, int[] prices, int[] stocks) {

        System.out.println(
                "編號：" + (index + 1)
                + "，名稱：" + names[index]
                + "，價格：" + prices[index]
                + "，庫存：" + stocks[index]
        );
    }

    // 找出名稱最長的商品
    public static int findLongestProductName(String[] names) {
        int longestIndex = 0;

        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > names[longestIndex].length()) {
                longestIndex = i;
            }
        }

        return longestIndex;
    }

    // 顯示名稱最長的商品
    public static void displayLongestProduct(
            String[] names, int[] prices, int[] stocks) {

        int index = findLongestProductName(names);

        System.out.println("\n名稱最長的商品：");
        displayProduct(index, names, prices, stocks);
        System.out.println("名稱長度：" + names[index].length());
    }

    // 顯示關鍵字第一次出現的位置
    public static void displayKeywordPosition(
            Scanner sc, String[] names) {

        String keyword = readKeyword(sc);
        String lowerKeyword = keyword.toLowerCase();
        boolean found = false;

        System.out.println("\n關鍵字出現位置：");

        for (int i = 0; i < names.length; i++) {
            int position = names[i].toLowerCase().indexOf(lowerKeyword);

            if (position >= 0) {
                System.out.println(
                        names[i] + "：第一次出現在索引 " + position
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("所有商品名稱中都找不到「" + keyword + "」。");
        }
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

        /*
         * 測試案例：
         * 1. 完整搜尋輸入 Keyboard，應找到商品 1。
         * 2. 完整搜尋輸入 keyboard，應忽略大小寫並找到商品 1。
         * 3. 完整搜尋輸入前後有空白的 "  Mouse  "，應找到商品 2。
         * 4. 部分搜尋輸入 o，應顯示 Mouse 與 Monitor。
         * 5. 部分搜尋輸入 cable，應找到 USB Cable。
         * 6. 搜尋輸入空白字串，應要求重新輸入。
         * 7. 完整搜尋輸入 Printer，應顯示找不到。
         * 8. 關鍵字位置輸入 e，應顯示各商品第一次出現的索引。
         */

        do {
            showMenu();

            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入 1～6 的整數。");
                sc.nextLine();
                continue;
            }

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;

                case 2:
                    searchExactProduct(sc, names, prices, stocks);
                    break;

                case 3:
                    searchPartialProduct(sc, names, prices, stocks);
                    break;

                case 4:
                    displayLongestProduct(names, prices, stocks);
                    break;

                case 5:
                    displayKeywordPosition(sc, names);
                    break;

                case 6:
                    System.out.println("系統結束。");
                    break;

                default:
                    System.out.println("選項錯誤，請輸入 1～6。");
            }

        } while (option != 6);

        sc.close();
    }
}