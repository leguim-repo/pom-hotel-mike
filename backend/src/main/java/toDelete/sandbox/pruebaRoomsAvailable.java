package toDelete.sandbox;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class pruebaRoomsAvailable {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("pruebaRoomsAvailable.class");

    private static SessionFactory dbConnection;

    public static void MyPrint(String TituloLista, List<RoomsEntity> lista) {
        Logger.info("** ** " + TituloLista + " ** **");
        Logger.info("En esta tabla hay " + lista.size() + " registros");
        for (RoomsEntity e : lista) {
            Logger.info("ID: " + e.getId());
            Logger.info("Descripcion:" + e.getDescription());
            Logger.info("");
        }
    }

    public static void main(String[] args) {
        // Configuracion de Full Hibernate
        try {
            Configuration config = new Configuration();
            //Registro de entidades
            config.addAnnotatedClass(RoomsEntity.class);
            config.addAnnotatedClass(BookingsEntity.class);
            config.configure();
            dbConnection = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        List<RoomsEntity> rooms = null;
        Session session = dbConnection.openSession();
        try {
            Query avaiRooms = session.createQuery(new StringBuilder().append("FROM RoomsEntity r LEFT JOIN BookingsEntity b ON r.id = b.roomsByFkRoomId WHERE r.guests >= 1 AND r.pricePerNight >= 20 AND r.pricePerNight <= 1000 AND r.roomtypesByFkRoomtypeId = '1' AND NOT EXISTS")
                    .append("(FROM BookingsEntity b WHERE b.roomsByFkRoomId == r.id AND ('2020-10-09' BETWEEN b.checkIn AND b.checkOut OR '2020-10-15' BETWEEN b.checkIn AND b.checkOut OR ('2020-10-09' <= \n")
                    .append("b.checkIn AND '2020-10-15' >= b.checkOut)))").toString());
            rooms = avaiRooms.list();

        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        MyPrint("Lista Rooms", rooms);
    }
}


