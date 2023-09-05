package pet.moneytransfersystembackend.controllers;

import org.springframework.web.bind.annotation.*;
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
        //TODO как вернуть несколько разных ответов? 200, 400, 500 с объектами? Через Exceptions + передаём данные в него

    }
}