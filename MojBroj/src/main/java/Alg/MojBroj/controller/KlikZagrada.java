package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KlikZagrada implements ActionListener {

    MainFrame frame;
    JButton button;
    String s;

    public KlikZagrada(MainFrame m, JButton button, String s){
        this.frame = m;
        this.button = button;
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(frame.getZatvorenaZagrada().equals(button));
        if(Database.getInstance().getVremeZaRacun() && frame.getZatvorenaZagrada().equals(button)){
            Database.getInstance().addResenje(s);
            frame.getResenje().setText(Database.getInstance().getResenjeString());
            button.setSelected(true);
            Database.getInstance().setVremeZaRacun(true);
            //System.out.println(Database.getInstance().getVremeZaZagradu());
        }
        else if(!Database.getInstance().getVremeZaRacun() && Database.getInstance().getVremeZaZagradu()){
            Database.getInstance().addResenje(s);
            frame.getResenje().setText(Database.getInstance().getResenjeString());
            button.setSelected(true);
            Database.getInstance().setVremeZaRacun(false);
            ///System.out.println(Database.getInstance().getVremeZaZagradu());
        }
        else
            button.setSelected(false);

    }
}
