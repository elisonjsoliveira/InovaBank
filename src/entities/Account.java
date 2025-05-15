package entities;


public class Account  {

    private final long id;
    private String accountNumber;
    private double balance;
    private final long clientId;

    // Não pode haver construtor vazio por conta das variáveis com anotação final.
    public Account(long id, String accountNumber, long clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.clientId = clientId;
    }

    public Account(Long id, String accountNumber, long clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.clientId = clientId;
    }

    public long getId() {
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

    // Método protegido para uso interno (ex: ORM)
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public long getClientId() {
        return clientId;
    }

    public void deposit(double amount){
        if (amount > 0) {
            this.balance += amount;

        } else {
            System.out.println("Invalid value");
        }
    }

    public void withdraw(double amount){
        if (balance - amount >= 0){
            this.balance -= amount;
        } else {
            System.out.println("Invalid value");
        }
    }

}
