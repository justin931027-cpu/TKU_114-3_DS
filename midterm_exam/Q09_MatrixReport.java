package midterm_exam;
public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };

        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    public static int rowSum(int[][] data, int row) {
        // 二維陣列或列索引不合法
        if (data == null || row < 0 || row >= data.length
                || data[row] == null) {
            return -1;
        }

        int sum = 0;

        for (int value : data[row]) {
            sum += value;
        }

        return sum;
    }

    public static int columnSum(int[][] data, int column) {
        // 二維陣列或欄索引不合法
        if (data == null || data.length == 0 || column < 0) {
            return -1;
        }

        int sum = 0;

        for (int[] row : data) {
            // 該列不存在，或沒有指定的欄位
            if (row == null || column >= row.length) {
                return -1;
            }

            sum += row[column];
        }

        return sum;
    }

    public static int countAtLeast(int[][] data, int target) {
        if (data == null) {
            return 0;
        }

        int count = 0;

        for (int[] row : data) {
            if (row == null) {
                continue;
            }

            for (int value : row) {
                if (value >= target) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int findRowWithLargestTotal(int[][] data) {
        // 二維陣列沒有資料
        if (data == null || data.length == 0) {
            return -1;
        }

        int largestIndex = -1;
        int largestTotal = Integer.MIN_VALUE;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null || data[i].length == 0) {
                continue;
            }

            int total = 0;

            for (int value : data[i]) {
                total += value;
            }

            /*
             * 只使用 >，不使用 >=。
             * 若總和相同，就會保留較早出現、索引較小的列。
             */
            if (total > largestTotal) {
                largestTotal = total;
                largestIndex = i;
            }
        }

        return largestIndex;
    }
}