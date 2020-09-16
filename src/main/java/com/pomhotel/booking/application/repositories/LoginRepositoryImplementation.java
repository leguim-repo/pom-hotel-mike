package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.LoginModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class LoginRepositoryImplementation implements LoginRepository{
    private static SessionFactory dbConnection;

    public LoginRepositoryImplementation() {
            // Configuracion de Full Hibernate
            try {
                Configuration config = new Configuration();
                //Registro de entidades
                config.addAnnotatedClass(LoginsEntity.class);
                config.configure();
                dbConnection = config.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
    }

    @Override
    public ClientsEntity authentification(LoginsEntity entity) {
        ClientsEntity client = null;
        //comprueba existencia username
        //entonces verifica que la contraena es correcta
        //si es correcta pasamos el cliente correspondiente sino, null

        return client;
    }

    @Override
    public LoginsEntity findById(long id) {
        LoginsEntity logins = null;
        Session session = this.dbConnection.openSession();
        try {
            logins = session.get(LoginsEntity.class, id);
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return logins;
    }

    @Override
    public LoginsEntity findByUsername(LoginsEntity entity) {
        LoginsEntity login = null;
        try (Session session = dbConnection.openSession()) {
            session.get(LoginsEntity.class, entity.getUsername());



        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return login;
    }

    @Override
    public List<LoginsEntity> findAll() {
        List<LoginsEntity> allLogins = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<LoginsEntity> cq = session.getCriteriaBuilder().createQuery(LoginsEntity.class);
            cq.select(cq.from(LoginsEntity.class));
            allLogins = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return allLogins;
    }

    @Override
    public void createNewLogin(LoginsEntity entity) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void updateLogin(LoginsEntity entity) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteLogin(long id) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            LoginsEntity tareaABorrar = session.load(LoginsEntity.class, id);
            transaction = session.beginTransaction();
            session.delete(tareaABorrar);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
