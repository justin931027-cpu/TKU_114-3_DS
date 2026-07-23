public class TaskLinkedList {
    private TaskNode head;

    public TaskLinkedList() {
        head = null;
    }

    public void addUrgentTask(String code, String description) {
        if (!isValidInput(code, description)) {
            return;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，工作代碼不可重複。");
            return;
        }

        TaskNode newNode = new TaskNode(
                code.trim(),
                description.trim()
        );

        newNode.next = head;
        head = newNode;

        System.out.println("緊急工作新增成功。");
    }

    public void addNormalTask(String code, String description) {
        if (!isValidInput(code, description)) {
            return;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，工作代碼不可重複。");
            return;
        }

        TaskNode newNode = new TaskNode(
                code.trim(),
                description.trim()
        );

        if (head == null) {
            head = newNode;
            System.out.println("一般工作新增成功。");
            return;
        }

        TaskNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        System.out.println("一般工作新增成功。");
    }

    public TaskNode findByCode(String code) {
        if (code == null) {
            return null;
        }

        TaskNode current = head;

        while (current != null) {
            if (current.code.equalsIgnoreCase(code.trim())) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public void completeTask(String code) {
        TaskNode task = findByCode(code);

        if (task == null) {
            System.out.println("完成失敗，找不到工作。");
            return;
        }

        if (task.completed) {
            System.out.println("此工作已經完成。");
            return;
        }

        task.completed = true;
        System.out.println("工作完成：" + task.description);
    }

    public void removeTask(String code) {
        if (head == null) {
            System.out.println("工作清單是空的，無法刪除。");
            return;
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("工作代碼不可空白。");
            return;
        }

        if (head.code.equalsIgnoreCase(code.trim())) {
            System.out.println("刪除成功：" + head.description);
            head = head.next;
            return;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.code.equalsIgnoreCase(code.trim())) {
                System.out.println(
                        "刪除成功：" + current.next.description
                );

                current.next = current.next.next;
                return;
            }

            current = current.next;
        }

        System.out.println("刪除失敗，找不到工作。");
    }

    public void showUnfinishedTasks() {
        if (head == null) {
            System.out.println("工作清單是空的。");
            return;
        }

        boolean found = false;
        TaskNode current = head;

        System.out.println("=== 未完成工作 ===");

        while (current != null) {
            if (!current.completed) {
                System.out.println(current);
                found = true;
            }

            current = current.next;
        }

        if (!found) {
            System.out.println("目前沒有未完成工作。");
        }
    }

    public void showAllTasks() {
        if (head == null) {
            System.out.println("工作清單是空的。");
            return;
        }

        System.out.println("=== 全部工作 ===");

        TaskNode current = head;
        int number = 1;

        while (current != null) {
            System.out.println(number + ". " + current);
            current = current.next;
            number++;
        }
    }

    public int getTotalTaskCount() {
        int count = 0;
        TaskNode current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public int getUnfinishedTaskCount() {
        int count = 0;
        TaskNode current = head;

        while (current != null) {
            if (!current.completed) {
                count++;
            }

            current = current.next;
        }

        return count;
    }

    private boolean isValidInput(String code, String description) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("工作代碼不可空白。");
            return false;
        }

        if (description == null || description.trim().isEmpty()) {
            System.out.println("工作說明不可空白。");
            return false;
        }

        return true;
    }
}