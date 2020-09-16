package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.LoginModel;
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
    public ClientsEntity authentification(LoginsEntity entity) {
        ClientsEntity client = null;
        try (Session session = dbConnection.openSession()) {
            session.get(LoginsEntity.class, entity.getUsername());
            //comprueba existencia username
            //entonces verifica que la contraena es correcta
            //si es correcta pasamos el cliente correspondiente sino, null


        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return client;
    }


}
