package view;

import java.awt.Color;

import model.AbstractHexagon;
import model.FilledHexagon;
import model.ReadOnlyReversi;
import model.ReversiModel;


/**
 * A simple text-based rendering of the Reversi game. In out textual
 * view a "_" represents a tile with no disc, a "X" represents a black
 * disc, and a "O" represents a white disc.
 */
public class ReversiTextualView {
  private final ReadOnlyReversi model;

  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  /**
   * A String representation of the Reversi game.
   *
   * @return A String of the Reversi game.
   */
  public String toString() {
    return this.getHexagonalGrid(this.model.getBoardSize());
  }

  // helper method for toString that returns a string representation of the game
  private String getHexagonalGrid(int size) {
    StringBuilder result = new StringBuilder();

    // creates the top half of the textual rendering
    int x = size - 1;
    for (int i = 0; i < size; i++) {
      x++;

      // Adds a decreasing amount of spaces to create the hexagonal pattern
      for (int j = 0; j < Math.abs(i - (2 * size - 1) / 2); j++) {
        result.append(" ");
      }

      for (int j = 0; j < x; j++) {
        AbstractHexagon current = this.model.getHex(j, i);
        result.append(this.fillBoard(current));
      }
      result = new StringBuilder(result.substring(0, result.length() - 1));
      result.append("\n");
    }

    // creates the bottom half of the textual rendering
    int p = 2 * size - 1;
    for (int m = size; m < 2 * size - 1; m++) {
      //p--;

      for (int j = 0; j < Math.abs(m - (2 * size - 1) / 2); j++) {
        result.append(" ");
      }

      for (int n = m - (size - 1); n < p; n++) {

        AbstractHexagon current = this.model.getHex(n, m);
        result.append(this.fillBoard(current));
      }
      result = new StringBuilder(result.substring(0, result.length() - 1));
      result.append("\n");
    }
    return result.toString();
  }

  /**
   * Fills hexagonal board with a "_", "X", or "O" depending
   * on the game state.
   *
   * @param current The hexagon that is being updated.
   * @return A String representation of the hexagon based on whether
   *         it is a black disc, white disc, or with no disc.
   */
  private String fillBoard(AbstractHexagon current) {
    String result = "";
    if (current instanceof FilledHexagon
            && ((FilledHexagon) current).getColor() == Color.BLACK) {
      result += "X ";
    } else if (current instanceof FilledHexagon
            && ((FilledHexagon) current).getColor() == Color.WHITE) {
      result += "O ";
    } else {
      result += "_ ";
    }
    return result;
  }
}


