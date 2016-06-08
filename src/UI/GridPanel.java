package UI;

import com.company.GridPuzzle;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Walid on 2016-06-01.
 */
public class GridPanel extends JPanel implements Observer{

    private GridPuzzle gPuzzle;
    private BoxSquareGrid[][] myButtons;

    public GridPanel(GridPuzzle gPuzzle){
        super();
        this.gPuzzle = gPuzzle;
        firstInitGrid();
    }

    public void firstInitGrid() {
        myButtons = new BoxSquareGrid[GridPuzzle.MAXIMUM][GridPuzzle.MAXIMUM];
        setLayout(new GridLayout(GridPuzzle.MAXIMUM,GridPuzzle.MAXIMUM));
        this.setBackground(Color.LIGHT_GRAY);

        for(int i=0; i<GridPuzzle.MAXIMUM;i++) {
            for(int j=0; j<GridPuzzle.MAXIMUM;j++) {

                    myButtons[i][j] = new BoxSquareGrid("0",i,j,Color.GRAY);
                    myButtons[i][j].initButton();
                    this.add(myButtons[i][j]);

                    if(gPuzzle.getCase(i,j) == 0){
                        myButtons[i][j].setVisible(false);
                    }
                    else if(gPuzzle.getCase(i,j) == 2) {
                        myButtons[i][j].setColor(Color.BLACK);
                    }
            }
        }

    }
    public void initGrid(int[][] puzzle) {

        gPuzzle.deleteObservers();
        gPuzzle = new GridPuzzle(puzzle);
        updateObserver();
        updateGrid();
    }

    private void updateGrid(){

        for(int i = 0 ; i<GridPuzzle.MAXIMUM;i++){

            for(int j = 0; j<GridPuzzle.MAXIMUM;j++){

                    myButtons[i][j].setColor(Color.GRAY);
                    if(gPuzzle.getCase(i,j) == 0){
                        myButtons[i][j].setVisible(false);
                    }
                    else if(gPuzzle.getCase(i,j) == 2) {
                        myButtons[i][j].setColor(Color.BLACK);
                    }

            }
        }
        validate();
        repaint();
    }
    private void updateObserver(){
        gPuzzle.addObserver(this);
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
