package main.java.com.atm;

import main.java.com.atm.model.ATM;
import main.java.com.atm.model.Account;
import main.java.com.atm.model.Card;
import main.java.com.atm.service.ATMService;
import main.java.com.atm.service.ATMServiceImpl;
import main.java.com.atm.ui.ATMInterface;
import main.java.com.atm.ui.ATMInterfaceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ATM Machine Simulation ===");

        ATM atm = new ATM("ATM001", 10000.0, ATM.ATMState.IDLE);

        Card card = new Card("1234567890123456", "1234", false, Card.CardType.DEBIT);
        Account account = new Account("ACC001", 5000.0);

        ATMService atmService = new ATMServiceImpl(atm);
        ATMInterface atmInterface = new ATMInterfaceImpl(atmService);

        runATMSimulation(atmInterface, atmService, card, account);
    }

    private static void runATMSimulation(ATMInterface atmInterface, ATMService atmService, Card card, Account account) {
        Scanner scanner = new Scanner(System.in);

        try {
            atmInterface.showWelcomeScreen();

            System.out.print("Please Enter to insert card...");
            scanner.nextLine();
            atmInterface.insertCard();

            atmService.startSession(card, account);

            System.out.println("Enter PIN: ");
            String pin = scanner.nextLine();
            atmInterface.enterPin(pin);

            if (!atmService.validatePin(pin)) {
                System.out.println("Invalid PIN. Please try again.");
                return;
            }

            boolean exit = false;
            while (!exit) {
                atmInterface.showMainMenu();
                System.out.print("Enter your choice (1-4): ");
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            atmInterface.handleBalanceInquiry();
                            break;
                        case 2:
                            System.out.print("Enter amount to withdraw: Rs.");
                            double withdrawAmount = scanner.nextDouble();
                            scanner.nextLine();
                            atmInterface.handleWithdrawal(withdrawAmount);
                            break;
                        case 3:
                            System.out.print("Enter amount to deposit: Rs.");
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine();
                            atmInterface.handleDeposit(depositAmount);
                            break;
                        case 4:
                            atmInterface.exitATM();
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                    if (!exit) {
                        System.out.print("Press Enter to continue...");
                        scanner.nextLine();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
