package strategy;

import java.util.ArrayList;
import java.util.List;

import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * The extra credit strategy that allows players to chain
 * strategies together. Strategy: takes multiple strategies
 * and returns multiple moves that will allow the user
 * to maximize their score.
 */
public class ExtraCredit {
  private List<ReversiStrategy> strategies;

  /**
   * Returns a possibility of moves that is a result of multiple
   * Reversi strategies chained together. The strategies can be
   * recombined in multiple different combinations and the possible
   * moves are determined based on the potential score moving to
   * that tile will result in.
   * @param model The ReversiModel that provides all the tiles in the grid
   * @param player The current player who is choosing a move
   * @param strategies The ReversiStrategies available to be chained
   *                   together
   * @return A list of possible moves that the player can move to
   *         in order to maximize their score.
   */
  public List<NoDiscHexagon> chooseTiles(Reversi model, Player player,
                                         List<ReversiStrategy> strategies) {
    int maxScore = 0;
    int score;
    List<NoDiscHexagon> possibleMoves = new ArrayList<>();
    for (ReversiStrategy strategy : strategies) {
      NoDiscHexagon hex = strategy.chooseTile(model, player);
      if (hex != null) {
        score = this.calculateScore(hex, player);
        if (score > maxScore) {
          maxScore = score;
          possibleMoves.add(hex);
        }
        else if (score == maxScore) {
          possibleMoves.add(hex);
        }
      }
    }
    return possibleMoves;
  }


  /**
   * Finds the potential score that will result from moving
   * to the given tile.
   * @param hex The tile that the player wants to move to.
   * @param player The current player who is choosing a move
   * @return The potential score which is the number of discs
   *          the player has on the game board.
   */
  private int calculateScore(NoDiscHexagon hex, Player player) {
    int size = 0;
    List<List<FilledHexagon>> list =
            hex.checkAll((hex.getFilledHexagons(player.getColor())),
                    hex, player.getColor());

    for (List<FilledHexagon> l : list) {
      size = size + l.size();
    }

    return size;
  }
}

