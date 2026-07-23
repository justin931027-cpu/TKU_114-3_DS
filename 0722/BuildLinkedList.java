public class BuildLinkedList {

    public static void main(String[] args) {
        // 建立四個 Node
        IntNode head = new IntNode(10);
        IntNode second = new IntNode(20);
        IntNode third = new IntNode(30);
        IntNode fourth = new IntNode(40);

        // 使用 next 依序連接
        head.next = second;
        second.next = third;
        third.next = fourth;

        // 從 head 開始走訪輸出
        System.out.println("=== 鏈結串列內容 ===");
        printList(head);

        // 計算節點數與總和
        System.out.println("節點數：" + countNodes(head));
        System.out.println("資料總和：" + sumNodes(head));

        // 測試搜尋功能
        int target = 30;

        if (contains(head, target)) {
            System.out.println("找到資料：" + target);
        } else {
            System.out.println("找不到資料：" + target);
        }
    }

    public static void printList(IntNode head) {
        if (head == null) {
            System.out.println("鏈結串列是空的。");
            return;
        }

        IntNode current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static int countNodes(IntNode head) {
        int count = 0;
        IntNode current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static int sumNodes(IntNode head) {
        int sum = 0;
        IntNode current = head;

        while (current != null) {
            sum += current.data;
            current = current.next;
        }

        return sum;
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
}