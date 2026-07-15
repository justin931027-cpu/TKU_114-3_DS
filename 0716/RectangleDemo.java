public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(5, 3);
        Rectangle rectangle2 = new Rectangle(4, 4);
        Rectangle rectangle3 = new Rectangle(-2, 6);

        displayRectangle("矩形 1", rectangle1);
        displayRectangle("矩形 2", rectangle2);
        displayRectangle("矩形 3", rectangle3);
    }

    public static void displayRectangle(
            String title, Rectangle rectangle) {

        System.out.println("\n===== " + title + " =====");
        System.out.println(rectangle);
        System.out.println("面積：" + rectangle.calculateArea());
        System.out.println("周長：" + rectangle.calculatePerimeter());
        System.out.println(
                "是否為正方形："
                        + (rectangle.isSquare() ? "是" : "否")
        );
    }
}