package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Vagon;
import model.Voz;

import java.io.IOException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak2DodavanjeVrednosti {

    static Dao<Vagon, Integer> vagonDao;
    static Dao<Voz, Integer> vozDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        // create our data-source for the database
        try{
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");

            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);
            vozDao = DaoManager.createDao(connectionSource, Voz.class);

            TableUtils.clearTable(connectionSource, Vagon.class);
            TableUtils.clearTable(connectionSource, Voz.class);

            Voz v1 = new Voz("Voz1", "Dizel");
            vozDao.create(v1);
            Voz v2 = new Voz("Voz2", "Elektricni");
            vozDao.create(v2);

            Vagon va1 = new Vagon("Vagon 1", "Za prenos goriva", 10, v1);
            vagonDao.create(va1);
            Vagon va2 = new Vagon("Vagon 2", "Za prenos toksicnih materija",5 , v1);
            vagonDao.create(va2);
            Vagon va3 = new Vagon("Vagon 3", "Za prenos psenice",20 , v1);
            vagonDao.create(va3);
            Vagon va4 = new Vagon("Vagon 4", "Za spavanje",5 , v2);
            vagonDao.create(va4);
            Vagon va5 = new Vagon("Vagon 5", "Restoran ",3 , v2);
            vagonDao.create(va5);

            System.out.println("-----------------");
            List<Voz> vozovi = vozDao.queryForAll();
            for(Voz v : vozovi)
                System.out.println("[vozovi] = " + v);
            System.out.println("-----------------");

            System.out.println("-----------------");
            List<Vagon> vagoni = vagonDao.queryForAll();
            for(Vagon v : vagoni)
                System.out.println("[vagoni] = " + v);
            System.out.println("-----------------");




        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
