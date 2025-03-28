package entities;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Transaction {

    private static final AtomicLong sequence = new AtomicLong(1);
    private final long id;
    private String typeTransaction;
    private double value;
    private LocalDate date;
    private long originAccountId;
    private long destinationAccountId;

    public Transaction(String typeTransaction, double value, LocalDate date, long originAccountId, long destinationAccountId) {
        this.id = sequence.getAndIncrement();
        this.typeTransaction = typeTransaction;
        this.value = value;
        this.date = date;
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    public long getId() {
        return id;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
}
