package toDelete.sandbox;

import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class pruebaLoginEntity {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("pruebaLoginEntity.class");

    private static SessionFactory dbConnection;

    public static void MyPrint(String TituloLista, List<LoginsEntity> lista) {
        Logger.info("** ** " + TituloLista + " ** **");
        Logger.info("En esta tabla hay " + lista.size() + " registros");
        for (LoginsEntity e : lista) {
            Logger.info("ID: " + e.getId());
            Logger.info("UserName:" + e.getUsername());
            Logger.info("");
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

        List<LoginsEntity> logins = null;
        Session session = dbConnection.openSession();
        try {
            CriteriaQuery<LoginsEntity> cq = session.getCriteriaBuilder().createQuery(LoginsEntity.class);
            cq.select(cq.from(LoginsEntity.class));
            logins = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        MyPrint("Lista Logins", logins);
    }

}
