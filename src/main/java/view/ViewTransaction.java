package view;

import entities.Transaction;
import services.TransactionService;

import java.time.LocalDate;
import java.util.Scanner;

public class ViewTransaction {

    private final TransactionService transactionService;
    private final Scanner scanner;

    public ViewTransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    void manageTransactions() {
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
                System.out.print("Enter ID transaction: ");
                long id = scanner.nextLong();
                scanner.nextLine();
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

                Transaction transaction = new Transaction(id, typeTransaction, value, date, originAccountId, destinationAccountId);
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
