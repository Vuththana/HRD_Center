import interfaces.AccountOperation;
import utils.Config;
import utils.Gender;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Account implements AccountOperation {
    private String accountNumber;
    private String userName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private double balance;
    private final String[] usedNumbers = new String[2];
    int usedNumbersCount = 0;
    protected Transaction[] transaction = new Transaction[100];
    protected int transactionCount = 0;

    protected Scanner scanner;

    Account(){}

    Account(Scanner scanner) {
        this.scanner = scanner;
    }

    private String validateName() {
        while (true) {
            System.out.print("[+] Please enter your username: ");
            String input = scanner.nextLine().trim();

            if (!input.matches(Config.IS_NAME)) {
                System.out.println(Config.INVALID_INPUT);
            } else {
                return input;
            }
        }
    }

    private LocalDate validateDob() {
        LocalDate today = LocalDate.now();
        while (true) {
            System.out.print("[+] Please enter your dateOfBirth (DD-MM-YYYY): ");
            String input = scanner.nextLine().trim();

            if (!input.matches(Config.IS_BOD)) {
                System.out.println(Config.INVALID_INPUT);
                continue;
            }

            int[] maxDays = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

            String[] parts = input.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            if (isLeap && month == 2) {
                maxDays[2] = 29;
            }

            if(month < 1 || month > 12) {
                System.out.println(Config.INVALID_MONTH);
                continue;
            }

            if (day < 1 || day > maxDays[month]) {
                System.out.println(Config.INVALID_DAY);
                continue;
            }


            LocalDate dob = LocalDate.parse(input, Config.FORMATTER);
            if(dob.isAfter(today)) {
                System.out.println(Config.BIRTH_DATE_FUTURE);
                continue;
            }

            LocalDate adultDate =  today.minusYears(18);
            if(dob.isAfter(adultDate)) {
                System.out.println(Config.NOT_OLD_ENOUGH);
            }
            return dob;
        }
    }

    private Gender validateGender() {
        while(true) {
            String input;
            System.out.println("[+] Please choose your gender (Male/Female)");
            System.out.print("""
                    1. Male
                    2. Female
                    """);
            System.out.print("=> Enter your choice: ");
            input = scanner.nextLine().trim();
            if(!input.matches(Config.IS_NUMBER) || Integer.parseInt(input) > 2 || Integer.parseInt(input) < 1) {
                System.out.println(Config.INVALID_INPUT);
            } else {
                if(Integer.parseInt(input) == 1) return Gender.MALE;
                if(Integer.parseInt(input) == 2) return Gender.FEMALE;
            }
        }
    }

    private String validatePhoneNumber() {
        while(true) {
            String input;
            System.out.print("[+] Please enter your phone number: ");
            input = scanner.nextLine().trim();
            if(!input.matches(Config.IS_PHONE_NUMBER)) {
                System.out.println(Config.INVALID_PHONE_NUMBER);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < input.length(); i++) {
                    if(i==3 || i ==6) {
                        sb.append(" ");
                    }
                    sb.append(input.charAt(i));
                }
                return sb.toString();
            }
        }
    }

    private String validateAccountNumber(){
        Random rand = new Random();
        String number;
        boolean exist = false;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                sb.append(rand.nextInt(10));
                if(i == 2 || i == 5) sb.append(" ");
            }
            number = sb.toString();
            exist = false;
            for (int i = 0; i < usedNumbersCount; i++) {
                if(usedNumbers[i].equals(number)) {
                    exist = true;
                    break;
                }
            }
        } while(exist);
        usedNumbers[usedNumbersCount++] = number;
        return number;
    }

    @Override
    public void setupAccount(String accountType) {
        System.out.print(Config.ANSI_CYAN + """
            ------------------------------------------------------------------
            |                   Input Account Information                    |
            ------------------------------------------------------------------
            """ + Config.ANSI_RESET);
        // Name
        this.userName = validateName();
        // Date Of Birth
        this.dateOfBirth = validateDob();
        //utils.Gender
        this.gender = validateGender();
        // Phone Number
        this.phoneNumber = validatePhoneNumber();
        // Account Number
        this.accountNumber = validateAccountNumber();

        System.out.println("\n" +Config.ANSI_GREEN + accountType + " Created" + Config.ANSI_RESET + "\n");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    @Override
    public void deposit(double amount, String remark) {
        balance += amount;

        if(transactionCount < transaction.length) {
            transaction[transactionCount] = new Transaction("Deposit", amount, getUserName(), getAccountNumber() , remark);
            transactionCount++;
        } else {
            System.out.println("Warning: Transaction history full!");
        }
    }

    @Override
    public void withdraw(double amount, String remark) {
        balance -= amount;
        if(transactionCount < transaction.length) {
            transaction[transactionCount] = new Transaction("Withdraw", amount, getUserName(), getAccountNumber() , remark);
            transactionCount++;
        } else {
            System.out.println("Warning: Transaction history full!");
        }
    }

    @Override
    public void transfer(AccountOperation target, double amount, String remark) {
        target.setBalance(target.getBalance() + amount);
        balance -= amount;
        if(transactionCount < transaction.length) {
            transaction[transactionCount] = new Transaction("Transfer", amount, target.getUserName(), target.getAccountNumber() , remark);
            transactionCount++;
        } else {
            System.out.println("Warning: Transaction history full!");
        }
    }

    @Override
    public void displayAccountInfo() {
        if(this instanceof CheckingAccount) {
            System.out.println(Config.ANSI_CYAN + """
            ------------------------------------------------------------------
            |                   Checking Account's Information               |
            ------------------------------------------------------------------
            
            """ + Config.ANSI_RESET);
        }
        if(this instanceof SavingAccount) {
            System.out.println(Config.ANSI_CYAN + """
            ------------------------------------------------------------------
            |                     Saving Account's Information               |
            ------------------------------------------------------------------
            
            """ + Config.ANSI_RESET);
        }

        System.out.println("Account Type    : " + (this instanceof SavingAccount ? "Saving Account" : "Checking Account"));
        System.out.println("Account Number  : " + this.getAccountNumber());
        System.out.println("User Name       : " + this.getUserName().toUpperCase());
        System.out.println("Date of Birth   : " + this.getDateOfBirth());
        System.out.println("Gender          : " + this.getGender().toString());
        System.out.println("Phone Number    : " + this.getPhoneNumber());
        System.out.println("Balance         : $" + this.getBalance());
        if(this instanceof SavingAccount)
            System.out.println("Rate            : " + ((SavingAccount) this).getInterestRate());
        System.out.println(Config.ANSI_CYAN + "==================================================================" + Config.ANSI_RESET);
    }

    @Override
    public void displayTransactionInfo() {
        if(transactionCount == 0) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println(Config.ANSI_CYAN + """
            ------------------------------------------------------------------
            |             Checking Account Transaction History               |
            ------------------------------------------------------------------
            
            """ + Config.ANSI_RESET);
        for(int i = 0; i < transactionCount; i++) {
            Transaction t = transaction[i];
            System.out.println("=".repeat(66));
            System.out.println("Transaction ID      : " + t.getTransactionId());
            System.out.println("Transaction Type    : " + t.getTransactionType());
            System.out.println("Transaction Date    : " + t.getTransactionDate());
            System.out.println("To Account          : " + t.getAmount());
            System.out.println("Remark              : " + t.getRemark());
        }
        System.out.println("=".repeat(66));
    }

    public boolean deleteAccount() {
        if(balance == 0) {
            System.out.println(Config.ANSI_GREEN + "Account deleted successfully." + Config.ANSI_RESET);
            return true;
        } else {
            displayAccountInfo();
            System.out.println(Config.ANSI_RED + "[!] Cannot delete Account because there is no other account to transfer the balance." + Config.ANSI_RESET);
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}