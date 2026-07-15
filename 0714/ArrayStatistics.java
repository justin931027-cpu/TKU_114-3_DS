import java.util.Scanner;

public class ArrayStatistics {

    // 輸入資料筆數，範圍為 1～50
    public static int readCount(Scanner sc) {
        int count;

        do {
            System.out.print("請輸入資料筆數（1～50）：");
            count = sc.nextInt();

            if (count < 1 || count > 50) {
                System.out.println("輸入錯誤，資料筆數必須介於 1～50。");
            }
        } while (count < 1 || count > 50);

        return count;
    }

    // 輸入每一筆成績，範圍為 0～100
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            do {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績（0～100）：");
                scores[i] = sc.nextInt();

                if (scores[i] < 0 || scores[i] > 100) {
                    System.out.println("輸入錯誤，成績必須介於 0～100。");
                }
            } while (scores[i] < 0 || scores[i] > 100);
        }
    }

    // 計算總分
    public static int calculateTotal(int[] scores) {
        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return total;
    }

    // 尋找最高分
    public static int findMax(int[] scores) {
        int max = scores[0];

        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }

        return max;
    }

    // 尋找最低分
    public static int findMin(int[] scores) {
        int min = scores[0];

        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }

        return min;
    }

    // 計算及格人數
    public static int countPass(int[] scores) {
        int passCount = 0;

        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }

        return passCount;
    }

    // 尋找目標成績第一次出現的索引
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);
        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.println("\n全部成績：");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("索引 " + i + "：" + scores[i]);
        }

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;

        System.out.println("\n成績統計結果：");
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f%n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);

        System.out.print("\n請輸入要搜尋的成績：");
        int target = sc.nextInt();

        int index = findIndex(scores, target);

        if (index >= 0) {
            System.out.println(target + " 第一次出現在索引 " + index);
        } else {
            System.out.println("找不到成績 " + target);
        }

        sc.close();
    }
}