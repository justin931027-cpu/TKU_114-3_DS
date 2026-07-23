public class Product {
    private String code;
    private String name;
    private int stock;

    public Product(String code, String name, int stock) {
        this.code = code;
        this.name = name;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void addStock(int amount) {
        stock += amount;
    }

    @Override
    public String toString() {
        return "商品編號：" + code
                + "，商品名稱：" + name
                + "，庫存：" + stock;
    }
}