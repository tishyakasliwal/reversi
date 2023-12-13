package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import controller.PlayerActions;
import model.AbstractHexagon;
import model.ReadOnlyReversi;

/**
 * This is an implementation of the ReversiView interface
 * that uses Java Swing to draw the initial hexagonal grid of a Reversi board.
 * It also allows users to select and deselect tiles and prints out the
 * coordinate that was selected in the console.
 */
public class ReversiGraphicsView extends JFrame implements ReversiView {

  private final ReadOnlyReversi model;
  private final ReversiPanelView panel;

  /**
   * Constructs a ReversiGraphicsView. Involves creating a JPanel
   * and instantiates the mouse and key listeners.
   * @param model The ReadOnlyReversi model that provides the
   *              functionality for the view.
   */
  public ReversiGraphicsView(ReadOnlyReversi model) {
    super();
    this.panel = new ReversiPanelView(model);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // instantiates a JPanel and adds it to the frame
    this.add(panel);
    this.pack();


    this.model = Objects.requireNonNull(model);

    // adds the mouse and key listener
    this.addClickListener();
    this.addKeyListener(new ReversiKey());
    this.setLocationRelativeTo(null);
  }

  /**
   * Shows or hides the GUI based on what the visibility is
   * set to.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Calls the windows paint component to refresh the
   * game board.
   */
  @Override
  public void refresh() {
    this.repaint();
  }


  /**
   * Adds a mouse listener to the JFrame to make the window
   * clickable.
   */
  @Override
  public void addClickListener() {
    this.panel.addMouseListener(new ReversiMouse(this.model, this.panel));
    this.refresh();
  }

  /**
   * Responsible for notifying the view that a player wants
   * to either move or pass their turn. If the player
   * presses 'm' they will be able to move to a given
   * tile and if the player presses 'p' they will be able
   * to pass their turn.
   * @param features Contains information about the given
   *                 players requests for their turn.
   */
  @Override
  public void addFeatures(PlayerActions features) {
    this.addKeyListener(new KeyListener() {

      /**
       * Determines what the user typed.
       * @param e the event to be processed
       */
      @Override
      public void keyTyped(KeyEvent e) {
        // Determines which key the user typed.
      }

      /**
       * Determines which key the user pressed. For our implementation,
       * if the user presses "m", then they can make a move. If the user
       * preses "p", then they can pass their turn.
       * @param e the event to be processed.
       */
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
          System.out.print("user pressed key m in add features");
          features.playerMove();

        }
        else if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
          System.out.print("user pressed key p in add features");
          features.playerPass();

        }
      }

      /**
       * Determines which key the user released.
       * @param e the event to be processed
       */
      @Override
      public void keyReleased(KeyEvent e) {
        // Determines which key the user released.
      }
    });
  }


  /**
   * Accesses the tile that has been selected most recently.
   * @return A tile that currently has no disc in it.
   */
  @Override
  public AbstractHexagon selectedTile() {
    return this.panel.getLastSelection();
  }

  /**
   * Provides a notification in the view when there is a change
   * in the current game state.
   * @param message A given message that needs to be displayed.
   */
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Information", JOptionPane.PLAIN_MESSAGE);

  }

  /**
   * Provides a notification in the view when the player
   * has caused an error that causes a change in the game
   * state.
   * @param message A given message that needs to be displayed.
   */
  public void showMessageInvalidMoves(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Error Message", JOptionPane.ERROR_MESSAGE);

  }

}
