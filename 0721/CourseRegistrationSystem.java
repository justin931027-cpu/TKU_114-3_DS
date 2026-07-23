import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

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
                    addCourse(courses, sc);
                    break;

                case 2:
                    registerCourse(courses, sc);
                    break;

                case 3:
                    dropCourse(courses, sc);
                    break;

                case 4:
                    deleteCourse(courses, sc);
                    break;

                case 5:
                    searchCourse(courses, sc);
                    break;

                case 6:
                    showAllCourses(courses);
                    break;

                case 7:
                    showStatistics(courses);
                    break;

                case 0:
                    System.out.println("選課管理系統結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 7。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 選課管理系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 選課");
        System.out.println("3. 退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示全部課程");
        System.out.println("7. 顯示課程統計");
        System.out.println("0. 結束程式");
    }

    public static void addCourse(
            ArrayList<Course> courses,
            Scanner sc
    ) {
        System.out.print("請輸入課程代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("課程代碼不可空白。");
            return;
        }

        if (findByCode(courses, code) != null) {
            System.out.println("新增失敗，課程代碼不可重複。");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("課程名稱不可空白。");
            return;
        }

        System.out.print("請輸入課程容量：");
        int capacity = readPositiveInteger(sc);

        if (capacity == -1) {
            return;
        }

        courses.add(new Course(code, name, capacity));
        System.out.println("課程新增成功。");
    }

    public static void registerCourse(
            ArrayList<Course> courses,
            Scanner sc
    ) {
        System.out.print("請輸入要選課的課程代碼：");
        String code = sc.nextLine().trim();

        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("選課失敗，找不到課程。");
            return;
        }

        if (course.registerStudent()) {
            System.out.println("選課成功：" + course.getName());
        } else {
            System.out.println("選課失敗，此課程已額滿。");
        }
    }

    public static void dropCourse(
            ArrayList<Course> courses,
            Scanner sc
    ) {
        System.out.print("請輸入要退選的課程代碼：");
        String code = sc.nextLine().trim();

        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("退選失敗，找不到課程。");
            return;
        }

        if (course.dropStudent()) {
            System.out.println("退選成功：" + course.getName());
        } else {
            System.out.println("退選失敗，目前選課人數已經是 0。");
        }
    }

    public static void deleteCourse(
            ArrayList<Course> courses,
            Scanner sc
    ) {
        System.out.print("請輸入要刪除的課程代碼：");
        String code = sc.nextLine().trim();

        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("刪除失敗，找不到課程。");
            return;
        }

        courses.remove(course);
        System.out.println("課程刪除成功：" + course.getName());
    }

    public static void searchCourse(
            ArrayList<Course> courses,
            Scanner sc
    ) {
        System.out.print("請輸入要搜尋的課程代碼：");
        String code = sc.nextLine().trim();

        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("找不到課程。");
        } else {
            System.out.println(course);
        }
    }

    public static void showAllCourses(
            ArrayList<Course> courses
    ) {
        if (courses.isEmpty()) {
            System.out.println("目前沒有任何課程。");
            return;
        }

        System.out.println("=== 全部課程 ===");

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static void showStatistics(
            ArrayList<Course> courses
    ) {
        int totalStudents = 0;
        int fullCourses = 0;

        for (Course course : courses) {
            totalStudents += course.getCurrentStudents();

            if (course.isFull()) {
                fullCourses++;
            }
        }

        System.out.println("=== 課程統計 ===");
        System.out.println("總課程數：" + courses.size());
        System.out.println("總選課人次：" + totalStudents);
        System.out.println("額滿課程數：" + fullCourses);
    }

    public static Course findByCode(
            ArrayList<Course> courses,
            String code
    ) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }

        return null;
    }

    public static int readPositiveInteger(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            int value = Integer.parseInt(input);

            if (value <= 0) {
                System.out.println("課程容量必須大於 0。");
                return -1;
            }

            return value;
        } catch (NumberFormatException e) {
            System.out.println("輸入錯誤，課程容量必須是整數。");
            return -1;
        }
    }
}