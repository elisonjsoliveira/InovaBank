package services;

import entities.Client;
import repository.ClientRepository;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void create(Client newClient){
        if(newClient != null){
            this.clientRepository.create(newClient);
        }
        else{
            System.out.println("Client can't be null");
        }
    }

    public void getAll(){
        System.out.println(this.clientRepository.getAll());
    }

    public void getById(long id){
        System.out.println(clientRepository.getById(id));
    }

    public void update(Client client){
        if(client != null){
            this.clientRepository.update(client);
        }
        else{
            System.out.println("Client does not exist");
        }
    }

    public void delete(long id){

    }
}
