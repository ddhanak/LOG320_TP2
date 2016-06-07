package UI;
import com.company.GridPuzzle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Walid on 2016-06-01.
 */
public class GridUIPuzzle extends JFrame implements Runnable {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private PrincipalPanel pPanel;
    private GridPanel gPanel;
    private MenuPuzzle menuPuzzle;
    private  OptionPanel oPanel;
    private GridPuzzle gPuzzle;

    public GridUIPuzzle(String title) {
        super(title);
    }

    public void init() {
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pPanel = new PrincipalPanel();
        gPanel = new GridPanel();
        oPanel = new OptionPanel();
        pPanel.add(gPanel, BorderLayout.CENTER);
        pPanel.add(oPanel,BorderLayout.SOUTH);
        setContentPane(pPanel);
        menuPuzzle = new MenuPuzzle(gPanel);
        setJMenuBar(menuPuzzle);

        this.setVisible(true);
    }

    public void run() {
        init();
    }

}
