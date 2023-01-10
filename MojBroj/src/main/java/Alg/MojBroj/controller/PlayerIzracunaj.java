package Alg.MojBroj.controller;

import Alg.MojBroj.model.Database;
import Alg.MojBroj.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class PlayerIzracunaj implements ActionListener {

    MainFrame frame;



    public PlayerIzracunaj(MainFrame m){

        this.frame = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = Database.getInstance().getResenjeString()+" = " + izracunaj(Database.getInstance().getResenjeString());
        frame.getPlayerResenje().setText(s);

    }

    public static double izracunaj(String input) {
        Stack<Double> vrednosti = new Stack<>();
        Stack<Character> operacije = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') continue;
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operacije.isEmpty() && hasPrecedence(c, operacije.peek()))
                    vrednosti.push(izracunaj(operacije.pop(), vrednosti.pop(), vrednosti.pop()));
                operacije.push(c);
            } else if (c == '(') {
                operacije.push(c);
            } else if (c == ')') {
                while (operacije.peek() != '(')
                    vrednosti.push(izracunaj(operacije.pop(), vrednosti.pop(), vrednosti.pop()));
                operacije.pop();
            } else {
                StringBuilder sbuf = new StringBuilder();
                while (i < input.length() && Character.isDigit(input.charAt(i)))
                    sbuf.append(input.charAt(i++));
                --i;
                vrednosti.push(Double.parseDouble(sbuf.toString()));
            }
        }

        while (!operacije.isEmpty())
            vrednosti.push(izracunaj(operacije.pop(), vrednosti.pop(), vrednosti.pop()));

        return vrednosti.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    public static double izracunaj(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Deljenje sa 0 - ERROR");
                return a / b;
        }
        return 0;
    }

}
