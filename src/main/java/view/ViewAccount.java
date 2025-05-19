package view;

import entities.Account;
import entities.Client;
import services.AccountService;
import services.ClientService;

import java.util.Optional;
import java.util.Scanner;

public class ViewAccount {

    private final AccountService accountService;
    private final Scanner scanner;

    private final ClientService clientService;

    public ViewAccount(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    void manageAccounts() {
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
                System.out.print("Enter client CPF: ");
                String clientCPF = scanner.nextLine();

                Optional<Client> client = clientService.getByCPF(clientCPF);

                if(client.isPresent()){
                    Account account = new Account(accountNumber, client.get());
                    accountService.create(account);
                    System.out.println("Account created successfully.");
                }else{
                    System.out.println("Account not created.");
                }

            }
            case 2 -> {
                System.out.print("Enter account number: ");
                String accountNumber = scanner.nextLine();
                accountService.getByAccountNumber(accountNumber).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Account not found."));
            }
            case 3 -> accountService.getAll().forEach(System.out::println);
            case 4 -> {
                System.out.print("Enter account number: ");
                String accountNumber = scanner.nextLine();
                accountService.getByAccountNumber(accountNumber).ifPresentOrElse(account -> {
                    System.out.print("Enter new account number: ");
                    String newAccountNumber = scanner.nextLine();
                    account.setAccountNumber(newAccountNumber);
                    accountService.update(account);
                    System.out.println("Account updated successfully.");
                }, () -> System.out.println("Account not found."));
            }
            case 5 -> {
                System.out.print("Enter account number: ");
                String accountNumber = scanner.nextLine();
                accountService.delete(accountNumber);
                System.out.println("Account deleted successfully.");
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }
}
