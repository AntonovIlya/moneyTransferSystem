package pet.moneytransfersystembackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestMoneyTransferSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(MoneyTransferSystemBackendApplication::main).with(TestMoneyTransferSystemBackendApplication.class).run(args);
    }

}
