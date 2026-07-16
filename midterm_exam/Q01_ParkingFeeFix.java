package midterm_exam;
public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};

        for (int minutes : testMinutes) {
            int fee = calculateFee(minutes);
            System.out.println("停車 " + minutes + " 分鐘，費用：" + fee + " 元");
        }
    }

    public static int calculateFee(int minutes) {
        // 停車時間小於 0 分鐘
        if (minutes < 0) {
            return -1;
        }

        // 0～30 分鐘免費
        if (minutes <= 30) {
            return 0;
        }

        // 超過 30 分鐘至 120 分鐘
        // 每開始 30 分鐘收費 20 元
        if (minutes <= 120) {
            return ((minutes - 30 + 29) / 30) * 20;
        }

        // 超過 120 分鐘
        // 前 120 分鐘為 60 元，之後每開始 60 分鐘加收 30 元
        int fee = 60 + ((minutes - 120 + 59) / 60) * 30;

        // 單次停車費最高 180 元
        if (fee > 180) {
            fee = 180;
        }

        return fee;
    }
}