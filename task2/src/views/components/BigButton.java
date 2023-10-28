package src.views.components;
  
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

import src.utils.UISettings;

public class BigButton  extends JButton{

    int posX=0, posY=0, width =300, heigh=80, sizeLetter = 40;

    public BigButton(String title ){
        super(title);
        this.setText(title);
        this.setBounds(posX, posY, width,heigh);
        this.setFont(UISettings.getBasicFont());
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setForeground(UISettings.getWhiteColor());
    }


    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(UISettings.getSecondaryColor());
        } else {
            g.setColor(UISettings.getPrimaryColor());
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }


    public void setNewFont(Font font){
        this.setFont(font);
    }

    public void setNewLocation(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.setBounds(posX, posY, 300,80);
    }

    public void setNewSize(int width, int heigh){
        this.width = width;
        this.heigh = heigh;
        this.setBounds(posX, posY, width,heigh);
    }

}


