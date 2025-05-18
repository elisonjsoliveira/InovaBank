package repository;

import entities.Transaction;
import interfaces.ITransactionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class TransactionRepository implements ITransactionRepository<Transaction> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");

    @Override
    public void create(Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Transaction> getById(long id) {
        EntityManager em = emf.createEntityManager();
        Transaction transaction = em.find(Transaction.class, id);
        em.close();
        return Optional.ofNullable(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Transaction> transactions = em.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
        em.close();
        return transactions;
    }

    @Override
    public void update(Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(transaction);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Transaction transaction = em.find(Transaction.class, id);
        if (transaction != null) {
            em.remove(transaction);
        }
        em.getTransaction().commit();
        em.close();
    }
}
