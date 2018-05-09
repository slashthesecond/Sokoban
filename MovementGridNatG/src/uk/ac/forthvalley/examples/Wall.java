package uk.ac.forthvalley.examples;

/**
 * This declares a public class called Wall, which is a subclass of GameToken.
 * 
 * Project: Sokoban
 * @author 547114 Natalie Gardner
 */

public class Wall extends GameToken {
	/** 
	 * The URL for the main sprite image
	 */
	private static final String MAIN_IMG_URL = "/uk/ac/forthvalley/examples/wall30p.jpg";

	/** 
	 * Constructor is called.
	 */
	public Wall() {
		super(MAIN_IMG_URL);

	}
}