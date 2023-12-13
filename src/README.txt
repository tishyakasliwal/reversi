Overview
For our representation of the ReversiGame we created a 2 player game that alternates between
Player A who uses black discs and Player B who uses white discs. As a player, the goal of this game
is to have your given color of discs fill the majority of the board tiles. Our representation will be
using a hexagonal grid with a user inputted size. If the user chooses not to input a size the
game will be played on a 6x6 hexagonal board. The players of the game can either place their
tile on an empty hexagon cell or they can pass. The game is over when both players are unable to
make any legal moves or one of the players fills the board. The player with the most tiles on the
board is the winner.

****NOTE****
We have implemented the extra credit for Assignment 6 that can be found in the strategy package.
All the extra strategies have been tested in the test folder in the file ReversiStrategyTest.

QuickStart

@Test
  public void testInitialGridWithDifferentSizes() {
    init();
    ReversiModel smallGrid = new ReversiModel(3);
    Assert.assertEquals(19, smallGrid.getGrid().size());
  }
 The user will create an instance of the ReversiGame by inputting his or her desired board

 public enum Player {
     A(Color.BLACK), B(Color.WHITE);

     //represents the color of a player
     private final Color col;

     Player(Color col) {
       this.col = col;
     }

     public Color getColor() {
       return this.col;
     }
   }
This is a two player game so there will be two individuals playing against each other. In our
representation, we will have Player A start with black discs. After Player A goes, it is then
Player B's turn with Player B using white discs.

 /**
    * Tests a valid move made by the player with black discs.
    */
   @Test
   public void testValidMakeMove() {
     init();
     this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
     Assert.assertTrue(this.rm.getHexagon(4, 3, this.grid) instanceof FilledHexagon);
     Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(5, 4,
             this.rm.getGrid())).getColor());
     Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(6, 5,
             this.rm.getGrid())).getColor());
   }
 Allows the user to call the makeMove() method that moves the user's disc of a given
 color to a given tile. If a player has no valid moves, they automatically pass their turn.
 If both players have no valid moves, the game is over.

Key Components

Model:
    The model of our Reversi game is responsible for making the game functional. It consists of
    starting the game by creating a hexagonal grid, enforcing the rules of the game by determining
    what moves are legal and illegal, and determining when the game is over.

    The control flow of the model are driven by the updates to the current game state. These updates
    consist of checking whether the player's moves are legal and updating the board depending on
    the player's turns and moves. The components that are "driven" by the model include determining
    whether the game is over.

View:
    The view of our Reversi game is responsible for displaying the output of our game. Currently,
    we only have a textual view representation of our game. The view consists of placing X's for the
    player using black discs and O's for the player using white discs. If there are no discs in the
    cell it is represented by a "_".

    Our GUI is responsible for displaying a graphical visualization of the output of our game.
    It consists of grey hexagons that represent the tiles a player can move into. There are black
    and white circular discs which represents where each player has already moved to. In order
    to select a tile a player can click on the tile and it will highlight to a teal color.

    The control flow of the view is driven by the changes in the game state that are handled by the
    model. The component's that are getting "driven" in the view include the toString() that is
    responsible for rendering the latest changes to the model.

Controller:
    For our representation of Reversi we included two controllers because users have the
    opportunity to play manually as "human" players or using an AI as machine players. Both
    the human controller and the machine controller allow the given player to move, pass
    their turn, and handle switching the player's turn. However, the difference between
    the controllers is that a human controller allows you to manually make your own move
    whereas a machine controller uses a strategy of the users choosing.

    The control flow of the controllers are driven by the features interface which is
    responsible for updating the view whenever a move has been made. The components that are
    getting "driven" in the controller include the view which is responsible for
    displaying a visual representation


Strategy
    For our game of Reversi we have 5 strategies that a player can choose from in order
    to maximize their chances of winning the game. The first strategy involves choosing
    a tile that captures as many tiles as possible. The second strategy involves avoiding
    the tiles next to the corner tiles. The next strategy prioritizes placing tiles on the
    corners. The fourth strategy involves minimizing the maximum moves the opponent can make.
    Lastly, our fifth strategy allows a player to take all the previous strategies
    and combine them any way they choose in order to create the most optimal strategy.

Key Subcomponents

Within the model:
    Creating the board:
    The initial grid is responsible for creating the hexagonal grid at the start of the game. It
    creates a new instance of a hexagon and fills the tiles surrounding the center tile with
    alternating black and white discs. This component is used to create a board that will then
    be used to enforce the rules of the game and determine whether a game is legal/illegal.

    Coordinate System:
    We created our coordinate system for our Hexagonal grid by first filling the rows of the
    grid and then filling the diagonals. By using this coordinate system we were able to
    access the diagonals of each tile which made it more efficient when linking the surrounding
    tiles to each other and switching the color of the surrounding tiles.

    Instantiating a tiles neighbors:
    After creating the grid, it needs to be linked so that each tile has a left, right, top right,
    top left, bottom left, and bottom right neighbor which is why this component exists. It is used
    while updating the game state because once a disc is placed on a tile the tiles neighbors
    colors may change based on whether the player captured the other player's tiles.

    Allowing the Player to make moves:
    This component is responsible for enforcing the rules of the game. It checks whether the current
    player is making legal or illegal moves. The result of a legal move is that all of the opposite
    player's discs in all directions that are sandwiched between two discs of the current player get
    flipped to the current player's. After a valid move is made, the player's turn is changed. If
    the current player cannot make a move, their move is passed on to the next player. This component
    is used to update the game state as the game progresses.

    Player Interface:
    This component allows for different types of players to be implemented and play the game. Any
    player can keep track of the tiles currently captured by them. We also included a method to
    identify the name of the player

Within the view:
    Fill Board:
    This first component is responsible for filling the game board with either "_", "X", or "O"
    depending on whether the tile is empty, has a black disc, or white disc. It is used
    when creating a string output of the given hexagonal tile.

    Get Hexagonal Grid:
    This component is responsible for filling in the "_", "X", or "O" in a hexagonal pattern. It
    loops through the grid created by the model and determines whether the current grid has
    tiles that are filled or empty. This method is used in the to string method to produce a
    text view visualization of the current game state.

    To String:
    This component produces the completed string output of the game state. It calls both the fillBoard
    and getHexagonalGrid methods.

    Creating the GUI:
    We used Java Swing to implement our GUI. We created a JFrame which represents the window that
    the game will be played on and then the JPanel class is responsible for drawing the
    grid onto the board. To make our view clickable we used MouseAdapter that allows us to
    get the coordinates of where the mouse was pressed. Once a user presses a certain tile on
    the board it will highlight to a teal color. In order to deselect the tile the user can either
    press it again, click another tile, or click outside the game board. To allow the user to press
    the key "m" to make a move or  "p" to pass their turn we used KeyAdapter.

Within the controller:
    ReversiController:
        Our ReversiController is responsible for communicating between the view and the
        model. It's main job is to determine which player's turn it is and communicate
        that to both the view and the model.
    ModelStatusObservers:
        In order to implement our controllers and have them communicate with our view
        and model we utilized the observer pattern. Therefore, our ModelStatusObservers
        interface represents the interface that contains methods for communicating
        with our model to tell each player when it is their turn and
        with the view to display a popup notification on the screen notifying the players.
    PlayerActions:
        Our PlayerActions interface is what determines whether the player is making a move
        or passing their turn.
    HumanController:
        The HumanController class is responsible for representing a human player. It registers
        itself as part of both the features interfaces in order to allow the player to make
        a move, pass their turn, and then effectively changes the player's turn to the
        next player.
    MachineController:
        The MachineController class is responsible for representing an AI player. It also
        registers itself as part of both the features interfaces. However, since the
        MachineController represents an AI player, the way it makes its moves is through
        any of the five strategies given: CaptureMaxTiles, AvoidCorners, AnyOpenCorner,
        Minimax, and ExtraCredit.


Within the strategy:
    CaptureMaxTiles:
        This strategy is responsible for finding the tile that will allow the player to
        capture the most tiles possible. It operates by iterating through the entire
        game board and determining how many tiles a given tile will capture and finds the
        hexagon that generates the maximum number of tiles captured. Ties are broken by
        choosing the upper left most coordinate.
    AvoidCorners:
        This strategy is responsible for avoiding the tiles next to the corner tiles. It operates
        by looping through the game board and returning a tile only if it is not a corner tile
        or the tiles neighboring a corner tile.
    AnyOpenCorner:
        This strategy is responsible for trying to access the corner tiles first. It works by
        determining whether any of the corner tiles are available and choosing those tiles
        before returning any other tile on the game board.
    Minimax:
        This strategy allows the user to try and guess what strategy their opponent is
        using so they can make a move that forces their opponent to capture the least
        amount of tiles possible. In other words, the strategy is responsible for
        minimizing the maximum moves the opponent can make.
    ExtraCredit:
        This strategy combines allows a player to chain together different
        combinations of the strategies we defined previously in order to
        create the most optimal strategy.


Source Organization:
    Source Directory:
        ReversiModel Package -->
            The Player Interface:
                An interface created so that Human or AI players can interact with the model.
            There are 4 classes designated for describing a Hexagon tile.
                AbstractHexagon: Represents the common template of a hexagon tile in this design
                EmptyHexagon: An tile with no coordinates
                NoDiscHexagon: A tile with coordinates but no disc
                FilledHexagon: A tile with a disc inside of it of a given color
            ReversiModel:
                Where the functionality of the model is implemented. Creates the hexagonal grid,
                ensures moves are legal, updates the game board, and determines whether the game
                is over.
            ReversiModelDeepCopy:
                A class dedicated to cloning the ReversiModel and by creating a deep copy
                of it. This class is necessary so that we can access a copy of the board at
                any given time.
            ReversiMockModel:
                A mock class that mocks the model that is necessary in order to effectively
                test the Reversi strategies.
        View Package -->
            ReversiView:
                An interface designed to describe what the JFrame that renders our GUI
                is capable of.
            ReversiGraphicsView:
                The class responsible for rendering our GUI. It involves creating the window
                that the game will be played on, adding the mouse and key functionalities so that
                users can press on tiles and indicate when they want to move or pass, and refreshes
                the GUI after click has been made.
            ReversiPanelView:
                This is the class responsible for drawing the hexagonal grid onto the window.
                It creates a single hexagon using Path2D.Double and then creates the hexagon
                of hexagons by using the built-in Graphics2D functions.
            ReversiMouse:
                This class is responsible for making the view clickable. It involves
                identifying when a particular tile has been clicked, printing the logical
                coordinates of the selected tile, and changing the color of the tile for
                when it is selected vs deselected.
            ReversiKey:
                This class indicates when a user presses a key and whether they make a move
                or pass their turn. For our representation we decided that when the user presses
                "m" that allows them to make a move and when they press "p" they will be
                able to pass their turn.
            ReversiTextualView:
                Represents the textual view output of the Reversi game.
        Controller package -->
            HumanController:
                The controller that represents a human player. The human player
                is able to manually choose their own tile to move into
                or pass their turn and depending on their choice it gets communicated
                to the view and model accordingly.
            MachineController:
                The controller that represents an AI player. Based on a given strategy,
                the AI player is able to make a move or pass their turn which
                gets communicated to the view and model accordingly.
            ModelStatusObservers:
                One of the features interfaces, it is responsible for determining
                the current status of the game and updating the game state
                accordingly. Communicates with the view and the model in order
                to notify both components that the players turns are changing.
            PlayerActions:
                One of the features interfaces, it is responsible for allowing the
                player to each make a move or pass their turn and communicating
                what the player chooses to both the model and the view.
            ReversiController:
                Our ReversiController is responsible for communicating between the view and the
                model. It's main job is to determine which player's turn it is and communicate
                that to both the view and the model.
        ReversiMain -->
            The class with our main method which makes our representation runnable.
            Command Line Arguments:
                In order to play our representation of the Reversi game users are able to
                choose whether they want to have a 2 player game with both human players,
                a 2 player game with one human player one AI player, or both AI players.
                If a user wants to play with an AI player they must choose a strategy as
                well. In our implementation we have 5 strategies: CaptureMaxTiles, AvoidCorners,
                AnyOpenCorner, Minimax, or ExtraCredit which are labeled as strategy1, strategy2,
                strategy3, strategy4, strategy5 respectively.
                To select the type of game wanted users can enter:
                (Note: these are just a few examples of game options, there are many more
                       combinations possible)
                    human human (for a both human players)
                    human machine strategy1/ machine strategy1 human (for a combination of
                    human and AI players)
                    machine strategy2 machine strategy1 (for 2 AI players)
        Strategy -->
            ReversiStrategy:
                Represents our strategy interface that allows a player to choose where
                they should move next to maximize their score.
            CaptureMaxTiles:
                Our first strategy, allows the user to choose the tile that captures
                the most tiles around it. This will increase the user's chances of
                winning since they will always have as many tiles as possible.
            AvoidCorners:
                This strategy is responsible for avoiding the tiles next to the corner tiles. By
                avoiding the tiles next to the corners the user will able to prevent their
                opponent from accessing the corner tiles during their next turns.
            AnyOpenCorner:
                This strategy allows the user to try and access the corner tiles first. By doing so,
                users will be able to maximize their score because corner tiles do not have
                tiles on either side of them.
            Minimax:
                This strategy allows the user to try and guess what strategy their opponent is
                using so they can make a move that forces their opponent to capture the least
                amount of tiles possible. In other words, the strategy is responsible for
                minimizing the maximum move the opponent can make.
            ExtraCredit:
                This strategy combines allows a player to chain together different
                combinations of the strategies we defined previously in order to
                create the most optimal strategy.


    Test Directory:
        ReversiModelTest:
            Contains the tests related to the model. In other words, tests to ensure the
            game has been started, the board is created correctly, moves are legal/illegal, and
            the game is over.
        ReversiHelperTest:
            Contains the tests related to the model that help with functionality between
            the different classes.
        ReversiViewTest:
            Contains test to ensure the board is being rendered correctly and that it can be
            rendered based on different board sizes.
        ReversiStrategyTest:
            Contains the tests related to the strategies. Instantiates a mock model that
            allows us to force the strategy to think that only a pre-determined move is valid.

Changes For Part 2:
    Model Changes:
        - Created an interface for our ReversiModel. We did this because it was necessary
          before separating our interfaces into a ReadOnlyReversiModel and the
          Reversi interface that contained our mutable methods.
        - Changed the way we initialized the bottom half of our hexagonal grid because once
          we started working on the view we realized that the diagonals of the right side
          of the grid were not being calculated correctly resulting in a misshapen GUI.
          We implemented this change by fixing the way our grid was initialized.
        - Fixed the passing of a player's turn because it was a necessary component of the
          game. We implemented this change by creating helper methods that switched the
          player's turn.
        - Created a copy of the board. By creating a deep copy, we were able to use it in our
          strategies when we needed to access the board at any time.


Changes for Part 3:
    Model Changes:
        - Abstracted some of the long methods
    View Changes:
        - Implemented a features interface to properly create
          callbacks and listeners.
        - Made sure the textual view took in a ReadOnlyReversi model rather
          than the mutable ReversiModel


