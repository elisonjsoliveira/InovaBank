package interfaces;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository<Account> {
    void create(Account account);
    Optional<Account> getById(long id);
    List<Account> getAll();
    void update(Account account);
    void delete(long id);
}
