package services;

import entities.Card;
import interfaces.ICardService;
import repository.CardRepository;
import java.util.List;
import java.util.Optional;

public class CardService implements ICardService<Card> {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void create(Card card) {
        if (card != null) {
            this.cardRepository.create(card);
        } else {
            System.out.println("Card can't be null");
        }
    }

    @Override
    public Optional<Card> getById(long id) {
        return cardRepository.getById(id);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.getAll();
    }

    @Override
    public void update(Card card) {
        if (card != null) {
            this.cardRepository.update(card);
        } else {
            System.out.println("Card does not exist");
        }
    }

    @Override
    public void delete(long id) {
        cardRepository.delete(id);
    }
}
