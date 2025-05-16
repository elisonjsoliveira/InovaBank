package interfaces;

import java.util.List;
import java.util.Optional;

public interface ICardRepository<Card> {

    void create(Card card);
    Optional<Card> getById(long id);
    List<Card> getAll();
    void update(Card card);
    void delete(long id);
}
