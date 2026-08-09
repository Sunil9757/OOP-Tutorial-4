package kfa.exception;

public class ItemOverdueException extends Exception {

    private int daysOverdue;

    public ItemOverdueException(String message, int daysOverdue) {
        super(message);
        this.daysOverdue = daysOverdue;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }
}