package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Walid on 2016-06-01.
 */
public class MenuPuzzle extends JMenuBar implements ActionListener {
    private JMenu menu;
    private JMenu solver;
    private JMenuItem openFile;


 public MenuPuzzle() {
     super();
     initMenu();
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
                File selected = fc.getSelectedFile();
            }
        }
    }
}
