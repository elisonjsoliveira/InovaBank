package entities;

import java.math.BigDecimal;

public class SavingsAccount extends Account{

    private double interestRate;

    public SavingsAccount(String accountNumber, BigDecimal balance, Long clientId, double interestRate) {
        super(accountNumber, balance, clientId);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
