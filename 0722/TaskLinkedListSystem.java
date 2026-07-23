import java.util.Scanner;

public class TaskLinkedListSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskLinkedList taskList = new TaskLinkedList();

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
                    addUrgentTask(taskList, sc);
                    break;

                case 2:
                    addNormalTask(taskList, sc);
                    break;

                case 3:
                    completeTask(taskList, sc);
                    break;

                case 4:
                    removeTask(taskList, sc);
                    break;

                case 5:
                    taskList.showUnfinishedTasks();
                    break;

                case 6:
                    taskList.showAllTasks();
                    break;

                case 7:
                    showStatistics(taskList);
                    break;

                case 0:
                    System.out.println("工作項目系統結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 7。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 工作項目系統 ===");
        System.out.println("1. 新增緊急工作");
        System.out.println("2. 新增一般工作");
        System.out.println("3. 完成工作");
        System.out.println("4. 刪除工作");
        System.out.println("5. 列出未完成工作");
        System.out.println("6. 列出全部工作");
        System.out.println("7. 顯示工作統計");
        System.out.println("0. 結束程式");
    }

    public static void addUrgentTask(
            TaskLinkedList taskList,
            Scanner sc
    ) {
        System.out.print("請輸入工作代碼：");
        String code = sc.nextLine();

        System.out.print("請輸入工作說明：");
        String description = sc.nextLine();

        taskList.addUrgentTask(code, description);
    }

    public static void addNormalTask(
            TaskLinkedList taskList,
            Scanner sc
    ) {
        System.out.print("請輸入工作代碼：");
        String code = sc.nextLine();

        System.out.print("請輸入工作說明：");
        String description = sc.nextLine();

        taskList.addNormalTask(code, description);
    }

    public static void completeTask(
            TaskLinkedList taskList,
            Scanner sc
    ) {
        System.out.print("請輸入要完成的工作代碼：");
        String code = sc.nextLine();

        taskList.completeTask(code);
    }

    public static void removeTask(
            TaskLinkedList taskList,
            Scanner sc
    ) {
        System.out.print("請輸入要刪除的工作代碼：");
        String code = sc.nextLine();

        taskList.removeTask(code);
    }

    public static void showStatistics(TaskLinkedList taskList) {
        System.out.println("=== 工作統計 ===");
        System.out.println(
                "工作總數：" + taskList.getTotalTaskCount()
        );
        System.out.println(
                "未完成數量：" + taskList.getUnfinishedTaskCount()
        );
    }
}