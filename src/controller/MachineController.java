package controller;

import model.NoDiscHexagon;
import model.Player;
import model.Reversi;
import model.ReversiPlayer;
import view.ReversiView;

/**
 * A class that represents the controller for a machine player.
 * In our representation of Reversi, a machine player requires
 * a strategy in order to compute their next move.
 */
public class MachineController implements ReversiController, PlayerActions, ModelStatusObservers {

  private final Reversi model;
  private final ReversiPlayer player;
  private ReversiView view;


  /**
   * Constructs an instance of a machine player.
   * @param model The Reversi model responsible for the functionality.
   * @param player The player of the game
   * @param view The visual representation of the Reversi game.
   */
  public MachineController(Reversi model, ReversiPlayer player, ReversiView view) {
    this.model = model;
    this.player = player;
    this.view = view;
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
    this.view.addFeatures(this);

  }


  /**
   * Refreshes the Reversi game board after each move
   * has been made to update the current game state.
   */
  @Override
  public void refreshView() {
    System.out.println("refresh!");
    this.view.refresh();
  }

  /**
   * Allows the machine player to move using a
   * given strategy. The machine player in this
   * game can choose out of 5 strategies: capturing the most tiles
   * possible, avoiding the corners, prioritizing the corners,
   * minimizing the amount of tiles the opponent can capture,
   * and combining all the strategies into one strategy. If the
   * machine player makes an invalid move the players will be
   * notified via a popup message.
   */
  @Override
  public void playerMove() {
    try {
      NoDiscHexagon toMove = player.play(this.model);
      model.makeMove(model.getPlayer(), toMove);
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      view.showMessageInvalidMoves("Invalid move");
      model.nextPlayer(this.model.getPlayer());
    }
  }

  /**
   * Allows the machine player to pass their turn if there
   * are no possible moves available.
   */
  @Override
  public void playerPass() {
    model.nextPlayer(model.getPlayer());
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
      view.showMessage("Game is over. Your score is: " + model.getScore(this.player.getPlay()));
    }
    else if (model.getPlayer().equals(this.player.getPlay())) {
      //tells the player it's their turn
      view.showMessage("It's your turn!");

      this.playerMove();
    }
  }
}
