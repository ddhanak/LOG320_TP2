package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Walid on 2016-06-01.
 */
public class GrillePuzzle extends JFrame implements Runnable {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private PrincipalPanel pPanel;
    private GridPanel gPanel;
    private MenuPuzzle menuPuzzle;
    private  OptionPanel oPanel;

    public GrillePuzzle(String title) {
        super(title);
    }

    public void init() {
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPuzzle = new MenuPuzzle();
        setJMenuBar(menuPuzzle);
        pPanel = new PrincipalPanel();
        gPanel = new GridPanel();
        oPanel = new OptionPanel();
        pPanel.add(gPanel, BorderLayout.CENTER);
        pPanel.add(oPanel,BorderLayout.SOUTH);
        setContentPane(pPanel);

        this.setVisible(true);
    }

    public void run() {
        init();
    }

}
