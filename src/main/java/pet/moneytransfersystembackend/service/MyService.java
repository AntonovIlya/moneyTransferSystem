package pet.moneytransfersystembackend.service;

import org.springframework.stereotype.Service;
import pet.moneytransfersystembackend.exceptions.ErrorInputDataException;
import pet.moneytransfersystembackend.exceptions.ErrorTransferException;
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

    public OperationID transfer(TransferDTO transferDTO) {
        parseDTO(transferDTO);
        if (!cardRepository.validateCard(cardFrom) || !cardRepository.validateCard(cardTo)) {
            throw new ErrorInputDataException(); //TODO 400 Обработать ответное сообщение отправ/получатель
        }
        if (cardRepository.getBalance(cardFrom.getNumber()) < amount.getValue()) {
            throw new ErrorTransferException(); //TODO проверка баланса (500)  + тело
        }
        return new OperationID("1");

    }

    public void parseDTO(TransferDTO transferDTO) {
        cardFrom = cardRepository.parseCardFrom(transferDTO);
        cardTo = cardRepository.parseCardTo(transferDTO);
        amount = transferDTO.getAmount();
    }
}
