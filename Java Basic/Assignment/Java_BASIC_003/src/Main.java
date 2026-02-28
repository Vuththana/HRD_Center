import interfaces.*;
import utils.Config;

void main() {
    Scanner scanner = new Scanner(System.in);
    AccountOperation checkingAccount = null;
    AccountOperation savingAccount = null;
    BankingOperation bank = new Bank(scanner);
    mainMenu:
    while(true) {
        String action;
        System.out.print(Config.ANSI_CYAN + """
                ------------------------------------------------------------------
                |                   Online Banking System                        |
                ------------------------------------------------------------------
                """ + Config.ANSI_RESET);
        System.out.println("""
                1. Create Account
                2. Deposit Money
                3. Withdraw Money
                4. Transfer Money
                5. View Transaction History
                6. Display Account Information
                7. Delete Account
                8. Exit
                """);
        System.out.print("=> Option: ");
        action = scanner.nextLine().trim();
        if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 8 || Integer.parseInt(action) < 1) {
            System.out.println(Config.INVALID_INPUT);
        } else {
            switch (Integer.parseInt(action)) {
                case 1: {
                    while(true) {
                        System.out.print(Config.ANSI_CYAN + """
                            ------------------------------------------------------------------
                            |                       Creating Account                         |
                            ------------------------------------------------------------------
                            """ + Config.ANSI_RESET);
                        System.out.println("""
                            1. Checking Account
                            2. Saving Account
                            3. Back
                            """);
                        System.out.print("=> Option: ");
                        action = scanner.nextLine();
                        if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                            System.out.println(Config.INVALID_INPUT);
                        } else {
                            switch(Integer.parseInt(action)) {
                                case 1: {
                                    if(checkingAccount == null) {
                                        checkingAccount = new CheckingAccount(scanner);
                                        checkingAccount.setupAccount("Checking Account");
                                    } else {
                                        System.out.println(Config.ANSI_RED + "[!] You already have a checking account." + Config.ANSI_RESET);
                                    }
                                    continue;
                                }
                                case 2: {
                                    if(savingAccount == null) {
                                        savingAccount = new SavingAccount(scanner);
                                        savingAccount.setupAccount("Saving Account");
                                    } else {
                                        System.out.println(Config.ANSI_RED + "[!] You already have a saving account." + Config.ANSI_RESET);
                                    }
                                    continue;
                                }
                                case 3: continue mainMenu;
                            }
                        }
                    }
                }
                case 2: {
                    if(checkingAccount == null && savingAccount == null) {
                        System.out.println(Config.ANSI_RED + "You don't have have any account created yet." + Config.ANSI_RED);
                        continue;
                    } else {
                        while(true) {
                            System.out.print(Config.ANSI_CYAN + """
                                ------------------------------------------------------------------
                                |                       Deposit Account                          |
                                ------------------------------------------------------------------
                                """ + Config.ANSI_RESET);
                                            System.out.println("""
                                1. Checking Account
                                2. Saving Account
                                3. Back
                                """);
                            System.out.print("=> Option: ");
                            action = scanner.nextLine();
                            if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                                System.out.println(Config.INVALID_INPUT);
                            } else {
                                if(Integer.parseInt(action) == 1) {
                                    if(checkingAccount == null) {
                                        System.out.println(Config.NO_CHECKING_ACCOUNT);
                                    }
                                    else {
                                        bank.setAccount(checkingAccount);
                                        bank.deposit();
                                    }
                                }
                                if(Integer.parseInt(action) == 2) {
                                    if(savingAccount == null) {
                                        System.out.println(Config.NO_SAVING_ACCOUNT);
                                    }
                                    else {
                                        bank.setAccount(savingAccount);
                                        bank.deposit();
                                    }
                                }
                                if(Integer.parseInt(action) == 3) { continue mainMenu; }
                            }
                        }
                    }
                }
                case 3: {
                    if(checkingAccount == null && savingAccount == null) {
                        System.out.println(Config.ANSI_RED + "You don't have have any account created yet." + Config.ANSI_RED);
                        continue;
                    } else {
                        while(true) {
                            System.out.print(Config.ANSI_CYAN + """
                                ------------------------------------------------------------------
                                |                       Withdraw Account                         |
                                ------------------------------------------------------------------
                                """ + Config.ANSI_RESET);
                            System.out.println("""
                                1. Checking Account
                                2. Saving Account
                                3. Back
                                """);
                            System.out.print("=> Option: ");
                            action = scanner.nextLine();
                            if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                                System.out.println(Config.INVALID_INPUT);
                            } else {
                                if(Integer.parseInt(action) == 1) {
                                    if(checkingAccount == null) {
                                        System.out.println(Config.NO_CHECKING_ACCOUNT);
                                    }
                                    else {
                                        bank.setAccount(checkingAccount);
                                        bank.withdraw();
                                    }
                                }
                                if(Integer.parseInt(action) == 2) {
                                    if(savingAccount == null) {
                                        System.out.println(Config.NO_SAVING_ACCOUNT);
                                    }
                                    else {
                                        bank.setAccount(savingAccount);
                                        bank.withdraw();
                                    }
                                }
                                if(Integer.parseInt(action) == 3) { continue mainMenu; }
                            }
                        }
                    }
                }
                case 4: {
                    while(true) {
                        System.out.print(Config.ANSI_CYAN + """
                                ------------------------------------------------------------------
                                |                       Transfer Account                         |
                                ------------------------------------------------------------------
                                """ + Config.ANSI_RESET);
                        System.out.println("""
                                1. Checking Account -> Saving Account
                                2. Saving Account -> Checking Account
                                3. Back
                                """);
                        System.out.print("=> Option: ");
                        action = scanner.nextLine();
                        if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                            System.out.println(Config.INVALID_INPUT);
                        } else {

                            if(Integer.parseInt(action) == 1) {
                                if(checkingAccount == null) {
                                    System.out.println(Config.NO_CHECKING_ACCOUNT);
                                } else {
                                    if(savingAccount == null) {
                                        System.out.println(Config.NO_SAVING_ACCOUNT);
                                    } else {
                                        bank.setAccount(checkingAccount);
                                        bank.transfer(savingAccount);
                                    }
                                }
                            }

                            if(Integer.parseInt(action) == 2) {
                                if(savingAccount == null) {
                                    System.out.println(Config.NO_SAVING_ACCOUNT);
                                } else {
                                    if(checkingAccount == null) {
                                        System.out.println(Config.NO_CHECKING_ACCOUNT);
                                    } else {
                                        bank.setAccount(savingAccount);
                                        bank.transfer(checkingAccount);
                                    }
                                }
                            }

                            if(Integer.parseInt(action) == 3) continue mainMenu;
                        }
                    }
                }
                case 5: {
                    while(true) {
                        System.out.print(Config.ANSI_CYAN + """
                                ------------------------------------------------------------------
                                |                   Transaction History Account                  |
                                ------------------------------------------------------------------
                                """ + Config.ANSI_RESET);
                        System.out.println("""
                                1. Checking Account
                                2. Saving Account
                                3. Back
                                """);
                        System.out.print("=> Option: ");
                        action = scanner.nextLine();
                        if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                            System.out.println(Config.INVALID_INPUT);
                        } else {
                            if(Integer.parseInt(action) == 1) {
                                if(checkingAccount == null) {
                                    System.out.println(Config.NO_CHECKING_ACCOUNT);
                                }
                                else {
                                   checkingAccount.displayTransactionInfo();
                                }
                            }
                            if(Integer.parseInt(action) == 2) {
                                if(savingAccount == null) {
                                    System.out.println(Config.NO_SAVING_ACCOUNT);
                                }
                                else {
                                    savingAccount.displayTransactionInfo();
                                }
                            }
                            if(Integer.parseInt(action) == 3) { continue mainMenu; }
                        }
                    }
                }
                case 6: {
                    if(checkingAccount == null && savingAccount == null) {
                        System.out.println(Config.ANSI_RED + "You don't have have any account created yet." + Config.ANSI_RED);
                    } else {
                        System.out.println(Config.ANSI_CYAN + """
                        ------------------------------------------------------------------
                        |                Displaying Account's Information                |
                        ------------------------------------------------------------------
                        """ + Config.ANSI_RESET);
                        if(checkingAccount != null) {
                            checkingAccount.displayAccountInfo();
                        }
                        if(savingAccount != null) {
                            savingAccount.displayAccountInfo();
                        }
                        System.out.print("\nPress Enter to continue...");
                        scanner.nextLine();
                    }
                    continue;
                }
                case 7: {
                    while(true) {
                        System.out.print(Config.ANSI_CYAN + """
                                ------------------------------------------------------------------
                                |                         Delete Account                         |
                                ------------------------------------------------------------------
                                """ + Config.ANSI_RESET);
                        System.out.println("""
                                1. Checking Account
                                2. Saving Account
                                3. Back
                                """);
                        System.out.print("=> Option: ");
                        action = scanner.nextLine();
                        if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 3 || Integer.parseInt(action) < 1) {
                            System.out.println(Config.INVALID_INPUT);
                        } else {
                            if(Integer.parseInt(action) == 1) {
                                if(checkingAccount == null) {
                                    System.out.println(Config.NO_CHECKING_ACCOUNT);
                                }
                                else {
                                    if(checkingAccount.deleteAccount()) checkingAccount = null;
                                }
                            }
                            if(Integer.parseInt(action) == 2) {
                                if(savingAccount == null) {
                                    System.out.println(Config.NO_SAVING_ACCOUNT);
                                }
                                else {
                                    if(savingAccount.deleteAccount()) savingAccount = null;
                                }
                            }
                            if(Integer.parseInt(action) == 3) { continue mainMenu; }
                        }
                    }
                }
                case 8: {
                    return;
                }
            }
        }

    }

}