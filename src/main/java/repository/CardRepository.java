package repository;

import entities.Account;
import entities.Card;
import entities.Client;
import interfaces.ICardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class CardRepository implements ICardRepository<Card> {

    @Override
    public void create(Card card) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Card> getByCardNumber(long cardNumber) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            Card card = em.createQuery("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber", Card.class)
                    .setParameter("cardNumber", cardNumber)
                    .getSingleResult();
            return Optional.of(card);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Card> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Card> cards = em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
        em.close();
        return cards;
    }

    @Override
    public void update(Card card) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(card);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long cardNumber) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Card card = em.createQuery("SELECT a FROM Card a WHERE a.cardNumber = :cardNumber", Card.class)
                    .setParameter("cardNumber", cardNumber)
                    .getSingleResult();
            em.remove(card);
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
