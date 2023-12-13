package strategy;

import model.AbstractHexagon;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * An implementation of a ReversiStrategy to maximize a player's chances
 * of winning the game. Strategy: avoid the tiles next to the corner
 * tiles to ensure the next player does not get to the corner tile.
 */
public class AvoidCorners implements ReversiStrategy {

  @Override
  public NoDiscHexagon chooseTile(Reversi model, Player player) {
    NoDiscHexagon n = null;
    for (NoDiscHexagon hex : model.getGrid()) {
      if (!(hex instanceof FilledHexagon) && !(this.checkNeighbor(hex, model))) {
        n = hex;
        break;
      }
    }
    return n;
  }

  // checks if any of the neighbors of the given tile are the corner tiles of the grid
  private boolean checkNeighbor(NoDiscHexagon hex, Reversi model) {
    int size = model.getBoardSize();
    return (this.neighbors(hex, model, 0, 0) ||
            this.neighbors(hex, model, size - 1, 0) ||
            this.neighbors(hex, model, (2 * model.getBoardSize() - 1),
                    (2 * model.getBoardSize() - 1)) ||
            this.neighbors(hex, model, size - 1, (2 * model.getBoardSize() - 1)) ||
            this.neighbors(hex, model, 0, size - 1) ||
            this.neighbors(hex, model, (2 * model.getBoardSize() - 1), size - 1));
  }

  // checks if any of the neighbors of the given hexagon tile is the tile for which
  // the coordinates are given
  private boolean neighbors(NoDiscHexagon hex, Reversi model, int diag, int row) {
    AbstractHexagon h = model.getHex(diag, row);
    return (hex.getLeft().equals(h) || hex.getTopLeft().equals(h)
            || hex.getBottomLeft().equals(h) || hex.getRight().equals(h)
            || hex.getTopRight().equals(h) || hex.getBottomRight().equals(h));
  }
}
