package pet.moneytransfersystembackend.service;

import org.springframework.stereotype.Service;
import pet.moneytransfersystembackend.exception.ErrorConfirmationException;
import pet.moneytransfersystembackend.exception.ErrorInputDataException;
import pet.moneytransfersystembackend.exception.ErrorTransferException;
import pet.moneytransfersystembackend.repository.*;

@Service
public class CardService {

    private Card cardFrom;
    private Card cardTo;
    private Amount amount;

    public CardRepository cardRepository;
    public OperationRepository operationRepository;

    public CardService(CardRepository cardRepository, OperationRepository operationRepository) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
    }

    public OperationID transfer(TransferDTO transferDTO) {
        parseDTO(transferDTO);
        if (!cardRepository.validateCard(cardFrom) || !cardRepository.validateCard(cardTo)) {
            throw new ErrorInputDataException("Incorrect card number");
        }
        if (cardRepository.getBalance(cardFrom.getNumber()) < amount.getValue()) {
            throw new ErrorTransferException("Error transfer");
        }

        OperationID successOperation = new OperationID("12345");
        operationRepository.addOperation(successOperation);
        return successOperation;

    }

    public void parseDTO(TransferDTO transferDTO) {
        cardFrom = cardRepository.parseCardFrom(transferDTO);
        cardTo = cardRepository.parseCardTo(transferDTO);
        amount = transferDTO.getAmount();
    }

    public OperationID confirm(VerificationCode verificationCode) {
        if (!operationRepository.validOperationID(verificationCode.getOperationId())) {
            throw new ErrorConfirmationException("Invalid operationId");
        }
        if (verificationCode.getCode().length() != 4) {
            throw new ErrorInputDataException("Incorrect verification code");
        }
        return operationRepository.getOperationID(Integer.parseInt(verificationCode.getOperationId()));
    }
}
