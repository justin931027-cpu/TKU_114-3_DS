import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

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
                    addContact(contacts, sc);
                    break;

                case 2:
                    searchContact(contacts, sc);
                    break;

                case 3:
                    updatePhone(contacts, sc);
                    break;

                case 4:
                    deleteContact(contacts, sc);
                    break;

                case 5:
                    showAllContacts(contacts);
                    break;

                case 0:
                    System.out.println("程式結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 5。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整清單");
        System.out.println("0. 結束程式");
    }

    public static void addContact(
            ArrayList<Contact> contacts,
            Scanner sc
    ) {
        System.out.print("請輸入聯絡人代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("代碼不可空白。");
            return;
        }

        if (findByCode(contacts, code) != null) {
            System.out.println("新增失敗，聯絡人代碼不可重複。");
            return;
        }

        System.out.print("請輸入姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("姓名不可空白。");
            return;
        }

        System.out.print("請輸入電話：");
        String phone = sc.nextLine().trim();

        if (phone.isEmpty()) {
            System.out.println("電話不可空白。");
            return;
        }

        System.out.print("請輸入電子郵件：");
        String email = sc.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("電子郵件不可空白。");
            return;
        }

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("新增成功。");
    }

    public static void searchContact(
            ArrayList<Contact> contacts,
            Scanner sc
    ) {
        System.out.print("請輸入要搜尋的代碼：");
        String code = sc.nextLine().trim();

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("找不到聯絡人。");
        } else {
            System.out.println(contact);
        }
    }

    public static void updatePhone(
            ArrayList<Contact> contacts,
            Scanner sc
    ) {
        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = sc.nextLine().trim();

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("修改失敗，找不到聯絡人。");
            return;
        }

        System.out.print("請輸入新的電話：");
        String newPhone = sc.nextLine().trim();

        if (newPhone.isEmpty()) {
            System.out.println("新電話不可空白。");
            return;
        }

        contact.setPhone(newPhone);
        System.out.println("電話修改成功。");
    }

    public static void deleteContact(
            ArrayList<Contact> contacts,
            Scanner sc
    ) {
        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = sc.nextLine().trim();

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("刪除失敗，找不到聯絡人。");
            return;
        }

        contacts.remove(contact);
        System.out.println("刪除成功：" + contact.getName());
    }

    public static void showAllContacts(
            ArrayList<Contact> contacts
    ) {
        if (contacts.isEmpty()) {
            System.out.println("目前沒有任何聯絡人。");
            return;
        }

        System.out.println("=== 完整聯絡人清單 ===");

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public static Contact findByCode(
            ArrayList<Contact> contacts,
            String code
    ) {
        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }

        return null;
    }
}