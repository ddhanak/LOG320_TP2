package UI;

import com.company.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by Walid on 2016-06-01.
 */
public class MenuPuzzle extends JMenuBar implements ActionListener {
    private JMenu menu;
    private JMenu solver;
    private JMenuItem openFile;
    private GridPanel gPanel;


 public MenuPuzzle(GridPanel gPanel) {
     super();
     initMenu();
     this.gPanel = gPanel;
 }


  public void initMenu() {
      menu = new JMenu("File");
      this.add(menu);
      solver = new JMenu("Puzzle Solver");
      this.add(solver);
      openFile = new JMenuItem("Open File");
      openFile.addActionListener(this);
      menu.add(openFile);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openFile) {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    gPanel.initGrid(FileHelper.getPuzzleFromFile(fc.getSelectedFile().getAbsolutePath()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}
