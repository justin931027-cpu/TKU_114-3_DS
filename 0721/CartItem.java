public class CartItem {
    private String code;
    private String name;
    private double price;
    private int quantity;

    public CartItem(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        quantity += amount;
    }

    public boolean setQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        }

        this.quantity = quantity;
        return true;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "商品代碼：" + code
                + "，商品名稱：" + name
                + "，單價：" + String.format("%.2f", price)
                + "，數量：" + quantity
                + "，小計：" + String.format("%.2f", getSubtotal());
    }
}