package pet.moneytransfersystembackend.repository;

public class VerificationCode {

    private String operationId;
    private String code;

    public VerificationCode() {
    }

    public VerificationCode(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
