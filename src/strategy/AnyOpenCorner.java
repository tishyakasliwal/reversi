package strategy;

import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * An implementation of a ReversiStrategy to maximize a user's
 * chances of winning the game. Strategy: go for the corner
 * cells since they don't have tiles on either side.
 */
public class AnyOpenCorner implements ReversiStrategy {
  @Override
  public NoDiscHexagon chooseTile(Reversi model, Player player) {

    for (NoDiscHexagon hex : model.getGrid()) {
      if (hex.getRow() == 0 && hex.getDiagonal() == 0 && !(hex instanceof FilledHexagon)) {
        return hex;
      } else if ((hex.getRow() == (2 * model.getBoardSize() - 1)) &&
              hex.getDiagonal() == (2 * model.getBoardSize() - 1) &&
              !(hex instanceof FilledHexagon)) {
        return hex;
      } else if ((hex.getRow() == (2 * model.getBoardSize() - 1)) &&
              hex.getDiagonal() == (model.getBoardSize() - 1) &&
              !(hex instanceof FilledHexagon)) {
        return hex;
      } else if (hex.getDiagonal() == model.getBoardSize() - 1 && hex.getRow() == 0 &&
              !(hex instanceof FilledHexagon)) {
        return hex;
      } else if (hex.getDiagonal() == 0 && hex.getRow() == model.getBoardSize() - 1 &&
              !(hex instanceof FilledHexagon)) {
        return hex;
      } else if (hex.getDiagonal() == (2 * model.getBoardSize() - 1) &&
              hex.getRow() == (model.getBoardSize() - 1) && !(hex instanceof FilledHexagon)) {
        return hex;
      }
    }
    return null;
  }
}
