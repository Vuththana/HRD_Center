package interfaces;

public interface AccountOperation {
    void setupAccount(String accountType);
    double getBalance();
    void setBalance(double balance);
    String getAccountNumber();
    String getUserName();

    void deposit(double amount, String remark);
    void withdraw(double amount, String remark);
    void transfer(AccountOperation target, double amount, String remark);
    void displayAccountInfo();
    void displayTransactionInfo();
    boolean deleteAccount();
}
