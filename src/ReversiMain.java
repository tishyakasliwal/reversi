import java.util.Scanner;

import controller.HumanController;
import controller.MachineController;
import controller.ModelStatusObservers;
import model.Player;
import model.ReversiModel;
import model.ReversiPlayerImpl;
import strategy.AnyOpenCorner;
import strategy.AvoidCorners;
import strategy.CaptureMaxTiles;
import strategy.Minimax;
import strategy.ReversiStrategy;
import view.ReversiGraphicsView;
import view.ReversiView;

/**
 * A class to implement the main method and serve as the entry-point for the Reversi game.
 */
public final class ReversiMain {


  static ModelStatusObservers rc1 = null;
  static ModelStatusObservers rc2 = null;
  static ReversiModel rm = new ReversiModel();
  static ReversiView view = new ReversiGraphicsView(rm);
  static ReversiView view2 = new ReversiGraphicsView(rm);

  /**
   * A main method to allow the user to choose different game variants from the command line,
   * when running the program.
   */
  public static void main(String[] args) {

    StringBuilder str = new StringBuilder();
    String sep = "";
    for (int i = 0; i < args.length; i++) {
      str.append(sep).append(args[i]);
      sep = " ";
    }
    String read = str.toString();
    Scanner scan = new Scanner(read);
    int count = 0;

    while (scan.hasNext()) {
      String s = scan.next();
      count ++;
      switch (s) {
        case "human":
          try {
            if (count == 1) {
              rc1 = new HumanController(rm, Player.A, view);
            } else if (count == 2) {
              rc2 = new HumanController(rm, Player.B, view2);
            }
          }
          catch (NullPointerException e) {
            System.out.print("Invalid input.");
          }
          break;
        case "strategy1":
          mainHelper(new CaptureMaxTiles(), count);
          break;
        case "strategy2":
          mainHelper(new AvoidCorners(), count);
          break;
        case "strategy3":
          mainHelper(new AnyOpenCorner(), count);
          break;
        case "strategy4":
          mainHelper(new Minimax(), count);
          break;
        default:
          System.out.println("Score: 0");
      }
    }

    try {
      rm.addObservers(rc1);
      rm.addObservers(rc2);
      view.makeVisible();
      view2.makeVisible();
      rc1.setView(view);
      rc2.setView(view2);
      rm.startGame();
    }
    catch (Exception e) {
      System.out.println("Game could not be started because of invalid input");
    }
  }

  // helper method that handles the try catch inside each case and creates the 2 controllers
  // used in the game
  private static void mainHelper(ReversiStrategy rs, int count) {
    try {
      if (count == 1) {
        ReversiPlayerImpl player1 = new ReversiPlayerImpl(Player.A, rs);
        rc1 = new MachineController(rm, player1, view);
      } else if (count == 2) {
        ReversiPlayerImpl player1 = new ReversiPlayerImpl(Player.B, rs);
        rc2 = new MachineController(rm, player1, view2);
      }
    }
    catch (NullPointerException e) {
      System.out.print("Invalid input");
    }
  }
}