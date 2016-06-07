package UI;

import com.company.GridPuzzle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Walid on 2016-06-01.
 */
public class GridPanel extends JPanel implements Observer{

    private GridPuzzle gPuzzle;

    public GridPanel(GridPuzzle gPuzzle){
        super();
        this.gPuzzle = gPuzzle;
        init();
    }

    public void initGrid(int[][] puzzle) {

        gPuzzle.deleteObservers();
        gPuzzle = new GridPuzzle(puzzle);
        update
        for(int line = 0 ; line<puzzle.length;line++){

            for(int colonne = 0; colonne<puzzle.length;colonne++){
                if(puzzle[line][colonne] == 0){
                    continue;
                }
                else {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = colonne;
                    gbc.gridy = line;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    JButton button = new JButton(String.valueOf("0"));
                    button.setFont(new Font(getName(), Font.BOLD, 24));
                    button.setEnabled(false);
                    if(puzzle[line][colonne] == 2) {
                        button.setBackground(Color.BLACK);
                    }
                    this.add(button, gbc);
                }
            }
        }

    }

    private void updateObserver(){
        gPuzzle.addObserver();
    }

    private void init(){
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.GRAY);
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
