package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Walid on 2016-06-01.
 */
public class GridPanel extends JPanel {



    public GridPanel(){
      init();
        for(int line = 0 ; line<7;line++){

            for(int colonne = 0; colonne<7;colonne++){
                if((line == 0 || line ==1 || line ==5 || line ==6) && (colonne== 0 || colonne ==1 ||colonne==5||colonne==6)) {
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
                    if(line ==3 && colonne == 3) {
                        button.setBackground(Color.BLACK);
                    }
                    this.add(button, gbc);
                }

            }



        }
    }
    public void init(){
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.GRAY);
    }


}
