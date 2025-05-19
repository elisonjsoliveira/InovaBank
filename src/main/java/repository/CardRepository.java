package repository;

import entities.Card;
import interfaces.ICardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
    public Optional<Card> getById(long id) {
        EntityManager em = emf.createEntityManager();
        Card card = em.find(Card.class, id);
        em.close();
        return Optional.ofNullable(card);
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
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Card card = em.find(Card.class, id);
        if (card != null) {
            em.remove(card);
        }
        em.getTransaction().commit();
        em.close();
    }
}
