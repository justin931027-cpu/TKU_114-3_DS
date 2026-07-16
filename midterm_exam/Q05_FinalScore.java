package midterm_exam;
public class Q05_FinalScore {
    public static void main(String[] args) {
        System.out.println(calculateFinalScore(80, 90, 5));
        System.out.println(calculateFinalScore(100, 100, 10));
        System.out.println(calculateFinalScore(-1, 80, 5));
        System.out.println(calculateFinalScore(70, 60, 11));
    }

    public static double calculateFinalScore(
        int examScore,
        int assignmentScore,
        int bonus
    ) {
        // 考試成績必須介於 0～100
        if (examScore < 0 || examScore > 100) {
            return -1.0;
        }

        // 作業成績必須介於 0～100
        if (assignmentScore < 0 || assignmentScore > 100) {
            return -1.0;
        }

        // 加分必須介於 0～10
        if (bonus < 0 || bonus > 10) {
            return -1.0;
        }

        // 考試占 40%，作業占 60%，再加上加分
        double finalScore =
                examScore * 0.4
                + assignmentScore * 0.6
                + bonus;

        // 最終成績最高為 100 分
        if (finalScore > 100) {
            finalScore = 100;
        }

        return finalScore;
    }
}