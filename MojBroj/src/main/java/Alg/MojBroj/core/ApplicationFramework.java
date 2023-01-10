package Alg.MojBroj.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApplicationFramework {

    private static ApplicationFramework instance;

    protected Gui gui;

    public void run(){
        this.gui.start();
    }

    public void initialize(Gui gui){

        this.gui = gui;
    }

    public static ApplicationFramework getInstance() {
        if(instance ==null)
            instance = new ApplicationFramework();
        return instance;
    }
}
