
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * This class represents the Minesweeper game.
 *
 * @author Bee Yang (320)
 */
public class Minesweeper {

    // The number of rows and columns in the game board
    private int rows, cols;

    // The number of mines on the game board
    private int mines;

    // The number of coordinates that have been clicked so far
    private int clicked;

    // The game board
    private char[][] board;
    private boolean[][] mineBoard;

    // Is the game running?
    private boolean isRunning;


    private int iRow;	//input for row
    private int iCol;	//input for col


    /**
     * Constructs a Minesweeper game with the mines placed on random tiles
     *
     * @param rows an int argument
     * @param cols an int argument
     * @param mines an int argument
     */
    public Minesweeper(int rows, int cols, int mines) {

    	this.rows=rows;
    	this.cols=cols;
    	this.mines=mines;

    	initialize();

    } // Minesweeper

    /**
     * Constructs a Minesweeper game with the mines set according to the input
     * file.
     *
     * @param filename The name of the file.
     */
    public Minesweeper(String filename) {

    	initialize(filename);

    } // Minesweeper

    /**
     * Initializes the game board with the mines placed on random coordinates
     */
    public void initialize() {

	// Set the state variable, indicating that the game is now running.
    	isRunning();

	//Create and assign the array to the board instance variable. Then
	//set all the coordinates to 'e' for empty.

    	board=new char[rows][cols];

    	for (int i=0; i<rows; i++)
    	{

    		for (int i2=0; i2<cols; i2++)
    		{
    			board[i][i2]='e';
    		}

    	}


	//Create and assign the array to the mineBoard instance variable. Then
	//set all the coordinates to false.

    	mineBoard=new boolean [rows][cols];

    	for (int i=0; i<rows; i++)
    	{
    		for (int i2=0; i2<cols; i2++)
    		{
    			mineBoard[i][i2]=false;
    		}
    	}


	//Set the mines randomly. Make sure that you don't try to place
	//a mine in the same place more than once. Set coordinates with a
	//mine on them to true.

    	int plantedMines=0;	//counts number of mines on field
    	while(plantedMines<mines)
    	{
    	for (int i=0; i<rows; i++)
    	{
    		for (int i2=0; i2<cols; i2++)

    			if ((mineBoard[i][i2]==false)&&(plantedMines<mines)) //checks if there is a mine already
    			{
    				Random randomMine=new Random();
    				mineBoard[i][i2]=randomMine.nextBoolean();
    				if (mineBoard[i][i2]==true)
    					{
    					plantedMines++;
    					}
    			}
    	}
    	}
    } // initialize


    /**
     * Initializes the game board with the mines placed on random coordinates
     */
    public void initialize(String filename) {

	//Set the state variable, indicating that the game is now running.
    	isRunning();


	// Setup some stuff for simple file I/O
	File file = new File(filename);
	Scanner input = null;

	try {
	    input = new Scanner(file);
	} catch (FileNotFoundException e) {
	    System.out.println("The file was not found!");
	    System.exit(1);
	} // try

	// Assign values to the instance variables from the file
	this.rows = input.nextInt();
	this.cols = input.nextInt();
	this.mines = input.nextInt();

 	//Create and assign the array to the board instance variable. Then
	//set all the coordinates to 'e' for empty.

	board=new char[rows][cols];

	for (int i=0; i<rows; i++)
	{

		for (int i2=0; i2<cols; i2++)
		{
			board[i][i2]='e';
		}

	}

 	//Create and assign the array to the mineBoard instance
	//variable. Then set all the coordinates to false for empty.

	mineBoard=new boolean [rows][cols];

	for (int i=0; i<rows; i++)
	{
		for (int i2=0; i2<cols; i2++)
		{
			mineBoard[i][i2]=false;
		}
	}




	// Set the mines according to the input file.
	while (input.hasNextInt()) {
	    int r = input.nextInt();
	    int c = input.nextInt();
	    this.mineBoard[r][c] = true;
	} // while

    } // initialize


    /**
     * Displays the game board to the user.
     */
    public void displayBoard() {

	//         If a coordinate hasn't been clicked on yet and doesn't contain a
	//         number then print a space at its coordinate, even if it has a
	//         mine. If it has been clicked on then print an 'x' at its
	//         coordinate.
	//
	//         If a tile has a number at its coordinate then print that
	//         number.

    	for (int countRows=0; countRows<rows; countRows++)
		{
			System.out.print(countRows);
			for (int countCols=0; countCols<cols; countCols++)
				{
				if(countCols==0)
					{
					if (board[countRows][countCols]=='c')
					System.out.print(" "+"|"+"x"+"|");
					else if (board[countRows][countCols]=='e')
					System.out.print(" "+"|"+" "+"|");
					else
						System.out.print(" "+"|"+board[countRows][countCols]+"|");
					}
				else if (board[countRows][countCols]=='c')
				{

					System.out.print("x"+"|");

					if(countCols==cols-1)		//next line
						{
						System.out.println("");
						}
				}

				else if (board[countRows][countCols]=='e')
				{
				System.out.print(" "+"|");

				if(countCols==cols-1)		//next line
					{
					System.out.println("");
					}
				}
				else
				{
					System.out.print(board[countRows][countCols]+"|");
					if (countCols==cols-1)
					{
						System.out.println("");
					}
				}
				}
		}

		for (int countCols=0; countCols<cols; countCols++)
		{
			if (countCols==0)
			{
				System.out.print("   "+countCols);
			}
			else{
			System.out.print(" "+countCols);
			}
		}

    } // displayBoard

    /**
     * Prompts the user for input
     */
    public void promptUser() {

	// Create a Scanner object that uses System.in for input.

    	Scanner keyboard= new Scanner(System.in);

	// Print a prompt for the user, asking them for input.
    	String inputRow, inputCol;

    	System.out.print("\n\nPick a coordinate [row col] or press [q] to quit\n: ");
    	inputRow=keyboard.next();
    	if (inputRow.equalsIgnoreCase("q"))
    		{
    		System.out.println("Game Over");
    		isRunning=false;
    		System.exit(0);
    		}

    	iRow=Integer.parseInt(inputRow);

    	inputCol=keyboard.next();
    	iCol=Integer.parseInt(inputCol);


	//         Get input from the user, checking for errors. If the input is
	//         correct (e.g., two numbers that in bounds and have not
	//         already been clicked), then call the click method for desired
	//         coordinates. If the user wants to quit the game then make sure
	//         to update the boolean state variable and print out a message.

    	if ((inBounds(iRow, iCol)==false)||(board[iRow][iCol]=='c'))
    			{
    			System.out.println("The coordinate that you have chosen is either out of bounds or has already been clicked.");
    			}
    	else{
    		click(iRow, iCol);
    		}


    } // promptUser


    public void click(int row, int col) {

	//         If the coordinate that has been clicked contains a mine, then
	//         the game is over and the state variable should be changed
	//         accordingly.
    	if (mineBoard[row][col]==true)
    	{
    		System.out.println("You hit a mine! Game Over!");
    		isRunning=false;
    		System.exit(0);

    	}

	//         Else, the number of clicks should be incremented and the
	//         value 'c' should be assigned to that coordinate in the array.

    		clicked++;
    		board[row][col]='c';

    } // clickTile

    public void update() {

	//         First check to see if the user has won the game. You can do
	//         this easily since you know the following information:
	//          - the size of the board,
	//          - the number of mines, and
	//          - the number of tiles that have been clicked.
	//         If the user has won the game then set the boolean state
	//         variable accordingly and print out a nice message.
    		int winClicks=(rows*cols)-mines;
    		if (clicked==winClicks)
    		{
    			System.out.println("You have won!");
    			isRunning=false;
    			System.exit(0);
    		}

	//         Else, for every coordinate that is
	//          - in bounds,
	//          - hasn't been clicked, and
	//          - is adjacent to a coordinate that has been clicked,
	//         you need to determine the number of mines that are adjacent
	//         to that tile and assign that number to coordinate in the
	//         array.

    		for (int countRows=0;countRows<rows;countRows++)	//searches the board
    		{
    			for (int countCols=0;countCols<cols;countCols++)
    			{

    				if(!(board[countRows][countCols]=='c')) //makes sure it does not over-write the 'c'
    				{
    					for (int r=-1; r<=1; r++)
    					{
    						for(int c=-1; c<=1;c++)
    						{
    							if (inBounds(countRows+r, countCols+c)&&(!(r==0&&c==0))) //in bounds and not itself
    							{
    								if (board[countRows+r][countCols+c]=='c')	//if 'c'
    								{

    									int mineCount=getNumAdjacentMines(countRows, countCols);

    									if (mineCount==0)
    									{
    										board[countRows][countCols]=48;
    									}
    									else if (mineCount==1)
    									{
    										board[countRows][countCols]=49;
    									}
    									else if (mineCount==2)
    									{
    										board[countRows][countCols]=50;
    									}
    									else if (mineCount==3)
    									{
    										board[countRows][countCols]=51;
    									}
    									else if (mineCount==4)
    									{
    										board[countRows][countCols]=52;
    									}
    									else if (mineCount==5)
    									{
    										board[countRows][countCols]=53;
    									}else if (mineCount==6)
    									{
    										board[countRows][countCols]=54;
    									}
    									else if (mineCount==6)
    									{
    										board[countRows][countCols]=55;
    									}
    									else if (mineCount==7)
    									{
    										board[countRows][countCols]=56;
    									}
    									else if (mineCount==8)
    									{
    										board[countRows][countCols]=57;
    									}

    								}
    							}
    						}
    					}
    				}


    			}

    		}

    } // update

    /**
     * Returns true if and only if the coordinate is both in bounds and hasn't
     * been already been clicked.
     *
     * @param row an argument
     * @param col an argument
     * @return true if and only if coordinate is both in bounds and not clicked
     */
    private boolean inBounds(int row, int col) {

    	if ((row>=0)&&(row<=rows-1)&&(col>=0)&&(col<=cols-1))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}

    } // inBounds

    /**
     * Returns the number of mines adjacent to the desired coordinate.
     *
     * @param row an argument
     * @param col an argument
     * @return number of mines adjacent
     */
    private int getNumAdjacentMines(int row, int col) {

    	int mineCount=0;

    	for (int r=-1; r<=1; r++)
    	{
    		for (int c=-1; c<=1; c++)
    		{
    			if (inBounds(row+r, col+c)&&(!(r==0&&c==0))) //
    			{
    				if (this.mineBoard[row+r][col+c])
    				{
    					mineCount +=1;
    				}
    			}
    		}
    	}

    	return mineCount;

    } // getMineDistance


    /**
     * Returns the number of rows in the gameboard.
     *
     * @return number of rows in gameboard
     */
    public int getRows() {

    	return rows;

    } // getRowns


    /**
     * Returns the number of columns in the gameboard.
     *
     * @return number of columns in gameboard
     */
    public int getCols() {

    	return cols;

    } // getCols


    /**
     * Returns whether or not the game is currently running.
     *
     * @return true if game is running
     */
    public boolean isRunning() {

    	return true;


    } // isRunning

} // Minesweeper
