//package UI;
//
//import com.company.Board;
//import com.company.Position;
//import com.company.PuzzleSolver;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
///**
// * Created by Walid on 2016-06-01.
// */
//public class GridPanel extends JPanel implements Observer{
//
//    private Board gPuzzle;
//    private PuzzleSolver pSolver;
//    private BoxSquareGrid[][] myButtons;
//    private static  int cnt = 0;
//    public static List<Position> myPositions;
//
//    public GridPanel(Board gPuzzle, PuzzleSolver pSolver){
//        super();
//        this.gPuzzle = gPuzzle;
//        this.pSolver = pSolver;
//        myPositions = new ArrayList<Position>();
//        firstInitGrid();
//    }
//
//    public void firstInitGrid() {
//        myButtons = new BoxSquareGrid[gPuzzle.getLength()][gPuzzle.getLength()];
//        setLayout(new GridLayout(gPuzzle.getLength(),gPuzzle.getLength()));
//        this.setBackground(Color.LIGHT_GRAY);
//
//        for(int i=0; i<gPuzzle.getLength();i++) {
//            for(int j=0; j<gPuzzle.getLength();j++) {
//
//                myButtons[i][j] = new BoxSquareGrid("0",i,j,Color.GRAY);
//                myButtons[i][j].initButton();
//                this.add(myButtons[i][j]);
//
//                if(gPuzzle.getCase(i,j) == 0){
//                    myButtons[i][j].setVisible(false);
//                }
//                else if(gPuzzle.getCase(i,j) == 2) {
//                    myButtons[i][j].setColor(Color.BLACK);
//                }
//            }
//        }
//
//    }
//    public void initGrid(int[][] puzzle) {
//
//        gPuzzle.deleteObservers();
//        gPuzzle = new Board(puzzle);
//        updateObserver();
//        updateGrid();
//    }
//
//    private void updateGrid(){
//
//
//        for(int i = 0 ; i<gPuzzle.getLength();i++){
//
//            for(int j = 0; j<gPuzzle.getLength();j++){
//
//                myButtons[i][j].setColor(Color.GRAY);
//                if(gPuzzle.getCase(i,j) == 0){
//                    myButtons[i][j].setVisible(false);
//                }
//                else if(gPuzzle.getCase(i,j) == 2) {
//                    myButtons[i][j].setColor(Color.BLACK);
//                }
//
//            }
//
//
//        }
//
//
//        validate();
//        repaint();
//    }
//
//    private void updateGridP(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(Position element : myPositions) {
//                    if(element.V == 1) {
//                        myButtons[element.X][element.Y].setColor(Color.GRAY);
//                    }
//                    else if(element.V == 2) {
//                        myButtons[element.X][element.Y].setColor(Color.BLACK);
//                    }
//                    System.out.println("(X,Y,V) = " + element.X+","+element.Y+","+element.V);
//                    validate();
//                    repaint();
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//    }
//    public void solve(){
//        if (pSolver.solvePuzzle() && OptionPanel.slow) {
//            updateGridP();
//        }
//    }
//
//
//    private void updateObserver(){
//        gPuzzle.addObserver(this);
//    }
//
//    private void init(){
//        GridBagLayout layout = new GridBagLayout();
//        this.setLayout(layout);
//        this.setBackground(Color.GRAY);
//    }
//
//
//    @Override
//    public void update(Observable o, Object arg) {
//
//
//            Position p = (Position) arg;
//           myPositions.add(new Position(p.X,p.Y,gPuzzle.getCase(p.X,p.Y)));
//           if(!OptionPanel.slow) {
//            if (gPuzzle.getCase(p.X, p.Y) == 1) {
//                myButtons[p.X][p.Y].setColor(Color.GRAY);
//            }
//            if (gPuzzle.getCase(p.X, p.Y) == 2) {
//                myButtons[p.X][p.Y].setColor(Color.BLACK);
//            }
//
//            validate();
//            repaint();
//        }
//        // System.out.print(cnt++);
//        //  updateGrid();
//    }
//}
