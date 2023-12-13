package model;

import java.awt.Color;

/**
 * FilledHexagon is a subclass of NoDiscHexagon and
 * represents a tile with a disc in it.
 */
public class FilledHexagon extends NoDiscHexagon {

  private int diagonal;
  private int row;

  // Color of the disc in this tile
  private final Color col;
  private AbstractHexagon right;
  private AbstractHexagon left;
  private AbstractHexagon topRight;
  private AbstractHexagon topLeft;
  private AbstractHexagon bottomRight;
  private AbstractHexagon bottomLeft;


  /**
   * Constructor for FilledHexagon. Sets the coordinates and
   * neighboring tiles using the superclass.
   * Initializes the color of disc inside it.
   *
   * @param diagonal is the 0 based index (from the left) diagonal coordinate of the tile.
   * @param row      is the 0 based index (from the top) row coordinate of the tile
   * @param col      is the color of the tile (black or white)
   */
  public FilledHexagon(int diagonal, int row, Color col) {
    super(diagonal, row);
    this.col = col;
  }

  /**
   * Constructs a FilledHexagon based on inputs.
   *
   * @param diagonal    is the 0 based index (from the left) diagonal coordinate of the tile.
   * @param row         is the 0 based index (from the top) row coordinate of the tile
   * @param right       the tile's right neighbor.
   * @param left        the tile's left neighbor.
   * @param topRight    the tile's top right neighbor.
   * @param topLeft     the tile's top left neighbor.
   * @param bottomRight the tile's bottom right neighbor
   * @param bottomLeft  the tile's bottom left neighbor
   * @param col         the color of the tile (black or white)
   */
  public FilledHexagon(int diagonal, int row, AbstractHexagon right, AbstractHexagon left,
                       AbstractHexagon topRight, AbstractHexagon topLeft,
                       AbstractHexagon bottomRight, AbstractHexagon bottomLeft, Color col) {
    super(diagonal, row, right, left, topRight, topLeft, bottomRight, bottomLeft);
    this.col = col;
  }

  /**
   * Access the color of the tile.
   *
   * @return the color of the given tile.
   */
  public Color getColor() {
    return this.col;
  }
}
