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

    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final int DIRECTION_NORTH = 0;
    private static final int DIRECTION_EAST = 1;
    private static final int DIRECTION_SOUTH = 2;
    private static final int DIRECTION_WEST = 3;
    private static final Color CAR_BODY_COLOR = Colors.BLACK;
    private static final Color CAR_WINDSHIELD_COLOR = Colors.WHITE;
    private static final int CAR_WINDSHIELD_BORDER = 2;
    private static final int CAR_WIDTH = 20;
    private static final int CAR_LENGTH = 50;
    private static final int CAR_SPEED = 1;
    private static final double TURN_PROBABILITY = 0.05;


    private int xPosition;
    private int yPosition;
    private int currentDirection;
    private Random rand = new Random();

    @Override
    public void initialize() {
        setCanvasSize(WIDTH, HEIGHT);
        xPosition = WIDTH / 2;
        yPosition = HEIGHT / 2;
        currentDirection = DIRECTION_NORTH;
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        updateCar();
        drawCar();
    }

    private void updateCar() {
        tryToUpdateDirection();
        updatePosition();
    }

    private void tryToUpdateDirection() {
        double randomSeed = rand.nextInt(100) / 100.0;
        if (randomSeed < TURN_PROBABILITY) {
            currentDirection++;
            if (currentDirection > DIRECTION_WEST) {
                currentDirection = DIRECTION_NORTH;
            }
        }
    }

    private void updatePosition() {
        switch (currentDirection) {
            case DIRECTION_NORTH:
                yPosition -= CAR_SPEED;
                break;
            case DIRECTION_EAST:
                xPosition += CAR_SPEED;
                break;
            case DIRECTION_SOUTH:
                yPosition += CAR_SPEED;
                break;
            case DIRECTION_WEST:
                xPosition -= CAR_SPEED;
                break;
        }
    }

    private void drawCar() {
        drawCarBody();
        drawWindShield();
    }

    private void drawCarBody() {
        int carWidth = CAR_WIDTH;
        int carHeight = CAR_LENGTH;
        if (currentDirection == DIRECTION_EAST || currentDirection == DIRECTION_WEST) {
            carWidth = CAR_LENGTH;
            carHeight = CAR_WIDTH;
        }
        Rectangle car = new Rectangle(xPosition, yPosition, carWidth, carHeight, CAR_BODY_COLOR);
        car.draw();
    }

    private void drawWindShield() {
        int windShieldSize = CAR_WIDTH - 2 * CAR_WINDSHIELD_BORDER;
        Rectangle windshield = new Rectangle(0, 0, windShieldSize, windShieldSize, CAR_WINDSHIELD_COLOR);
        int windshieldXPosition = xPosition + CAR_WINDSHIELD_BORDER;
        int windshieldYPosition = yPosition + CAR_WINDSHIELD_BORDER;
        switch (currentDirection) {
            case DIRECTION_NORTH:
                break;
            case DIRECTION_EAST:
                windshieldXPosition = xPosition + CAR_LENGTH - CAR_WINDSHIELD_BORDER - windShieldSize;
                break;
            case DIRECTION_SOUTH:
                windshieldYPosition = yPosition + CAR_LENGTH - CAR_WINDSHIELD_BORDER - windShieldSize;
                break;
            case DIRECTION_WEST:
                break;
        }
        windshield.setPosition(windshieldXPosition, windshieldYPosition);
        windshield.draw();
    }
}
