package midterm_exam;
public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));
        System.out.println(sumOddRange(7, 3));
        System.out.println(sumOddRange(0, 2));
        System.out.println(sumOddRange(-3, 3));
    }

    public static int sumOddRange(int start, int end) {
        int sum = 0;

        // 如果起點大於終點，交換兩者
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // 包含起點與終點
        for (int i = start; i <= end; i++) {
            // 奇數才加總
            if (i % 2 != 0) {
                sum += i;
            }
        }

        return sum;
    }
}