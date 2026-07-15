/*
錯誤 1
錯誤位置：
System.out.println("系統結束，年齡：" + age)

錯誤類型：
編譯錯誤。

原因：
println 敘述後面缺少分號。

修正方式：
改成：
System.out.println("系統結束，年齡：" + age);


錯誤 2
錯誤位置：
for (int i = 0; i <= scores.length; i++)

錯誤類型：
陣列越界錯誤。

原因：
scores.length 是 3，但合法索引只有 0、1、2。
當 i 等於 3 時，程式會存取 scores[3]，造成
ArrayIndexOutOfBoundsException。

修正方式：
將 <= 改成 <：
for (int i = 0; i < scores.length; i++)


錯誤 3
錯誤位置：
if (command == "exit")

錯誤類型：
字串比較邏輯錯誤。

原因：
== 比較的是物件位址，不是字串內容。

修正方式：
使用 equalsIgnoreCase()：
if (command.equalsIgnoreCase("exit"))


錯誤 4
錯誤位置：
double average = total / scores.length;

錯誤類型：
整數除法造成的邏輯錯誤。

原因：
total 和 scores.length 都是 int，
會先進行整數除法，小數部分會被捨去。

修正方式：
將其中一個值轉成 double：
double average = (double) total / scores.length;


錯誤 5
錯誤位置：
int age = sc.nextInt();
String command = sc.nextLine();

錯誤類型：
Scanner 換行問題。

原因：
nextInt() 只讀取整數，不會讀取按下 Enter 後留下的換行字元。
下一個 nextLine() 會直接讀到換行，因此 command 會變成空字串。

修正方式：
在 nextInt() 後加入：
sc.nextLine();


Breakpoint 文字記錄：

斷點位置：
total += scores[i];

重要變數值：
第 1 次迴圈：
i = 0
scores[i] = 80
total 執行前 = 0
total 執行後 = 80

第 2 次迴圈：
i = 1
scores[i] = 75
total 執行前 = 80
total 執行後 = 155

第 3 次迴圈：
i = 2
scores[i] = 92
total 執行前 = 155
total 執行後 = 247

原錯誤程式進入第 4 次迴圈時：
i = 3
scores.length = 3
準備存取 scores[3]
但合法索引只有 0～2，因此發生陣列越界錯誤。

修正後平均值計算：
total = 247
scores.length = 3
average = 82.33333333333333
*/

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equalsIgnoreCase("exit")) {
            System.out.println("系統結束，年齡：" + age);
        } else {
            System.out.println("收到指令：" + command);
        }

        sc.close();
    }
}