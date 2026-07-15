public class BankAccount {
    private String accountNumber;
    private String accountName;
    private int balance;

    // 建構子
    public BankAccount(String accountNumber, String accountName, int balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;

        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("初始餘額不可小於 0，已設定為 0。");
            this.balance = 0;
        }
    }

    // getter，不提供 setBalance()
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getBalance() {
        return balance;
    }

    // 存款
    public boolean deposit(int amount) {
        if (amount <= 0) {
            System.out.println("存款金額必須大於 0。");
            return false;
        }

        balance += amount;
        return true;
    }

    // 提款
    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("提款金額必須大於 0。");
            return false;
        }

        if (amount > balance) {
            System.out.println("餘額不足，提款失敗。");
            return false;
        }

        balance -= amount;
        return true;
    }

    // 轉帳
    public boolean transferTo(BankAccount target, int amount) {
        if (target == null) {
            System.out.println("目標帳戶不存在，轉帳失敗。");
            return false;
        }

        if (target == this) {
            System.out.println("不能轉帳給自己的帳戶。");
            return false;
        }

        if (amount <= 0) {
            System.out.println("轉帳金額必須大於 0。");
            return false;
        }

        if (amount > balance) {
            System.out.println("餘額不足，轉帳失敗。");
            return false;
        }

        balance -= amount;
        target.balance += amount;

        return true;
    }

    @Override
    public String toString() {
        return "帳號：" + accountNumber
                + "，戶名：" + accountName
                + "，餘額：" + balance;
    }
}