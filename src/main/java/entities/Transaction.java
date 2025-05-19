package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeTransaction;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "origin_account_id", nullable = false)
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", nullable = false)
    private Account destinationAccount;

    public Transaction() {
        // Construtor vazio exigido pelo JPA
    }

    public Transaction(String typeTransaction, double value, LocalDate date, Account originAccount, Account destinationAccount) {

        try{
            if(value > 0){
                if(originAccount.getBalance() > value){
                    originAccount.setBalance(-value);
                    destinationAccount.setBalance(value);
                }else throw new RuntimeException("invalid value");
            }else throw new RuntimeException("value insuficient");
        }catch (RuntimeException ignored){

        }

        this.typeTransaction = typeTransaction;
        this.value = value;
        this.date = date;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public Long getId() {
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

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + getId() +
                ", typeTransaction='" + getTypeTransaction() + '\'' +
                ", value=" + getValue() +
                ", date=" + getDate() +
                ", originAccount=" + getOriginAccount() +
                ", destinationAccount=" + getDestinationAccount() +
                '}';
    }
}
