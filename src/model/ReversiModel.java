package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.ModelStatusObservers;


/**
 * A representation of a ReversiModel game. Our game will be played on a hexagonal grid of a user
 * inputted size. If the user does not input a size the default is 6x6. This is a two Player
 * game with the first player having black discs to move and the second player having white
 * discs to move. The game is over when either player can no longer make a legal move or
 * if the board is completely full.
 */
public class ReversiModel implements Reversi {


  // current state of the game
  private boolean gameStarted;

  // list of tiles in the board of the game
  private List<NoDiscHexagon> grid;

  // represents the player who is not next in turn.
  private Player turn;

  // the board size representing the number of tiles along the length of each side of the hexagonal
  // board (user inputted)
  // INVARIANT: the size is always greater than 1
  private final int size;

  private List<ModelStatusObservers> controllers = new ArrayList<>();

  /**
   * Constructor for ReversiModel. Game start state is set to false and the size of the board is
   * based on the input of the user. The game board is initialized to represent its state at the
   * start of the game. The initial turn is set to Player B.
   *
   * @throws IllegalArgumentException if the size of the board is less than or equal to 0.
   */
  public ReversiModel(int size) {
    this.gameStarted = false;
    this.turn = Player.A;
    if (size <= 1) {
      throw new IllegalArgumentException("Size must be greater than 1");
    }
    this.size = size;
    this.grid = this.linkedGrid(this.initialGrid(this.size));

  }

  /**
   * Default Constructor for ReversiModel.
   */
  public ReversiModel() {
    this.gameStarted = false;
    this.turn = Player.A;
    // default board size
    this.size = 6;
    this.grid = this.linkedGrid(this.initialGrid(this.size));


  }

  /**
   * Constructor for ReversiModel used to create deep clones of the model.
   */
  public ReversiModel(boolean gameStarted, Player player, int size, List<NoDiscHexagon> grid) {
    this.gameStarted = gameStarted;
    this.turn = player;
    this.size = size;
    this.grid = grid;

  }




  /**
   * Returns the current state of the game.
   * @return True if the game has started.
   */
  public boolean gameState() {
    return this.gameStarted;
  }

  /**
   * Returns the initial list of tiles in the game board of given size with no neighbors linked.
   *
   * @return the board
   */

  public List<NoDiscHexagon> initialGrid(int size) {
    // Maintains the invariant that the size is greater than 1 because an illegal argument
    // exception is thrown
    if (size <= 1) {
      throw new IllegalArgumentException("Size must be greater than 1");
    }
    List<NoDiscHexagon> grid = new ArrayList<>();
    int x = size - 1;
    for (int i = 0; i < size; i++) {
      x++;
      for (int j = 0; j < x; j++) {
        if (i == size - 1 && j == size - 2 || i == size - 2 && j == size - 1) {
          FilledHexagon hex = new FilledHexagon(i, j, Color.WHITE);
          grid.add(hex);
        } else if (i == size - 2 && j == size - 2 || i == size - 1 && j == size) {
          FilledHexagon hex = new FilledHexagon(i, j, Color.BLACK);
          grid.add(hex);
        } else {
          NoDiscHexagon hex = new NoDiscHexagon(i, j);
          grid.add(hex);
        }
      }
    }

    int p = 2 * size - 1;

    int k = 0;
    for (int m = size; m < 2 * size - 1; m++) {
      k++;
      for (int n = k; n < p; n++) {
        if (m == size && n == size) {
          FilledHexagon hex = new FilledHexagon(m, n, Color.WHITE);
          grid.add(hex);
        } else if (m == size && n == size - 1) {
          FilledHexagon hex = new FilledHexagon(m, n, Color.BLACK);
          grid.add(hex);
        } else {
          NoDiscHexagon hex = new NoDiscHexagon(m, n);
          grid.add(hex);
        }
      }
    }
    return grid;
  }


  /**
   * Returns the tile with the given coordinates in the given list of tiles.
   *
   * @param i    the 0-based index (from the left) of the diagonal coordinate of the tile.
   * @param j    the 0-based index (from the top) of the row coordinate of the tile.
   * @param grid the grid of tiles to find the tile of given coordinates in.
   */
  public AbstractHexagon getHexagon(int i, int j, List<NoDiscHexagon> grid) {
    NoDiscHexagon hex = null;

    for (NoDiscHexagon h : grid) {
      if (h.getDiagonal() == i && h.getRow() == j) {
        hex = h;
        break;
      }
    }
    if (hex == null) {
      return new EmptyHexagon();
    } else {
      return hex;
    }
  }

  /**
   * Returns the list of tiles in the game board with all of its neighbors linked.
   *
   * @param grid is the list of board tiles with no neighbors linked.
   * @return the board
   */
  public List<NoDiscHexagon> linkedGrid(List<NoDiscHexagon> grid) {

    for (NoDiscHexagon hex : grid) {
      int i = hex.getDiagonal();
      int j = hex.getRow();
      if (i == 0) {
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i, j + 1, grid));
      } else if (j == (2 * this.size - 2)) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));

      } else if (i == (2 * this.size - 2)) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
        hex.setBottomLeft(this.getHexagon(i, j + 1, grid));
      } else if (j == 0) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i, j + 1, grid));
      } else if (i - j == (2 * this.size - 2) / 2) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setBottomLeft(this.getHexagon(i, j + 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
      } else if (j - i == (2 * this.size - 2) / 2) {
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));

      } else {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setBottomLeft(this.getHexagon(i, j + 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
      }
    }
    return grid;
  }


  /**
   * Starts the game by setting the current state of the game to started and notifying the
   * observers that it is the first player's turn.
   */
  public void startGame() {
    this.gameStarted = true;
    for (ModelStatusObservers ms: controllers) {
      ms.changePlayer();
    }
  }

  /**
   * Changes the turn to the next player.
   */
  public void nextPlayer(Player who) {
    if (who.equals(Player.A)) {
      this.turn = Player.B;
    }
    else {
      this.turn = Player.A;
    }
    for (ModelStatusObservers ms: controllers) {
      ms.changePlayer();
    }
  }

  /**
   * Returns the size of this game board.
   *
   * @return the size of the board.
   */
  public int getBoardSize() {
    return this.size;
  }

  /**
   * Returns grid that the game is being played on.
   *
   * @return the hexagonal grid that the game is being played on.
   */
  public List<NoDiscHexagon> getGrid() {
    return this.grid;
  }

  /**
   * Returns the player whose turn it is.
   */
  @Override
  public Player getPlayer() {
    return this.turn;
  }



  /**
   * Moves a disc of the current player's color to the given tile. The result of a legal move is
   * that all of the opposite player's discs in all directions that are sandwiched between two
   * discs of the current player get flipped to the current player's. After a valid move is made,
   * the player's turn is changed. If the current player cannot make a move, their move is
   * passed on to the next player.
   *
   * @param who   the Player who wants to make a move
   * @param where the tile where the current player wants to make a move
   * @throws IllegalStateException    if the game hasn't been started yet
   * @throws IllegalArgumentException if the wrong player is making a move or if the player or tile
   *                                  to make a move in is an invalid null input
   * @throws IllegalStateException    if the tile is not a tile with no disc in it.
   */
  public void makeMove(Player who, AbstractHexagon where) {
    // illegal state exceptions for if the game hasn't been started or if the player
    // is trying to move to an empty spot
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started");
    } else if (who == null || where == null) {
      throw new IllegalArgumentException();
    }
    // cannot put a disc in a hexagon that is empty or already has a disc in it
    else if (where instanceof EmptyHexagon || where instanceof FilledHexagon) {
      throw new IllegalStateException();
    }
    // throw an illegal argument when the player is incorrect
    else if (who != this.turn) {
      throw new IllegalArgumentException();
    }
    // if one of the neighbors have a hexagon with a disc in it and the subsequent hexagons make for
    // a valid move we can make the changes to the board accordingly

    else if ((!((NoDiscHexagon) where).allNeighborsEmpty())
            && !where.checkAll((((NoDiscHexagon) where).getFilledHexagons(who.getColor())),
            (NoDiscHexagon) where, who.getColor()).isEmpty()) {
      List<List<FilledHexagon>> l =
              where.checkAll((((NoDiscHexagon) where).getFilledHexagons(who.getColor())),
                      (NoDiscHexagon) where, who.getColor());
      NoDiscHexagon h = (NoDiscHexagon) where;
      FilledHexagon newH = new FilledHexagon(h.getDiagonal(),
              h.getRow(), h.getRight(), h.getLeft(), h.getTopRight(), h.getTopLeft(),
              h.getBottomRight(), h.getBottomLeft(), who.getColor());
      this.grid.set(this.getGrid().indexOf(where), newH);
      this.changeNeighbors(h, newH);
      for (List<FilledHexagon> lofh : l) {
        for (FilledHexagon hex : lofh) {
          FilledHexagon newHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
                  hex.getRight(), hex.getLeft(), hex.getTopRight(), hex.getTopLeft(),
                  hex.getBottomRight(), hex.getBottomLeft(), who.getColor());
          this.grid.set(this.getGrid().indexOf(hex), newHex);
          this.changeNeighbors(hex, newHex);
        }
      }
      for (ModelStatusObservers ms: controllers) {
        ms.refreshView();
      }
      this.nextPlayer(who);
    }

    // current player cannot make a move and their turn is passed
    else {
      throw new IllegalArgumentException();
    }
  }

  // updates the linking of neighbor tiles after a valid move has been made in one tile
  private void changeNeighbors(NoDiscHexagon oldHex, FilledHexagon hex) {
    oldHex.getRight().setLeft(hex);
    oldHex.getLeft().setRight(hex);
    oldHex.getBottomLeft().setTopRight(hex);
    oldHex.getBottomRight().setTopLeft(hex);
    oldHex.getTopLeft().setBottomRight(hex);
    oldHex.getTopRight().setBottomLeft(hex);
  }

  /**
   * Returns the current score for the given player, which is the sum of the number of tiles
   * filled with a disc of the color of that player.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  public int getScore(Player who) {
    ArrayList<FilledHexagon> tiles = new ArrayList<>();
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }

    for (NoDiscHexagon hex : this.grid) {
      if (hex instanceof FilledHexagon) {
        if (((FilledHexagon) hex).getColor().equals(who.getColor())) {
          tiles.add((FilledHexagon) hex);
        }
      }
    }
    return tiles.size();
  }

  @Override
  public AbstractHexagon getHex(int i, int j) {
    NoDiscHexagon hex = null;

    for (NoDiscHexagon h : this.grid) {
      if (h.getDiagonal() == i && h.getRow() == j) {
        hex = h;
        break;
      }
    }
    if (hex == null) {
      return new EmptyHexagon();
    } else {
      return hex;
    }
  }

  /**
   * Signal if the game is over or not.  A game is over if there are no more
   * possible moves to be made by both players.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */

  public boolean gameOver() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    for (NoDiscHexagon hex : this.grid) {
      if (!(hex instanceof FilledHexagon)) {
        if ((!hex.allNeighborsEmpty())
                && ((!hex.checkAll(((hex).getFilledHexagons(Color.BLACK)),
                hex, Color.BLACK).isEmpty())
                || (!hex.checkAll(((hex).getFilledHexagons(Color.WHITE)),
                hex, Color.WHITE).isEmpty()))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Adds the observer to the list of observers that need to observe the model.
   * @param rc the Model Status Observer
   */
  @Override
  public void addObservers(ModelStatusObservers rc) {
    controllers.add(rc);
  }
}
