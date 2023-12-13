package model;

/**
 * A class responsible for making a deep copy of the
 * ReversiModel.
 */
public class ReversiModelDeepCopy implements Cloneable {

  // We made our model field public because we needed to
  // access it in our strategy package in order to
  // implement the MiniMax strategy
  public Reversi model;

  /**
   * Constructs a ReversiModelDeepCopy.
   * @param model The ReversiModel instance that this class
   *              is copying.
   */
  public ReversiModelDeepCopy(Reversi model) {
    this.model = model;
  }

  @Override
  public ReversiModelDeepCopy clone() throws CloneNotSupportedException {
    ReversiModelDeepCopy cloned = (ReversiModelDeepCopy) super.clone();
    // Perform deep copy for mutable objects
    cloned.model = new ReversiModel(this.model.gameState(), this.model.getPlayer(),
            this.model.getBoardSize(), this.model.getGrid());
    return cloned;
  }
}







