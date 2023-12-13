package controller;

/**
 * A features interface that represents the actions that a player
 * is able to request from the game.
 */
public interface PlayerActions  {

  /**
   * Allows the human player to make a move. If the player makes
   * an invalid move then the player will be notified via a popup
   * message on the view.
   */
  void playerMove();

  /**
   * Allows the machine player to pass their turn if there
   * are no possible moves available.
   */
  void playerPass();

}
