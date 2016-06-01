package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("FrameDemo");
        frame.setVisible(true);
        frame.setSize(500,500);

        JButton b1 = new JButton("Charger... 1");
        JButton b2 = new JButton("Résoudre");
        JButton b3 = new JButton("Solution");
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);


    }
}
