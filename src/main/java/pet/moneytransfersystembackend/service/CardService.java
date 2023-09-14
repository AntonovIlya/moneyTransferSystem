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
    private Logger logger = new Logger();

    public final CardRepository cardRepository;
    public final OperationRepository operationRepository;

    public CardService(CardRepository cardRepository, OperationRepository operationRepository) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
    }

    public OperationID transfer(TransferDTO transferDTO) {
        parseDTO(transferDTO);
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Validate cards");
        if (!cardRepository.validateCard(cardFrom) || !cardRepository.validateCard(cardTo)) {
            throw new ErrorInputDataException("Incorrect card number");
        }
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Balance check");
        if (cardRepository.getBalance(cardFrom.getNumber()) < amount.getValue()) {
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
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Validate operationId");
        if (!operationRepository.validOperationID(verificationCode.getOperationId())) {
            throw new ErrorConfirmationException("Invalid operationId");
        }
        logger.log(LogLevel.DEBUG, this.getClass().getName(),"Validate verificationCode");
        if (verificationCode.getCode().length() != 4) {
            throw new ErrorInputDataException("Incorrect verification code");
        }
        return operationRepository.getOperationID(Integer.parseInt(verificationCode.getOperationId()));
    }
}
