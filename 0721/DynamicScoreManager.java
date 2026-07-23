import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");

            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入整數。");
                sc.next();
                continue;
            }

            int score = sc.nextInt();

            if (score == -1) {
                break;
            }

            if (score < 0 || score > 100) {
                System.out.println("成績只能輸入 0 到 100。");
                continue;
            }

            scores.add(score);
        }

        if (scores.isEmpty()) {
            System.out.println("沒有輸入任何成績。");
        } else {
            showStatistics(scores);
            showPassedStudents(scores);
        }

        sc.close();
    }

    public static void showStatistics(ArrayList<Integer> scores) {
        int total = 0;
        int highest = scores.get(0);
        int lowest = scores.get(0);

        for (int score : scores) {
            total += score;

            if (score > highest) {
                highest = score;
            }

            if (score < lowest) {
                lowest = score;
            }
        }

        double average = (double) total / scores.size();

        System.out.println("\n=== 成績統計 ===");
        System.out.println("成績筆數：" + scores.size());
        System.out.printf("平均成績：%.2f%n", average);
        System.out.println("最高成績：" + highest);
        System.out.println("最低成績：" + lowest);
    }

    public static void showPassedStudents(ArrayList<Integer> scores) {
        ArrayList<Integer> passedScores = new ArrayList<>();

        for (int score : scores) {
            if (score >= 60) {
                passedScores.add(score);
            }
        }

        System.out.println("及格名單：" + passedScores);
    }
}