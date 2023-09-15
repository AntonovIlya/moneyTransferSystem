package pet.moneytransfersystembackend.service;

import org.springframework.stereotype.Service;
import pet.moneytransfersystembackend.exception.ErrorConfirmationException;
import pet.moneytransfersystembackend.exception.ErrorInputDataException;
import pet.moneytransfersystembackend.exception.ErrorTransferException;
import pet.moneytransfersystembackend.logger.LogLevel;
import pet.moneytransfersystembackend.logger.Logger;
import pet.moneytransfersystembackend.repository.*;

@Service
public class CardService {

    private Card cardFrom;
    private Card cardTo;
    private Amount amount;
    private final Logger logger = new Logger();

    public final CardRepository cardRepository;
    public final OperationRepository operationRepository;

    public CardService(CardRepository cardRepository, OperationRepository operationRepository) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
    }

    public OperationID transfer(TransferDTO transferDTO) {
        parseDTO(transferDTO);
        if (cardRepository.validateCard(cardFrom) || cardRepository.validateCard(cardTo)) {
            logger.log(LogLevel.ERROR, this.getClass().getName(),"Incorrect card number");
            throw new ErrorInputDataException("Incorrect card number");
        }
        if (cardRepository.getBalance(cardFrom.getNumber()) < amount.getValue()) {
            logger.log(LogLevel.ERROR, this.getClass().getName(),"Error transfer");
            throw new ErrorTransferException("Error transfer");
        }

        OperationID successOperation = new OperationID("12345");
        operationRepository.addOperation(successOperation);
        return successOperation;

    }

    public void parseDTO(TransferDTO transferDTO) {
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Parse transfer JSON");
        cardFrom = cardRepository.parseCardFrom(transferDTO);
        cardTo = cardRepository.parseCardTo(transferDTO);
        amount = transferDTO.getAmount();
    }

    public OperationID confirm(VerificationCode verificationCode) {
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Confirm operation");
        if (!operationRepository.validOperationID(verificationCode.getOperationId())) {
            logger.log(LogLevel.ERROR, this.getClass().getName(),"Invalid operationId");
            throw new ErrorConfirmationException("Invalid operationId");
        }
        if (verificationCode.getCode().length() != 4) {
            logger.log(LogLevel.ERROR, this.getClass().getName(),"Incorrect verification code");
            throw new ErrorInputDataException("Incorrect verification code");
        }
        logger.log(LogLevel.INFO, this.getClass().getName(),"Debiting funds from the card "
                + cardFrom.getNumber() + " in the amount of " + amount.getValue() + " " + amount.getCurrency());
        logger.log(LogLevel.INFO, this.getClass().getName(),"Crediting funds to card "
                + cardTo.getNumber() + " in the amount of " + amount.getValue() + " " + amount.getCurrency());
        return operationRepository.getOperationID(Integer.parseInt(verificationCode.getOperationId()));
    }
}
