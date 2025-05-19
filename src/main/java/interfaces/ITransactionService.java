package interfaces;

import entities.Account;

import java.util.List;
import java.util.Optional;

public interface ITransactionService<Transaction> {

    void create(Transaction transaction);
    Optional<Transaction> getById(long id);
    List<Transaction> getAll();
    void update(Transaction transaction);
    void delete(long id);
}
