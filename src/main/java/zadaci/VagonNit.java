package zadaci;

import model.Vagon;

import java.util.Random;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class VagonNit extends Thread{
    public String oznaka;
    public Vagon vagon;

    VagonNit (){

    }

    VagonNit (String oznaka, Vagon vagon){
        this.oznaka = oznaka;
        this.vagon = vagon;
    }

    Random random = new Random();
    public double masa = 0;


    public void pocetak(){
        System.out.println("[" + oznaka + "] krece utovar vagona : [" + vagon.getOznaka() + "].");
    }

    public synchronized void rad(){
        while(vagon.getNosivost()<vagon.getTeret()){
            System.out.println("[" + oznaka + "] tovari u vagon : [" + vagon.getOznaka() + "].  Tezina je sada ["+vagon.getTeret()+"]");
            try {
                sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vagon.setTeret(vagon.getTeret() + 1);

        }


    }

    public void zavrsetak(){
        System.out.println("[" + oznaka + "] zavrsen utovar vagona : [" + vagon.getOznaka() + "].");
    }

    @Override
    public void run() {
        pocetak();
        rad();
        zavrsetak();
    }
}
