package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restart implements ActionListener {

    MainFrame mf;
    public Restart(MainFrame mf){
        this.mf = mf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        mf.getResenje().setText("");
        mf.getBroj1().setSelected(false);
        mf.getBroj1().setText("0");
        mf.getBroj2().setSelected(false);
        mf.getBroj2().setText("0");
        mf.getBroj3().setSelected(false);
        mf.getBroj3().setText("0");
        mf.getBroj4().setSelected(false);
        mf.getBroj4().setText("0");
        mf.getBroj5().setSelected(false);
        mf.getBroj5().setText("0");
        mf.getBroj6().setSelected(false);
        mf.getBroj6().setText("0");
        mf.getPlayerResenje().setText("");
        mf.getBotResenje().setText("");
        mf.getTrazenibroj().setText("0");
        Database.getInstance().restart();
    }
}
