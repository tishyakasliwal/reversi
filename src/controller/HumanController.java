package controller;

import model.AbstractHexagon;
import model.Player;
import model.Reversi;
import view.ReversiView;

/**
 * A class to represent the controller for a human player in a
 * Reversi game. A human player is able to choose which
 * tile they want to place a disc in manually and wll be notified
 * if the move is invalid.
 */
public class HumanController implements ReversiController, PlayerActions, ModelStatusObservers {
  private final Reversi model;
  private final Player player;
  private ReversiView view;



  /**
   * Constructs an instance of a human player.
   * @param model The Reversi model responsible for the functionality.
   * @param player The player of the game
   * @param view The visual representation of the Reversi game.
   */
  public HumanController(Reversi model, Player player, ReversiView view) {
    this.model = model;
    this.player = player;
    this.view = view;
  }

  /**
   * Allows the human player to make a move. If the player makes
   * an invalid move then the player will be notified via a popup
   * message on the view.
   */
  @Override
  public void playerMove() {
    try {
      AbstractHexagon toMove = this.view.selectedTile();
      model.makeMove(model.getPlayer(), toMove);
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      view.showMessageInvalidMoves("Invalid move");
      model.nextPlayer(this.model.getPlayer());
    }

  }

  /**
   * Refreshes the Reversi game board after each move
   * has been made to update the current game state.
   */
  @Override
  public void refreshView() {
    this.view.refresh();
  }

  /**
   * Allows the machine player to pass their turn if there
   * are no possible moves available.
   */
  @Override
  public void playerPass() {
    model.nextPlayer(this.model.getPlayer());

  }

  /**
   * Provides the view with all the necessary callbacks
   * to update the game state after each move has been
   * made.
   * @param v The visual representation of the Reversi game.
   */
  @Override
  public void setView(ReversiView v) {
    this.view = v;
    try {
      this.view.addFeatures(this);
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      System.out.print("error");
    }
  }

  /**
   * Determines which player's turn it is.
   * @return The player whose turn it currently is.
   */
  @Override
  public Player checkTurn() {
    return model.getPlayer();
  }


  /**
   * Switches the current player to whose ever turn is
   * next. When the turn is switched both player's will
   * be notified via popup message.
   */
  @Override
  public void changePlayer() {
    if (model.gameOver()) {
      view.showMessage("Game is over. Your score is: " + model.getScore(this.player));
    }
    else if (model.getPlayer().equals(this.player)) {
      //tells the player it's their turn
      view.showMessage("It's your turn!");
    }
  }
}
