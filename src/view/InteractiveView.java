package view;

import entities.Account;
import entities.Card;
import entities.Client;
import entities.Transaction;
import services.AccountService;
import services.CardService;
import services.ClientService;
import services.TransactionService;

import java.time.LocalDate;
import java.util.Scanner;

public class InteractiveView {
    private final AccountService accountService;
    private final CardService cardService;
    private final ClientService clientService;
    private final TransactionService transactionService;
    private final Scanner scanner;

    public InteractiveView(AccountService accountService, CardService cardService, ClientService clientService, TransactionService transactionService) {
        this.accountService = accountService;
        this.cardService = cardService;
        this.clientService = clientService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n==== Banking System ====");
            System.out.println("1. Manage Accounts");
            System.out.println("2. Manage Clients");
            System.out.println("3. Manage Cards");
            System.out.println("4. Manage Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageAccounts();
                case 2 -> manageClients();
                case 3 -> manageCards();
                case 4 -> manageTransactions();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void manageAccounts() {
        System.out.println("\n==== Account Management ====");
        System.out.println("1. Create Account");
        System.out.println("2. View Account");
        System.out.println("3. View All Accounts");
        System.out.println("4. Update Account");
        System.out.println("5. Delete Account");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter account number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter client ID: ");
                long clientId = scanner.nextLong();
                scanner.nextLine();
                Account account = new Account(accountNumber, clientId);
                accountService.create(account);
                System.out.println("Account created successfully.");
            }
            case 2 -> {
                System.out.print("Enter account ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                accountService.getById(id).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Account not found."));
            }
            case 3 -> accountService.getAll().forEach(System.out::println);
            case 4 -> {
                System.out.print("Enter account ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                accountService.getById(id).ifPresentOrElse(account -> {
                    System.out.print("Enter new account number: ");
                    String newAccountNumber = scanner.nextLine();
                    System.out.print("Enter new balance: ");
                    double newBalance = scanner.nextDouble();
                    account.setBalance(newBalance);
                    accountService.update(account);
                    System.out.println("Account updated successfully.");
                }, () -> System.out.println("Account not found."));
            }
            case 5 -> {
                System.out.print("Enter account ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                accountService.delete(id);
                System.out.println("Account deleted successfully.");
            }
            default -> System.out.println("Invalid choice. Try again.");
        }


    }

    private void manageClients() {
            System.out.println("\n==== Client Management ====");
            System.out.println("1. Create Client");
            System.out.println("2. View Client");
            System.out.println("3. View All Clients");
            System.out.println("4. Update Client");
            System.out.println("5. Delete Client");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter client name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter client CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Enter client phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter client email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter client birth date (YYYY-MM-DD): ");
                    String birthDateStr = scanner.nextLine();
                    LocalDate birthDate = LocalDate.parse(birthDateStr);

                    Client client = new Client(name, cpf, phone, email, birthDate);
                    clientService.create(client);
                    System.out.println("Client created successfully.");
                }
                case 2 -> {
                    System.out.print("Enter client ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    clientService.getById(id).ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Client not found."));
                }
                case 3 -> clientService.getAll().forEach(System.out::println);
                case 4 -> {
                    System.out.print("Enter client ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    clientService.getById(id).ifPresentOrElse(client -> {
                        System.out.print("Enter new client name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new client CPF: ");
                        String newCpf = scanner.nextLine();
                        System.out.print("Enter new client phone: ");
                        String newPhone = scanner.nextLine();
                        System.out.print("Enter new client email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter new client birth date (YYYY-MM-DD): ");
                        String newBirthDateStr = scanner.nextLine();
                        LocalDate newBirthDate = LocalDate.parse(newBirthDateStr);

                        client.setName(newName);
                        client.setCpf(newCpf);
                        client.setPhone(newPhone);
                        client.setEmail(newEmail);
                        client.setBirthDate(newBirthDate);
                        clientService.update(client);
                        System.out.println("Client updated successfully.");
                    }, () -> System.out.println("Client not found."));
                }
                case 5 -> {
                    System.out.print("Enter client ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    clientService.delete(id);
                    System.out.println("Client deleted successfully.");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

    }

    private void manageCards() {
        System.out.println("\n==== Card Management ====");
        System.out.println("1. Create Card");
        System.out.println("2. View Card");
        System.out.println("3. View All Cards");
        System.out.println("4. Update Card");
        System.out.println("5. Delete Card");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter card number: ");
                int cardNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter card validity (YYYY-MM-DD): ");
                String validityStr = scanner.nextLine();
                LocalDate validity = LocalDate.parse(validityStr);
                System.out.print("Enter CVV: ");
                int cvv = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter card type (e.g., Credit, Debit): ");
                String cardType = scanner.nextLine();
                System.out.print("Enter credit limit: ");
                double creditLimit = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter account ID associated with the card: ");
                long accountId = scanner.nextLong();
                scanner.nextLine();

                Card card = new Card(cardNumber, validity, cvv, cardType, creditLimit, accountId);
                cardService.create(card);
                System.out.println("Card created successfully.");
            }
            case 2 -> {
                System.out.print("Enter card ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                cardService.getById(id).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Card not found."));
            }
            case 3 -> cardService.getAll().forEach(System.out::println);
            case 4 -> {
                System.out.print("Enter card ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                cardService.getById(id).ifPresentOrElse(card -> {
                    System.out.print("Enter new validity (YYYY-MM-DD): ");
                    String newValidityStr = scanner.nextLine();
                    LocalDate newValidity = LocalDate.parse(newValidityStr);
                    System.out.print("Enter new card type: ");
                    String newCardType = scanner.nextLine();
                    System.out.print("Enter new credit limit: ");
                    double newCreditLimit = scanner.nextDouble();
                    scanner.nextLine();

                    card.setValidity(newValidity);
                    card.setCardType(newCardType);
                    card.setCreditLimit(newCreditLimit);
                    cardService.update(card);
                    System.out.println("Card updated successfully.");
                }, () -> System.out.println("Card not found."));
            }
            case 5 -> {
                System.out.print("Enter card ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                cardService.delete(id);
                System.out.println("Card deleted successfully.");
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private void manageTransactions() {
        System.out.println("\n==== Transaction Management ====");
        System.out.println("1. Create Transaction");
        System.out.println("2. View Transaction");
        System.out.println("3. View All Transactions");
        System.out.println("4. Update Transaction");
        System.out.println("5. Delete Transaction");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter transaction type (e.g., Deposit, Withdrawal): ");
                String typeTransaction = scanner.nextLine();
                System.out.print("Enter transaction value: ");
                double value = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter transaction date (YYYY-MM-DD): ");
                String dateStr = scanner.nextLine();
                LocalDate date = LocalDate.parse(dateStr);
                System.out.print("Enter origin account ID: ");
                long originAccountId = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Enter destination account ID: ");
                long destinationAccountId = scanner.nextLong();
                scanner.nextLine();

                Transaction transaction = new Transaction(typeTransaction, value, date, originAccountId, destinationAccountId);
                transactionService.create(transaction);
                System.out.println("Transaction created successfully.");
            }
            case 2 -> {
                System.out.print("Enter transaction ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                transactionService.getById(id).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Transaction not found."));
            }
            case 3 -> transactionService.getAll().forEach(System.out::println);
            case 4 -> {
                System.out.print("Enter transaction ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                transactionService.getById(id).ifPresentOrElse(transaction -> {
                    System.out.print("Enter new transaction type: ");
                    String newTypeTransaction = scanner.nextLine();
                    System.out.print("Enter new transaction value: ");
                    double newValue = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter new transaction date (YYYY-MM-DD): ");
                    String newDateStr = scanner.nextLine();
                    LocalDate newDate = LocalDate.parse(newDateStr);
                    System.out.print("Enter new origin account ID: ");
                    long newOriginAccountId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter new destination account ID: ");
                    long newDestinationAccountId = scanner.nextLong();
                    scanner.nextLine();

                    transaction.setTypeTransaction(newTypeTransaction);
                    transaction.setValue(newValue);
                    transaction.setDate(newDate);
                    transaction.setOriginAccountId(newOriginAccountId);
                    transaction.setDestinationAccountId(newDestinationAccountId);
                    transactionService.update(transaction);
                    System.out.println("Transaction updated successfully.");
                }, () -> System.out.println("Transaction not found."));
            }
            case 5 -> {
                System.out.print("Enter transaction ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                transactionService.delete(id);
                System.out.println("Transaction deleted successfully.");
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

}
