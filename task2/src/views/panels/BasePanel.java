package src.views.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BasePanel extends JPanel {
    public BasePanel(){
        super();
        setLayout(null);
    }

    public void addPanel(JPanel panel){
        this.add(panel);
    }

    public void addButton(JButton button){
        this.add(button);
    }

    public void addLabel(JLabel label){
        this.add(label);
    }

    public void addInput(JTextField input){
        this.add(input);
    }
}
