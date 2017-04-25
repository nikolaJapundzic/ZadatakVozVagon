package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;
import model.Voz;

import java.io.IOException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class StartKlasa {
    static Dao<Vagon, Integer> vagonDao;
    static Dao<Voz, Integer> vozDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        // create our data-source for the database
        try{
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");
            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);
            vozDao = DaoManager.createDao(connectionSource, Voz.class);

            System.out.println("-----------------");
            List<Voz> vozovi = vozDao.queryForAll();
            for(Voz v : vozovi)
                System.out.println("[vozovi] = " + v);
            System.out.println("-----------------");

// kako da pronadjem izvucem iz voz-a 1 sve vagone?

            List<Voz> zaIzlistavanje = vozDao.queryForEq(Voz.POLJE_OZNAKA, "Voz1");
            for(Voz v : zaIzlistavanje){
                System.out.println("[vozovi] = " + v);

            }


            List<Vagon> sviVagoni = vagonDao.queryForAll();

            VagonNit va1 = new VagonNit("va1", sviVagoni.get(2));
            VagonNit va2 = new VagonNit("va2", sviVagoni.get(3));

            va1.start();
            va2.start();

            va1.join();
            va2.join();

            System.out.println("Svi vagoni natovareni.");


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
