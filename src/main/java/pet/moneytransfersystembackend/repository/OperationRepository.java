package pet.moneytransfersystembackend.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OperationRepository {

    private final Map<Integer, OperationID> operationIDs;

    public OperationRepository() {
        operationIDs = new ConcurrentHashMap<>();
    }

    public boolean validOperationID(String id) {
        return operationIDs.containsKey(Integer.parseInt(id));
    }

    public void addOperation(OperationID operationID) {
        operationIDs.put(Integer.parseInt(operationID.getOperationId()), operationID);
    }
}
