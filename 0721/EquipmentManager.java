import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipment> equipments = new ArrayList<>();

        while (true) {
            showMenu();
            System.out.print("請輸入選項：");
            String input = sc.nextLine();

            int option;

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入數字。\n");
                continue;
            }

            switch (option) {
                case 1:
                    addEquipment(equipments, sc);
                    break;

                case 2:
                    searchEquipment(equipments, sc);
                    break;

                case 3:
                    borrowEquipment(equipments, sc);
                    break;

                case 4:
                    returnEquipment(equipments, sc);
                    break;

                case 5:
                    showAvailableEquipment(equipments);
                    break;

                case 6:
                    showAllEquipment(equipments);
                    break;

                case 0:
                    System.out.println("程式結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 6。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 設備管理系統 ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出可借設備");
        System.out.println("6. 列出全部設備");
        System.out.println("0. 結束程式");
    }

    public static void addEquipment(
            ArrayList<Equipment> equipments,
            Scanner sc
    ) {
        System.out.print("請輸入設備代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("設備代碼不可空白。");
            return;
        }

        if (findByCode(equipments, code) != null) {
            System.out.println("新增失敗，設備代碼不可重複。");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("設備名稱不可空白。");
            return;
        }

        equipments.add(new Equipment(code, name));
        System.out.println("新增成功。");
    }

    public static void searchEquipment(
            ArrayList<Equipment> equipments,
            Scanner sc
    ) {
        System.out.print("請輸入要搜尋的設備代碼：");
        String code = sc.nextLine().trim();

        Equipment equipment = findByCode(equipments, code);

        if (equipment == null) {
            System.out.println("找不到設備。");
        } else {
            System.out.println(equipment);
        }
    }

    public static void borrowEquipment(
            ArrayList<Equipment> equipments,
            Scanner sc
    ) {
        System.out.print("請輸入要借出的設備代碼：");
        String code = sc.nextLine().trim();

        Equipment equipment = findByCode(equipments, code);

        if (equipment == null) {
            System.out.println("借出失敗，找不到設備。");
            return;
        }

        if (equipment.isBorrowed()) {
            System.out.println("借出失敗，此設備已被借出。");
            return;
        }

        equipment.borrowEquipment();
        System.out.println("借出成功：" + equipment.getName());
    }

    public static void returnEquipment(
            ArrayList<Equipment> equipments,
            Scanner sc
    ) {
        System.out.print("請輸入要歸還的設備代碼：");
        String code = sc.nextLine().trim();

        Equipment equipment = findByCode(equipments, code);

        if (equipment == null) {
            System.out.println("歸還失敗，找不到設備。");
            return;
        }

        if (!equipment.isBorrowed()) {
            System.out.println("歸還失敗，此設備目前沒有借出。");
            return;
        }

        equipment.returnEquipment();
        System.out.println("歸還成功：" + equipment.getName());
    }

    public static void showAvailableEquipment(
            ArrayList<Equipment> equipments
    ) {
        boolean found = false;

        System.out.println("=== 可借設備 ===");

        for (Equipment equipment : equipments) {
            if (!equipment.isBorrowed()) {
                System.out.println(equipment);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有可借設備。");
        }
    }

    public static void showAllEquipment(
            ArrayList<Equipment> equipments
    ) {
        if (equipments.isEmpty()) {
            System.out.println("目前沒有任何設備。");
            return;
        }

        System.out.println("=== 全部設備 ===");

        for (Equipment equipment : equipments) {
            System.out.println(equipment);
        }
    }

    public static Equipment findByCode(
            ArrayList<Equipment> equipments,
            String code
    ) {
        for (Equipment equipment : equipments) {
            if (equipment.getCode().equalsIgnoreCase(code)) {
                return equipment;
            }
        }

        return null;
    }
}