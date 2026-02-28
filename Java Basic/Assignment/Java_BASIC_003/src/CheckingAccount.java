import interfaces.InterestRate;

import java.util.Scanner;

public class CheckingAccount extends Account implements InterestRate {
    public CheckingAccount(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void interestRate(){}



}
