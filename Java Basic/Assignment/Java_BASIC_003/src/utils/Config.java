package utils;

import java.time.format.DateTimeFormatter;

public class Config {
    // ANSI Colors
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PINK   = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[0;35m";
    public static final String ANSI_GREEN  = "\u001B[32m";

    // Formatter
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Regex
    public static String IS_LETTER = "^([a-zA-Z]{3,})(?:\\s[a-zA-Z]{3,})*$";
    public static String IS_NUMBER = "^\\d+$";
    public static String IS_MONEY = "^\\d*.\\d+$";
    public static String IS_NAME = "^\\p{L}{2,30}(?:\\s\\p{L}{2,30}){0,2}$";
    public static String IS_BOD = "^\\d{2}-\\d{2}-\\d{4}$";
    public static String IS_PHONE_NUMBER = "^[0-9][0-9]{9}$";

    // Quick Message


    // Validation Message
    public static String INVALID_INPUT = ANSI_RED + "Invalid Input" + ANSI_RESET;
    public static String NOT_OLD_ENOUGH = ANSI_RED + "You must be 18 years old." + ANSI_RESET;
    public static String BIRTH_DATE_FUTURE = ANSI_RED + "  Birth date cannot be in the future." + ANSI_RESET;
    public static String INVALID_DAY = ANSI_RED + "Invalid day for the given month." + ANSI_RESET;
    public static String INVALID_MONTH = ANSI_RED + "Invalid given month." + ANSI_RESET;
    public static String INVALID_PHONE_NUMBER = ANSI_RED + "Phone number must be this format(0123456789)" + ANSI_RESET;
    public static String INVALID_DEPOSIT = ANSI_RED + "[!] Deposit Amount is invalid!" + ANSI_RESET;
    public static String INVALID_WITHDRAW = ANSI_RED + "[!] Withdraw Amount is invalid!" + ANSI_RESET;
    public static String NO_SAVING_ACCOUNT = ANSI_RED + "[!] You don't have a saving account yet." + ANSI_RESET;
    public static String NO_CHECKING_ACCOUNT = ANSI_RED + "[!] You don't have a checking account yet." + ANSI_RESET;
    public static String AMOUNT_GREATER_THAN_ZERO = ANSI_RED + "[!] Amount must be greater than 0." + ANSI_RESET;
    public static String AMOUNT_GREATER_THAN_BALANCE = ANSI_RED + "[!] Amount must not be greater than balance." + ANSI_RESET;


}