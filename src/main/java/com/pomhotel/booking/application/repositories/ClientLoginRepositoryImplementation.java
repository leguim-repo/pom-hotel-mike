package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientLoginRepositoryImplementation implements ClientLoginRepository {
    private static SessionFactory dbConnection;

    @Autowired
    public ClientLoginRepositoryImplementation(SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
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
}
