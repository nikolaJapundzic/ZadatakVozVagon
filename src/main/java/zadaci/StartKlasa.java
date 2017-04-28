package zadaci;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;
import model.Voz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class StartKlasa {
    static Dao<Vagon, Integer> vagonDao;
    static Dao<Voz, Integer> vozDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        try{
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");
            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);
            vozDao = DaoManager.createDao(connectionSource, Voz.class);

            System.out.println("-----------------");
            List<Voz> vozovi = vozDao.queryForAll();
            for(Voz v : vozovi)
                System.out.println("[vozovi] = " + v);
            System.out.println("-----------------");


            ArrayList<VagonNit> sviVagoni = new ArrayList<VagonNit>();

            List<Voz> zaIzlistavanje = vozDao.queryForEq(Voz.POLJE_OZNAKA, "Voz1");
            for(Voz v : zaIzlistavanje){
                System.out.println("[vozovi] = " + v);
                ForeignCollection<Vagon> vagoni = v.getVagon();
                CloseableIterator<Vagon> iterator = vagoni.closeableIterator();

                int i = 0;
                try{
                    while(iterator.hasNext()){
                        Vagon va = iterator.next();
                        System.out.println("vagoni = " + va);
                        String oznaka = "vagon" + i;
                        i++;
                        VagonNit vag = new VagonNit(oznaka, va);
                        sviVagoni.add(vag);

                    }
                }catch (Exception e)
                {
                    System.out.println("Greska prilikom iteracije");
                }finally {
                    iterator.close();
                }

            }


            for(int i = 0; i < sviVagoni.size(); i++ ){
                sviVagoni.get(i).start();
            }
            for(int i = 0; i < sviVagoni.size(); i++ ){
                sviVagoni.get(i).join();
            }


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
