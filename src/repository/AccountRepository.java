package repository;

import entities.Account;
import interfaces.CrudRepository;
import interfaces.IAccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements IAccountRepository<Account> {
    List<Account> accounts = new ArrayList<>();

    @Override
    public void create(Account account) {
        accounts.add(account);
    }

    @Override
    public Optional<Account> getById(long id) {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public void update(Account accountUpdate) {
        getById(accountUpdate.getId()).ifPresent(account -> {
            accounts.remove(account);
            accounts.add(accountUpdate);
        });
    }

    @Override
    public void delete(long id) {
        accounts.removeIf(account -> account.getId() == id);
    }
}
