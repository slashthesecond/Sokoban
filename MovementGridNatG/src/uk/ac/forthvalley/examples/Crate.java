package uk.ac.forthvalley.examples;

/**
 * This declares a public class called Crate, which is a subclass of GameToken.
 * 
 * Project : Sokoban
 * 
 * @author 547114 Natalie Gardner
 */
public class Crate extends GameToken {
	/**
	 * The URL for the main sprite image Crate.
	 */
	private static final String MAIN_IMG_URL = "/uk/ac/forthvalley/examples/Crate30p.png";

	/**
	 * Constructor is called.
	 */

	public Crate() {
		super(MAIN_IMG_URL);
	}
}
