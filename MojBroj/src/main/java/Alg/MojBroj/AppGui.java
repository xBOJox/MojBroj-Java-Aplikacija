package Alg.MojBroj;

import Alg.MojBroj.core.Gui;
import Alg.MojBroj.view.MainFrame;

public class AppGui implements Gui {

    private MainFrame instance;

    public AppGui(){

    }

    public void start(){
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
