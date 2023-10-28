package src.views.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import src.utils.UISettings;

public class CustomLabel extends JLabel {
    
    public CustomLabel(String text){
        super();
        this.setText(text);
        this.setFont(UISettings.getLabelFont());
        this.setOpaque(true);
        this.setForeground(UISettings.getBlackColor());
        this.setHorizontalAlignment(CENTER);
        this.setBackground(UISettings.getThinWhiteColor());
    }

    public void setNewFont(Font font){
        this.setFont(font);
    }



}
