package src.views.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.utils.InfoToolkit;
import java.awt.Container;

public class BaseFrame extends JFrame{
    private Container container;
    public BaseFrame(){
        super();
        container = this.getContentPane();
        this.setLayout(null);
        this.setTitle("Student Grade Calculator");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.stablishDimensions();
    }

    public void stablishDimensions(){
        int width = InfoToolkit.getFullWidth();
        int height = InfoToolkit.getFullHeight();
        this.setBounds(0, 0, width, height);
    }

    public void addPanel(JPanel panel){
        this.add(panel);
    }

    public void cleanFrame(){
        this.container.removeAll();
        this.revalidate();
        this.repaint();
    }

}
