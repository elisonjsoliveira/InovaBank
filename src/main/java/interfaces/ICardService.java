package interfaces;

import java.util.List;
import java.util.Optional;

public interface ICardService<Card> {

    void create(Card card);
    Optional<Card> getByCardNumber(long cardNumber);
    List<Card> getAll();
    void update(Card card);
    void delete(long cardNumber);

}
