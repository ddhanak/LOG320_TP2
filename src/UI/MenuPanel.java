package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Walid on 2016-06-02.
 */
public class MenuPanel extends JPanel {

    public MenuPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.red);
        JButton myB = new JButton("Allo");
        this.add(myB);
        JButton myB2 = new JButton("TEXT");
        this.add(myB2);


    }
}
