import org.newdawn.slick.Input;

/***
 * This class ExtraLife using World class and Player class generates an extra life for a player
 * Also keeps the extra life floating on screen on the log
 * @author ANEESH
 *
 */
public class ExtraLife extends Sprite {
	private static final String ASSET_PATH = "assets/extralife.png";
	static float SPEED; //as per the log it is on
	boolean moveRight; //as per the log
	
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}

    /**
     * This is the constructor for ExtraLife
     * @param x             X coordinate of the ExtraLife
     * @param y             Y coordinate of the ExtraLife
     * @param moveRight     Direction of ExtraLife movement
     */
	
	public ExtraLife(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y);
		
		this.moveRight = moveRight;
		movobj=moveRight;
	}
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			setX(getInitialX());
		}
	}
}
