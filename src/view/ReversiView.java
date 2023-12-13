package view;

import controller.PlayerActions;
import model.AbstractHexagon;

/**
 * Represents the interface that implements the GUI. Involves
 * making the JFrame window visible, updating based on the
 * game state, and making the window clickable.
 */
public interface ReversiView {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();


  /**
   * Adds a mouse listener to the JFrame to make the window
   * clickable.
   */
  void addClickListener();


  /**
   * Responsible for notifying the view that a player wants
   * to either move or pass their turn. If the player
   * presses 'm' they will be able to move to a given
   * tile and if the player presses 'p' they will be able
   * to pass their turn.
   * @param features Contains information about the given
   *                 players requests for their turn.
   */
  void addFeatures(PlayerActions features);


  /**
   * Accesses the tile that has been selected most recently.
   * @return A tile that currently has no disc in it.
   */
  AbstractHexagon selectedTile();

  /**
   * Provides a notification in the view when there is a change
   * in the current game state.
   * @param message A given message that needs to be displayed.
   */
  void showMessage(String message);

  /**
   * Provides a notification in the view when the player
   * has caused an error that causes a change in the game
   * state.
   * @param s A given message that needs to be displayed.
   */
  void showMessageInvalidMoves(String s);
}
