package entities;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Account  {

    private static final AtomicLong sequence = new AtomicLong();
    private long id;
    private final String accountNumber;
    private double balance;
    private final long clientId;

    public Account(String accountNumber, double balance, long clientId) {
        this.id = sequence.getAndIncrement();
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.clientId = clientId;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getClientId() {
        return clientId;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount){
        this.balance -= amount;
    }

}
