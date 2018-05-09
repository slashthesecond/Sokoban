package uk.ac.forthvalley.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uk.org.asch.swing.GridPanel;
import javax.swing.border.LineBorder;

/**
 * A simple example of labels with icons and absolute layout managers to
 * facilitate movement of components. And along the way, a new Swing component.
 * 
 * Project: Sokoban
 * 
 * @author andreas.schoter and 547114 Natalie Gardner
 */
public class MovementGrid {
	/** 
	 * The size of the (square) grid in cells 
	 */
	private static final int GRID_SIZE = 20;

	/**
	 *  The size of a (square) cell in the grid 
	 */
	private static final int CELL_SIZE = 20;

	/** 
	 * The URL of the background image for the grid 
	 */
	private static final String GRID_BACKGROUND = "/uk/ac/forthvalley/examples/BackgroundOcean.png";

	/** 
	 * The custom panel used to display the grid and its contents 
	 */
	private GridPanel pnlDisplay;

	/** 
	 * The grid for managing the game icons 
	 */
	private GridManager<Character> gameGrid;

	/** 
	 * The main frame for the application
	 */
	private JFrame frmGridMovement;
	/** 
	 * Move counter 
	 */
	public int moveCount;

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) { // Driver class
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MovementGrid window = new MovementGrid("Level 1"); // Begins
																		// level
																		// at
																		// number
																		// 1
					window.frmGridMovement.setVisible(true); // Sets game window
																// to visible
				} catch (Exception e) { // Catches exception
		
				}
			}
		});
	}

	/**
	 * Launch the application.
	 * @return frmGridMovement
	 */
	@SuppressWarnings("javadoc")
	public JFrame getGridMovement() {

		return this.frmGridMovement;
	}

	/**
	 * Create the application and initialize the key event dispatcher for arrow
	 * keys.
	 * 
	 * @param lvl
	 *            - passes in game level
	 * @exception ex
	 *                if level doesn't load
	 */
	public MovementGrid(String lvl) {
		initialize();

		gameGrid = new GridManager<Character>(this, pnlDisplay);


		try {
			loadLevel(lvl);

			// loadLevel("Level 2"); //These worked loading the levels
			// separately
			// loadLevel("Level 3");
			// loadLevel("Level 4");
//			 loadLevel("Level 5");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Invalid level! File contains illegal characters", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}

		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_UP) { // If up key is
															// pressed
						gameGrid.moveSprite(Direction.UP); // Sprite moves up
															// one space
						moveCount = moveCount + 1; // Adds one to move count
						return true;
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// If down
																	// key is
																	// pressed
						gameGrid.moveSprite(Direction.DOWN); // Sprite moves
																// down one
																// space
						moveCount = moveCount + 1; // Adds one to move count
						return true;
					} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// If left
																	// key is
																	// pressed
						gameGrid.moveSprite(Direction.LEFT);// Sprite moves left
															// one space
						moveCount = moveCount + 1; // Adds one to move count
						return true;
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// If right
																		// key
																		// is
																		// pressed
						gameGrid.moveSprite(Direction.RIGHT);// Sprite moves
																// right one
																// space
						moveCount = moveCount + 1; // Adds one to move count
						return true;
					}
				}
				return false;

			}
		});
	}


	/**
	 * Gets the move count of the total moves the player has made during the
	 * entire game, when the player completes the level the current total is
	 * displayed
	 * 
	 * @return moveCount
	 */
	public int getMoveCount() {
		return moveCount;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGridMovement = new JFrame();
		frmGridMovement.setResizable(false);
		frmGridMovement.setTitle("Sokoban");
		frmGridMovement.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MovementGrid.class.getResource("/uk/ac/forthvalley/examples/Dolphin.png")));
		frmGridMovement.setBounds(100, 100, 700, 700);
		frmGridMovement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlControls = new JPanel();
		pnlControls.setBackground(new Color(0, 102, 153));
		pnlControls.setForeground(Color.BLACK);
		frmGridMovement.getContentPane().add(pnlControls, BorderLayout.WEST);
		GridBagLayout gbl_pnlControls = new GridBagLayout();
		gbl_pnlControls.columnWidths = new int[] { 0, 0 };
		gbl_pnlControls.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlControls.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlControls.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlControls.setLayout(gbl_pnlControls);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		pnlControls.add(label, gbc_label);

		JPanel pnl1 = new JPanel();
		pnl1.setBackground(Color.BLACK);
		frmGridMovement.getContentPane().add(pnl1, BorderLayout.CENTER);

		pnl1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pnlDisplay = new GridPanel(new Dimension(20, 20), new Dimension(30, 30), GRID_BACKGROUND);
		pnlDisplay.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDisplay.setBackground(new Color(0, 0, 0));
		pnl1.add(pnlDisplay);

	}

	
	/**
	 * This loads every level from the file 'SokobanLevelsEdit.txt and assigns a
	 * character from the text file to the corresponding GameToken
	 * 
	 * @param name this the the .txt file that is passed in and read
	 * @throws Error popup message to user is there is an invalid character in the .txt file
	 */

	public void loadLevel(String name) throws Exception {
		System.out.println("I am loading the level " + name); // Prints to
																// console for
																// testing
		gameGrid.reset();

	//String mapResource = "SokobanLevelsEdit.txt"; // Resource name of text
														// file in package
		String mapResource = "SokobanLevels.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(mapResource))); // wraps
																													// an
																													// InputStreamReader
																													// within
																													// a
																													// BufferedReader
		boolean levelFound = false; // assumes level wasn't found
		int row = 0; // Starts row at nothing
		String line = null; // Starts line at nothing
		while ((line = br.readLine()) != null) {// While the line does not
												// contains chars
			line = line.trim(); // Removes blank spaces and empty lines
			if (levelFound && line.isEmpty()) { // Level is not found.
				return;

			} else if (levelFound) { // if there is a level found
				nextChar: for (int col = 0; col < line.length(); col++) { // Searches
																			// for
																			// next
																			// char
																			// in
																			// file
					char c = line.toUpperCase().charAt(col); // charAt returns a
																// char at
																// specified
																// index
					switch (c) {
					case 'C':// if char is c/C (Crate)
						c = 'c';

						break;
					case 'W': // if char is w/W (Wall
						c = 'w';

						break;
					case 'S': // if char is s/S (Dolphin)
						c = 'd';

						break;
					case 'D': // if char is z (Diamond)
						c = 'z';

						break;
					case ' ':// When there is a space it ignores the space and
								// looks for..

						continue nextChar; // next char in .txt file

					default:
						JOptionPane.showInputDialog("Invalid character '" + c + "' found in " + mapResource + " (col=" + col
								+ ", row=" + row + ") !");
						// Throws output to user when met with an invalid char in txt
						// file
					}
					Point lp = pnlDisplay.convertFromLogical(new Point(col, row));
					JLabel l = gameGrid.addSprite(lp, c);// Per every char
															// found, it adds
															// corresponding
															// sprite to
															// grid

					if (l != null)
						pnlDisplay.add(l); // if l is not empty then add to
											// panel

				}
				row++; // increments row by 1
			} else if (line.startsWith("LevelName:")) {// Searches through .txt
														// file
				String currentLevel = line.replace("LevelName:", "").trim(); // Trims
																				// for
																				// relevance
				if (currentLevel.equals(name)) {// If currentLevel starts with
												// "LevelName then a level has
					levelFound = true; // been found successfully!

				}
			}
		}
		if (!levelFound) {// If a level is not found
			throw new Exception("Level '" + name + "' not found !");// Exception
		//	JOptionPane.showMessageDialog(frmGridMovement, "Level not found.");												// is thrown

		}
	}

}
