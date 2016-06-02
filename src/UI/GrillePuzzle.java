package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Walid on 2016-06-01.
 */
public class GrillePuzzle extends JFrame implements Runnable {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private PrincipalPanel pPanel;
    private GridPanel gpanel;
    private MenuPuzzle menuPuzzle;

    public GrillePuzzle(String title) {
        super(title);
    }

    public void init() {
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPuzzle = new MenuPuzzle();
        setJMenuBar(menuPuzzle);
        pPanel = new PrincipalPanel();
        gpanel = new GridPanel();
        pPanel.add(gpanel, BorderLayout.CENTER);
        setContentPane(pPanel);

        this.setVisible(true);
    }

    public void run() {
        init();
    }

}
