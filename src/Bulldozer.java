import org.newdawn.slick.Input;
/**
 * This class is also a solid object, through World class it pushes the player in its direction
 * This class also keeps the bulldozer on screen
 * @author ANEESH
 *
 */

public class Bulldozer extends Sprite {
	private static final String ASSET_PATH = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	boolean moveRight;
	
	
	
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}

    /**
     * This is the constructor for Bulldozer
     * @param x             X coordinate of the Bulldozer
     * @param y             Y coordinate of the Bulldozer
     * @param moveRight     Direction of Bulldozer movement
     */
	public Bulldozer(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y);
		
		this.moveRight = moveRight;
		movobj=moveRight;
	}
	
	
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		// check if the Bulldozer has moved off the screen
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			setX(getInitialX());
		}
	}
}
