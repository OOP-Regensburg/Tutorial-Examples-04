package ColorPalette;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

/**
 * Diese GraphicsApp-Anwendung stellt in jedem Frame ein Gitternetz aus mehreren farbigen Rechtecken dar. Jedes Rechteck
 * verfügt über eine zufällige Farbe. Im inneren des Rechteck wird eine aufsteigende Nummer, beginnend bei "1" angezeigt.
 */

public class ColorPalette extends GraphicsApp {

    /**
     * Konstanten mit wichtigen, unveränderbaren Werten für die Darstellung der Szene. Die Größe der farbigen Rechtecke
     * (Quadrate) wird beim Start des Programms automatisch aus der Größe der Zeichenfläche und der Anzahl der darzu-
     * stellenden Quadrate berechnet.
     */
    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final Color LABEL_COLOR= Colors.WHITE;
    private static final int NUM_OF_SQUARES = 25;
    private static final int SQUARE_SIZE = (int) (WIDTH / Math.sqrt(NUM_OF_SQUARES));

    @Override
    public void initialize() {
        /**
         * Reduziert die Bildwiederholrate auf 1 FPS um die Darstellung besser
         * sichtbar zu machen und stellt die Größe der Zeichenfläche korrekt ein.
         */
        setFrameRate(1);
        setCanvasSize(WIDTH, HEIGHT);
    }

    @Override
    public void draw() {
        /**
         * In jedem Frame wird zu Beginn die Hintergrundfarbe gesetzt.
         */
        drawBackground(BACKGROUND_COLOR);
        /**
         * Mittels Schleife werden alle notwendigen Rechtecke erstellt, gezeichnet und mit einem Label versehen. Dazu
         * wird über den vollständigen Positionsbereich (0 bis 24) iteriert (es gibt 25 Rechtecke) und für jede Position
         * ein passendes Rechteck erstellt.
         */
        for (int i = 0; i < NUM_OF_SQUARES; i++) {
            /**
             * Die Konstruktion (nicht das Zeichnen) der Rechtecke wird in eine generische Methode ausgelagert,
             * die als Parameter die Position (hier 0 bis 24) übergeben bekommt und ein Rechteck zurückgibt, dessen
             * x- und y-Position auf Basis dieser Position berechnet wurde. Die Farbe des zurückgegebene Rechtecks ist
             * zufällig gewählt.
             */
            Rectangle rect = getRectangleForPosition(i);
            /**
             * Das in der Variable rect gespeicherte Rechteck wird gezeichnet.
             */
            rect.draw();
            /**
             * Im Anschluss wird die Positionsnummer (zur "schöneren" Darstellung hier nicht mehr 0-indexiert) über  das
             * soeben gezeichnete Rechteck gemalt.
             */
            drawLabelForRectangle(i+1, rect);
        }
    }

    /**
     * Diese Methode rechnet die übergebene Positon (0 bis 24) in ein passendes Koordinatenpaar für die Rechtecke im
     * Gitternetz um, berücksichtigt dabei die "Zeilenumbrüche", generiert eine Zufallsfarbe und gibt das auf Basis dieser
     * Werte erstellte Recheck als Ergebnis (Datentyp Rectangle) zurück.
     */
    private Rectangle getRectangleForPosition(int position) {
        /**
         * Aus der Breite der Zeichenfläche und der Größe eines einzelnen Rechtecks wird die Anzahl der Rechecke pro
         * Zeile berechnet.
         */
        int squaresPerRow = WIDTH / SQUARE_SIZE;
        /**
         * Zur Berechnung der x-Position wird der Modulo-Operator verwendet: Durch die Modulo-Division (Rest) der aktuellen
         * Position durch die Anzahl der Rechtecke pro Zeile ergibt sich die Position des aktuellen Rechtecks innerhalb
         * der Zeile (0 bis 4). Durch die anschließende Multiplikation mit SQUARE_SIZE wird die korrekte Pixel-Position
         * berechnet.
         */
        float xPosition = (position % squaresPerRow) * SQUARE_SIZE;
        /**
         * Zur Berechnung der y-Position wird die Division ohne Rest verwendet: Der Quotient aus aktueller Position und
         * Anzahl der Rechtecke pro Zeile entspricht der "Zeile", in die das aktuelle Rechteck gezeichnet werden soll
         * 0 bis). Durch die anschließende Multiplikation mit SQUARE-SIZE wird die korrekte Pixel-Position berechnet.
         */
        float yPositon = (position / squaresPerRow) * SQUARE_SIZE;
        Color randomColor = Colors.getRandomColor();
        /**
         * Auf Basis der vorher berechneten bzw. erzeugten Werte wird ein Rechteck-Objekt (Instanz von Rectangle)
         * erstellt und mittels return-Befehl als Ergebnis der Methode zurückgeliefert.
         */
        Rectangle rect = new Rectangle(xPosition, yPositon, SQUARE_SIZE, SQUARE_SIZE, randomColor);
        return rect;
    }

    /**
     * Diese Methode zeichnet die als position übergebene Zahl in etwa in der Mitte des übergebenen Rechtecks ein.
     */
    private void drawLabelForRectangle(int position, Rectangle rect) {
        /**
         * x- und y-Position des Textes werden auf Basis der Position und Größe des Rechtecks berechnet.
         */
        float xPositon = rect.getXPos() + rect.getWidth()/2;
        float yPosition = rect.getYPos() + rect.getHeight()/2;
        /**
         * Zur Darstellung des Textes wird ein Label an der berechneten Pixel-Position erstellt. Als Inhalt (Text)
         * dient die übergebenen Position mit dem Präfix "#".
         */
        Label label = new Label(xPositon, yPosition, "#" + position);
        /**
         * Vor dem eigentlichen Zeichnen (draw) wird die Textfarbe des Labels gesetzt.
         */
        label.setColor(LABEL_COLOR);
        label.draw();
    }


}
