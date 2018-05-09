package uk.ac.forthvalley.examples;

/**
 * This declares a public class called Diamond, which is a subclass of
 * GameToken.
 * 
 * Project : Sokoban
 * 
 * @author 547114 Natalie Gardner
 */

public class Diamond extends GameToken {
	/**
	 * The URL for the main sprite image
	 */
	private static final String MAIN_IMG_URL = "/uk/ac/forthvalley/examples/diamond30p.png";

	/**
	 * Setting the crate to not be on a diamond, by default, with a boolean
	 * (yes/no)
	 */

	private boolean crateOnDiamond = false; // Default crate not over diamond
											// position

	/**
	 * Checks to see if a crate is on a diamond
	 * 
	 * @returns crateOnDiamond
	 */
	public boolean isCrateOnDiamond() {
		return crateOnDiamond;
	}

	/**
	 * This checks if crate is or is not on a diamond
	 * 
	 * @param crateOnDiamond
	 */
	public void setCrateOnDiamond(boolean crateOnDiamond) {
		this.crateOnDiamond = crateOnDiamond;
	}

	/**
	 * Constructor is called.
	 */
	public Diamond() {
		super(MAIN_IMG_URL);
	}
}
