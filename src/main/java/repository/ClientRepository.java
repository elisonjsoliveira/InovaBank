package repository;

import entities.Client;
import interfaces.CrudRepository;
import interfaces.IClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements IClientRepository<Client> {

    private final List<Client> clients = new ArrayList<>();

    @Override
    public void create(Client client) {
        clients.add(client);
    }

    @Override
    public Optional<Client> getById(long id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clients);
    }

    @Override
    public void update(Client clientUpdate) {
        getById(clientUpdate.getId()).ifPresent(client -> {
            clients.remove(client);
            clients.add(clientUpdate);
        });
    }

    @Override
    public void delete(long id) {
        clients.removeIf(client -> client.getId() == id);
    }
}
