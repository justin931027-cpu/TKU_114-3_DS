import java.util.Scanner;

public class TextAnalyzer {

    // 輸入不可為空字串或全部空白
    public static String readNonBlankText(Scanner sc, String message) {
        String text;

        do {
            System.out.print(message);
            text = sc.nextLine();

            if (text.trim().isEmpty()) {
                System.out.println("輸入錯誤，內容不可為空白，請重新輸入。");
            }
        } while (text.trim().isEmpty());

        return text;
    }

    // 使用連續空白切割單字
    public static String[] splitWords(String text) {
        return text.trim().split("\\s+");
    }

    // 計算有效字元數
    public static int countValidCharacters(String text) {
        return text.trim().length();
    }

    // 計算英文字母母音總數
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        return count;
    }

    // 找出最長單字
    public static String findLongestWord(String[] words) {
        String longestWord = words[0];

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        return longestWord;
    }

    // 計算關鍵字出現次數，忽略大小寫
    public static int countKeyword(String[] words, String keyword) {
        int count = 0;

        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }

        return count;
    }

    // 顯示所有單字
    public static void displayWords(String[] words) {
        System.out.println("\n單字內容：");

        for (int i = 0; i < words.length; i++) {
            System.out.println("第 " + (i + 1) + " 個單字：" + words[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = readNonBlankText(
                sc, "請輸入一行非空白文字："
        );

        String trimmedText = original.trim();
        String[] words = splitWords(trimmedText);

        System.out.println("\n===== 文字分析結果 =====");
        System.out.println("原始文字：" + original);
        System.out.println("移除前後空白：" + trimmedText);
        System.out.println(
                "有效字元數：" + countValidCharacters(original)
        );

        displayWords(words);

        System.out.println("\n單字數量：" + words.length);
        System.out.println(
                "英文字母母音總數：" + countVowels(trimmedText)
        );
        System.out.println(
                "最長單字：" + findLongestWord(words)
        );

        String keyword = readNonBlankText(
                sc, "\n請輸入要搜尋的關鍵字："
        ).trim();

        int keywordCount = countKeyword(words, keyword);

        System.out.println(
                "關鍵字「" + keyword + "」出現次數："
                        + keywordCount
        );

        sc.close();
    }
}