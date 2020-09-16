package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.LoginModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;

import java.util.List;
@Repository
public class LoginRepositoryImplementation implements LoginRepository{
    private static SessionFactory dbConnection;

    @Autowired
    public LoginRepositoryImplementation() {
            // Configuracion de Full Hibernate
            try {
                Configuration config = new Configuration();
                //Registro de entidades
                config.addAnnotatedClass(LoginsEntity.class);
                config.addAnnotatedClass(ClientsEntity.class);
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
        //TODO la logica de authtentifiation se va al servicio
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
    public LoginsEntity findByEntity(LoginsEntity entity) {
        LoginsEntity login = null;
        try (Session session = dbConnection.openSession()) {
        //<<<<<<< HEAD
            session.get(LoginsEntity.class, entity.getId()); //TODO esto tengo que revisarlo
        //=======
        //            session.get(LoginsEntity.class, entity.getUsername());
        //>>>>>>> ed3afb8bbfbff0a5e088ac14214e5146f6ca86fb
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
    public boolean createNewLoginAndUser(LoginsEntity login, ClientsEntity client) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(login);
            session.persist(client);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public void update(LoginsEntity entity) {
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
    public void deleteByEntity(LoginsEntity entity) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            LoginsEntity target = session.load(LoginsEntity.class, id);
            transaction = session.beginTransaction();
            session.delete(target);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean checkLoginExists(LoginsEntity entity) {
        if (findByEntity(entity) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkClientExists(ClientsEntity entity) {
        ClientsEntity logins = null;
        Session session = this.dbConnection.openSession();
        try {
            logins = session.get(ClientsEntity.class, entity.getId());
        } catch (Throwable ex) {
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
