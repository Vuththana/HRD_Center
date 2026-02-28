import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    public static int transactionCounter = 600231;

    private final int transactionId;
    private final String transactionType;
    private final LocalDateTime transactionDate;
    private final String userName;
    private final String accountNumber;
    private final double amount;
    private final String remark;

    public Transaction(String transactionType, double amount, String userName, String accountNumber, String remark) {
        this.transactionId = transactionCounter++;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.remark = remark;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getRemark() {
        return remark;
    }
}
