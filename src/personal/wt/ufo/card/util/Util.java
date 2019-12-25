package personal.wt.ufo.card.util;

import java.awt.*;

public class Util {
    public static Dimension getScreenSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();
    }

    public static String getProjectDir(){
        return System.getProperty("user.dir");
    }
}
