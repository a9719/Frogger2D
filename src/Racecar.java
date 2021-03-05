import org.newdawn.slick.Input;
/***
 * Racecar class is also a hazard to the player which on contact kills him
 * @author ANEESH
 *
 */
public class Racecar extends Sprite {
	private static final String ASSET_PATH = "assets/racecar.png";
	private static final float SPEED = 0.5f;
	
	private boolean moveRight;
	
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}
	
	/***
	 * Constructor
	 * @param x X position of race car 
	 * @param y	Y position of race car
	 * @param moveRight Direction of car moving in
	 */
	public Racecar(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y, new String[] { Sprite.HAZARD });
		
		this.moveRight = moveRight;
	}
	
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		// check if the racecar has moved off the screen
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			setX(getInitialX());
		}
	}
}
