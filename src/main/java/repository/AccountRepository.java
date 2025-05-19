package repository;

import entities.Account;
import entities.Client;
import interfaces.IAccountRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
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
    public Optional<Account> getByAccountNumber(String accountNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
            return Optional.of(account);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
    public void delete(String accountNumber) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
            em.remove(account);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Account not found.");
            em.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
