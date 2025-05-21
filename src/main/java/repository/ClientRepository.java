package repository;

import entities.Client;
import interfaces.IClientRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class ClientRepository implements IClientRepository<Client> {

    @Override
    public void create(Client client) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Client> getByCPF(String cpf) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
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
        EntityManager em = JPAUtil.getEntityManager();
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        em.close();
        return clients;
    }

    @Override
    public void update(Client client) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Client client = em.createQuery("SELECT c FROM Client c WHERE c.cpf = :cpf", Client.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
            em.remove(client);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Client not found.");
            em.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
