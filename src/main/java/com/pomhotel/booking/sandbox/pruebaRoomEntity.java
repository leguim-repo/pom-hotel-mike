package com.pomhotel.booking.sandbox;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class pruebaRoomEntity {
    private static SessionFactory dbConnection;

    public static void MyPrint(String TituloLista, List<RoomsEntity> lista) {
        System.out.println("** ** " + TituloLista + " ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (RoomsEntity e : lista) {
            System.out.println("ID: " + e.getId());
            System.out.println("Descripcion:" + e.getDescription());
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // Configuracion de Full Hibernate
        try {
            Configuration config = new Configuration();
            //Registro de entidades
            config.addAnnotatedClass(RoomsEntity.class);

            config.configure();
            dbConnection = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        List<RoomsEntity> rooms = null;
        Session session = dbConnection.openSession();
        try {
            CriteriaQuery<RoomsEntity> cq = session.getCriteriaBuilder().createQuery(RoomsEntity.class);
            cq.select(cq.from(RoomsEntity.class));
            rooms = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        MyPrint("Lista Rooms", rooms);
    }

}
