package pet.moneytransfersystembackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ErrorInputDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorInputData(ErrorInputDataException error) {
        return new ErrorResponse("The card user is not a bank customer", 1);
    }

    @ExceptionHandler(ErrorTransferException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse errorTransfer(ErrorTransferException error) {
        return new ErrorResponse("Error transfer", 2);
    }


}
