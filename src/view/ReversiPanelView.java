package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.AbstractHexagon;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.ReadOnlyReversi;

/**
 * An implementation of the Java swing JPanel class
 * that is responsible for drawing the hexagonal grid
 * onto the window.
 */
public class ReversiPanelView extends JPanel {

  private final ReadOnlyReversi model;


  /**
   * Constructs a ReversiPanelView.
   *
   * @param model An instance of a ReadOnlyReversi model
   *              that is responsible for providing the
   *              functionality of the ReversiModel.
   */
  public ReversiPanelView(ReadOnlyReversi model) {
    this.model = model;
    this.setBackground(Color.BLACK);
    this.getPreferredSize();

  }

  HashMap<ArrayList<Integer>, ArrayList<Integer>> hexagons = new HashMap<>();

  List<AbstractHexagon> selectedTiles = new ArrayList<AbstractHexagon>();

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 400x400 pixels.
   *
   * @return Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }

  /**
   * The method responsible for drawing the hexagonal grid onto the
   * window. It translates the logical coordinates of the hexagon to
   * be presented on the JFrame window and determines which tiles
   * have a disc on them or which tiles are empty.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();
    int size = this.model.getBoardSize();
    g2d.setColor(Color.DARK_GRAY);


    for (NoDiscHexagon hex : this.model.getGrid()) {
      ArrayList<Integer> originalCoords = new ArrayList<>();
      ArrayList<Integer> drawCoords = new ArrayList<>();
      int x = hex.getRow();
      int y = hex.getDiagonal();

      originalCoords.add(y);
      originalCoords.add(x);

      int yCoord = this.getPreferredSize().height / 2 + (30 * (x - (size - 1)));
      int xCoord = (int) (this.getPreferredSize().width / 2 +
              (Math.cos(Math.toRadians(30)) * 20) *
                      (y - (size - 1) - (x - y)));

      drawCoords.add(xCoord);
      drawCoords.add(yCoord);
      hexagons.put(originalCoords, drawCoords);

      AbstractHexagon current = this.model.getHex(y, x);

      fillBoard(g2d, xCoord, yCoord, current);
    }
  }

  /**
   * Determines whether this tile in the grid is filled with a disc or
   * empty. If the disc is filled, depending on the player, it will be
   * filled with a black or white disc. Otherwise, the empty hexagon
   * is grey.
   *
   * @param g2d     The Graphics2D instance responsible for drawing the hexagon.
   * @param x       The x coordinate for where the hexagon will be drawn.
   * @param y       The y coordinate for where the hexagon will be drawn.
   * @param current The current hexagon that will be drawn.
   */
  protected void fillBoard(Graphics2D g2d, int x, int y, AbstractHexagon current) {
    if (current instanceof FilledHexagon
            && ((FilledHexagon) current).getColor() == Color.BLACK) {
      this.drawHexagon(g2d, x, y);
      g2d.setColor(Color.BLACK);
      g2d.fillOval((x - 10 + (int) Math.sqrt(3)), y - 10, 20, 20);
    } else if (current instanceof FilledHexagon
            && ((FilledHexagon) current).getColor() == Color.WHITE) {
      this.drawHexagon(g2d, x, y);
      g2d.setColor(Color.WHITE);
      g2d.fillOval((x - 10 + (int) Math.sqrt(3)), y - 10, 20, 20);
    } else {
      g2d.setColor(Color.GRAY);
      this.drawHexagon(g2d, x, y);
    }
  }

  /**
   * Draws a single hexagon using Path2D.Double. This method starts
   * drawing from the bottom left corner and draws the lines
   * of the hexagon at an angle to get the hexagonal shape.
   *
   * @param g2d    The Graphics2D instance responsible for drawing the hexagon.
   * @param xCoord The x coordinate for where the hexagon will be drawn.
   * @param yCoord The y coordinate for where the hexagon will be drawn.
   */
  protected void drawHexagon(Graphics2D g2d, int xCoord, int yCoord) {
    int size = 20;
    Path2D.Double hex = new Path2D.Double();

    for (int i = 0; i < 6; i++) {
      double angleDeg = 60 * i - 30;
      double angle = Math.toRadians(angleDeg);
      double x = xCoord + size * Math.cos(angle);
      double y = yCoord + size * Math.sin(angle);
      if (i == 0) {
        hex.moveTo(x, y);
      } else {
        hex.lineTo(x, y);
      }
    }
    hex.closePath();
    g2d.setColor(Color.GRAY);
    g2d.fill(hex);
    g2d.setColor(Color.BLACK);
    g2d.draw(hex);

  }


  /**
   * Draws a single hexagon using Path2D.Double but changes the
   * color to represent when a tile has been selected. Follows
   * the same process for drawing a gray hexagon.
   *
   * @param g2d    The Graphics2D instance responsible for drawing the hexagon.
   * @param xCoord The x coordinate for where the hexagon will be drawn.
   * @param yCoord The y coordinate for where the hexagon will be drawn.
   */
  protected void changeColor(Graphics2D g2d, int xCoord, int yCoord) {

    int size = 20;
    Path2D.Double hex = new Path2D.Double();
    for (int i = 0; i < 6; i++) {
      double angleDeg = 60 * i - 30;
      double angle = Math.toRadians(angleDeg);
      double x = xCoord + size * Math.cos(angle);
      double y = yCoord + size * Math.sin(angle);
      if (i == 0) {
        hex.moveTo(x, y);
      } else {
        hex.lineTo(x, y);
      }
    }
    hex.closePath();
    g2d.setColor(Color.CYAN);
    g2d.fill(hex);
    g2d.setColor(Color.BLACK);
    g2d.draw(hex);
  }


  /**
   * Returns the logical coordinates.
   * of the grid and the actual coordinates based on this frame.
   *
   * @return A HashMap containing a list of the logical coordinates of this
   *        grid and a list of the coordinates based on this window.
   */
  public HashMap<ArrayList<Integer>, ArrayList<Integer>> getHexagonMap() {
    return hexagons;
  }

  /**
   * Adds to the list of tiles that have been selected by the given
   * player.
   * @param hex The tile that has been selected.
   */
  public void addSelectedTile(AbstractHexagon hex) {
    selectedTiles.add(hex);
  }

  /**
   * Determines the tile that has been selected most
   * recently.
   * @return The last tile that has been selected.
   */
  public AbstractHexagon getLastSelection() {
    return selectedTiles.get(selectedTiles.size() - 1);
  }


}