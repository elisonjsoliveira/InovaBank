package interfaces;

import entities.Account;
import java.util.List;
import java.util.Optional;

public interface IAccountRepository<T> {
    void create(T entity);
    Optional<T> getByAccountNumber(String accountNumber);
    List<T> getAll();
    void update(T entity);
    void delete(String accountNumber);
}
