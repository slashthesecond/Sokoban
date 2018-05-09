package uk.ac.forthvalley.examples;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import uk.ac.forthvalley.examples.Direction;
import uk.ac.forthvalley.examples.GameToken;
import uk.org.asch.swing.GridPanel;

/**
 * Manages a grid of sprites, keeping track of locations and collisions, and
 * updating sprites accordingly. The sprites live in a square grid of fixed
 * size.
 * 
 * Project: Sokoban
 * 
 * @author Natalie Gardner And andreas.schoter
 * @param <tkn>
 *            Passes in game token
 */
public class GridManager<tkn> {
	/**
	 * The grid of sprites, an array of arrays
	 */
	private GameToken[][] grid;

	/**
	 * The panel used to display the grid
	 */
	private GridPanel gridPanel;

	/**
	 * Movement grid for game
	 */
	private MovementGrid movementGrid;

	/**
	 * The currently active sprite. which is dolphin
	 */
	private GameToken currentSprite;

	/**
	 * Array list of diamonds
	 */
	private ArrayList<Diamond> diamonds = new ArrayList<>(); // using this so i
																// don't have to
																// specify
																// number of
																// diamonds

	/** 
	 * Specifies which level the game begins at. 
	 * It begins at level 1.
	 */
	private int levelNo = 1; // when grid loads, the game begins at level 1

	/**
	 * Construct a manager for the given grid panel.
	 * 
	 * @param gridPanel
	 *            - the panel to display the grid is passed in and stored as a
	 *            variable
	 */

	public GridManager(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		grid = new GameToken[gridPanel.getGridSize().height][gridPanel.getGridSize().width];
	}

	/**
	 * Construct a manager for the given grid panel.
	 * 
	 * @param gridPanel
	 * @param movementGrid
	 *            - the movementGrid and gridPanel are passed in and stored as
	 *            variables
	 * 
	 */
	public GridManager(MovementGrid movementGrid, GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		grid = new GameToken[gridPanel.getGridSize().height][gridPanel.getGridSize().width];
		this.movementGrid = movementGrid;

	}

	/**
	 * Increments the level counter as the player proceeds through each level
	 * 
	 * @returns levelNo++ (level number plus one incremented)
	 */

	public void incrementLevelCount() {
		this.levelNo++;// Increments the level counter

	}

	/**
	 * Returns the current level number
	 *  
	 * @returns levelNo - Current level number
	 */

	@SuppressWarnings("javadoc")
	public int getLevelNo() {// returns the level number
		return this.levelNo;
	}

	/**
	 * Add a new sprite at a physical location. The physical location must be
	 * converted to a logical location in the grid.
	 * 
	 * @param p
	 *            the physical location for the new sprite.
	 * @param t
	 *            the game token, tkn
	 * @return the new sprite, or null if the sprite could not be added.
	 * @exception ("oops")
	 *                if invalid case is met
	 */
	public GameToken addSprite(Point p, tkn t) {

		char token = t.toString().charAt(0);
		/**
		 * switch statement executes all statements that follow the matching
		 * case label
		 */
		GameToken gameToken = null;

		switch (token) {

		case 'w':// if there is a Wall (w=wall)

			gameToken = new Wall();// A wall gametoken is added
			break;

		case 'c':// If there is a Crate (c=Crate)

			gameToken = new Crate();// A crate gametoken is added
			break;

		case 'd':// If there is a Dolphin Warehouse keeper (d=Dolphin)

			gameToken = new Dolphin();// A Dolphin gametoken is added
			setCurrent(gameToken);
			break;

		case 'z':// if there is a Diamond (z=Diamond)

			gameToken = new Diamond(); // A Diamond gametoken is added
			diamonds.add((Diamond) gameToken);
			break;
		default:

			new RuntimeException("oops");// Error handing if no cases are met
			break;

		}

		Point gridP = gridPanel.convertFromPhysical(p);
		if (grid[gridP.y][gridP.x] != null) { // if there is something in the
												// grid location already
			return null;
		}
		grid[gridP.y][gridP.x] = gameToken;
		Point panelP = gridPanel.convertFromLogical(gridP);

		gameToken.setInitialPosition(panelP.x, panelP.y);

		grid[gridP.y][gridP.x] = gameToken;

		return gameToken;
	}

	/**
	 * Make the given label the currently active sprite, updating icon images as
	 * necessary. Sets the current active sprite as a dolphin
	 * 
	 * @param d
	 *            game token dolphin (warehouse keeper)
	 */

	public void setCurrent(GameToken d) {// This sets the current active sprite
											// to Dolphin (d=Dolphin)

		if (currentSprite != null) {// if the current sprite is NOT equal to
									// null then

			currentSprite.setActive(false); // This is not current sprite
		}

		currentSprite = d;// Current sprite is set to Dolphin (d=Dolphin)

		currentSprite.setActive(true); // Current sprite Dolphin is set to true
	}

	/**
	 * Move the currently active sprite (always dolphin) in the requested
	 * direction (up/down/left/right). If the sprite is at a grid boundary, it
	 * will not move past the given boundary This sets up the game rules
	 * involving collisions with all game tokens
	 * 
	 * @param d
	 *            the direction to move.
	 * @param p
	 *            the starting position
	 * @param q
	 *            the end position
	 * 
	 * 
	 * 
	 */
	public void moveSprite(Direction d) {

		if (currentSprite == null) {// If the current sprite (dolphin) is empty
			return;
		}

		Point p = gridPanel.convertFromPhysical(currentSprite.getLocation()); // the
																				// starting
																				// position
		Point q = getLocation(p, d); // the end position

		if (q != null) { // if the end position is not equal to null

			if (grid[q.y][q.x] == null) { // If the position is empty
				grid[q.y][q.x] = grid[p.y][p.x]; // Move to the next location
				grid[p.y][p.x] = null; // Set the previous one to empty
				currentSprite.setLocation(gridPanel.convertFromLogical(q)); // Sets
																			// location
																			// of
																			// dolphin
			} else {
				GameToken token = grid[q.y][q.x]; // GETS THE OBJECT ON THE END
													// POSITION

				if (token instanceof Wall) { // passes in object token to see if
												// its a wall

					return;

				} else if (token instanceof Crate) { // Passes in object token
														// to see if its a crate

					Crate crate = (Crate) token; // sets crate as token, creates
													// crate

					Point crateFinalPosition = getLocation(q, d); // Gets end
																	// position
																	// of
																	// crate/position
																	// its gonna
																	// move to

					GameToken nextToken = grid[crateFinalPosition.y][crateFinalPosition.x]; // End
																							// position
																							// of
																							// crate

					if (nextToken == null || nextToken instanceof Diamond) { // checking
																				// if
																				// space
																				// im
																				// trying
																				// to
																				// move
																				// into
																				// is
																				// a
																				// diamond,
																				// checking
																				// if
																				// i
																				// can
																				// move
																				// crate

						if (nextToken instanceof Diamond) { // If the next token
															// is a diamond
							Diamond diamond = (Diamond) nextToken; // Sets next
																	// token as
																	// diamond
							diamond.setCrateOnDiamond(true); // Becomes true
																// when crate is
																// pushed onto
																// diamond
							System.out.println("Crate on diamond"); // Prints to
																	// console
																	// for
																	// reference

						}

						grid[crateFinalPosition.y][crateFinalPosition.x] = grid[q.y][q.x]; // moving
																							// crate
																							// to
																							// position
																							// i
																							// want
																							// it
																							// to
																							// be
																							// in
																							// (u/d/l/r)
						crate.setLocation(gridPanel.convertFromLogical(crateFinalPosition));

						grid[q.y][q.x] = grid[p.y][p.x]; // Move to the next
															// location
						grid[p.y][p.x] = null; // Set the previous one to empty
						currentSprite.setLocation(gridPanel.convertFromLogical(q));// Sets
																					// location
																					// of
																					// crate

					}

				} else if (token instanceof Diamond) { // player over diamond
					Diamond diamond = (Diamond) token; // Sets next token as
														// diamond
					Point diamondFinalPosition = getLocation(q, d); // Gets
																	// location
																	// off final
																	// position
																	// on
																	// diamond
					grid[diamondFinalPosition.y][diamondFinalPosition.x] = grid[q.y][q.x];
					grid[q.y][q.x] = null;

				}

			}
		}
		if (checkDiamonds()) { // THIS IS WHAT HAPPENS WHEN A LEVEL IS
								// COMPLETED!
			System.out.println("This level is completed."); // Prints to console
															// for testing
			JOptionPane.showMessageDialog(currentSprite, "Level Completed! Press enter to proceed to next level"
					+ "\n You have moved " + movementGrid.getMoveCount() + " times in Sokoban"); // pops
			// up
			// message
			// to
			// player
			reset();

			this.incrementLevelCount(); // Moves to next level when level is
										// completed

			if (getLevelNo() > 5) { // If you get past level five the game is
									// completed
				JOptionPane.showMessageDialog(currentSprite, "Game Completed! YOU ARE AWESOME!"); // pops
																									// up
																									// message
																									// to
																									// player
				System.out.println("You finished the game"); // Prints to
																// console
				System.exit(0); // Exits game
			}

			try {
				movementGrid.loadLevel("Level " + this.getLevelNo());
			} catch (Exception e) { // Catches exception
				JOptionPane.showMessageDialog(gridPanel, "Invalid char in game.");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * This checks if all crates are on diamonds, which marks the level as
	 * completed
	 *
	 * @return returns levelComplete - this is true if level is complete boolean
	 *         is set to true
	 */
	public boolean checkDiamonds() {
		boolean levelComplete = true; // becomes false if a single crate is not
										// on diamond

		for (int i = 0; i < diamonds.size(); i++ // for every diamond in the
													// list, do..
		) {
			if (diamonds.get(i).isCrateOnDiamond() == false) { // if this
																// specific
																// diamond has a
																// crate on top,
																// checking
																// every one

				levelComplete = false; // Needs all crates on top of diamonds to
										// allow level to be completed
			}
		}
		return levelComplete; // returns level complete
	}

	/**
	 * Get location arrived at by moving one step in the given direction from
	 * the given point. If the movement would take the point outside the bounds
	 * of the array, then null is returned.
	 * 
	 * @param p
	 *            the starting point.
	 * @param d
	 *            the direction to move.
	 * @return the new location, or null if the given movement is not possible.
	 */
	private Point getLocation(Point p, Direction d) {
		Point q = null;

		switch (d) {
		case UP:
			if (p.y > 0) {
				q = new Point(p.x, p.y - 1);
			}
			break;
		case DOWN:
			if (p.y < grid.length - 1) {
				q = new Point(p.x, p.y + 1);
			}
			break;
		case LEFT:
			if (p.x > 0) {
				q = new Point(p.x - 1, p.y);
			}
			break;
		case RIGHT:
			if (p.x < grid[0].length - 1) {
				q = new Point(p.x + 1, p.y);
			}
		}

		return q;
	}

	/**
	 * This resets the level for loading the next level
	 * 
	 */
	public void reset() {
		System.out.println("I am resetting the level"); // Prints to console for
														// own reference

		grid = new GameToken[gridPanel.getGridSize().height][gridPanel.getGridSize().width];

		gridPanel.removeAll(); // removes tokens to grid
		gridPanel.revalidate(); // revalidates tokens to grid
		gridPanel.repaint(); // repaints tokens to grid
	}
}
