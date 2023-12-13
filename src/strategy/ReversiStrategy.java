package strategy;

import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * A Strategy interface for choosing where to play next for the given player.
 */
public interface ReversiStrategy {

  /**
   * Returns the empty tile that this player can move to that allows this
   * player to capture the most tiles as possible. This strategy works
   * by going through all the empty tiles in the grid and looking at all the
   * tiles it can capture in all directions. It will then find the tile that
   * is able to return the maximum number of tiles captured.
   * @param model The ReversiModel which determines which tiles will be
   *              captured.
   * @param player The ReversiPlayer that is currently playing.
   * @return A NoDiscHexagon that the player can move to and get the
   *         greatest number of tiles captured.
   */
  NoDiscHexagon chooseTile(Reversi model, Player player);

}
