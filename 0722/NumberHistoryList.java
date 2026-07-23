public class NumberHistoryList {

    public static void main(String[] args) {
        IntNode head = null;

        // 第 1 次操作：空串列輸出
        System.out.println("1. 初始串列：");
        printList(head);

        // 第 2 次操作：前端新增
        head = addFirst(head, 20);
        System.out.println("\n2. 前端新增 20：");
        printList(head);

        // 第 3 次操作：前端新增
        head = addFirst(head, 10);
        System.out.println("\n3. 前端新增 10：");
        printList(head);

        // 第 4 次操作：尾端新增
        head = addLast(head, 30);
        System.out.println("\n4. 尾端新增 30：");
        printList(head);

        // 第 5 次操作：尾端新增
        head = addLast(head, 40);
        System.out.println("\n5. 尾端新增 40：");
        printList(head);

        // 第 6 次操作：搜尋
        System.out.println("\n6. 搜尋 30：" + contains(head, 30));

        // 第 7 次操作：刪除
        head = removeValue(head, 20);
        System.out.println("\n7. 刪除 20：");
        printList(head);

        // 第 8 次操作：統計
        System.out.println("\n8. 串列統計：");
        printStatistics(head);

        // 額外測試找不到資料
        System.out.println("\n搜尋 99：" + contains(head, 99));

        // 額外測試刪除不存在資料
        head = removeValue(head, 99);
    }

    public static IntNode addFirst(IntNode head, int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        return newNode;
    }

    public static IntNode addLast(IntNode head, int value) {
        IntNode newNode = new IntNode(value);

        if (head == null) {
            return newNode;
        }

        IntNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
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
            System.out.println("串列是空的，無法刪除。");
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
            System.out.println("串列是空的。");
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

    public static void printStatistics(IntNode head) {
        if (head == null) {
            System.out.println("size：0");
            System.out.println("總和：0");
            System.out.println("最大值：無");
            System.out.println("最小值：無");
            return;
        }

        int size = 0;
        int sum = 0;
        int max = head.data;
        int min = head.data;

        IntNode current = head;

        while (current != null) {
            size++;
            sum += current.data;

            if (current.data > max) {
                max = current.data;
            }

            if (current.data < min) {
                min = current.data;
            }

            current = current.next;
        }

        System.out.println("size：" + size);
        System.out.println("總和：" + sum);
        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }
}