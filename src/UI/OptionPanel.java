package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by walid on 2016-06-04.
 */
public class OptionPanel extends JPanel {

    private ButtonListener buttonListener;
    private JButton openButton;
    private JButton solveButton;
    private  JButton solutionButton;

    public OptionPanel(){
           super();
           init();
    }

    public void init(){
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        ButtonListener myLister = new ButtonListener();
        openButton = new JButton("Ouvrir");
        openButton.addActionListener(buttonListener);
        solveButton = new JButton("Résoudre");
        solveButton.addActionListener(buttonListener);
        solutionButton = new JButton("Solutionner");
        solutionButton.addActionListener(buttonListener);
        this.add(openButton);
        this.add(solveButton);
        this.add(solutionButton);

    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == openButton) {

            }
            else if(e.getSource() == solveButton) {

            }
            else if(e.getSource() == solutionButton) {

            }
        }
    }
}
