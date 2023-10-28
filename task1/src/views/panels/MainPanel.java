package src.views.panels;

import java.awt.Color;

import src.utils.InfoToolkit;


public class MainPanel extends BasePanel {    

    public MainPanel(){
    }

    public void setFullScreen(){
        int width = InfoToolkit.getFullWidth();
        int heigh = InfoToolkit.getFullHeight();
        this.setBounds(0,0,width,heigh);
    }

    public void setBackgroundColor(Color color){
        this.setBackground(color);
    }

    
}
