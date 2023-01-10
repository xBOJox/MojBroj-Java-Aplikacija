package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Obrisi implements ActionListener {

    MainFrame frame;

    public Obrisi(MainFrame frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Database.getInstance().obrisi();
        frame.getResenje().setText("");
        frame.getBroj1().setSelected(false);
        frame.getBroj2().setSelected(false);
        frame.getBroj3().setSelected(false);
        frame.getBroj4().setSelected(false);
        frame.getBroj5().setSelected(false);
        frame.getBroj6().setSelected(false);
    }
}
