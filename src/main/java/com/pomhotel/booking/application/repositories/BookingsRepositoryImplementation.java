package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

//--- Repository -------------------------------------------------------
@Repository
public class BookingsRepositoryImplementation implements BookingsRepository{

    //--- Session ------------------------------------------------------
    private static SessionFactory dbConnection;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookingsRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public BookingsEntity findById(long id) {
        BookingsEntity entity = null;
        Session session = this.dbConnection.openSession();
        try {
            entity = session.get(BookingsEntity.class, id);
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public List<BookingsEntity> findAll() {
        List<BookingsEntity> entities = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<BookingsEntity> cq = session.getCriteriaBuilder().createQuery(BookingsEntity.class);
            cq.select(cq.from(BookingsEntity.class));
            entities = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entities;
    }

    @Override
    public void saveOrUpdate(BookingsEntity entity) {
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
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
            transaction = session.beginTransaction();
            BookingsEntity entity = session.load(BookingsEntity.class, id);
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
    public void delete(BookingsEntity entity) {
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

}
