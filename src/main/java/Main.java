import services.AccountService;
import services.CardService;
import services.ClientService;
import services.TransactionService;
import repository.AccountRepository;
import repository.CardRepository;
import repository.ClientRepository;
import repository.TransactionRepository;
import view.InteractiveView;

public class Main {
    public static void main(String[] args) {
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
