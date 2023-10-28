package src.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class InfoToolkit {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Dimension dimension = toolkit.getScreenSize();

    private static int fullWidth = dimension.width;
    private static int fullHeigh = dimension.height;

    public static int getFullWidth(){
        return fullWidth;
    }

    public static int getFullHeight(){
        return fullHeigh;
    }

}
