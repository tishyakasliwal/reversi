package model;


/**
 * Represents a single Player in a game of Reversi. Since there are many different
 * types of Players, the main functionality of this interface is to identify the
 * player, allow the player to be best informed on how to move their discs,
 * and when the game is over.
 */
public interface ReversiPlayer {

  /**
   * Allows the player to choose the tile
   * they want to move in and play the game.
   * @param model The Reversi model that provides the ability
   *              to move a disc into a tile.
   * @return The tile that the player wants to move into.
   */
  NoDiscHexagon play(Reversi model);

  /**
   * Determines the Player whose turn it currently is.
   * @return The Player who is currently playing the game.
   */
  Player getPlay();
}
