import org.newdawn.slick.Input;
/**
 * This class represents an object the Player can ride.
 *  Standing on a Log protects the player from water. 
 *  Player also follows the log in its direction
 */
public class Log extends Sprite {
	private static final String ASSET_PATH = "assets/log.png";
	private static final float SPEED = 0.1f;
	boolean moveRight;
	
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}
	/**
     * Constructor for Log
     * @param x             X position of the Log
     * @param y             Y position of the Log
     * @param moveRight     Direction of movement of the Log
     */
	public Log(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y,new String[] { Sprite.HAZARDN });
		
		this.moveRight = moveRight;
		movobj=moveRight;
	}
	public float getY1()
	{
		return getY();
	}
	
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		
		
		// check if the Longlog has moved off the screen
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			setX(getInitialX());
		}
	}
}
