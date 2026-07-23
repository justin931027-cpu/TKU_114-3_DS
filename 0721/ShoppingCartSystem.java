import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        while (true) {
            showMenu();
            System.out.print("請輸入選項：");
            String input = sc.nextLine().trim();

            int option;

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入數字。\n");
                continue;
            }

            switch (option) {
                case 1:
                    addItem(cart, sc);
                    break;

                case 2:
                    updateQuantity(cart, sc);
                    break;

                case 3:
                    removeItem(cart, sc);
                    break;

                case 4:
                    showCart(cart);
                    break;

                case 5:
                    showTotal(cart);
                    break;

                case 0:
                    System.out.println("購物車系統結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 5。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 購物車系統 ===");
        System.out.println("1. 加入商品");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 顯示購物車");
        System.out.println("5. 計算總額");
        System.out.println("0. 結束程式");
    }

    public static void addItem(
            ArrayList<CartItem> cart,
            Scanner sc
    ) {
        System.out.print("請輸入商品代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("商品代碼不可空白。");
            return;
        }

        System.out.print("請輸入數量：");
        int quantity = readPositiveInteger(sc);

        if (quantity == -1) {
            return;
        }

        CartItem existingItem = findByCode(cart, code);

        if (existingItem != null) {
            existingItem.addQuantity(quantity);
            System.out.println("商品已存在，數量增加成功。");
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("商品名稱不可空白。");
            return;
        }

        System.out.print("請輸入商品單價：");
        double price = readPositiveDouble(sc);

        if (price == -1) {
            return;
        }

        cart.add(new CartItem(code, name, price, quantity));
        System.out.println("商品加入成功。");
    }

    public static void updateQuantity(
            ArrayList<CartItem> cart,
            Scanner sc
    ) {
        System.out.print("請輸入要修改的商品代碼：");
        String code = sc.nextLine().trim();

        CartItem item = findByCode(cart, code);

        if (item == null) {
            System.out.println("修改失敗，找不到商品。");
            return;
        }

        System.out.print("請輸入新的數量：");
        int quantity = readPositiveInteger(sc);

        if (quantity == -1) {
            return;
        }

        item.setQuantity(quantity);
        System.out.println("數量修改成功。");
    }

    public static void removeItem(
            ArrayList<CartItem> cart,
            Scanner sc
    ) {
        System.out.print("請輸入要移除的商品代碼：");
        String code = sc.nextLine().trim();

        CartItem item = findByCode(cart, code);

        if (item == null) {
            System.out.println("移除失敗，找不到商品。");
            return;
        }

        cart.remove(item);
        System.out.println("商品移除成功：" + item.getName());
    }

    public static void showCart(ArrayList<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的。");
            return;
        }

        System.out.println("=== 購物車內容 ===");

        for (CartItem item : cart) {
            System.out.println(item);
        }
    }

    public static void showTotal(ArrayList<CartItem> cart) {
        double total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        System.out.printf("購物車總額：%.2f%n", total);
    }

    public static CartItem findByCode(
            ArrayList<CartItem> cart,
            String code
    ) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }

        return null;
    }

    public static int readPositiveInteger(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            int value = Integer.parseInt(input);

            if (value <= 0) {
                System.out.println("數量必須大於 0。");
                return -1;
            }

            return value;
        } catch (NumberFormatException e) {
            System.out.println("輸入錯誤，數量必須是整數。");
            return -1;
        }
    }

    public static double readPositiveDouble(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            double value = Double.parseDouble(input);

            if (value <= 0) {
                System.out.println("單價必須大於 0。");
                return -1;
            }

            return value;
        } catch (NumberFormatException e) {
            System.out.println("輸入錯誤，單價必須是數字。");
            return -1;
        }
    }
}