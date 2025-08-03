package main.java.com.atm.model;

public class ATM {
    private String atmId;
    private double availableCash;
    private ATMState state;

    public enum ATMState {
        IDLE,
        CARD_INSERTED,
        PIN_ENTERED,
        TRANSACTION_IN_PROGRESS,
        OUT_OF_CASH;
    }

    public ATM(String atmId, double availableCash, ATMState state) {
        this.availableCash = availableCash;
        this.atmId = atmId;
        this.state = state;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public double getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(double availableCash) {
        this.availableCash = availableCash;
    }

    public ATMState getState() {
        return state;
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public boolean canDispenseCash(double amount) {
        return amount <= availableCash && state != ATMState.OUT_OF_CASH;
    }

    public void dispenseCash(double amount) {
        if (canDispenseCash(amount)) {
            availableCash -= amount;
            if (availableCash < 100) {
                state = ATMState.OUT_OF_CASH;
            }
        }
    }

    public void acceptCash(double amount) {
        availableCash += amount;
    }

}
