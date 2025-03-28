package repository;

import entities.Card;
import interfaces.CrudRepository;
import interfaces.ICardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepository implements ICardRepository<Card> {
    List<Card> cards = new ArrayList<>();

    @Override
    public void create(Card card) {
        cards.add(card);
    }

    @Override
    public Optional<Card> getById(long id) {
        return cards.stream()
                .filter(card -> card.getId() == id)
                .findFirst();
    }

    @Override
    public List<Card> getAll() {

        return new ArrayList<>(cards);
    }

    @Override
    public void update(Card cardUpdate) {
    getById(cardUpdate.getId()).ifPresent(card -> {
        cards.remove(card);
        cards.add(cardUpdate);
    });
    }

    @Override
    public void delete(long id) {
        cards.removeIf(card -> card.getId() == id);
    }
}
