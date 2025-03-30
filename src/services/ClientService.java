package services;

import entities.Client;
import interfaces.IClientService;
import repository.ClientRepository;
import java.util.List;
import java.util.Optional;

public class ClientService implements IClientService<Client> {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client) {
        if (client != null) {
            this.clientRepository.create(client);
        } else {
            System.out.println("Client can't be null");
        }
    }

    @Override
    public Optional<Client> getById(long id) {
        return clientRepository.getById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public void update(Client client) {
        if (client != null) {
            this.clientRepository.update(client);
        } else {
            System.out.println("Client does not exist");
        }
    }

    @Override
    public void delete(long id) {
        clientRepository.delete(id);
    }
}
