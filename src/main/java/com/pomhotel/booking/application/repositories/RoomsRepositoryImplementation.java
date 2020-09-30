package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class RoomsRepositoryImplementation implements RoomsRepository {
    private static SessionFactory dbConnection;

    @Autowired
    public RoomsRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public RoomsEntity findById(long id) {
        RoomsEntity entity = null;
        Session session = this.dbConnection.openSession();
        try {
            entity = session.get(RoomsEntity.class, id);
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public List<RoomsEntity> findAll() {
        List<RoomsEntity> entities = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<RoomsEntity> cq = session.getCriteriaBuilder().createQuery(RoomsEntity.class);
            cq.select(cq.from(RoomsEntity.class));
            entities = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entities;
    }

    @Override
    public List<RoomsEntity> findApplyingFilter() {
        List<RoomsEntity> entities = null;
        Session session = this.dbConnection.openSession();
        try {
            entities = session.createSQLQuery("SELECT r.id, r.fk_roomtype_id, r.code, r.description, r.pricePerNight, r.image, r.guests FROM rooms r LEFT JOIN bookings b ON r.id = b.fk_room_id WHERE r.guests >= 1 AND r.pricePerNight >= 20").getResultList();
            //AND r.pricePerNight <= 1000 AND r.fk_roomtype_id = 1 AND NOT EXISTS( SELECT b.fk_room_id FROM bookings b WHERE b.fk_room_id = r.id AND ('2020-10-09' BETWEEN b.checkIn AND b.checkOut OR '2020-10-15' BETWEEN b.checkIn AND b.checkOut OR ('2020-10-09' <= b.checkIn AND '2020-10-15' >= b.checkOut))))")
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return entities;
    }

    @Override
    public void saveOrUpdate(RoomsEntity entity) {
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
            RoomsEntity entity = session.load(RoomsEntity.class, id);
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
    public void delete(RoomsEntity entity) {
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
