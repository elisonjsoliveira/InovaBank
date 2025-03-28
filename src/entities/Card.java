package entities;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Card {

    private static final AtomicLong sequence = new AtomicLong(1);
    private final long id;
    private final int cardNumber;
    private LocalDate validity;
    private final int cvv;
    private String cardType;
    private double creditLimit;
    private final Long accountId;

    public Card(int cardNumber, LocalDate validity, int cvv, String cardType, double creditLimit, Long accountId) {
        this.id = sequence.getAndIncrement();
        this.cardNumber = cardNumber;
        this.validity = validity;
        this.cvv = cvv;
        this.cardType = cardType;
        this.creditLimit = creditLimit;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public int getCvv() {
        return cvv;
    }


    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public long getAccountId() {
        return accountId;
    }

}
