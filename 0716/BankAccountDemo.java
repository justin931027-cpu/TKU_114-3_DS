public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account1 =
                new BankAccount("A001", "王小明", 10000);

        BankAccount account2 =
                new BankAccount("A002", "陳小華", 5000);

        System.out.println("===== 初始帳戶資料 =====");
        System.out.println(account1);
        System.out.println(account2);

        // 存款測試
        System.out.println("\n===== 存款測試 =====");

        boolean depositResult = account1.deposit(2000);

        if (depositResult) {
            System.out.println("存款成功。");
        } else {
            System.out.println("存款失敗。");
        }

        System.out.println(account1);

        // 提款成功測試
        System.out.println("\n===== 提款成功測試 =====");

        boolean withdrawSuccess = account1.withdraw(3000);

        if (withdrawSuccess) {
            System.out.println("提款成功。");
        } else {
            System.out.println("提款失敗。");
        }

        System.out.println(account1);

        // 提款失敗測試
        System.out.println("\n===== 提款失敗測試 =====");

        boolean withdrawFail = account2.withdraw(10000);

        if (withdrawFail) {
            System.out.println("提款成功。");
        } else {
            System.out.println("提款失敗，帳戶餘額沒有改變。");
        }

        System.out.println(account2);

        // 成功轉帳測試
        System.out.println("\n===== 成功轉帳測試 =====");

        boolean transferSuccess =
                account1.transferTo(account2, 2000);

        if (transferSuccess) {
            System.out.println("轉帳成功。");
        } else {
            System.out.println("轉帳失敗。");
        }

        System.out.println(account1);
        System.out.println(account2);

        // 失敗轉帳測試
        System.out.println("\n===== 失敗轉帳測試 =====");

        int account1Before = account1.getBalance();
        int account2Before = account2.getBalance();

        boolean transferFail =
                account1.transferTo(account2, 50000);

        if (transferFail) {
            System.out.println("轉帳成功。");
        } else {
            System.out.println("轉帳失敗，兩個帳戶餘額沒有改變。");
        }

        System.out.println(account1);
        System.out.println(account2);

        System.out.println(
                "帳戶 1 餘額是否未改變："
                        + (account1Before == account1.getBalance())
        );

        System.out.println(
                "帳戶 2 餘額是否未改變："
                        + (account2Before == account2.getBalance())
        );
    }
}