package main.java.com.atm.model;

public class Card {
    private String cardNumber;
    private String pin;
    private boolean isBlocked;
    private CardType cardType;

    public enum CardType {
        DEBIT,
        CREDIT
    }

    public Card(String cardNumber, String pin, boolean isBlocked, CardType cardType) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.isBlocked = isBlocked;
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Card.CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin) && !isBlocked;
    }

    public void blockCard() {
        this.isBlocked = true;
    }

    public void unblockCard() {
        this.isBlocked = false;
    }
}
