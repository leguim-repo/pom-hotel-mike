package com.pomhotel.booking;

import com.pomhotel.booking.application.domain.entities.RoomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class pruebaRoomEntity {
    private static SessionFactory dbConnection;

    public static void MyPrint(String TituloLista, List<RoomEntity> lista) {
        System.out.println("** ** " + TituloLista + " ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (RoomEntity e : lista) {
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
            config.addAnnotatedClass(RoomEntity.class);

            config.configure();
            dbConnection = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        //TODO pasar el try para arriba -> throws Throwable
        List<RoomEntity> rooms = null;
        Session session = dbConnection.openSession();
        try {
            CriteriaQuery<RoomEntity> cq = session.getCriteriaBuilder().createQuery(RoomEntity.class);
            cq.select(cq.from(RoomEntity.class));
            rooms = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        MyPrint("Lista Rooms", rooms);
    }

}
