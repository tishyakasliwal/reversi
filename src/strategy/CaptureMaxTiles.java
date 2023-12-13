package strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * An implementation of a strategy to be increase a player's chances
 * of winning in a game of Reversi. Strategy: tries to capture as many
 * tiles as possible in one move.
 */
public class CaptureMaxTiles implements ReversiStrategy {

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
  @Override
  public NoDiscHexagon chooseTile(Reversi model, Player player) {

    HashMap<NoDiscHexagon, Integer> possibleHexagons = new HashMap<>();

    for (NoDiscHexagon hex : model.getGrid()) {
      int size = 0;
      if (!(hex instanceof FilledHexagon)) {
        List<List<FilledHexagon>> list =
                hex.checkAll((hex.getFilledHexagons(player.getColor())),
                        hex, player.getColor());

        for (List<FilledHexagon> l : list) {
          size = size + l.size();
        }
        possibleHexagons.put(hex, size);
      }
    }
    return this.getBestHexagon(possibleHexagons);
  }

  // returns a hashmap with the maximum number of tiles the player can capture if they make a move
  // in any of the possible tiles

  /**
   * Determines the hashmap with the maximum number of tiles the player can
   * capture if they make a move in any of the possible tiles.
   * @param loh The possible tiles available to make a move in.
   * @param player The ReversiPlayer that is currently playing.
   * @return Returns a hashmap with the maximum number of tiles
   */
  public HashMap<NoDiscHexagon, Integer> chooseHelper(List<NoDiscHexagon> loh, Player player) {
    HashMap<NoDiscHexagon, Integer> possibleHexagons = new HashMap<>();

    int size = 0;
    for (NoDiscHexagon hex: loh) {
      //the player cannot make a move in a tile with a disc already in it
      if (!(hex instanceof  FilledHexagon)) {
        List<List<FilledHexagon>> list =
                hex.checkAll((hex.getFilledHexagons(player.getColor())),
                        hex, player.getColor());
        for (List<FilledHexagon> l: list) {
          size = size + l.size();
        }
        possibleHexagons.put(hex, size);
      }
    }
    return possibleHexagons;
  }


  /**
   * Iterates through a hashmap that contains all the hexagons in the grid and
   * finds which hexagon has the largest number of tiles captured.
   * @param map A hashmap that contains a NoDiscHexagon and the largest amount
   *            of tiles that that tile will capture in one move.
   * @return A NoDiscHexagon that will capture the largest amount of tiles.
   */
  private NoDiscHexagon getBestHexagon(HashMap<NoDiscHexagon, Integer> map) {

    int max = 0;
    NoDiscHexagon bestHex = null;
    List<NoDiscHexagon> ties = new ArrayList<>();

    for (HashMap.Entry<NoDiscHexagon, Integer> entry : map.entrySet()) {
      if (entry.getValue() == max) {
        ties.add(entry.getKey());
      }
      if (entry.getValue() > max) {
        max = entry.getValue();
        ties.clear();
        ties.add(entry.getKey());
      }
    }

    bestHex = ties.get(0);

    for (NoDiscHexagon hex : ties) {
      if (hex.getDiagonal() < bestHex.getDiagonal() ||
              (hex.getDiagonal() == bestHex.getDiagonal() && hex.getRow() < bestHex.getRow())) {
        bestHex = hex;
      }
    }
    return bestHex;
  }
}
