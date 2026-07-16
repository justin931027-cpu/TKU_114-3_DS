package midterm_exam;
public class Q03_AccessDecision {
    public static void main(String[] args) {
        System.out.println(decideAccess(20, true, false, false));
        System.out.println(decideAccess(20, false, true, false));
        System.out.println(decideAccess(17, true, true, false));
        System.out.println(decideAccess(30, true, true, true));
        System.out.println(decideAccess(130, true, true, false));
    }

    public static String decideAccess(
        int age,
        boolean member,
        boolean hasInvitation,
        boolean suspended
    ) {
        // 1. 年齡無效
        if (age < 0 || age > 120) {
            return "INVALID";
        }

        // 2. 帳號已停權
        if (suspended) {
            return "DENIED";
        }

        // 3. 年滿 18 歲，且具有會員資格或邀請碼
        if (age >= 18 && (member || hasInvitation)) {
            return "ALLOWED";
        }

        // 4. 其他情況
        return "REVIEW";
    }
}