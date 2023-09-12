package pet.moneytransfersystembackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.moneytransfersystembackend.repository.OperationID;
import pet.moneytransfersystembackend.repository.VerificationCode;
import pet.moneytransfersystembackend.service.CardService;

@RestController
@RequestMapping("/confirmOperation")
public class ConfirmOperationController {

    public CardService cardService;

    public ConfirmOperationController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public OperationID confirmOperation(@RequestBody VerificationCode verificationCode) {
        System.out.println();
        return cardService.confirm(verificationCode);
    }
}
