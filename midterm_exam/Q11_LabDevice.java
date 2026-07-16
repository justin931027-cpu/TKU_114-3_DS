package midterm_exam;
public class Q11_LabDevice {
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    public Q11_LabDevice(
            String code,
            String name,
            int usageHours,
            boolean active
    ) {
        // code 為 null 或去除空白後為空字串
        if (code == null || code.trim().isEmpty()) {
            this.code = "UNKNOWN";
        } else {
            this.code = code.trim();
        }

        // name 為 null 或去除空白後為空字串
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim();
        }

        // 使用時數小於 0 時存成 0
        if (usageHours < 0) {
            this.usageHours = 0;
        } else {
            this.usageHours = usageHours;
        }

        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getUsageHours() {
        return usageHours;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        // 只接受非 null 且去除空白後非空的名稱
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void addUsageHours(int hours) {
        // 只接受大於 0 的時數
        if (hours > 0) {
            usageHours += hours;
        }
    }

    public void deactivate() {
        active = false;
    }

    public boolean needsMaintenance() {
        return usageHours >= 100;
    }

    @Override
    public String toString() {
        return code + " | "
                + name + " | "
                + usageHours + " hours | "
                + (active ? "active" : "inactive");
    }

    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(
                " D01 ",
                " Printer ",
                90,
                true
        );

        device.addUsageHours(30);
        device.addUsageHours(-5);
        device.setName(" 3D Printer ");

        System.out.println(device);
        System.out.println("需要保養：" + device.needsMaintenance());

        device.deactivate();
        System.out.println("啟用狀態：" + device.isActive());
    }
}