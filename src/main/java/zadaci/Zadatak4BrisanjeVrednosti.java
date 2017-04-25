package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;

import java.io.IOException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak4BrisanjeVrednosti {
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


            List<Vagon> zaIzmenu = vagonDao.queryForEq(Vagon.POLJE_NOSIVOST, 10);
            for(Vagon v : zaIzmenu){
                vagonDao.delete(v);
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
