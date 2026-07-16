package midterm_exam;
public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        // null 或全部都是空白
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        // 使用 -1 保留結尾的空欄位
        String[] parts = record.split("\\|", -1);

        // 必須剛好有 4 個欄位
        if (parts.length != 4) {
            return false;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();

        // 代碼與名稱不可為空字串
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());

            // 數量必須大於 0，單價必須大於等於 0
            return quantity > 0 && price >= 0;
        } catch (NumberFormatException e) {
            // 數量或單價無法轉成整數
            return false;
        }
    }

    public static int calculateRecordTotal(String record) {
        // 不合法紀錄回傳 -1
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|", -1);

        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());

        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }

        int count = 0;

        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }

        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        if (records == null) {
            return 0;
        }

        int grandTotal = 0;

        for (String record : records) {
            int recordTotal = calculateRecordTotal(record);

            // 只加總合法紀錄
            if (recordTotal != -1) {
                grandTotal += recordTotal;
            }
        }

        return grandTotal;
    }
}