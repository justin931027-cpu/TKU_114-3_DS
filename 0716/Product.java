public class Product {
    private String name;
    private int price;
    private int stock;

    // 建構子：驗證名稱不可空白、價格大於 0、庫存不得小於 0
    public Product(String name, int price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "未命名商品";
        } else {
            this.name = name.trim();
        }

        if (price > 0) {
            this.price = price;
        } else {
            this.price = 1;
        }

        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    // getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // 修改價格，價格必須大於 0
    public boolean setPrice(int price) {
        if (price <= 0) {
            return false;
        }

        this.price = price;
        return true;
    }

    // 補貨，數量必須大於 0
    public boolean restock(int amount) {
        if (amount <= 0) {
            return false;
        }

        stock += amount;
        return true;
    }

    // 銷售，數量必須大於 0 且不能超過庫存
    public boolean sell(int amount) {
        if (amount <= 0 || amount > stock) {
            return false;
        }

        stock -= amount;
        return true;
    }

    // 判斷是否為低庫存商品
    public boolean isLowStock() {
        return stock < 10;
    }

    // 計算庫存總價值
    public int getInventoryValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return "商品名稱：" + name
                + "，價格：" + price
                + "，庫存：" + stock;
    }

    // 直接在同一個檔案測試
    public static void main(String[] args) {
        Product product = new Product("Keyboard", 890, 12);

        System.out.println("===== 初始商品資料 =====");
        System.out.println(product);
        System.out.println("是否低庫存：" + product.isLowStock());
        System.out.println("庫存總價值：" + product.getInventoryValue());

        System.out.println("\n===== 銷售測試 =====");
        boolean sellResult = product.sell(5);

        if (sellResult) {
            System.out.println("銷售成功。");
        } else {
            System.out.println("銷售失敗。");
        }

        System.out.println(product);
        System.out.println("是否低庫存：" + product.isLowStock());

        System.out.println("\n===== 補貨測試 =====");
        boolean restockResult = product.restock(10);

        if (restockResult) {
            System.out.println("補貨成功。");
        } else {
            System.out.println("補貨失敗。");
        }

        System.out.println(product);

        System.out.println("\n===== 修改價格測試 =====");
        boolean priceResult = product.setPrice(990);

        if (priceResult) {
            System.out.println("價格修改成功。");
        } else {
            System.out.println("價格修改失敗。");
        }

        System.out.println(product);
        System.out.println("庫存總價值：" + product.getInventoryValue());

        System.out.println("\n===== 失敗操作測試 =====");
        System.out.println("銷售超過庫存：" + product.sell(100));
        System.out.println("補貨負數：" + product.restock(-5));
        System.out.println("價格設為 0：" + product.setPrice(0));
        System.out.println("失敗後商品資料：" + product);
    }
}