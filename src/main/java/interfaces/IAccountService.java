package interfaces;

import entities.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService<Account> {

    void create(Account account);
    Optional<Account> getByAccountNumber(String accountNumber);
    List<Account> getAll();
    void update(Account account);
    void delete(String accountNumber);
}
