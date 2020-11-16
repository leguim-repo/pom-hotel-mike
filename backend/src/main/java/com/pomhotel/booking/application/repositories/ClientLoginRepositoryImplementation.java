package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
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

//--- Repository -------------------------------------------------------
@Repository
public class ClientLoginRepositoryImplementation implements ClientLoginRepository {

    //--- Session ------------------------------------------------------
    private static SessionFactory dbConnection;

    //--- Constructor --------------------------------------------------
    @Autowired
    public ClientLoginRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public ClientsEntity findClientByUsername(String key) {
        ClientsEntity client = null;
        Session session = this.dbConnection.openSession();
        try {
            LoginsEntity login = this.findLoginByUsername(key);
            client = login.getClientsByFkClientId();
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
    public boolean createClientAndLogin(LoginsEntity login) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ClientsEntity client = login.getClientsByFkClientId();
            client.setId((long) session.save(client));
            login.setClientsByFkClientId(client);
            session.saveOrUpdate(login);
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
