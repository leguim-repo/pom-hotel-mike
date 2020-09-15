package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

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
    public LoginsEntity findByUsername(String username) {
        LoginsEntity login = null;
        try (Session session = dbConnection.openSession()) {
            login = session.get(LoginsEntity.class, username);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return login;
    }


}
