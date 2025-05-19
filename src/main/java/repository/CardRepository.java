package repository;

import entities.Card;
import entities.Client;
import interfaces.ICardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class CardRepository implements ICardRepository<Card> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("inovabank");

    @Override
    public void create(Card card) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Card> getByCardNumber(long cardNumber) {
        try (EntityManager em = emf.createEntityManager()) {
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
        EntityManager em = emf.createEntityManager();
        List<Card> cards = em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
        em.close();
        return cards;
    }

    @Override
    public void update(Card card) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(card);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long cardNumber) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Card card = em.find(Card.class, cardNumber);
        if (card != null) {
            em.remove(card);
        }
        em.getTransaction().commit();
        em.close();
    }
}
