package services;

import entities.Account;
import interfaces.IAccountService;
import repository.AccountRepository;
import java.util.List;
import java.util.Optional;

public class AccountService implements IAccountService<Account> {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(Account account) {
        if (account != null) {
            this.accountRepository.create(account);
        } else {
            System.out.println("Account can't be null");
        }
    }

    @Override
    public Optional<Account> getById(long id) {
        return accountRepository.getById(id);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    @Override
    public void update(Account account) {
        if (account != null) {
            this.accountRepository.update(account);
        } else {
            System.out.println("Account does not exist");
        }
    }

    @Override
    public void delete(long id) {
        this.accountRepository.delete(id);
    }

    @Override
    public void deposit(long id, double amount) {
        accountRepository.getById(id).ifPresentOrElse(account -> {
            if (amount > 0) {
                account.deposit(amount);
                accountRepository.update(account);
            } else {
                System.out.println("Invalid deposit amount");
            }
        }, () -> System.out.println("Account not found"));
    }

    @Override
    public void withdraw(long id, double amount) {
        accountRepository.getById(id).ifPresentOrElse(account -> {
            if (amount > 0 && account.getBalance() >= amount) {
                account.withdraw(amount);
                accountRepository.update(account);
            } else {
                System.out.println("Invalid withdrawal operation");
            }
        }, () -> System.out.println("Account not found"));
    }
}