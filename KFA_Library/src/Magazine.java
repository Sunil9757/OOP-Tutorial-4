package kfa.model;

public class Magazine extends LibraryItem {

    private int issueNumber;

    public Magazine(
            String title,
            String isbn,
            double price,
            int issueNumber
    ) {
        super(title, isbn, price);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public int getLendingPeriodDays() {
        return 7;
    }

    @Override
    public String toString() {
        String status = isAvailable() ? "Available" : "Not Available";

        return String.format(
                "[%s] Magazine: %s — Issue %d — Rs %.2f (%s)",
                getIsbn(),
                getTitle(),
                issueNumber,
                getPrice(),
                status
        );
    }

    // Magazines cannot be renewed according to the library policy.
}