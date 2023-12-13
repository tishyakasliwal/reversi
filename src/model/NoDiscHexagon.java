package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * NoDiscHexagon extends the AbstractHexagon and represents a hexagon tile that has no disc inside
 * it. It has a diagonal and row coordinate to represent its position and 6 neighbor tiles.
 */
public class NoDiscHexagon extends AbstractHexagon {

  //the 0-based index (from the left) diagonal coordinate of this tile
  private final int diagonal;

  //the 0-based index (from the top) row coordinate of this tile
  private final int row;

  // left neighbor tile of this tile
  private AbstractHexagon left;

  //top left neighbor tile of this tile
  private AbstractHexagon topLeft;

  // top right neighbor tile of this tile
  private AbstractHexagon topRight;

  // right neighbor tile of this tile
  private AbstractHexagon right;

  // bottom left neighbor tile of this tile
  private AbstractHexagon bottomLeft;

  //bottom right neighbor tile of this tile
  private AbstractHexagon bottomRight;

  /**
   * Constructor for NoDiscHexagon.
   *
   * @param diagonal is the 0 based index (from the left) diagonal coordinate of the tile
   * @param row      is the 0 based index (from the top) row coordinate of the tile
   * @throws IllegalArgumentException if the diagonal or row coordinate is less than 0.
   */
  public NoDiscHexagon(int diagonal, int row) {
    if (diagonal < 0 || row < 0) {
      throw new IllegalArgumentException();
    }
    this.diagonal = diagonal;
    this.row = row;
    this.left = new EmptyHexagon();
    this.topLeft = new EmptyHexagon();
    this.right = new EmptyHexagon();
    this.topRight = new EmptyHexagon();
    this.bottomLeft = new EmptyHexagon();
    this.bottomRight = new EmptyHexagon();
  }

  /**
   * Constructs a NoDiscHexagon where the arguments can be inputted.
   *
   * @param diagonal    is the 0 based index (from the left) diagonal coordinate of the tile.
   * @param row         is the 0 based index (from the top) row coordinate of the tile
   * @param right       the tile's right neighbor.
   * @param left        the tile's left neighbor.
   * @param topRight    the tile's topRight neighbor.
   * @param topLeft     the tile's topLeft neighbor.
   * @param bottomRight the tile's bottomRight neighbor.
   * @param bottomLeft  the tile's bottomLeft neighbor.
   */
  public NoDiscHexagon(int diagonal, int row, AbstractHexagon right, AbstractHexagon left,
                       AbstractHexagon topRight, AbstractHexagon topLeft,
                       AbstractHexagon bottomRight, AbstractHexagon bottomLeft) {
    if (diagonal < 0 || row < 0) {
      throw new IllegalArgumentException();
    }
    this.diagonal = diagonal;
    this.row = row;
    this.right = right;
    this.left = left;
    this.topRight = topRight;
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
    this.bottomLeft = bottomLeft;
  }

  /**
   * Accesses the right neighbor of this tile.
   * @return the right neighbor.
   */
  public AbstractHexagon getRight() {
    return this.right;
  }

  /**
   * Accesses the bottom right neighbor of this tile.
   * @return the bottom right neighbor.
   */
  public AbstractHexagon getBottomRight() {
    return this.bottomRight;
  }

  /**
   * Accesses the left neighbor of this tile.
   * @return the left neighbor.
   */
  public AbstractHexagon getLeft() {
    return this.left;
  }

  /**
   * Accesses the top right neighbor of this tile.
   * @return the top right neighbor.
   */
  public AbstractHexagon getTopRight() {
    return this.topRight;
  }

  /**
   * Accesses the top left neighbor of this tile.
   * @return the top left neighbor.
   */
  public AbstractHexagon getTopLeft() {
    return this.topLeft;
  }

  /**
   * Accesses the bottom left neighbor of this tile.
   * @return the bottom left neighbor.
   */
  public AbstractHexagon getBottomLeft() {
    return this.bottomLeft;
  }

  /**
   * Sets the right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setRight(AbstractHexagon hex) {
    this.right = hex;
  }

  /**
   * Sets the bottom right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setBottomRight(AbstractHexagon hex) {
    this.bottomRight = hex;
  }

  /**
   * Sets the left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setLeft(AbstractHexagon hex) {
    this.left = hex;
  }

  /**
   * Sets the top right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setTopRight(AbstractHexagon hex) {
    this.topRight = hex;
  }

  /**
   * Sets the top left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setTopLeft(AbstractHexagon hex) {
    this.topLeft = hex;
  }

  /**
   * Sets the bottom left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setBottomLeft(AbstractHexagon hex) {
    this.bottomLeft = hex;
  }


  /**
   * Gets the 0-based index (from the left) diagonal coordinate of this tile.
   * @return the diagonal coordinate of this tile.
   */
  public int getDiagonal() {
    return this.diagonal;
  }


  /**
   * Gets the 0-based index (from the top) row coordinate of this tile.
   * @return the row coordinate of this tile.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Checks to see if all the surrounding tiles of the given NoDiscTile are empty.
   * @return true if all the neighbor tiles of this tile have no disc inside it or are an
   *         EmptyHexagon,false if even one of the neighbor tiles have a disc inside it
   */
  protected boolean allNeighborsEmpty() {
    return (!(this.topLeft instanceof FilledHexagon)
            && !(this.left instanceof FilledHexagon)
            && !(this.bottomLeft instanceof FilledHexagon)
            && !(this.topRight instanceof FilledHexagon)
            && !(this.right instanceof FilledHexagon)
            && !(this.bottomRight instanceof FilledHexagon));
  }


  /**
   * Finds and returns a list of tiles that directly neighbor this tile and contain a disc of the
   * opposite player.
   * @param col the given color to of the player who is currently playing.
   * @return a list of filled hexagons of neighboring tiles of the opposite player's color.
   */
  public List<FilledHexagon> getFilledHexagons(Color col) {
    List<FilledHexagon> filledHexagons = new ArrayList<>();

    if (this.topLeft instanceof FilledHexagon) {
      FilledHexagon topLeft = (FilledHexagon) this.topLeft;
      if (topLeft.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.topLeft);
      }
    }
    if (this.topRight instanceof FilledHexagon) {
      FilledHexagon topRight = (FilledHexagon) this.topRight;
      if (topRight.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.topRight);

      }
    }
    if (right instanceof FilledHexagon) {
      FilledHexagon right = (FilledHexagon) this.right;
      if (right.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.right);
      }
    }
    if (this.left instanceof FilledHexagon) {
      FilledHexagon left = (FilledHexagon) this.left;
      if (left.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.left);
      }
    }
    if (this.bottomLeft instanceof FilledHexagon) {
      FilledHexagon bottomLeft = (FilledHexagon) this.bottomLeft;
      if (bottomLeft.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.bottomLeft);
      }
    }
    if (this.bottomRight instanceof FilledHexagon) {
      FilledHexagon bottomRight = (FilledHexagon) this.bottomRight;
      if (bottomRight.getColor() != col) {
        filledHexagons.add((FilledHexagon) this.bottomRight);
      }
    }
    return filledHexagons;
  }
}