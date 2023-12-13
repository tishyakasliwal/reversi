package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractHexagon is an abstract class for all types of tiles that contains methods for
 * functionalities that are common to all types of tiles.
 */
public abstract class AbstractHexagon {

  /**
   * Sets the right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setRight(AbstractHexagon hex);

  /**
   * Sets the bottom right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setBottomRight(AbstractHexagon hex);

  /**
   * Sets the left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setLeft(AbstractHexagon hex);

  /**
   * Sets the top right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setTopRight(AbstractHexagon hex);

  /**
   * Sets the top left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setTopLeft(AbstractHexagon hex);

  /**
   * Sets the bottom left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public abstract void setBottomLeft(AbstractHexagon hex);

  /**
   * A helper method that finds and returns a list of the lists of tiles in different directions
   * that neighbor the tile the current player wants to move in and can be flipped to the current
   * player's tiles according to the rules of the game. They are tiles
   * sandwiched between two tiles of the current player.
   * @param lofh is the list of neighbor tiles with discs of the opposite player's color.
   * @param from is the tile the player potentially wants to make a move in.
   * @param col is the color of the current player.
   * @return a list that contains the lists of tiles with discs in different directions of this tile
   *         that can be flipped to this player's tiles.
   */
  public List<List<FilledHexagon>> checkAll(List<FilledHexagon> lofh,
                                               NoDiscHexagon from, Color col) {
    List<List<FilledHexagon>> validLine = new ArrayList<>();
    for (FilledHexagon hex : lofh) {
      ArrayList<FilledHexagon> l = new ArrayList<>();
      // bottomLeft case
      if (hex.getDiagonal() == from.getDiagonal() && (hex.getRow() - from.getRow() == 1)) {
        this.check("bottom left", hex, col, l);
      }
      // bottomRight
      if ((hex.getDiagonal() - from.getDiagonal() == 1) && (hex.getRow() - from.getRow() == 1)) {
        this.check("bottom right", hex, col, l);
      }
      // topRight case
      else if (hex.getDiagonal() == from.getDiagonal() && (from.getRow() - hex.getRow() == 1)) {
        this.check("top right", hex, col, l);

      }
      // right case
      else if ((hex.getDiagonal() - from.getDiagonal() == 1) && hex.getRow() == from.getRow()) {
        this.check("right", hex, col, l);
      }

      // left case
      else if ((from.getDiagonal() - hex.getDiagonal() == 1) && hex.getRow() == from.getRow()) {
        this.check("left", hex, col, l);
      }

      // topLeft case
      else if ((from.getDiagonal() - hex.getDiagonal() == 1) &&
              (from.getRow() - hex.getRow() == 1)) {
        this.check("top left", hex, col, l);
      }
      if (!l.isEmpty() && l.get(l.size() - 1).getColor() == col) {
        l.remove(l.size() - 1);
        validLine.add(l);
      }
    }
    return validLine;

  }

  // A helper method that checks if the straight line neighbors of the given filled hexagon tile
  // in one direction is of the opposite player's color and returns a list of those
  // subsequent neighbors.
  private void check(String str, FilledHexagon hex, Color col, List<FilledHexagon> l) {

    if (str.equals("bottom left")) {
      if (hex.getBottomLeft() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getBottomLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("bottom left", h, col, l);
        }
      }
    } else if (str.equals("bottom right")) {
      if (hex.getBottomRight() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getBottomRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("bottom right", h, col, l);
        }
      }
    } else if (str.equals("top left")) {
      if (hex.getTopLeft() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getTopLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {

          l.add(hex);
          this.check("top left", h, col, l);
        }
      }
    } else if (str.equals("top right")) {
      if (hex.getTopRight() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getTopRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {

          l.add(hex);
          this.check("top right", h, col, l);
        }
      }
    } else if (str.equals("right")) {
      if (hex.getRight() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("right", h, col, l);
        }
      }
    } else if (str.equals("left")) {
      if (hex.getLeft() instanceof FilledHexagon) {
        FilledHexagon h = (FilledHexagon) hex.getLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("left", h, col, l);
        }
      }
    }
  }
}
