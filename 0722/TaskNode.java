public class TaskNode {
    String code;
    String description;
    boolean completed;
    TaskNode next;

    public TaskNode(String code, String description) {
        this.code = code;
        this.description = description;
        this.completed = false;
        this.next = null;
    }

    @Override
    public String toString() {
        String status;

        if (completed) {
            status = "已完成";
        } else {
            status = "未完成";
        }

        return "工作代碼：" + code
                + "，說明：" + description
                + "，狀態：" + status;
    }
}