package Alg.MojBroj.view;

import Alg.MojBroj.controller.*;
import Alg.MojBroj.model.Database;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private JButton stop;
    Database db;
    JPanel panel = new JPanel();
    private JTextField resenje;
    private JTextField playerResenje;
    private JTextField botResenje;

    private JButton trazenibroj;
    private JToggleButton broj1;
    private JToggleButton broj2;
    private JToggleButton broj3;
    private JToggleButton broj4;
    private JToggleButton broj5;
    private JToggleButton broj6;

    private JButton sabiranje;

    private JButton oduzimanje;

    private JButton mnozenje;

    private JButton deljenje;
    private JButton obrisi;
    private JButton zapocniPonovo;
    private JButton otvorenaZagrada;
    private JButton zatvorenaZagrada;

    private JButton racunaj;
    private JButton resenjeBtn;

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }
    public void setInstance(){
        instance = null;
    }

    private MainFrame(){

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        db = new Database();
        setSize(screenWidth / 3, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MojBroj");

        panel = new JPanel();
        stop = new JButton("Stop");
        stop.addActionListener(new Stop(this));
        obrisi = new JButton("Obrisi");
        obrisi.addActionListener(new Obrisi(this));
        zapocniPonovo = new JButton("Restart");
        zapocniPonovo.addActionListener(new Restart(this));


        resenje = new JTextField();
        resenje.setEditable(false);
        resenje.setHorizontalAlignment(JTextField.CENTER);
        playerResenje = new JTextField();
        botResenje = new JTextField();
        playerResenje.setEditable(false);
        playerResenje.setHorizontalAlignment(JTextField.CENTER);
        botResenje.setEditable(false);
        botResenje.setHorizontalAlignment(JTextField.CENTER);

        broj1 = new JToggleButton("0");
        broj1.addActionListener(new KlikBroj(this,broj1));
        broj2 = new JToggleButton("0");
        broj2.addActionListener(new KlikBroj(this,broj2));
        broj3 = new JToggleButton("0");
        broj3.addActionListener(new KlikBroj(this,broj3));
        broj4 = new JToggleButton("0");
        broj4.addActionListener(new KlikBroj(this,broj4));
        broj5 = new JToggleButton("0");
        broj5.addActionListener(new KlikBroj(this,broj5));
        broj6 = new JToggleButton("0");
        broj6.addActionListener(new KlikBroj(this,broj6));
        trazenibroj = new JButton("0");
        trazenibroj.setEnabled(false);

        racunaj = new JButton("Racunaj");
        racunaj.addActionListener(new PlayerIzracunaj(this));
        resenjeBtn = new JButton("Resenje");
        resenjeBtn.addActionListener(new BotIzracunaj(this));

        sabiranje = new JButton(new ImageIcon(getClass().getClassLoader().getResource("plus.png")));
        oduzimanje = new JButton(new ImageIcon(getClass().getClassLoader().getResource("minus.png")));
        mnozenje = new JButton(new ImageIcon(getClass().getClassLoader().getResource("multiply.png")));
        deljenje = new JButton(new ImageIcon(getClass().getClassLoader().getResource("divide.png")));

        otvorenaZagrada = new JButton(new ImageIcon(getClass().getClassLoader().getResource("open-bracket.png")));
        zatvorenaZagrada = new JButton(new ImageIcon(getClass().getClassLoader().getResource("close-bracket.png")));

        otvorenaZagrada.addActionListener(new KlikZagrada(this, otvorenaZagrada,"("));
        zatvorenaZagrada.addActionListener(new KlikZagrada(this, zatvorenaZagrada,")"));

        sabiranje.addActionListener(new KlikOperacije(this, sabiranje,"+"));
        oduzimanje.addActionListener(new KlikOperacije(this, oduzimanje,"-"));
        mnozenje.addActionListener(new KlikOperacije(this, mnozenje,"*"));
        deljenje.addActionListener(new KlikOperacije(this, deljenje,"/"));

        panel.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));
        panel.setLayout(new GridBagLayout());

        PostaviDugmice();
        add(panel, BorderLayout.NORTH);

    }

    private void PostaviDugmice(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,5,5);
        //panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(trazenibroj,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(zapocniPonovo,gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(new JLabel("Trazeni Broj: "),gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(broj1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(broj2,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(broj3,gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(broj4,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.weightx= 2;
        panel.add(broj5,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weightx = 2;
        panel.add(broj6,gbc);

        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panel.add(stop,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        panel.add(resenje,gbc);

        gbc.gridx = 7;
        gbc.gridy = 3;
        panel.add(obrisi,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(sabiranje,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(oduzimanje,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(mnozenje,gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(deljenje,gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(otvorenaZagrada,gbc);

        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(zatvorenaZagrada,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 7;
        panel.add(playerResenje,gbc);

        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(racunaj,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 7;
        panel.add(botResenje,gbc);

        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(resenjeBtn,gbc);
    }

}
