package kfa.model;

public class Book extends LibraryItem implements Renewable {

    private static int totalBooks = 0;

    private String author;

    public Book(String title, String author, String isbn, double price) {
        super(title, isbn, price);
        this.author = author;
        totalBooks++;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    @Override
    public int getLendingPeriodDays() {
        return 14;
    }

    @Override
    public void renew(int extraDays) {
        System.out.println(
                "Book \"" + getTitle() +
                        "\" renewed for " + extraDays + " extra days."
        );
    }

    @Override
    public String toString() {
        String status = isAvailable() ? "Available" : "Not Available";

        return String.format(
                "[%s] %s by %s — Rs %.2f (%s)",
                getIsbn(),
                getTitle(),
                author,
                getPrice(),
                status
        );
    }
}
