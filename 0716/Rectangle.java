public class Rectangle {
    private double width;
    private double height;

    // 建構子
    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    // getter
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // setter，必須驗證正數
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("寬必須大於 0，已設定為 1。");
            this.width = 1;
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("高必須大於 0，已設定為 1。");
            this.height = 1;
        }
    }

    // 計算面積
    public double calculateArea() {
        return width * height;
    }

    // 計算周長
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // 判斷是否為正方形
    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "寬：" + width + "，高：" + height;
    }
}