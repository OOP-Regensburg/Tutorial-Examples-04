package Collisions;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

/**
 * In diesem Beispiel bewegt sich Ball vom Mittelpunkt bis in die untere rechte Ecke der Zeichenfläche.
 * Beim Erreichen der Ränder wird die Bewegung gestoppt.
 */

public class Collisions extends GraphicsApp {
    /**
     * Konstanten mit wichtigen, unveränderbaren Werten für die Darstellung  Szene.
     */
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int BALL_SPEED = 1;
    /**
     * Graphisches Element für die Darstellung des Balls
     */
    private Circle ball;
    /**
     * Wahrheitswert, der verwendet wird, um die Bewegung des Balls zu steuern. Sobald der Ball die Ränder der Zeichenfläche
     * erreicht hat, wird der Wert auf false (FALSCH) gesetzt.
     */
    private boolean canMove = true;

    @Override
    public void initialize() {
        /**
         * Initialisieren der Zeichenfläche und erstellen des Balls.
         */
        setCanvasSize(WIDTH,HEIGHT);
        ball = new Circle(WIDTH/2, HEIGHT/2, WIDTH/10, Colors.RED);
    }

    @Override
    public void draw() {
        drawBackground(Colors.WHITE);
        /**
         * Wenn der Ball die Ränder noch nicht erreicht hat (canMove == false) ...
         */
        if(canMove == true) {
            /**
             * ... wird die Position des Balls verändert ...
             */
            ball.move(BALL_SPEED, BALL_SPEED);
            /**
             * ... und die resultierende Distanz zwischen Mittelpunkt und Rändern berechnet.
             */
            double xDistance = WIDTH - ball.getXPos();
            double yDistance = WIDTH - ball.getXPos();
            /**
             * Hat der Ball die Ränder erreicht, d.h. ist die Distanz zwischen äußerem Rand des Kreises und den Rändern
             * der Zeichenfläche kleiner oder gleich dem Kreisradius, wird die boolean-Variable canMove auf false
             * gesetzt. Beim nächsten Aufruf der Methode werden die Befehle in dieser if-Bedingung dann nicht mehr
             * ausgeführt.
             */
            if(xDistance <= ball.getRadius() || yDistance <= ball.getRadius()) {
                canMove = false;
            }
        }
        /**
         * Bei jedem Aufruf der Methode wird der Ball an der letzten oder möglicherweise angepassten (ball.move())
         * Position gezeichnet.
         */
        ball.draw();
    }
}
