package repository;

import entities.Transaction;
import interfaces.CrudRepository;
import interfaces.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements ITransactionRepository<Transaction> {

    List<Transaction> transactions = new ArrayList<>();

    @Override
    public void create(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public Optional<Transaction> getById(long id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    @Override
    public List<Transaction> getAll() {
        return new ArrayList<>(transactions);
    }

    @Override
    public void update(Transaction transactionUpdate) {
        getById(transactionUpdate.getId()).ifPresent(transaction -> {
            transactions.remove(transaction);
            transactions.add(transactionUpdate);
        });
    }

    @Override
    public void delete(long id) {
        transactions.removeIf(transaction -> transaction.getId() == id);
    }
}
