package interfaces;


import java.util.List;
import java.util.Optional;

public interface IClientRepository<T> {
    void create(T entity);
    Optional<T> getById(long id);
    List<T> getAll();
    void update(T entity);
    void delete(long id);
}
