package uk.ac.forthvalley.examples;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A token in the game that can be displayed in the panel and manages its icon
 * depending on its state.
 * 
 * Project: Sokoban
 * 
 * @author andreas.schoter and Natalie Gardner
 */
@SuppressWarnings("serial")
public class GameToken extends JLabel {
	/**
	 * Paints the Dolphin (Warehouse Keeper) from Image created from URL in
	 * Dolphin class
	 **/
	private ImageIcon Dolphin;
	/** 
	 * Paints the Crate from Image created from URL in Crate class
	 */
	private ImageIcon Crate;
	/** 
	 * Paints the Wall from Image created from URL in Wall class 
	 */
	private ImageIcon Wall;
	/** 
	 * Paints the Diamond from Image created from URL in Diamond class 
	 */
	private ImageIcon Diamond;

	/**
	 * Sets every game token to the corresponding image
	 * 
	 * @param url
	 *            Passes in image for GameToken. Each token has a different
	 *            image passed in.
	 */
	public GameToken(String url) {
		super("");
		Dolphin = new ImageIcon(MovementGrid.class.getResource(url));// Sets
																		// Dolphin
																		// image
																		// to
																		// Dolphin
																		// token
		setIcon(Dolphin);
		Crate = new ImageIcon(MovementGrid.class.getResource(url)); // Sets
																	// Crate
																	// image to
																	// Crate
																	// token
		setIcon(Crate);
		Wall = new ImageIcon(MovementGrid.class.getResource(url)); // Sets Wall
																	// image to
																	// Wall
																	// token
		setIcon(Wall);
		Diamond = new ImageIcon(MovementGrid.class.getResource(url));// Sets
																		// Diamond
																		// image
																		// to
																		// Diamond
																		// token
		setIcon(Diamond);
	}

	/**
	 * Set the initial physical position for the icon.
	 * 
	 * @param x
	 *            the x position in the containing panel.
	 * @param y
	 *            the y position in the containing panel.
	 * 
	 *            (x+y = new location of the top-left corner )
	 * 
	 */

	public void setInitialPosition(int x, int y) {
		setBounds(x, y, Dolphin.getIconWidth(), Dolphin.getIconHeight());
		setBounds(x, y, Crate.getIconWidth(), Crate.getIconHeight());
		setBounds(x, y, Wall.getIconWidth(), Wall.getIconHeight());
		setBounds(x, y, Diamond.getIconWidth(), Diamond.getIconHeight());
	}

	/**
	 * Boolean (Yes or no) Option to show if the Token is active, or not.
	 * 
	 * @param b
	 *            yes or no value for active
	 */

	public void setActive(boolean b) {
		// TODO Auto-generated method stub

	}

}
