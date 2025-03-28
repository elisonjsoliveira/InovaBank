package interfaces;

import java.util.List;
import java.util.Optional;

public interface IClientService<Client> {

    void create(Client client);
    Optional<Client> getById(long id);
    List<Client> getAll();
    void update(Client client);
    void delete(long id);
}
