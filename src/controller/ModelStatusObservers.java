package controller;

import view.ReversiView;

/**
 * Another features interface that represents the observers
 * that determine the current state of the model
 * and that provides the functionality for the Reversi game.
 */
public interface ModelStatusObservers {


  /**
   * Switches the current player to whose ever turn is
   * next. When the turn is switched both player's will
   * be notified via popup message.
   */
  void changePlayer();

  /**
   * Provides the view with all the necessary callbacks
   * to update the game state after each move has been
   * made.
   * @param view The visual representation of the Reversi game.
   */
  void setView(ReversiView view);

  /**
   * Refreshes the Reversi game board after each move
   * has been made to update the current game state.
   */
  void refreshView();


}
