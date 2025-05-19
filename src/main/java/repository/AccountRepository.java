package repository;

import entities.Account;
import interfaces.IAccountRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class AccountRepository implements IAccountRepository<Account> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("inovabank");

    @Override
    public void create(Account account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Account> getById(long id) {
        EntityManager em = emf.createEntityManager();
        Account account = em.find(Account.class, id);
        em.close();
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Account> accounts = em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        em.close();
        return accounts;
    }

    @Override
    public void update(Account account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Account account = em.find(Account.class, id);
        if (account != null) {
            em.remove(account);
        }
        em.getTransaction().commit();
        em.close();
    }
}
