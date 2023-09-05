package pet.moneytransfersystembackend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.moneytransfersystembackend.repository.OperationID;
import pet.moneytransfersystembackend.repository.TransferDTO;
import pet.moneytransfersystembackend.service.MyService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    public MyService service;

    public TransferController(MyService service) {
        this.service = service;
    }

    @PostMapping
    public OperationID transfer(@RequestBody TransferDTO transferDTO) {
        return service.transfer(transferDTO);
    }
}