package main.java.com.atm.service;

import main.java.com.atm.model.Account;
import main.java.com.atm.model.Card;

public interface ATMService {
    boolean validateCard(String cardNumber);
    boolean validatePin(String pin);
    double checkBalance();
    boolean withdraw(double amount);
    boolean deposit(double amount);
    void startSession();
    void endSession();
    boolean isSessionActive();
}


