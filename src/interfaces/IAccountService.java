package interfaces;

import entities.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService<Account> {

    void create(Account account);
    Optional<Account> getById(long id);
    List<Account> getAll();
    void update(Account account);
    void delete(long id);
    void deposit(double amount);
    void withdraw(double amount);
}
