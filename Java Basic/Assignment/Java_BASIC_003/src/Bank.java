import interfaces.AccountOperation;
import interfaces.BankingOperation;
import utils.Config;

import java.util.Scanner;

public class Bank implements BankingOperation {
    private AccountOperation acc;

    protected Scanner scanner;
    Bank(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void setAccount(AccountOperation acc) {
        this.acc = acc;
    }

    @Override
    public void deposit() {
        while(true) {
            System.out.print("=> Input Deposit Amount: $");
            String input = scanner.nextLine().trim();
            if(!input.matches(Config.IS_NUMBER) || Double.parseDouble(input) == 0) {
                System.out.println(Config.INVALID_DEPOSIT);

            }
            double amount = Double.parseDouble(input);
            if(amount <= 0) {
                System.out.println(Config.ANSI_RED + "[!] Amount must be greater than 0" + Config.ANSI_RESET);
            }
            else {
                System.out.print("=> Enter Remark (Enter to Skip): ");
                String remark = scanner.nextLine().trim();
                if(remark.isEmpty()) {
                    remark = "Cash Deposit";
                }
                acc.deposit(amount, remark);
                updateInterest();
                System.out.println(Config.ANSI_GREEN + "\nDeposit Successful!" + Config.ANSI_RESET);
                System.out.println("Deposited: $" + amount);
                System.out.println("New Balance: $" + acc.getBalance());
                break;
            }
        }
    }

    @Override
    public void withdraw() {
        while(true) {
            System.out.print("=> Input Withdraw Amount: $");
            String input = scanner.nextLine().trim();
            if(!input.matches(Config.IS_NUMBER) || Double.parseDouble(input) == 0) {
                System.out.println(Config.INVALID_WITHDRAW);
                continue;
            }
            double amount = Double.parseDouble(input);
            if(amount < 0) {
                System.out.println(Config.AMOUNT_GREATER_THAN_ZERO);
            }
            if(amount > acc.getBalance()) {
                System.out.println(Config.AMOUNT_GREATER_THAN_BALANCE);
                if(askContinue()) {
                    withdraw();
                } else {
                    break;
                }
            }
            else {
                System.out.print("=> Enter Remark (Enter to Skip): ");
                String remark = scanner.nextLine().trim();
                if(remark.isEmpty()) {
                    remark = "Cash Withdraw";
                }
                acc.withdraw(amount, remark);
                updateInterest();
                System.out.println(Config.ANSI_GREEN + "\nWithdraw Successful!" + Config.ANSI_RESET);
                System.out.println("Withdrew: $" + amount);
                System.out.println("New Balance: $" + acc.getBalance());
                break;
            }
        }
    }


    @Override
    public void transfer(AccountOperation toAcc) {
        if(toAcc instanceof SavingAccount) {
            System.out.println("""
                ------------------------------------------------------------------
                |        Transfer From Checking Account -> Saving Account        |
                ------------------------------------------------------------------
                """);
        }
        if(toAcc instanceof CheckingAccount) {
            System.out.println("""
                ------------------------------------------------------------------
                |        Transfer From  Saving Account -> Checking Account       |
                ------------------------------------------------------------------
                """);
        }
        inputAmount:
        while(true) {
            System.out.print("=> Input Transfer Amount: $");
            String amount = scanner.nextLine().trim();
            System.out.println("==================================================================");
            if(!amount.matches(Config.IS_MONEY) || Double.parseDouble(amount) == 0) {
                System.out.println(Config.INVALID_INPUT);
            } else {
                if(Double.parseDouble(amount) > acc.getBalance()) {
                    System.out.println(Config.AMOUNT_GREATER_THAN_BALANCE);
                    if(askContinue()) {
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.print("=> Enter Remark (Enter to Skip): ");
                    String remark = scanner.nextLine().trim();
                    while(true) {
                        System.out.print("Are you sure you want to Transfer? (Yy/Nn) ");
                        String yn = scanner.nextLine().trim().toLowerCase();
                        if(yn.equals("y")) {
                            acc.transfer(toAcc, Double.parseDouble(amount), remark);
                            updateInterest();
                            if(askContinue()) {
                                continue inputAmount;
                            } else {
                                break inputAmount;
                            }
                        }
                        if(yn.equals("n")) break inputAmount;
                        else {
                            System.out.println(Config.INVALID_INPUT);
                        }
                    }
                }
            }
        }
    }
    private boolean askContinue() {
        while(true) {
            System.out.print("Do you want to continue? (Yy/Nn) ");
            String yn = scanner.nextLine().trim().toLowerCase();
            if(yn.equals("y")) return true;
            if(yn.equals("n")) return false;
            else System.out.println(Config.INVALID_INPUT);
        }
    }
    private void updateInterest() {
        if(acc instanceof SavingAccount) {
            ((SavingAccount) acc).setInterestRate();
            ((SavingAccount) acc).updateInterestRate();
        }
    }
}
