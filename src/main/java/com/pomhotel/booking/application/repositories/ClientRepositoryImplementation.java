package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientRepositoryImplementation implements ClientRepository{

    private static SessionFactory dbConnection;

    public ClientRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean checkLoginExists(LoginsEntity entity) {
        try (Session session = dbConnection.openSession()) {
            session.get(LoginsEntity.class, entity.getUsername());
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkClientExists(ClientsEntity entity) {
        try (Session session = dbConnection.openSession()) {
            session.get(LoginsEntity.class, entity.getEmail());
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean createNewLoginAndClient(LoginsEntity login, ClientsEntity client) {
        Session session = dbConnection.openSession();
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
    public boolean create(ClientsEntity entity) {
        Session session = dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
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
    public boolean update(ClientsEntity entity) {
        Session session = dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
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
    public boolean delete(ClientsEntity entity) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
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
    public List<ClientsEntity> findAll() {
        List<ClientsEntity> allEntities = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<ClientsEntity> cq = session.getCriteriaBuilder().createQuery(ClientsEntity.class);
            cq.select(cq.from(ClientsEntity.class));
            allEntities = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return allEntities;
    }

    @Override
    public ClientsEntity findById(long id) {
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
    public List<ClientsEntity> findByName(String cadena) {
        List<ClientsEntity> lista = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClientsEntity> q = cb.createQuery(ClientsEntity.class);
            Root<ClientsEntity> c = q.from(ClientsEntity.class);
            Predicate predicate = cb.like(c.<String>get("name"),"%"+cadena+"%");
            q.where(predicate);
            Query<ClientsEntity> query = session.createQuery(q);
            lista = query.getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public List<ClientsEntity> findEmail(String cadena) {
        List<ClientsEntity> lista = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClientsEntity> q = cb.createQuery(ClientsEntity.class);
            Root<ClientsEntity> c = q.from(ClientsEntity.class);
            Predicate predicate = cb.like(c.<String>get("name"),"%"+cadena+"%");
            q.where(predicate);
            Query<ClientsEntity> query = session.createQuery(q);
            lista = query.getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public void createNewLogin(LoginsEntity entity) {
        Session session = dbConnection.openSession();
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
}
