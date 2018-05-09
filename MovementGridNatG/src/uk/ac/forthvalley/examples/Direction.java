package uk.ac.forthvalley.examples;

/**
 * This sets up the directions of movement for a sprite. (UP, DOWN, LEFT &
 * RIGHT)
 * 
 * Project: Sokoban
 * 
 * @author andreas.schoter and 547114 Natalie Gardner
 */
public enum Direction {
	/**
	 * Movement up - decrement the y co-ordinate.
	 */
	UP,

	/**
	 * Movement down - increment the y co-ordinate.
	 */
	DOWN,

	/**
	 * Movement left - decrement the x co-ordinate.
	 */
	LEFT,

	/**
	 * Movement right - increment the x co-ordinate.
	 */
	RIGHT;
}
