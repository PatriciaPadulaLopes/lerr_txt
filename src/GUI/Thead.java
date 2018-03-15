package GUI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Thead extends Thread {

    private final String nome;
    private final long tempo;

    public Thead(String n, long t) {
        this.nome = n;
        this.tempo = t;
        start();//inicia
    }

    @Override
    public void run() {
        try {
            new Interfaz().getjTable1().repaint();
            System.out.println(" Atualizando tabelas :" + new  SimpleDateFormat(" hh:mm:ss ").format(new Date()));
            sleep(tempo);
            run();
        } catch (InterruptedException e) {
            System.err.println(" Error " + e );
        }
    }
}
