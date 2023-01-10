package Alg.MojBroj.model;

import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    private List<Integer> datiBrojevi;

    private int brojStopova;

    boolean vremeZaRacun;
    boolean vremeZaZagradu;

    private List<String> resenje;

    private static Database instance = null;

    public Database(){
        datiBrojevi = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0));
        brojStopova = 0;
        vremeZaRacun = false;
        vremeZaZagradu = true;
        resenje =  new ArrayList<String>();
    }

    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
        }
        return instance;
    }

    public void povecajBrojStopova(){
        brojStopova++;
    }
    public int getBrojStopova(){
        return brojStopova;
    }

    public void setBrojStopova(int i){
        brojStopova = i;
    }

    public void dodajBroj(Integer i){
        //System.out.println("Broj Kliktanja: "+ brojStopova);
        if(brojStopova<4){
            datiBrojevi.set(0,datiBrojevi.get(0)*10+i);
        }
        else{
            datiBrojevi.set(brojStopova-3,i);
        }
        //System.out.println(datiBrojevi);
    }
    public List<Integer> getDatiBrojevi(){
        return datiBrojevi;
    }

    public String getResenjeString(){
        String res = new String();
        for (String s : resenje) {
            res += s;
        }
        return res;
    }

    public List<String> getResenje(){
        return resenje;
    }

    public void addResenje(String a){
        resenje.add(a);
    }
    public void setVremeZaRacun(boolean b){
        vremeZaRacun = b;
    }

    public boolean getVremeZaRacun(){
        return vremeZaRacun;
    }
    public void setVremeZaZagradu(boolean b){
        vremeZaZagradu = b;
    }

    public boolean getVremeZaZagradu(){
        return vremeZaZagradu;
    }

    public void obrisi(){
        vremeZaRacun = false;
        vremeZaZagradu = true;
        resenje =  new ArrayList<>();

    }
    public void restart(){
        setBrojStopova(0);
        obrisi();
        datiBrojevi = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
        //System.out.println("" + brojStopova + getDatiBrojevi());
    }
}
