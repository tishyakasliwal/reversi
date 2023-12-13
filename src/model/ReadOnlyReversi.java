package model;

import java.util.List;

/**
 * An interface that contains all the observation methods of
 * our model implementation. When called, we can ensure
 * that no other components are modifying the model
 * unintentionally.
 */
public interface ReadOnlyReversi {

  /**
   * Signal if the game is over or not.  A game is over if there are no more
   * possible moves to be made by both players.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */

  boolean gameOver();

  /**
   * Returns the current score for the given player, which is the sum of the number of tiles
   * filled with a disc of the color of that player.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  int getScore(Player who);


  /**
   * Returns the tile with the given coordinates.
   *
   * @param i    the 0-based index (from the left) of the diagonal coordinate of the tile.
   * @param j    the 0-based index (from the top) of the row coordinate of the tile.
   */
  AbstractHexagon getHex(int i, int j);


  /**
   * Returns the size of this game board.
   *
   * @return the size of the board.
   */
  int getBoardSize();

  /**
   * Returns grid that the game is being played on.
   *
   * @return the hexagonal grid that the game is being played on.
   */
  List<NoDiscHexagon> getGrid();


  /**
   * Returns the current player of the game.
   *
   * @return the player whose turn it is.
   */
  Player getPlayer();

  /**
   * Returns the current state of the game.
   * @return True if the game has started.
   */
  boolean gameState();


  void nextPlayer(Player player);
}

