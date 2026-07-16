package midterm_exam;
public class Q06_CommandValidator {
    public static void main(String[] args) {
        String[] commands = {
            "START",
            new String("stop"),
            "  Pause  ",
            "RUN",
            "",
            null
        };

        for (String command : commands) {
            System.out.println(command + " -> " + isValidCommand(command));
        }
    }

    public static boolean isValidCommand(String command) {
        // null 不能呼叫 trim()，所以要先判斷
        if (command == null) {
            return false;
        }

        // 移除前後空白
        String cleanedCommand = command.trim();

        // 忽略英文大小寫，比對三種有效指令
        return cleanedCommand.equalsIgnoreCase("START")
                || cleanedCommand.equalsIgnoreCase("STOP")
                || cleanedCommand.equalsIgnoreCase("PAUSE");
    }
}