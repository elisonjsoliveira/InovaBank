package repository;

import entities.Client;
import interfaces.IClientRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class ClientRepository implements IClientRepository<Client> {
    // Persistence é uma classe utilitária do JPA que carrega o que está configurado no arquivo persistence.xml
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("inovabank");
    // O createEntityManagerFactory("bancoPU"); vai criar a fabrica de acordo com a configuração da unidade

    @Override
    public void create(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Client> getByCPF(String cpf) {
        try (EntityManager em = emf.createEntityManager()) {
            Client client = em.createQuery("SELECT c FROM Client c WHERE c.cpf = :cpf", Client.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
            return Optional.of(client);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Client> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        em.close();
        return clients;
    }

    @Override
    public void update(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(String cpf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, cpf);
        if (client != null) {
            em.remove(client);
        }
        em.getTransaction().commit();
        em.close();
    }
}
