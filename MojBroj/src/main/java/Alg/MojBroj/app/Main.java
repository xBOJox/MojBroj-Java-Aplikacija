package Alg.MojBroj.app;

import Alg.MojBroj.AppGui;
import Alg.MojBroj.core.ApplicationFramework;
import Alg.MojBroj.core.Gui;

public class Main {

    public static void main(String[] args) {
        Gui gui = new AppGui();

        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize(gui);
        appCore.run();
    }
}