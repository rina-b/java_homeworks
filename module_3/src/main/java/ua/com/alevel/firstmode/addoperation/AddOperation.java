package ua.com.alevel.firstmode.addoperation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.entity.Balance;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.Instant;

public class AddOperation {
    public void addOperation(Long userId, String username, String password, Long amount, String description, Instant timestamp, Long balanceId){
        Configuration conf = new Configuration().configure()
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password);
        try(SessionFactory sessionFactory = conf.buildSessionFactory()){
        EntityManager entityManager = sessionFactory.createEntityManager();;
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            User user = entityManager.find(User.class, userId);
            TypedQuery<Balance> query = entityManager.createQuery("select a from Balance a where a.id=:balanceId and a.user=:user", Balance.class);
            query.setParameter("balanceId", balanceId);
            query.setParameter("user", user);
            Balance balance = query.getSingleResult();
            Operation operation = new Operation();
            operation.setBalance(balance);
            operation.setAmount(amount);
            operation.setDescription(description);
            operation.setTimestamp(timestamp);
            entityManager.persist(operation);
            transaction.commit();
        } catch (RuntimeException ex){
            transaction.rollback();
            throw ex;
        }
    }
    }
}

