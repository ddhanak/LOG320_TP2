package UI;

import javax.swing.*;

/**
 * Created by Walid on 2016-06-01.
 */
public class MenuPuzzle extends JMenuBar {
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
      menu.add(openFile);
  }
}
