public class LinkedListReverse {

    public static void main(String[] args) {
        System.out.println("=== 測試空串列 ===");
        IntNode emptyHead = null;
        printList(emptyHead);

        emptyHead = reverse(emptyHead);

        System.out.println("反轉後：");
        printList(emptyHead);

        System.out.println("\n=== 測試單一節點 ===");
        IntNode singleHead = new IntNode(10);

        System.out.println("反轉前：");
        printList(singleHead);

        singleHead = reverse(singleHead);

        System.out.println("反轉後：");
        printList(singleHead);

        System.out.println("\n=== 測試多個節點 ===");
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.println("反轉前：");
        printList(head);

        head = reverse(head);

        System.out.println("反轉後：");
        printList(head);
    }

    public static IntNode reverse(IntNode head) {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next;

            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
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