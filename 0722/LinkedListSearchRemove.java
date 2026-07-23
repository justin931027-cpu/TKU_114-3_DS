public class LinkedListSearchRemove {

    public static void main(String[] args) {
        IntNode head = createList();

        System.out.println("原始鏈結串列：");
        printList(head);

        System.out.println("\n搜尋 30：" + contains(head, 30));
        System.out.println("搜尋 99：" + contains(head, 99));

        System.out.println("\n刪除 head：10");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n刪除中間節點：30");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n刪除最後節點：40");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n刪除不存在的資料：99");
        head = removeValue(head, 99);
        printList(head);
    }

    public static IntNode createList() {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        return head;
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            System.out.println("鏈結串列是空的，無法刪除。");
            return null;
        }

        if (head.data == target) {
            System.out.println("刪除成功：" + target);
            return head.next;
        }

        IntNode current = head;

        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                System.out.println("刪除成功：" + target);
                return head;
            }

            current = current.next;
        }

        System.out.println("找不到資料：" + target);
        return head;
    }

    public static void printList(IntNode head) {
        if (head == null) {
            System.out.println("鏈結串列是空的。");
            return;
        }

        IntNode current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}