package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // Fábrica, é o objeto que gerencia as operações do banco, como o CRUD, transações e consultas, como se fosse a conexão com o banco.
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("inovabank");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
