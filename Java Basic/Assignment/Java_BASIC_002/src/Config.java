import java.util.Date;

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
    public static Date DATE = new Date();

    // Validation
    public static String IS_LETTER = "^([a-zA-Z]{3,})(?:\\s[a-zA-Z]{3,})*$";
    public static String IS_NUMBER = "^\\d+$";
    public static String IS_NAME = "^\\p{L}{2,30}(?:\\s\\p{L}{2,30}){0,2}$";
    public static String IS_ACTIVE_YEAR = "^([1-9])[0-9]{3}-([1-9])[0-9]{3}$";
    public static String IS_YEAR = "^([1-9])[0-9]{3}$";
    public static String IS_BOOK_NAME = "^\\p{L}+(?:\\s{1}\\p{L}+)*";
    public static String IS_ADDRESS = "^\\p{L}+(?:\\s{1}\\p{L}+)*";

    // Quick Message
    public static String DO_YOU_WANT = "Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET;
    public static String ARE_YOU_SURE = "Are you sure" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET;
    public static String BOOK_ADDED_COMPLETED = ANSI_GREEN +  "Book is added successfully" + ANSI_RESET;
    public static String BOOK_BORROWED_COMPLETED = ANSI_GREEN + "Book is borrowed successfully" + ANSI_RESET;
    public static String BOOK_RETURNED_COMPLETED = ANSI_GREEN + "This book is returned successfully" + ANSI_RESET;
    public static String BOOK_DELETE_COMPLETED = ANSI_GREEN + "Book deleted successfully" + ANSI_RESET;

    // Validation Message
    public static String INVALID_INPUT_FOR_LIBRARY = ANSI_RED + "Invalid Input, only letters please." + ANSI_RESET;
    public static String INVALID_INPUT = ANSI_RED + "Invalid Input" + ANSI_RESET;
    public static String NOT_APPROPRIATE_AGE = ANSI_RED + "Not an appropriate year" + ANSI_RESET;
    public static String NOT_APPROPRIATE_BIRTHYEAR = ANSI_RED + "The birth year must not be greater than death year" + ANSI_RESET;
    public static String NOT_APPROPRIATE_DEATHYEAR = ANSI_RED + "The death year must be less than today year" + ANSI_RESET;
    public static String NOT_APPROPRIATE_PUBLISHYEAR_AGE = ANSI_RED + "Author must be at least 18 years old to publish a book" + ANSI_RESET;
    public static String NOT_APPROPRIATE_LESSER_PUBLISHYEAR = ANSI_RED + "Publish year must not be lower than birth year" + ANSI_RESET;
    public static String NOT_APPROPRIATE_GREATER_PUBLISHYEAR = ANSI_RED + "Publish year must not be greater than death year" + ANSI_RESET;
    public static String REACH_THE_END = ANSI_RED + "You reached the end" + ANSI_RESET;
    public static String NO_BOOK_ID_FOUND = ANSI_RED + "No book with that ID is found" + ANSI_RESET;
    public static String BOOK_IS_ALREADY_BORROWED = ANSI_RED + "This book is already borrowed" + ANSI_RESET;
    public static String BOOK_IS_ALREADY_RETURNED = ANSI_RED + "This book is already returned" + ANSI_RESET;
    public static String BOOK_IS_CANT_BE_DELETED = ANSI_RED + "This book can't be deleted because it hasn't been returned yet" + ANSI_RESET;
}
