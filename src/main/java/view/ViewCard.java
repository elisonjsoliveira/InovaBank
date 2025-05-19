package view;

import entities.Account;
import entities.Card;
import services.AccountService;
import services.CardService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class ViewCard {

    private final CardService cardService;
    private final Scanner scanner;

    private final AccountService accountService;

    public ViewCard(CardService cardService, AccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.scanner = new Scanner(System.in);
    }

    void manageCards() {
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
                long cardNumber = scanner.nextInt();
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
                System.out.print("Enter account number associated with the card: ");
                String accountNumber = scanner.nextLine();

                Optional<Account> account = accountService.getByAccountNumber(accountNumber);

                if(account.isPresent()){
                    Card card = new Card(cardNumber, validity, cvv, cardType, creditLimit, account.get());
                    cardService.create(card);
                    System.out.println("Card created successfully.");
                }else{
                    System.out.println("Card not created.");
                }


            }
            case 2 -> {
                System.out.print("Enter card number: ");
                long cardNumber = scanner.nextLong();
                scanner.nextLine();
                cardService.getByCardNumber(cardNumber).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Card not found."));
            }
            case 3 -> cardService.getAll().forEach(System.out::println);
            case 4 -> {
                System.out.print("Enter card number: ");
                long cardNumber = scanner.nextLong();
                scanner.nextLine();
                cardService.getByCardNumber(cardNumber).ifPresentOrElse(card -> {
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
                System.out.print("Enter card number: ");
                long cardNumber = scanner.nextLong();
                scanner.nextLine();
                cardService.delete(cardNumber);
                System.out.println("Card deleted successfully.");
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }
}
