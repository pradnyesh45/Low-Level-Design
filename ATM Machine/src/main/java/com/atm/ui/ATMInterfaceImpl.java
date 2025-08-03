package main.java.com.atm.ui;

import main.java.com.atm.service.ATMService;

public class ATMInterfaceImpl implements ATMInterface {
    private final ATMService atmService;

    public ATMInterfaceImpl(ATMService atmService) {
        this.atmService = atmService;
    }

    @Override
    public void showWelcomeScreen() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Please enter your card");
    }

    @Override
    public void insertCard() {
        System.out.println("Card inserted. Please enter your PIN.");
    }

    @Override
    public void enterPin(String pin) {
        if (atmService.validatePin(pin)){
            System.out.println("PIN validated successfully!");
        }else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }

    @Override
    public void showMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Exit");
    }

    @Override
    public void handleWithdrawal(double amount) {
        if (atmService.withdraw(amount)){
            System.out.println("WITHDRAWAL SUCCESSFUL");
            System.out.println("Amount withdrawn: Rs." + String.format("%.2f", amount));
            System.out.println("Please take your cash and card");
        }else {
            System.out.println("WITHDRAWAL FAILED");
            System.out.println("Insufficient funds or ATM out of cash");
        }
    }

    @Override
    public void handleDeposit(double amount) {
        if (atmService.deposit(amount)){
            System.out.println("DEPOSIT SUCCESSFUL");
            System.out.println("Amount deposited: Rs." + String.format("%.2f", amount));
        }else {
            System.out.println("DEPOSIT FAILED");
            System.out.println("Deposit failed. Please try again.");
        }
    }

    @Override
    public void handleBalanceInquiry() {
        double balance = atmService.checkBalance();
        System.out.println("Your current balance is: Rs." + String.format("%.2f", balance));
    }

    @Override
    public void exitATM() {
        System.out.println("Thank you for using the ATM!");
        System.out.println("Please take your card.");
        atmService.endSession();
    }
}
