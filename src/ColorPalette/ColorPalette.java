package ColorPalette;

import de.ur.mi.oop.app.GraphicsApp;

/**
 * Diese GraphicsApp-Anwendung stellt in jedem Frame ein Gitternetz aus mehreren farbigen Rechtecken dar. Jedes Rechteck
 * verfügt über eine zufällige Farbe. Im inneren des Rechteck wird eine aufsteigende Nummer, beginnen bei "1" angezeigt.
 */

public class ColorPalette extends GraphicsApp {

    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;
    private static final int NUM_OF_SQUARES = 25;
    private static final int SQUARE_SIZE = WIDTH/NUM_OF_SQUARES;

    @Override
    public void initialize() {
        /**
         * Reduziert die Bildwiederholrate auf 1 FPS um die Darstellung besser
         * sichtbar zu machen.
         */
        setFrameRate(1);
    }

    @Override
    public void draw() {
    }

}
