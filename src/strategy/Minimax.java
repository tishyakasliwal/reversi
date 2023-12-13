package strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.NoDiscHexagon;
import model.Player;
import model.Reversi;
import model.ReversiModel;
import model.ReversiModelDeepCopy;

/**
 * An implementation of a strategy to be increase a player's chances
 * of winning in a game of Reversi. Strategy: minimizes the maximum move the opponent can make
 * by guessing the strategy used by opponent
 */
public class Minimax implements ReversiStrategy {

  //the other 3 strategies used in the game
  //capture maximum tiles strategy
  CaptureMaxTiles captureMax;

  //avoid the tiles next to corners strategy
  AvoidCorners avoidCorners;

  //choose the corner tiles strategy
  AnyOpenCorner openCorners;

  /**
   * Constructor for Minimax. All other strategies are initialized to find the best possible move.
   *
   */
  public Minimax() {
    this.captureMax = new CaptureMaxTiles();
    this.avoidCorners = new AvoidCorners();
    this.openCorners = new AnyOpenCorner();
  }

  @Override
  public NoDiscHexagon chooseTile(Reversi model, Player player) {
    //three possible moves the player could make
    NoDiscHexagon strategy1 = openCorners.chooseTile(model, player);
    NoDiscHexagon strategy2 = avoidCorners.chooseTile(model, player);
    NoDiscHexagon strategy3 = captureMax.chooseTile(model, player);


    //find the maximum number of tiles the opponent could capture (using any of the 3 strategies)
    // if the current player makes a move in the 3 possible tiles and stores it in a hashmap
    HashMap<NoDiscHexagon, Integer> minMoves = new HashMap<>();
    this.calcScore(strategy1, model, player, minMoves);
    this.calcScore(strategy2, model, player, minMoves);
    this.calcScore(strategy3, model, player, minMoves);

    //use the strategy which leaves the next opponent with the least possible score in
    // their next move
    int min = 0;
    NoDiscHexagon bestHex = null;
    for (HashMap.Entry<NoDiscHexagon, Integer> entry : minMoves.entrySet()) {
      if (entry.getValue() < min) {
        min = entry.getValue();
        bestHex = entry.getKey();
      }
    }
    return bestHex;
  }

  // find the maximum number of tiles the opponent could capture
  // if the current player makes a move in the given tile and stores it in a hashmap that maps
  // the tile the current player would move in with the maximum number of tiles the opponent would
  // capture in their move
  private void calcScore(NoDiscHexagon hex, Reversi model, Player player,
                         HashMap<NoDiscHexagon, Integer> map) {
    if (hex != null) {
      Player p = this.next(player);
      //creates a copy of the model where we can make a move to calculate the score for the next
      //possible move
      ReversiModelDeepCopy model2 = new ReversiModelDeepCopy((ReversiModel) model);
      ReversiModelDeepCopy copyModel = null;
      try {
        copyModel = model2.clone();
      } catch (CloneNotSupportedException e) {
        throw new RuntimeException(e);
      }
      copyModel.model.makeMove(player, hex);
      NoDiscHexagon strategy1 = openCorners.chooseTile(copyModel.model, p);
      NoDiscHexagon strategy2 = avoidCorners.chooseTile(copyModel.model, p);
      NoDiscHexagon strategy3 = captureMax.chooseTile(copyModel.model, p);
      List<NoDiscHexagon> l = Arrays.asList(strategy1, strategy2, strategy3);

      HashMap<NoDiscHexagon, Integer> m = captureMax.chooseHelper(l, p);

      int max = 0;

      for (HashMap.Entry<NoDiscHexagon, Integer> entry : m.entrySet()) {
        if (entry.getValue() > max) {
          max = entry.getValue();
        }
      }
      map.put(hex, max);
    }

  }

  /**
   * Returns who the next player will be.
   */
  private Player next(Player who) {
    if (who.equals(Player.A)) {
      return Player.B;
    }
    else {
      return Player.A;
    }
  }
}