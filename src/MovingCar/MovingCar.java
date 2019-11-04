package MovingCar;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;

/**
 * Diese GraphicsApp-Anwendung stellt ein Auto aus der Top-Down-Perspektive dar, das sich über den Bildschirm bewegt
 * und zufällig seine Richtung ändert.
 */

public class MovingCar extends GraphicsApp {

    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    @Override
    public void initialize() {
        setCanvasSize(WIDTH, HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
    }
}
