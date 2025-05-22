package repository;

import entities.Account;
import entities.Transaction;
import interfaces.ITransactionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements ITransactionRepository<Transaction> {

    @Override
    public void create(Transaction transaction) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Account originAccount = em.find(Account.class, transaction.getOriginAccount().getId());
            Account destinationAccount = em.find(Account.class, transaction.getDestinationAccount().getId());

            if (originAccount.getBalance() < transaction.getValue()) {
                throw new RuntimeException("Saldo insuficiente na conta de origem.");
            }

            originAccount.setBalance(originAccount.getBalance() - transaction.getValue());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getValue());

            em.merge(originAccount);
            em.merge(destinationAccount);
            em.persist(transaction);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    @Override
    public Optional<Transaction> getById(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Transaction transaction = em.find(Transaction.class, id);
        em.close();
        return Optional.ofNullable(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Transaction> transactions = em.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
        em.close();
        return transactions;
    }

    @Override
    public void update(Transaction transaction) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(transaction);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Transaction transaction = em.find(Transaction.class, id);
        if (transaction != null) {
            em.remove(transaction);
        }
        em.getTransaction().commit();
        em.close();
    }
}
