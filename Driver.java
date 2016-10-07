/**
 * This class represents the driver for the game.
 */
public class Driver {

    public static void main(String[] args) {

	Minesweeper game = new Minesweeper(10, 10, 10);

	while (game.isRunning()) {
	    game.displayBoard();
	    game.promptUser();
	    game.update();
	} // while

    } // main

} // Driver