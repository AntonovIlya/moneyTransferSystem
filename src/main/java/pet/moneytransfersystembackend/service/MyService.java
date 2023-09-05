package pet.moneytransfersystembackend.service;

import org.springframework.stereotype.Service;
import pet.moneytransfersystembackend.repository.*;

@Service
public class MyService {

    private Card cardFrom;
    private Card cardTo;
    private Amount amount;

    public CardRepository cardRepository;

    public MyService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    //TODO как вернуть несколько разных ответов? 200, 400, 500 с объектами? Через Exceptions + передаём данные в него?
    //TODO выбрасываем разные типы исключений

    public OperationID transfer(TransferDTO transferDTO) {
        parseDTO(transferDTO);

        // TODO Обернуть всё в TRY и выбрасывать 500 в случае ошибки на каком-то из этапов
        if (!cardRepository.validateCard(cardFrom) || !cardRepository.validateCard(cardTo)); //TODO 400 Error input data + тело
        if (cardFrom.getBalance() < amount.getValue()); //TODO проверка баланса (500)  + тело
        return new OperationID("1");

    }

    public void parseDTO(TransferDTO transferDTO) {
        cardFrom = cardRepository.parseCardFrom(transferDTO);
        cardTo = cardRepository.parseCardTo(transferDTO);
        amount = transferDTO.getAmount();
    }

}
