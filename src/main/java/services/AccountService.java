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
    public Optional<Account> getByAccountNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber);
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
    public void delete(String accountNumber) {
        this.accountRepository.delete(accountNumber);
    }

}