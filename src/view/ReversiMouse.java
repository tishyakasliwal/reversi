package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import model.ReadOnlyReversi;

/**
 * An implementation of the MouseListener that is used
 * to handle when the cell has been selected. If a tile
 * is selected then it becomes highlighted to a teal
 * color.
 */
public class ReversiMouse extends MouseAdapter {

  private final ReadOnlyReversi model;
  private final ReversiPanelView panel;

  /**
   * Constructs a ReversiMouse instance.
   *
   * @param model The ReadOnlyReversi model that is used to access
   *              the functionality of the ReversiModel.
   * @param panel The JPanel implementation that updates the game state
   *              when a tile is selected or deselected.
   */
  public ReversiMouse(ReadOnlyReversi model, ReversiPanelView panel) {

    this.model = model;
    this.panel = panel;
  }

  /**
   * Determines what occurred to the game state when the mouse
   * has been pressed. Allows the user to select and deselect a
   * given tile when the press on another tile
   *
   * @param e the event to be processed
   */
  public void mousePressed(MouseEvent e) {

    for (Map.Entry<ArrayList<Integer>, ArrayList<Integer>> coords :
            this.panel.getHexagonMap().entrySet()) {
      if ((((Math.abs(coords.getValue().get(0) - e.getX())) <= 20) ||
              (Math.abs(e.getX() - coords.getValue().get(0))) <= 20) &&
              ((Math.abs(coords.getValue().get(1) - e.getY())
                      <= (Math.cos(Math.toRadians(30)) * 20)) ||
                      Math.abs(e.getY() - coords.getValue().get(1))
                              <= (Math.cos(Math.toRadians(30)) * 20))) {
        int x2 = coords.getKey().get(0);
        int y2 = coords.getKey().get(1);
        System.out.println("new (" + x2 + "," + y2 + ")");

        try {
          Robot r = new Robot();
          Color color = r.getPixelColor(e.getLocationOnScreen().x, e.getLocationOnScreen().y);

          if (color.toString().equals(Color.CYAN.toString())) {
            this.panel.drawHexagon((Graphics2D) this.panel.getGraphics(), coords.getValue().get(0),
                    coords.getValue().get(1));
          } else if (color.toString().equals(Color.GRAY.toString())) {
            this.panel.changeColor((Graphics2D) this.panel.getGraphics(), coords.getValue().get(0),
                    coords.getValue().get(1));
            this.panel.addSelectedTile(this.model.getHex(x2, y2));
          }
        } catch (AWTException ex) {
          System.out.print("Unable to change the color");
        }
      } else {
        int x = coords.getKey().get(0);
        int y = coords.getKey().get(1);
        this.panel.fillBoard((Graphics2D) this.panel.getGraphics(), coords.getValue().get(0),
                coords.getValue().get(1), this.model.getHex(x, y));
      }
    }
  }
}

