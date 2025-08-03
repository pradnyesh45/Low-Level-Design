package main.java.com.atm.ui;

public interface ATMInterface {
    void showWelcomeScreen();
    void insertCard();
    void enterPin(String pin);
    void showMainMenu();
    void handleWithdrawal(double amount);
    void handleDeposit(double amount);
    void handleBalanceInquiry();
    void exitATM();
}
