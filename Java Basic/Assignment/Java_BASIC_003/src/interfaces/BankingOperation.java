package interfaces;

public interface BankingOperation {
    void setAccount(AccountOperation account);
    void deposit();
    void withdraw();
    void transfer(AccountOperation toAccount);
}
