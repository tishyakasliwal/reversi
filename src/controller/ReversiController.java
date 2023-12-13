package controller;

import model.Player;

/**
 * The controller for a game of Reversi that handles
 * which player's turn it is and updates the game
 * state accordingly.
 */
public interface ReversiController {

  /**
   * Determines which player's turn it is.
   * @return The player whose turn it currently is.
   */
  Player checkTurn();
}
