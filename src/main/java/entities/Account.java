package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Account() {
        // Construtor padrão exigido pelo JPA
    }

    public Account(String accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = 2000;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        this.balance += amount;
    }

    public Client getClient() {
        return client;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", accountNumber='" + getAccountNumber() + '\'' +
                ", balance=" + getBalance() +
                ", client=" + getClient() +
                '}';
    }
}
