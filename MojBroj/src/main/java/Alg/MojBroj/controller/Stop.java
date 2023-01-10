package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stop implements ActionListener {

    MainFrame frame;
    Database db;

    public Stop(MainFrame m){

        this.frame = m;
        this.db = frame.getDb();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Database.getInstance().getBrojStopova() > 8){
            return;
        }
        db.getInstance().povecajBrojStopova();
        int i = db.getInstance().getBrojStopova();
        int r;
        if(i<4){
            r = (int)Math.floor(Math.random()*(9-0+1)+0);
        } else if (i < 8) {
            r = (int)Math.floor(Math.random()*(9-1+1)+1);
        } else if(i<9){
            List<Integer> list = new ArrayList<>();

            list.add(10);
            list.add(15);
            list.add(20);

            r = list.get(new Random().nextInt(list.size()));
        }
        else{
            List<Integer> list = new ArrayList<>();

            list.add(25);
            list.add(50);
            list.add(75);
            list.add(100);

            r = list.get(new Random().nextInt(list.size()));
        }
        //System.out.println("Broj koji je dodat random "+r);
        db.getInstance().dodajBroj(r);
        //int rezultat = 100*Database.getInstance().getDatiBrojevi().get(0)+10*Database.getInstance().getDatiBrojevi().get(1)+Database.getInstance().getDatiBrojevi().get(2);
        frame.getTrazenibroj().setText(""+ Database.getInstance().getDatiBrojevi().get(0));
        frame.getBroj1().setText(""+ Database.getInstance().getDatiBrojevi().get(1));
        frame.getBroj2().setText(""+ Database.getInstance().getDatiBrojevi().get(2));
        frame.getBroj3().setText(""+ Database.getInstance().getDatiBrojevi().get(3));
        frame.getBroj4().setText(""+ Database.getInstance().getDatiBrojevi().get(4));
        frame.getBroj5().setText(""+ Database.getInstance().getDatiBrojevi().get(5));
        frame.getBroj6().setText(""+ Database.getInstance().getDatiBrojevi().get(6));
    }
}
