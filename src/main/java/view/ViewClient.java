package view;

import entities.Client;
import services.ClientService;

import java.time.LocalDate;
import java.util.Scanner;

public class ViewClient {

    private final ClientService clientService;
    private final Scanner scanner;

    public ViewClient(ClientService clientService) {
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    void manageClients() {
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
                System.out.print("Enter name Id: ");
                long id = scanner.nextLong();
                scanner.nextLine();
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

                Client client = new Client(id, name, cpf, phone, email, birthDate);
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
                    String newCPF = scanner.nextLine();
                    System.out.print("Enter new client phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter new client email: ");
                    String newEmail = scanner.nextLine();

                    client.setName(newName);
                    client.setCPF(newCPF);
                    client.setPhone(newPhone);
                    client.setEmail(newEmail);
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
}
