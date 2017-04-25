package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;
import model.Voz;

import java.io.IOException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak3IzmenaVrednosti {
    static Dao<Vagon, Integer> vagonDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        // create our data-source for the database
        try{
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");

            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);

            System.out.println("-----------------");
            List<Vagon> vagoni = vagonDao.queryForAll();
            for(Vagon v : vagoni)
                System.out.println("[vagoni] = " + v);
            System.out.println("-----------------");


            List<Vagon> zaIzmenu = vagonDao.queryForEq(Vagon.POLJE_OPIS, "Restoran");
            for(Vagon v : zaIzmenu){
                v.setOpis("Za sedenje");
                vagonDao.update(v);
                System.out.println(v);

            }

            System.out.println("-----------------");
            vagoni = vagonDao.queryForAll();
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
