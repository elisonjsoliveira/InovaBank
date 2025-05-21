import services.AccountService;
import services.CardService;
import services.ClientService;
import services.TransactionService;
import repository.AccountRepository;
import repository.CardRepository;
import repository.ClientRepository;
import repository.TransactionRepository;
import util.JPAUtil;
import view.InteractiveView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Mensagem inicial bonitinha, só uma vez
        System.out.println("=====================================");
        System.out.println("    InovaBank - Acesso ao sistema    ");
        System.out.println("=====================================");
        System.out.println("Por favor! Antes de iniciar o sistema, digite o seu User e o password para iniciar a sua conexão com o banco.");
        System.out.println();

        String user;
        String password;
        boolean conectado = false;

        // Loop até conectar com sucesso
        while (!conectado) {
            System.out.print("Digite seu usuário do banco: ");
            user = sc.nextLine();

            System.out.print("Digite sua senha do banco: ");
            password = sc.nextLine();

            try {
                JPAUtil.init(user, password);
                conectado = true;

                System.out.println("\n=====================================");
                System.out.println("      InovaBank - Acesso liberado     ");
                System.out.println("=====================================\n");

            } catch (Exception e) {
                System.out.println("\nErro ao conectar ao banco: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
        }

        // create repositories
        AccountRepository accountRepository = new AccountRepository();
        CardRepository cardRepository = new CardRepository();
        ClientRepository clientRepository = new ClientRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        // create services
        AccountService accountService = new AccountService(accountRepository);
        CardService cardService = new CardService(cardRepository);
        ClientService clientService = new ClientService(clientRepository);
        TransactionService transactionService = new TransactionService(transactionRepository);

        // create view
        InteractiveView interactiveView = new InteractiveView(accountService, cardService, clientService, transactionService);

        // show menu
        interactiveView.showMenu();
    }
}
