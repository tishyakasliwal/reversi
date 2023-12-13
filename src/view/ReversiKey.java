package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * An implementation of the KeyAdapter class. This class
 * is responsible for detecting which keys the user is pressing,
 * typing, or when they release these keys.
 */
public class ReversiKey extends KeyAdapter {


  /**
   * Determines which key the user pressed. For our implementation,
   * if the user presses "m", then they can make a move. If the user
   * preses "p", then they can pass their turn.
   * @param e the event to be processed.
   */
  @Override
  public void keyPressed(KeyEvent e) {

    if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
      System.out.println("user pressed key m");
    }
    else if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
      System.out.println("user pressed key p");

    }
  }
}
