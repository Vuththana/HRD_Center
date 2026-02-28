import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.Year;
import java.util.Scanner;

public class Library {

    Scanner scanner = new Scanner(System.in);
    Book[] book = new Book[100];
    int perPage = 3;
    private String libraryName;
    private String libraryAddress;
    Library() {
        book[0] = new Book(1, "Journey to Java", "Keo Vuththana", "2000-2025", 2026, Status.AVAILABLE);
        book[1] = new Book(2, "Mastering Java", "Alice Johnson", "1995-2020", 2021,Status.AVAILABLE);
        book[2] = new Book(3, "Data Structures", "Bob Smith", "1980-2015", 2010, Status.UNAVAILABLE);
        book[3] = new Book(4, "Lua", "Jame Smith", "1990-2012", 2010, Status.UNAVAILABLE);
        book[4] = new Book(5, "Journey to Java", "Keo Vuththana", "2000-2025", 2026, Status.AVAILABLE);
        book[5] = new Book(6, "Mastering Java", "Alice Johnson", "1995-2020", 2021,Status.AVAILABLE);

        setupLibrary();
    }

    public void setupLibrary() {
        setupLibrary:
        while(true) {
            String libraryName;
            String libraryAddress;

            System.out.println(">".repeat(20) + " Setup Library " + "<".repeat(20));
            System.out.print("Enter Library's Name: ");
            libraryName = scanner.nextLine().trim();
            if(!libraryName.matches(Config.IS_LETTER)) System.out.println(Config.INVALID_INPUT);
            else {
                while(true) {
                    System.out.print("Enter Library's Address: ");
                    libraryAddress = scanner.nextLine().trim();
                    if(!libraryAddress.matches(Config.IS_ADDRESS)) System.out.println(Config.INVALID_INPUT_FOR_LIBRARY);
                    else {
                        System.out.println(Config.ANSI_GREEN + libraryName.toUpperCase() + Config.ANSI_RESET + " Library is created in " + Config.ANSI_GREEN + libraryAddress.toUpperCase() + Config.ANSI_RESET + " address successfully on " + Config.ANSI_GREEN + Config.DATE + Config.ANSI_RESET);
                       this.libraryName = libraryName;
                       this.libraryAddress = libraryAddress;
                        mainMenu();
                        break setupLibrary;
                    }
                }
            }
        }
    }

    public void mainMenu() {
        mainMenu:
        while(true) {
            String action;
            System.out.println("=".repeat(20) + " " + this.libraryName.toUpperCase() + ", " + this.libraryAddress.toUpperCase()+ " "  + "=".repeat(20));
            System.out.print("""
                1. Add Book
                2. Show All Book
                3. Show All Available Book
                4. Borrow Book
                5. Return Book
                6. Set row to show Record
                7. Delete Book by Id
                8. Exit
                """);
            System.out.println("=".repeat(40));
            System.out.print("-> Enter your choice: ");
            action = scanner.nextLine().trim();
            if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 8 || Integer.parseInt(action) < 1) {
                System.out.println(Config.INVALID_INPUT);
            }
            else {
                switch(Integer.parseInt(action)) {
                    case 1: {
                        this.addBook();
                    }
                    case 2: {
                        this.showAllBook();
                    }
                    case 3: {
                        this.showAvailableBook();
                    }
                    case 4: {
                        this.borrowBook();
                    }
                    case 5: {
                        this.returnBook();
                    }
                    case 6: {
                        this.setRowToRecord();
                    }
                    case 7: {
                        this.deleteBook();
                    }
                    case 8: {
                        while (true) {
                            System.out.print(Config.ARE_YOU_SURE);
                            String question = scanner.nextLine().trim().toLowerCase();
                            if(question.matches("y")) break mainMenu;
                            if(question.matches("n")) break;
                            else System.out.println(Config.INVALID_INPUT);
                        }
                    }
                }
            }

        }
    }

    public void addBook() {
            System.out.println("=".repeat(20) + " ADD BOOK INFO "  + "=".repeat(20));
            int count = 0;
            for (Book value : book) {
                if (value != null) count++;
            }
            for (int i = count; i < book.length;) {
                if(book[i] == null) {
                    book[i] = new Book();
                    String bookName;
                    System.out.println("=> Book ID: " + (i + 1));
                    book[i].setId(i + 1);
                    while(true) {
                        System.out.print("=> Enter Book's Name: ");
                        bookName = scanner.nextLine().trim();
                        if(!bookName.matches(Config.IS_BOOK_NAME)) System.out.println(Config.INVALID_INPUT);
                        else {book[i].setTitle(bookName); break;}
                    }

                    String authorName;
                    while(true) {
                        System.out.print("=> Enter Book Author Name: ");
                        authorName = scanner.nextLine().trim();
                        if(!authorName.matches(Config.IS_NAME)) System.out.println(Config.INVALID_INPUT);
                        else {book[i].setAuthor(authorName); break;}
                    }

                    String authorActiveYear;
                    String[] parts;
                    while(true) {
                        System.out.print("=> Enter Author Year Active(1234-1234): ");
                        authorActiveYear = scanner.nextLine().trim();
                        if(!authorActiveYear.matches(Config.IS_ACTIVE_YEAR)) System.out.println(Config.INVALID_INPUT);
                        else {
                            parts = authorActiveYear.split("-");
                            if(Integer.parseInt(parts[0]) < Integer.parseInt(parts[1])) {
                                if (Integer.parseInt(parts[1]) <= Year.now().getValue()) {
                                    if(Integer.parseInt(parts[1]) - Integer.parseInt(parts[0]) >= 18 && Integer.parseInt(parts[1]) - Integer.parseInt(parts[0]) <= 100) {
                                        book[i].setActiveYear(authorActiveYear); break;
                                    } else {
                                        System.out.println(Config.NOT_APPROPRIATE_AGE);
                                    }
                                } else {
                                    System.out.println(Config.NOT_APPROPRIATE_DEATHYEAR);
                                }
                            } else {
                                System.out.println(Config.NOT_APPROPRIATE_BIRTHYEAR);
                            }
                        }
                    }

                    String publishedYear;
                    while(true) {
                        System.out.print("=> Enter Published Year(1234): ");
                        publishedYear = scanner.nextLine().trim();
                        if(!publishedYear.matches(Config.IS_YEAR)) System.out.println(Config.INVALID_INPUT);
                        else {
                            if(Integer.parseInt(parts[0]) < Integer.parseInt(publishedYear)) {
                                if(Integer.parseInt(parts[1]) >= Integer.parseInt(publishedYear)) {
                                    if(Integer.parseInt(publishedYear) - Integer.parseInt(parts[0])  >= 18 ) {
                                        book[i].setPublishedYear(Integer.parseInt(publishedYear)); break;
                                    } else {
                                        System.out.println(Config.NOT_APPROPRIATE_PUBLISHYEAR_AGE);
                                    }
                                } else {
                                    System.out.println(Config.NOT_APPROPRIATE_GREATER_PUBLISHYEAR);
                                }

                            } else {
                                System.out.println(Config.NOT_APPROPRIATE_LESSER_PUBLISHYEAR);
                            }
                        }
                    }
                    book[i].setStatus(Status.AVAILABLE);
                    System.out.println(Config.BOOK_ADDED_COMPLETED);
                    break;
                } else {
                    i++;
                }
            }
            mainMenu();
    }

    public void showAllBook() {
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        int totalBooks = 0;
        for (Book value : book) {
            if (value != null) {
                totalBooks++;
            }
        }
        int totalPages = totalBooks / perPage;
        if (totalBooks % perPage != 0) {
            totalPages++;
        }
        int currentPage = 1;
        while(true) {
            System.out.println("=".repeat(50) + " ALL BOOKS INFO " + "=".repeat(50));
            int startIndex = (currentPage - 1) * perPage;
            int endIndex = startIndex + perPage;
            if (endIndex > totalBooks) {
                endIndex = totalBooks;
            }
            Table table = new Table(110, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            table.addCell(Config.ANSI_YELLOW + "ID" + Config.ANSI_RESET, center, 10);
            table.addCell(Config.ANSI_GREEN + "TITLE" + Config.ANSI_RESET, center, 30);
            table.addCell("AUTHOR" + Config.ANSI_RESET, center, 40);
            table.addCell(Config.ANSI_YELLOW + "PUBLISH DATE", center, 10);
            table.addCell(Config.ANSI_GREEN +"STATUS" + Config.ANSI_RESET, center, 20);
            int bookCount = 0;
            int displayCount = 0;
            for (int i = 0; i < book.length && displayCount < perPage; i++) {
                if(book[i]!=null) {
                    if (bookCount >= startIndex && bookCount < endIndex) {
                        table.addCell(Config.ANSI_YELLOW + book[i].getId() + Config.ANSI_RESET, center, 10);
                        table.addCell(Config.ANSI_GREEN + book[i].getTitle() + Config.ANSI_RESET, center, 30);
                        table.addCell(book[i].getAuthorName() + " (" + book[i].getActiveYear() + ")", center, 40);
                        table.addCell(Config.ANSI_YELLOW + book[i].getPublishedYear() + Config.ANSI_RESET, center, 10);
                        String status = book[i].getStatus() == Status.AVAILABLE ?
                                Config.ANSI_GREEN + book[i].getStatus().name() + Config.ANSI_RESET : Config.ANSI_RED + book[i].getStatus().name() + Config.ANSI_RESET;
                        table.addCell(status + Config.ANSI_RESET, center, 20);

                        displayCount++;
                    }
                    bookCount++;
                }
            }
            System.out.println(table.render());
            System.out.println("1. Next Page        2. Previous Page        3. First Page       4. Last Page        5. Exit\n");
            int choice = intChoice(scanner);
            currentPage = pagination(choice, currentPage, totalPages);
            if(choice == 5)  {
                mainMenu();
                return;
            }
        }
    }

    public void showAvailableBook() {
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        int totalBooks = 0;
        for (Book value : book) {
            if (value != null && value.getStatus() == Status.AVAILABLE) {
                totalBooks++;
            }
        }
        int totalPages = totalBooks / perPage;
        if (totalBooks % perPage != 0) {
            totalPages++;
        }
        int currentPage = 1;
        while(true) {
            System.out.println("=".repeat(50) + " ALL AVAILABLE BOOKS " + "=".repeat(50));
            int startIndex = (currentPage - 1) * perPage;
            int endIndex = startIndex + perPage;
            if (endIndex > totalBooks) {
                endIndex = totalBooks;
            }
            Table table = new Table(110, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            table.addCell(Config.ANSI_YELLOW + "ID" + Config.ANSI_RESET, center, 10);
            table.addCell(Config.ANSI_GREEN + "TITLE" + Config.ANSI_RESET, center, 30);
            table.addCell("AUTHOR" + Config.ANSI_RESET, center, 40);
            table.addCell(Config.ANSI_YELLOW + "PUBLISH DATE", center, 10);
            table.addCell(Config.ANSI_GREEN +"STATUS" + Config.ANSI_RESET, center, 20);
            int bookCount = 0;
            int displayCount = 0;
            for (int i = 0; i < book.length && displayCount < perPage; i++) {
                if (book[i] != null && book[i].getStatus() == Status.AVAILABLE) {
                    if (bookCount >= startIndex && bookCount < endIndex) {
                        table.addCell(Config.ANSI_YELLOW + book[i].getId() + Config.ANSI_RESET, center, 10);
                        table.addCell(Config.ANSI_GREEN + book[i].getTitle() + Config.ANSI_RESET, center, 30);
                        table.addCell(book[i].getAuthorName() + " (" + book[i].getActiveYear() + ")", center, 40);
                        table.addCell(Config.ANSI_YELLOW + book[i].getPublishedYear() + Config.ANSI_RESET, center, 10);
                        table.addCell(Config.ANSI_GREEN + book[i].getStatus().name() + Config.ANSI_RESET, center, 20);
                        displayCount++;
                    }
                    bookCount++;
                }
            }
            System.out.println(table.render());
            System.out.println("1. Next Page        2. Previous Page        3. First Page       4. Last Page        5. Exit\n");
            int choice = intChoice(scanner);
            currentPage = pagination(choice, currentPage, totalPages);
            if(choice == 5)  {
                mainMenu();
                return;
            }
        }
    }

    public void borrowBook() {
        borrowBook:
        while(true) {
            System.out.print("-> Enter book ID to borrow: ");
            String findId = scanner.nextLine().trim();
            if(!findId.matches(Config.IS_NUMBER)) System.out.println(Config.INVALID_INPUT);
            else {
                boolean found = false;
                for (Book value : book) {
                    if (value != null) {
                        if (value.getId() == Integer.parseInt(findId)) {
                            if (value.getStatus() == Status.AVAILABLE) {
                                System.out.println("Book ID: " + value.getId());
                                System.out.println("Book Title: " + value.getTitle());
                                System.out.println("Book Author: " + value.getAuthorName() + " (" + value.getActiveYear() + ")");
                                System.out.println("Published Year: " + value.getPublishedYear());
                                System.out.println(Config.BOOK_BORROWED_COMPLETED);
                                value.setStatus(Status.UNAVAILABLE);
                                found = true;
                                break;
                            } else {
                                System.out.println(Config.BOOK_IS_ALREADY_BORROWED);
                                while (true) {
                                    System.out.print(Config.DO_YOU_WANT);
                                    String question = scanner.nextLine().trim().toLowerCase();
                                    if (question.matches("y")) continue borrowBook;
                                    if (question.matches("n")) {
                                        mainMenu();
                                        return;
                                    } else System.out.println(Config.INVALID_INPUT);
                                }
                            }
                        }
                    }
                }
                if(!found) {
                    System.out.println(Config.NO_BOOK_ID_FOUND);
                }
                while(true) {
                    System.out.print(Config.DO_YOU_WANT);
                    String question = scanner.nextLine().trim().toLowerCase();
                    if(question.matches("y")) continue borrowBook;
                    if(question.matches("n")) {mainMenu(); return;}
                    else System.out.println(Config.INVALID_INPUT);
                }
            }
        }
    }

    public void returnBook() {
        returnBook:
        while(true) {
            System.out.print("-> Enter book ID to borrow: ");
            String findId = scanner.nextLine().trim();
            if(!findId.matches(Config.IS_NUMBER)) System.out.println(Config.INVALID_INPUT);
            else {
                boolean found = false;
                for (Book value : book) {
                    if (value != null) {
                        if (value.getId() == Integer.parseInt(findId)) {
                            if (value.getStatus() == Status.UNAVAILABLE) {
                                System.out.println("\nBook ID: " + value.getId());
                                System.out.println("Book Title: " + value.getTitle());
                                System.out.println("Book Author: " + value.getAuthorName() + " (" + value.getActiveYear() + ")");
                                System.out.println("Published Year: " + value.getPublishedYear());
                                System.out.println(Config.BOOK_RETURNED_COMPLETED);
                                value.setStatus(Status.AVAILABLE);
                                found = true;
                                break;
                            } else {
                                System.out.println(Config.BOOK_IS_ALREADY_RETURNED);
                                while (true) {
                                    System.out.print(Config.DO_YOU_WANT);
                                    String question = scanner.nextLine().trim().toLowerCase();
                                    if (question.matches("y")) continue returnBook;
                                    if (question.matches("n")) {
                                        mainMenu();
                                        return;
                                    } else System.out.println(Config.INVALID_INPUT);
                                }
                            }
                        }
                    }
                }
                if(!found) {
                    System.out.println(Config.NO_BOOK_ID_FOUND);
                }
                while(true) {
                    System.out.print(Config.DO_YOU_WANT);
                    String question = scanner.nextLine().trim().toLowerCase();
                    if(question.matches("y")) continue returnBook;
                    if(question.matches("n")) {mainMenu(); return;}
                    else System.out.println(Config.INVALID_INPUT);
                }
            }
        }
    }

    public void setRowToRecord() {
        while(true) {
            String setRow;
            System.out.print("-> Enter how many you want to show: ");
            setRow = scanner.nextLine().trim();
            if(!setRow.matches(Config.IS_NUMBER) || Integer.parseInt(setRow) < 1) {
                System.out.println(Config.INVALID_INPUT);
            } else {
                this.perPage = Integer.parseInt(setRow);
                mainMenu();
                return;
            }
        }
    }

    public void deleteBook() {
        deleteBook:
        while(true) {
            System.out.print("\n-> Enter Book ID to delete: ");
            String findId = scanner.nextLine().trim();
            if(!findId.matches(Config.IS_NUMBER)) {
                System.out.println(Config.INVALID_INPUT);
            } else {
                boolean found = false;
                for(int i = 0; i < book.length; i++) {
                    if(book[i] != null) {
                        if(book[i].getId() == Integer.parseInt(findId)) {
                            found = true;
                            if(book[i].getStatus() != Status.UNAVAILABLE) {
                                System.out.println("\nBook ID: " + book[i].getId());
                                System.out.println("Book Title: " + book[i].getTitle());
                                System.out.println("Book Author: " + book[i].getAuthorName() + " (" + book[i].getActiveYear() + ")");
                                System.out.println("Published Year: " + book[i].getPublishedYear());
                                while(true) {
                                    System.out.print("Are you sure you want to delete" + Config.ANSI_PURPLE+ "(Y/N): " + Config.ANSI_RESET);
                                    String input = scanner.nextLine().trim().toLowerCase();
                                    if(input.matches("y")) {
                                        book[i] = null;
                                        System.out.println(Config.BOOK_DELETE_COMPLETED);
                                        mainMenu();
                                        return;
                                    }
                                    if(input.matches("n")) {mainMenu(); return;}
                                }
                            } else {
                                System.out.println(Config.BOOK_IS_CANT_BE_DELETED);
                            }
                        }
                    }
                }
                if(!found) {
                    System.out.println(Config.NO_BOOK_ID_FOUND);
                }
                while(true) {
                    System.out.print(Config.DO_YOU_WANT);
                    String question = scanner.nextLine().trim().toLowerCase();
                    if(question.matches("y")) continue deleteBook;
                    if(question.matches("n")) {mainMenu(); return;}
                    else System.out.println(Config.INVALID_INPUT);
                }
            }
        }
    }

    private static int intChoice (Scanner scanner) {
        String action;
        while(true) {
            System.out.print("--> Enter your choice" + "(" + 1 + "-" + 5 + "): ");
            action = scanner.nextLine().trim();
            if(!action.matches(Config.IS_NUMBER) || Integer.parseInt(action) > 5 || Integer.parseInt(action) < 1)
                System.out.println(Config.INVALID_INPUT);
            else {
                break;
            }
        }
        return Integer.parseInt(action);
    }

    private static int pagination(int choice, int currentPage, int totalPages) {
        switch (choice) {
            case 1:
                if (currentPage < totalPages) {
                    return currentPage + 1;
                }
                System.out.println(Config.REACH_THE_END);
                return currentPage;

            case 2:
                if (currentPage > 1) {
                    return currentPage - 1;
                }
                System.out.println(Config.REACH_THE_END);
                return currentPage;

            case 3:
                return 1;

            case 4:
                return totalPages;

            default:
                return currentPage;
        }
    }
}