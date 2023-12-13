package model;

import java.awt.Color;

/**
 * Represents the players of the game.
 */
public enum Player {
  A(Color.BLACK), B(Color.WHITE);

  private final Color col;

  Player(Color col) {
    this.col = col;
  }

  public Color getColor() {
    return this.col;
  }
}
