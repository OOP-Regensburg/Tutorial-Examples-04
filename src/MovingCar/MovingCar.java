package MovingCar;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.Random;

/**
 * Diese GraphicsApp-Anwendung stellt ein Auto aus der Top-Down-Perspektive dar, das sich über den Bildschirm bewegt
 * und zufällig seine Richtung ändert.
 */

public class MovingCar extends GraphicsApp {

    /**
     * Konstanten mit wichtigen, unveränderbaren Werten für die Darstellung des Autos und der übrigen Szene.
     */
    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final Color CAR_BODY_COLOR = Colors.BLACK;
    private static final Color CAR_WINDSHIELD_COLOR = Colors.WHITE;
    private static final int CAR_WINDSHIELD_BORDER = 2;
    private static final int CAR_WIDTH = 20;
    private static final int CAR_LENGTH = 50;
    private static final int CAR_SPEED = 1;
    /**
     * Numerische Werte für die Repräsentation der möglichen Bewegungsrichtungen des Autos. Durch die Beschreibung
     * der Richtung als Zahl sind imm Programm einfache  Richtungsprüfungen und - wechsel möglich.
     */
    private static final int DIRECTION_NORTH = 0;
    private static final int DIRECTION_EAST = 1;
    private static final int DIRECTION_SOUTH = 2;
    private static final int DIRECTION_WEST = 3;
    /**
     * Wahrscheinlichkeit, mit der in jedem Frame ein Wechsel der Bewegungsrichtung statt findet.
     */
    private static final double TURN_PROBABILITY = 0.05;
    /**
     * Aktuelle Position und Bewegungsrichtung des Autos.
     */
    private int xPosition;
    private int yPosition;
    private int currentDirection;
    /**
     * GraphicsObjects zum Zeichnen der Bestandteile des Autos.
     */
    private Rectangle car;
    private Rectangle windshield;
    /**
     * Zufallsgenerator für die Berechung des Richtungsänderungen.
     */
    private Random rand = new Random();

    @Override
    public void initialize() {
        /**
         * Hier werden Größe der Zeichenfläche sowie Position und Bewegungsrichtung des Autos initial festgelegt.
         */
        setCanvasSize(WIDTH, HEIGHT);
        xPosition = WIDTH / 2;
        yPosition = HEIGHT / 2;
        currentDirection = DIRECTION_NORTH;
        /**
         * Hier werden die graphischen Elemente (Rechtecke) zur Darstellung des Autos initialisiert.
         */
        car = new Rectangle(xPosition, yPosition, CAR_WIDTH, CAR_LENGTH, CAR_BODY_COLOR);
        int windShieldSize = CAR_WIDTH - 2 * CAR_WINDSHIELD_BORDER;
        windshield = new Rectangle(0, 0, windShieldSize, windShieldSize, CAR_WINDSHIELD_COLOR);
    }

    @Override
    public void draw() {
        /**
         * Bei jedem Aufruf der draw-Methode wird:
         *  - der Hintergrund neu gezeichnet
         *  - die Bewegungsrichtung, Position und Ausrichtung des Autos angepasst
         *  - das Auto gezeichnet
         */
        drawBackground(BACKGROUND_COLOR);
        updateCar();
        drawCar();
    }

    private void updateCar() {
        /**
         * Prüft anhand einer Zufallszahl, ob eine Bewegungsänderung vorgenommen werden soll und passt ggf. den Wert der
         * Instanzvariable "currentDirection" an.
         */
        tryToUpdateDirection();
        /**
         * Aktualisiert die Position des Autos auf Basis der aktuellen Bewegungsrichtung.
         */
        updatePosition();
    }

    private void tryToUpdateDirection() {
        /**
         * rand.nextInt(100) gibt eine uufällige Ganzzahl zwischen 0 (inklusive) und 100 (exklusive), also zwischen 0
         * und 99, zurück. Die Zahl wird durch den double-Wert 100.0 geteilt.
         */
        double randomSeed = rand.nextInt(100) / 100.0;
        /**
         * Die angepasste Zufallszahl wird mit der in TURN_PROBABILITY gespeicherten Wahrscheinlichkeit verglichen. Liegt
         * der Wert darunter, findet eine Bewegungsänderung statt.
         */
        if (randomSeed < TURN_PROBABILITY) {
            /**
             * Die Bewegungsrichtungen werden - in der Reihenfolge Nord, Ost, Süd, West - durch aufsteigende Ganzzahlen
             * repräsentiert (0 = Norden, 1 = Osten, 2 = Süden, 3 = Westen). Bewegungsänderungen erfolgen immer im
             * Uhrzeigersinn. Dies ist einfach durch die Inkrementierung des aktuellen Werts bzw. der aktuellen
             * Richtung (gespeichert in currentDirection) möglich. Nach jeder Änderung wird geprüft, ob der aktuelle
             * Wert außerhalb des Wertebereichs 0 bis 3 liegt. Dies ist der Fall, wenn die letzte Richtung "West" bzw.
             * DIRECTION_WEST war. In diesem Fall wird die aktuelle Richtung auf "Norden" bzw. DIRECTION_NORTH gesetzt.
             */
            currentDirection++;
            if (currentDirection > DIRECTION_WEST) {
                currentDirection = DIRECTION_NORTH;
            }
        }
    }

    private void updatePosition() {
        /**
         * In Abhängigkeit der aktuellen Bewedungsrichtung werden x- bzw. y-Position des Autos angepasst. Die
         * Geschwindkeit in Pixel per Frame wird der Konstanten CAR_SPEED entnommen. Da der Ursprung des Koordinaten-
         * systems in der oberen, linken Ecke liegt, werden Bewegungen nach OST und SÜD durch positive und Bewegungen
         * nach WEST und Nord durch negative Größenänderungen dargestellt.
         */
        if(currentDirection == DIRECTION_NORTH) {
            yPosition -= CAR_SPEED;
        }
        if(currentDirection == DIRECTION_EAST) {
            xPosition += CAR_SPEED;
        }
        if(currentDirection == DIRECTION_SOUTH) {
            yPosition += CAR_SPEED;
        }
        if(currentDirection == DIRECTION_WEST) {
            xPosition -= CAR_SPEED;
        }
        /**
         * Die Position des Rechtecks, das das Auto repräsentiert, wird mit Hilfe der neuen Positionswerte aktualisiert.
         */
        car.setPosition(xPosition, yPosition);
    }

    private void drawCar() {
        drawCarBody();
        drawWindShield();
    }

    private void drawCarBody() {
        /**
         * In Abhängigkeit der aktuellen Bewegungsrichtung werden Länge und Breite des Autos ausgewählt.
         */
        int carWidth = CAR_WIDTH;
        int carHeight = CAR_LENGTH;
        if (currentDirection == DIRECTION_EAST || currentDirection == DIRECTION_WEST) {
            carWidth = CAR_LENGTH;
            carHeight = CAR_WIDTH;
        }
        /**
         * Die berechneten Werte werden für die Anpassung des Rechtecks verwendet, welches anschließend gezeichnet wird.
         */
        car.setWidth(carWidth);
        car.setHeight(carHeight);
        car.draw();
    }

    private void drawWindShield() {
        /**
         * Die Windschutzscheibe soll, mit leichtem Versatz (CAR_WINDSHIELD_BORDER) an der aktuellen Position des Autos
         * eingezeichnet werden.
         */
        int windshieldXPosition = xPosition + CAR_WINDSHIELD_BORDER;
        int windshieldYPosition = yPosition + CAR_WINDSHIELD_BORDER;
        /**
         * Fährt das Auto aktuell nach Osten oder Süden, muss die Position der Windschutzscheibe angepasst werden. Die
         * Position wird auf Basis der aktuellen x- bzw. y-Koordinate des Autos, dessen Länge, dem Abstand der Wind-
         * schutzscheibe zum Auto und der Größe der Scheibe berechnet.
         */
        if(currentDirection == DIRECTION_EAST) {
            windshieldXPosition = (int) (xPosition + CAR_LENGTH - CAR_WINDSHIELD_BORDER - windshield.getWidth());
        }
        if(currentDirection == DIRECTION_SOUTH) {
            windshieldYPosition = (int) (yPosition + CAR_LENGTH - CAR_WINDSHIELD_BORDER - windshield.getWidth());
        }
        /**
         * Die berechneten Werte werden für die Anpassung des Rechtecks verwendet, welches anschließend gezeichnet wird.
         */
        windshield.setPosition(windshieldXPosition, windshieldYPosition);
        windshield.draw();
    }
}
