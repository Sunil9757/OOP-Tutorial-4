import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.Book;
import kfa.model.DVD;
import kfa.model.LibraryItem;
import kfa.model.Magazine;
import kfa.service.LibrarySystem;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // SECTION A
        // ==========================================

        System.out.println("==========================================");
        System.out.println("SECTION A - BOOKS");
        System.out.println("==========================================");

        Book book1 = new Book(
                "Clean Code",
                "Robert Martin",
                "1234567890123",
                850
        );

        Book book2 = new Book(
                "Java Basics",
                "James Gosling",
                "2345678901234",
                950
        );

        Book book3 = new Book(
                "Effective Java",
                "Joshua Bloch",
                "3456789012345",
                1200
        );

        Book book4 = new Book(
                "Head First Java",
                "Kathy Sierra",
                "4567890123456",
                1100
        );

        Book[] books = {
                book1,
                book2,
                book3,
                book4
        };

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println(
                "Total books: " + Book.getTotalBooks()
        );


        // ==========================================
        // SECTION B
        // ==========================================

        System.out.println("\n==========================================");
        System.out.println("SECTION B - INHERITANCE & POLYMORPHISM");
        System.out.println("==========================================");

        Magazine magazine = new Magazine(
                "Tech Monthly",
                "5678901234567",
                500,
                25
        );

        DVD dvd = new DVD(
                "Java Programming Course",
                "6789012345678",
                1500,
                120
        );

        LibraryItem[] items = {
                book1,
                magazine,
                dvd
        };

        for (LibraryItem item : items) {

            System.out.println(item);

            System.out.println(
                    "Lending period: " +
                            item.getLendingPeriodDays() +
                            " days"
            );

            System.out.println();
        }

        // Polymorphism works because LibraryItem
        // defines the common methods overridden by subclasses.

        book1.renew(7);
        dvd.renew(3);


        // ==========================================
        // SECTION C
        // ==========================================

        System.out.println("==========================================");
        System.out.println("SECTION C - EXCEPTION HANDLING");
        System.out.println("==========================================");

        LibrarySystem librarySystem = new LibrarySystem();

        // Successful borrowing
        try {

            librarySystem.borrowItem(book1);

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow error: " + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: " +
                            book1.getTitle()
            );
        }


        // Attempt to borrow the same book again
        // This should trigger BookNotAvailableException.
        try {

            librarySystem.borrowItem(book1);

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow error: " + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: " +
                            book1.getTitle()
            );
        }


        // Successful return
        try {

            librarySystem.returnItem(book1, 0);

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return error: " + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: " +
                            book1.getTitle()
            );
        }


        // Return with overdue days
        try {

            librarySystem.returnItem(book1, 5);

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return error: " + e.getMessage()
            );

            System.out.println(
                    "Days overdue: " +
                            e.getDaysOverdue()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: " +
                            book1.getTitle()
            );
        }


        // ==========================================
        // SECTION D
        // ==========================================

        System.out.println("\n==========================================");
        System.out.println("SECTION D - STRINGS");
        System.out.println("==========================================");


        // D1 - Member ID Generator

        System.out.println("\n--- D1: MEMBER ID ---");

        String memberId = generateMemberId(
                "Aarav Shrestha"
        );

        System.out.println(
                "Generated Member ID: " + memberId
        );


        // Single-word name test
        String singleNameId = generateMemberId(
                "Aarav"
        );

        System.out.println(
                "Single-word name ID: " +
                        singleNameId
        );


        // D2 - ISBN Validation

        System.out.println("\n--- D2: ISBN VALIDATION ---");

        String[] isbnTests = {
                "1234567890123",  // valid
                "123456789012",   // wrong length
                "123456789012A",  // contains letter
                "0234567890123"   // starts with 0
        };

        for (String isbn : isbnTests) {

            System.out.println(
                    isbn + " -> " +
                            isValidIsbn(isbn)
            );
        }


        // D3 - Catalogue Report

        System.out.println("\n--- D3: CATALOGUE REPORT ---");

        String report = buildCatalogueReport(items);

        System.out.println(report);


        // Search report

        System.out.println(
                "\n--- SEARCH: java ---"
        );

        String searchReport = buildSearchReport(
                items,
                "java"
        );

        System.out.println(searchReport);


        // StringBuilder explanation
        System.out.println(
                "\nStringBuilder is preferred because String objects " +
                        "are immutable. Repeated += concatenation creates " +
                        "new String objects, while StringBuilder modifies " +
                        "the same object efficiently."
        );
    }


    // ==========================================
    // D1 - MEMBER ID GENERATOR
    // ==========================================

    public static String generateMemberId(String fullName) {

        fullName = fullName.trim();

        String[] parts = fullName.split(" ");

        String firstName = parts[0];

        String firstPart;

        if (firstName.length() >= 3) {
            firstPart = firstName.substring(0, 3);
        } else {
            firstPart = firstName;
        }

        String lastPart = "";

        if (parts.length > 1) {

            String lastName = parts[parts.length - 1];

            if (lastName.length() >= 2) {
                lastPart = lastName.substring(0, 2);
            } else {
                lastPart = lastName;
            }

        } else {

            // If there is no last name, use "XX".
            lastPart = "XX";
        }

        Random random = new Random();

        int randomNumber = 100 + random.nextInt(900);

        return firstPart.toUpperCase()
                + lastPart.toUpperCase()
                + randomNumber;
    }


    // ==========================================
    // D2 - ISBN VALIDATION
    // ==========================================

    public static boolean isValidIsbn(String isbn) {

        if (isbn == null) {
            return false;
        }

        // Rule 1: Must contain exactly 13 characters.
        if (isbn.length() != 13) {
            return false;
        }

        // Rule 2: First character cannot be 0.
        if (isbn.charAt(0) == '0') {
            return false;
        }

        // Rule 3: Every character must be a digit.
        for (int i = 0; i < isbn.length(); i++) {

            char ch = isbn.charAt(i);

            if (ch < '0' || ch > '9') {
                return false;
            }
        }

        return true;
    }


    // ==========================================
    // D3 - CATALOGUE REPORT
    // ==========================================

    public static String buildCatalogueReport(
            LibraryItem[] items) {

        StringBuilder report = new StringBuilder();

        report.append("========== KFA LIBRARY CATALOGUE ==========\n");

        for (LibraryItem item : items) {

            report.append("Title: ")
                    .append(item.getTitle())
                    .append("\n");

            report.append("Availability: ")
                    .append(
                            item.isAvailable()
                                    ? "Available"
                                    : "Not Available"
                    )
                    .append("\n");

            report.append("--------------------------------------------\n");
        }

        return report.toString();
    }


    // ==========================================
    // D3 - SEARCH
    // ==========================================

    public static String buildSearchReport(
            LibraryItem[] items,
            String keyword) {

        StringBuilder report = new StringBuilder();

        keyword = keyword.toLowerCase();

        report.append(
                "========== SEARCH RESULTS ==========\n"
        );

        for (LibraryItem item : items) {

            String title = item.getTitle();

            if (title.toLowerCase().contains(keyword)) {

                report.append("Title: ")
                        .append(title)
                        .append("\n");

                report.append("Availability: ")
                        .append(
                                item.isAvailable()
                                        ? "Available"
                                        : "Not Available"
                        )
                        .append("\n");
            }
        }

        return report.toString();
    }
}