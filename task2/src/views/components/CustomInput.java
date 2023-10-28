package src.views.components;

import javax.swing.JTextField;

import src.utils.UISettings;

public class CustomInput extends JTextField{
    
    public CustomInput(){
        super();
        this.setFont(UISettings.getInputFont());
        this.setForeground(UISettings.getBlackColor());
    }

}
