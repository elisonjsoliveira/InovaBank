package entities;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Card {

    private final long id;
    private final long cardNumber;
    private LocalDate validity;
    private final int cvv;
    private String cardType;
    private double creditLimit;
    private final Long accountId;

    // Não pode haver construtor vazio por conta das variáveis com anotação final.
    public Card(long id, long cardNumber, int cvv, Long accountId) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.accountId = accountId;
    }
    public Card(long id, long cardNumber, LocalDate validity, int cvv, String cardType, double creditLimit, Long accountId) {
        this.id = id;
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

    public long getCardNumber() {
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
