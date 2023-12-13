package model;

import controller.ModelStatusObservers;

/**
 * An interface that contains all the actions methods of our model implementation.
 */
public interface Reversi extends ReadOnlyReversi {

  /**
   * Moves a disc of the current player's color to the given tile. The result of a legal move is
   * that all of the opposite player's discs in all directions that are sandwiched between two
   * discs of the current player get flipped to the current player's. After a valid move is made,
   * the player's turn is changed. If the current player cannot make a move, their move is
   * passed on to the next player.
   *
   * @param who   the Player who wants to make a move
   * @param where the tile where the current player wants to make a move
   * @throws IllegalStateException    if the game hasn't been started yet
   * @throws IllegalArgumentException if the wrong player is making a move or if the player or tile
   *                                  to make a move in is an invalid null input
   * @throws IllegalStateException    if the tile is not a tile with no disc in it.
   */
  void makeMove(Player who, AbstractHexagon where);

  /**
   * Changes the player's turn to the next player.
   * @param player the Player whose turn it currently is
   */
  void nextPlayer(Player player);

  /**
   * Adds the observer to the list of observers that need to observe the model.
   * @param rc the Model Status Observer
   */
  void addObservers(ModelStatusObservers rc);


}
