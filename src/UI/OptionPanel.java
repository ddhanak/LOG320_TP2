//package UI;
//
//import com.company.FileHelper;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
///**
// * Created by walid on 2016-06-04.
// */
//public class OptionPanel extends JPanel {
//
//    private JButton openButton;
//    private JButton solveButton;
//    private  JButton slowButton;
//    private GridPanel gPanel;
//    public static  boolean slow = false;
//
//    public OptionPanel(GridPanel gPanel){
//           super();
//           init();
//           this.gPanel = gPanel;
//    }
//
//    public void init(){
//        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//        ButtonListener myListener = new ButtonListener();
//        openButton = new JButton("Ouvrir");
//        openButton.addActionListener(myListener);
//        solveButton = new JButton("Résoudre");
//        solveButton.addActionListener(myListener);
//        slowButton = new JButton("Mode ralenti");
//        slowButton.addActionListener(myListener);
//        this.add(openButton);
//        this.add(solveButton);
//        this.add(slowButton);
//
//    }
//
//    private class ButtonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource() == openButton) {
//                final JFileChooser fc = new JFileChooser();
//                int returnVal = fc.showOpenDialog(null);
//                if(returnVal == JFileChooser.APPROVE_OPTION) {
//                    try {
//                        gPanel.initGrid(FileHelper.getPuzzleFromFile(fc.getSelectedFile().getAbsolutePath()));
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//
//                }
//            }
//            else if(e.getSource() == solveButton) {
//                    slow = false;
//                    gPanel.solve();
//
//            }
//            else if(e.getSource() == slowButton) {
//                    slow = true;
//                    gPanel.solve();
//
//            }
//        }
//    }
//}
