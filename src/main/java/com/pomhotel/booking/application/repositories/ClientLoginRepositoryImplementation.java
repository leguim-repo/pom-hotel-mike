package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class ClientLoginRepositoryImplementation implements ClientLoginRepository {
    private static SessionFactory dbConnection;

    @Autowired
    public ClientLoginRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ClientsEntity findClientByUsername(String key) {
        ClientsEntity client = null;
        Session session = this.dbConnection.openSession();
        try {
            LoginsEntity login = this.findLoginByUsername(key);
            client = session.get(ClientsEntity.class, login.getId());
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return client;
    }

    @Override
    public LoginsEntity findLoginByUsername(String key){
        LoginsEntity entity = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<LoginsEntity> q = cb.createQuery(LoginsEntity.class);
            Root<LoginsEntity> c = q.from(LoginsEntity.class);

            Predicate predicate = cb.like(c.<String>get("username"), key.trim());
            q.where(predicate);
            Query<LoginsEntity> query = session.createQuery(q);
            entity = query.getResultList().get(0);

        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public ClientsEntity findClientById(long id) {
        ClientsEntity entity = null;
        Session session = this.dbConnection.openSession();
        try {
            entity = session.get(ClientsEntity.class, id);
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }


    @Override
    public boolean createNewClientAndLogin(ClientsEntity newclient, LoginsEntity newlogin) {
        ClientsEntity client;
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        long newClientId = 0;

        try {
            transaction = session.beginTransaction();
            newClientId = (long) session.save(newclient);
            transaction.commit();
        }
        catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
            return false;

        } finally {
            session.close();
        }

        session = this.dbConnection.openSession();


        try {
            client = findClientById(newClientId);
            newlogin.setClientsByFkClientId(client);

            transaction = session.beginTransaction();
            session.save(newlogin);
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
    public boolean createNewLoginAndUser_Old(LoginsEntity login) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        long newClientId;
        try {
            transaction = session.beginTransaction();
            //session.persist(login.getClientsByFkClientId());
            //session.save(login.getClientsByFkClientId());
            ClientsEntity client = login.getClientsByFkClientId();
            client.setId((long) session.save(client));
            login.setClientsByFkClientId(client);
            session.saveOrUpdate(login);
            //session.persist(login);
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
}
