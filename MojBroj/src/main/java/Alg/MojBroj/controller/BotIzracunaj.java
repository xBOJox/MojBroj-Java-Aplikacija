package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
public class BotIzracunaj implements ActionListener {

    public static final byte SAB = 1;
    public static final byte ODUZ = 2;
    public static final byte MNOZ = 3;
    public static final byte DELJ = 4;

    int trazeniBroj;
    MainFrame mf;

    int brResenja;
    String rezultat;

    List<Integer> ponudjeniBrojevi;

    public BotIzracunaj(MainFrame mf){

        this.mf = mf;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        trazeniBroj = Database.getInstance().getDatiBrojevi().get(0);
        ponudjeniBrojevi = new ArrayList<>();
        brResenja=0;
        rezultat = null;
        //System.out.println(""+Database.getInstance().getDatiBrojevi());
        for(int i = 1;i<Database.getInstance().getDatiBrojevi().size();i++){
            ponudjeniBrojevi.add(Database.getInstance().getDatiBrojevi().get(i));
        }
        System.out.println("Ponudjeni: " + ponudjeniBrojevi + " Trazeni: " + trazeniBroj + "br resenja: " + brResenja);

        pronadjiPodskup(ponudjeniBrojevi, new ArrayList<>(Arrays.asList(new Boolean[ponudjeniBrojevi.size()])));
        if (brResenja > 0) {
            System.out.println("Nasao " + brResenja + " resenja!");
            System.out.println("Resenje za " + trazeniBroj + " = " + rezultat);
        }
        else {
            System.out.println("Nije nadjeno Resenje!");
        }

        mf.getBotResenje().setText(trazeniBroj + " = " + rezultat);
    }


    //Trazimo sve podskupove skupa brojeva jer ne moramo sve brojeve da iskoristimo da bi dosli do rezultata
    private void pronadjiPodskup(List<Integer> brojevi, List<Boolean> clanoviSkupa) {


        //podesen i na 6 za 6 brojeva u igri
        for(int i = 0; i<6;i++)
            clanoviSkupa.set(i,false);
        //System.out.println(clanoviSkupa);

        //broj podskupova od skupa sa n elemenata je 2^n
        for (int k = 0; k < Math.pow(2, brojevi.size()); k++) {

            int size = 0;

            for (int i = 0; i < brojevi.size(); i++) {
                if (clanoviSkupa.get(i)) {
                    size++;
                }
            }
            int[] playset = new int[size];

            // popunjavamo niz sa preostalim brojevima
            for (int i = 0, j = 0; i < brojevi.size(); i++) {
                if (clanoviSkupa.get(i)) {
                    playset[j] = brojevi.get(i);
                    j++;
                }
            }

//            for(int i = 0;i<playset.length;i++)
//                System.out.print(playset[i] + " ");
//            System.out.println(" Brojevi koji se permutiraju");

            //podskupovima se menjaju mesta da bi dobili svaku kombinaciju brojeva na svakom razlicitom mestu
            if (playset.length > 0) {
                razmenaMesta(playset, 0);
            }

            //booleanima oznacavamo koje brojeve koristimo za podskup
            int i = brojevi.size() - 1;
            while (i >= 0 && clanoviSkupa.get(i)) {
                clanoviSkupa.set(i,false);
                i--;
            }
            if (i >= 0) {
                clanoviSkupa.set(i,true);
            }
            //System.out.println(clanoviSkupa);
        }

    }

    //prvo razmenimo mesta
    private void razmenaMesta(int[] brojevi, int brIteracija) {

        if (brIteracija == brojevi.length) {
//            for(int i = 0;i< numbers.length;i++)
//                System.out.print(numbers[i] + " ");
//            System.out.println(" zamenjeni brojevi");
            operacije(brojevi, new byte[brojevi.length - 1], 0);
        }
        else {
            for (int i = brIteracija; i < brojevi.length; i++) {
                int temp = brojevi[i];
                brojevi[i] = brojevi[brIteracija];
                brojevi[brIteracija] = temp;
                razmenaMesta(brojevi, brIteracija + 1);
            }
        }
    }


    //Isprobava sve moguce kombinacije sa operacijama nad zadatim podskupom
    private void operacije(int[] brojevi, byte[] nizOperacija, int pos) {

        //Vrsi operacije nad ponudjenim podskupom
        if (pos == brojevi.length) {
            int total = brojevi[0];
            for (int i = 1; i < brojevi.length; i++) {
                switch (nizOperacija[i - 1]) {
                    case SAB:
                        total += brojevi[i];
                        break;
                    case ODUZ:
                        total -= brojevi[i];
                        break;
                    case MNOZ:
                        total *= brojevi[i];
                        break;
                    case DELJ:
                        if (total % brojevi[i] != 0) {
                            return;
                        }
                        total /= brojevi[i];
                        break;
                }
            }

            //ako je resnje isto trazenom pamti brojeve i operacije u String
            if (total == trazeniBroj) {
                brResenja++;
                if (rezultat == null) {
                    StringBuffer pomocnoResenje = new StringBuffer();
                    byte lastOp = 0;
                    for (int i = 0; i < brojevi.length - 1; i++) {
                        pomocnoResenje.append(brojevi[i]);
                        if (lastOp != 0 && nizOperacija[i] >= 3 && lastOp <= 2) {
                            pomocnoResenje.append(")");
                            pomocnoResenje.insert(0, "(");
                        }
                        lastOp = nizOperacija[i];


                        switch (nizOperacija[i]) {
                            case SAB:
                                pomocnoResenje.append(" + ");
                                break;
                            case ODUZ:
                                pomocnoResenje.append(" - ");
                                break;
                            case MNOZ:
                                pomocnoResenje.append("*");
                                break;
                            case DELJ:
                                pomocnoResenje.append("/");
                                break;
                        }
                    }
                    System.out.println("");
                    pomocnoResenje.append(brojevi[brojevi.length - 1]);
                    rezultat = pomocnoResenje.toString();
                    pomocnoResenje.setLength(0);
                }
            }
        }
        //ovde operacija krece da isprobava sve moguce operacije
        else {
            if (pos == 0) {
                operacije(brojevi, nizOperacija, ++pos);
            }
            else {
//                for(int i=0;i<nizOperacija.length;i++)
//                    System.out.print(nizOperacija[i]);
//                System.out.println("");
                int novaPoz = pos + 1;
                int proslaPoz = pos - 1;

                //Rekurzijom pravimo sve kombinacije sa operacijama na 5 mesta jer ima 6 brojeva  poslednja provera je *****

                nizOperacija[proslaPoz] = SAB;
                operacije(brojevi, nizOperacija, novaPoz);
                nizOperacija[proslaPoz] = ODUZ;
                operacije(brojevi, nizOperacija, novaPoz);
                nizOperacija[proslaPoz] = MNOZ;
                operacije(brojevi, nizOperacija, novaPoz);
                nizOperacija[proslaPoz] = DELJ;
                operacije(brojevi, nizOperacija, novaPoz);
            }
        }
    }
}


