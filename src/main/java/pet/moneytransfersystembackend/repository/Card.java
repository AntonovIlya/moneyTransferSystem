package pet.moneytransfersystembackend.repository;

public class Card {

    private String number;
    private String cvv;
    private String expirationDate;
    private long balance;

    public Card() {
    }

    public Card(String number, String cvv, String expirationDate, long balance) {
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.balance = balance;
    }

    public Card(String number, String cvv, String expirationDate) {
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public Card(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
