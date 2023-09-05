package pet.moneytransfersystembackend.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {

    private final Map<Long, Card> cards;

    public CardRepository() {
        cards = new ConcurrentHashMap<>();
        //TODO Реализовать фабрику

        // Упрощённая БД
        cards.put(5536913919918223L, new Card("5536913919918223", "502", "12/28", 1000L));
        cards.put(5536913919918224L, new Card("5536913919918224"));
    }

    public Card parseCardFrom(TransferDTO transferDTO) {
        Card cardFrom = new Card(transferDTO.getCardFromNumber(), transferDTO.getCardFromCVV(), transferDTO.getCardFromValidTill());
        return cardFrom;
    }

    public Card parseCardTo(TransferDTO transferDTO) {
        Card cardTo = new Card(transferDTO.getCardToNumber());
        return cardTo;
    }

    public boolean validateCard(Card card) {
        return cards.containsKey(Long.parseLong(card.getNumber()));
    }

    public long getBalance(String number) {
        return cards.get(Long.parseLong(number)).getBalance();
    }
}
