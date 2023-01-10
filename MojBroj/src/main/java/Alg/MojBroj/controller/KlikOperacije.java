package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KlikOperacije implements ActionListener {

    MainFrame frame;
    JButton button;
    String s;

    public KlikOperacije(MainFrame m, JButton button, String s){
        this.frame = m;
        this.button = button;
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Database.getInstance().getVremeZaRacun() && !Database.getInstance().getVremeZaZagradu()){
                Database.getInstance().addResenje(s);
                frame.getResenje().setText(Database.getInstance().getResenjeString());
                button.setSelected(true);
                Database.getInstance().setVremeZaZagradu(true);
                Database.getInstance().setVremeZaRacun(false);
                //System.out.println("VremeZaRacun: "+Database.getInstance().getVremeZaRacun() + "  VremeZaZagradu: "+ Database.getInstance().getVremeZaZagradu());
        }
        else
            button.setSelected(false);

    }
}
