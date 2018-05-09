package uk.ac.forthvalley.examples;

/**
 * This declares a public class called Dolphin, which is a subclass of
 * GameToken.
 * 
 * Project: Sokoban 
 * 
 * @Author: 547114 Natalie Gardner
 */
public class Dolphin extends GameToken {
	/**
	 * The URL for the main sprite image
	 */
	private static final String MAIN_IMG_URL = "/uk/ac/forthvalley/examples/dolphin30p.png";

	/** 
	 * Constructor is called.
	 */
	public Dolphin() {
		super(MAIN_IMG_URL);
	}
}
