package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KlikBroj implements ActionListener {

    MainFrame frame;
    JToggleButton button;

    public KlikBroj(MainFrame m, JToggleButton button){
        this.frame = m;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = button.getText();
        if(!Database.getInstance().getVremeZaRacun()){
            if(button.isSelected()){
                Database.getInstance().addResenje(s);
                frame.getResenje().setText(Database.getInstance().getResenjeString());
                button.setSelected(true);
                Database.getInstance().setVremeZaRacun(true);
                Database.getInstance().setVremeZaZagradu(false);
                //System.out.println("VremeZaRacun: "+Database.getInstance().getVremeZaRacun() + "  VremeZaZagradu: "+ Database.getInstance().getVremeZaZagradu());
            }
            else
                button.setSelected(true);
        }
        else
            button.setSelected(false);
    }


}
