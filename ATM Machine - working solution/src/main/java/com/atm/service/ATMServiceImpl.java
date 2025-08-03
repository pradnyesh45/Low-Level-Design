package main.java.com.atm.service;

import main.java.com.atm.model.ATM;
import main.java.com.atm.model.Account;
import main.java.com.atm.model.Card;

public class ATMServiceImpl implements ATMService{
    private ATM atm;
    private Card currentCard;
    private Account currentAccount;
    private boolean sessionActive;

    public ATMServiceImpl(ATM atm, Card card, Account account) {
        this.atm = atm;
        this.currentCard = card;
        this.currentAccount = account;
        this.sessionActive = false;
    }

    @Override
    public boolean validateCard(String cardNumber){
        return currentCard != null &&
                currentCard.getCardNumber().equals(cardNumber) &&
                !currentCard.isBlocked();
    }

    @Override
    public boolean validatePin(String pin){
        if (currentCard == null) return false;
        return currentCard.validatePin(pin);
    }

    @Override
    public double checkBalance() {
        if (!sessionActive || currentAccount == null) return 0.0;
        return currentAccount.getBalance();
    }

    @Override
    public boolean withdraw(double amount){
        if (!sessionActive || currentAccount == null) return false;

        if (currentAccount.withdraw(amount) && atm.canDispenseCash(amount)){
            atm.dispenseCash(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean deposit(double amount) {
        if (!sessionActive || currentAccount == null) return false;

        currentAccount.deposit(amount);
        atm.acceptCash(amount);
        return true;
    }

    @Override
    public void startSession() {
        this.sessionActive = true;
        atm.setState(ATM.ATMState.CARD_INSERTED);
    }

    @Override
    public void endSession() {
        this.currentCard = null;
        this.currentAccount = null;
        this.sessionActive = false;
        atm.setState(ATM.ATMState.IDLE);
    }

    @Override
    public boolean isSessionActive() {
        return sessionActive;
    }

    public ATM getAtm(){
        return atm;
    }

    public Card getCurrentCard(){
        return currentCard;
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }
}
