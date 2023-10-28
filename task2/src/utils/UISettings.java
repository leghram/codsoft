package src.utils;

import java.awt.Color;
import java.awt.Font;

public class UISettings {
    private static Color primaryColor = new Color(106, 69, 255);
    private static Color secondaryColor = new Color(87,75,255);
    private static Color whiteColor = Color.white;
    private static Color grayClearColor = new Color(97,97,97);
    private static Color grayColor = new Color(76,76,76);
    private static Color blackColor = new Color(49,49,49);
    private static Color thinWhiteColor = new Color(250,250,250);

    private static Font basicFont = new Font("Arial", Font.BOLD, 20);
    private static Font labelFont = new Font("Arial", Font.BOLD, 22);
    private static Font inputFont = new Font("Arial", Font.BOLD, 22);

    public static Color getPrimaryColor(){
        return primaryColor;
    }
    public static Color getSecondaryColor(){
        return secondaryColor;
    }
    public static Color getWhiteColor(){
        return whiteColor;
    }
    public static Color getGrayClearColor(){
        return grayClearColor;
    }
    public static Color getGrayColor(){
        return grayColor;
    }
    public static Color getBlackColor(){
        return blackColor;
    }
    public static Color getThinWhiteColor(){
        return thinWhiteColor;
    }

    public static Font getBasicFont(){
        return basicFont;
    }
    public static Font getLabelFont(){
        return labelFont;
    }
    public static Font getInputFont(){
        return inputFont;
    }




}
