package pet.moneytransfersystembackend.repository;

public class OperationID {

    private String operationId;

    public OperationID() {
    }

    public OperationID(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
