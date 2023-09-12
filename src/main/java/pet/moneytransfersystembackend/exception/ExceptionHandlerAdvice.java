package pet.moneytransfersystembackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ErrorInputDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorInputData(ErrorInputDataException error) {
        return new ErrorResponse(error.getMessage(), 1);
    }

    @ExceptionHandler(ErrorTransferException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse errorTransfer(ErrorTransferException error) {
        return new ErrorResponse(error.getMessage(), 2);
    }

    @ExceptionHandler(ErrorConfirmationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse errorTransfer(ErrorConfirmationException error) {
        return new ErrorResponse(error.getMessage(), 3);
    }


}
