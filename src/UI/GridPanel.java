package UI;

import com.company.GridPuzzle;
import com.company.Position;
import com.company.PuzzleSolver;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Walid on 2016-06-01.
 */
public class GridPanel extends JPanel implements Observer{

    private GridPuzzle gPuzzle;
    private PuzzleSolver pSolver;
    private BoxSquareGrid[][] myButtons;
    private static  int cnt = 0;
    public static List<Position> myPositions;

    public GridPanel(GridPuzzle gPuzzle, PuzzleSolver pSolver){
        super();
        this.gPuzzle = gPuzzle;
        this.pSolver = pSolver;
        myPositions = new ArrayList<Position>();
        firstInitGrid();
    }

    public void firstInitGrid() {
        myButtons = new BoxSquareGrid[gPuzzle.getLength()][gPuzzle.getLength()];
        setLayout(new GridLayout(gPuzzle.getLength(),gPuzzle.getLength()));
        this.setBackground(Color.LIGHT_GRAY);

        for(int i=0; i<gPuzzle.getLength();i++) {
            for(int j=0; j<gPuzzle.getLength();j++) {

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


        for(int i = 0 ; i<gPuzzle.getLength();i++){

            for(int j = 0; j<gPuzzle.getLength();j++){

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

    private void updateGridP(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Position element : myPositions) {
                    if(element.v == 1) {
                        myButtons[element.x][element.y].setColor(Color.GRAY);
                    }
                    else if(element.v == 2) {
                        myButtons[element.x][element.y].setColor(Color.BLACK);
                    }
                    System.out.println("(X,Y,V) = " + element.x +","+element.y +","+element.v);
                    validate();
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    public void solve(){
        if (pSolver.solvePuzzle() && OptionPanel.slow) {
            updateGridP();
        }
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


            Position p = (Position) arg;
           myPositions.add(new Position(p.x,p.y,gPuzzle.getCase(p.x,p.y)));
           if(!OptionPanel.slow) {
            if (gPuzzle.getCase(p.x, p.y) == 1) {
                myButtons[p.x][p.y].setColor(Color.GRAY);
            }
            if (gPuzzle.getCase(p.x, p.y) == 2) {
                myButtons[p.x][p.y].setColor(Color.BLACK);
            }

            validate();
            repaint();
        }
        // System.out.print(cnt++);
        //  updateGrid();
    }
}
