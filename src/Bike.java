import org.newdawn.slick.Input;

/**
 * This class Bike is a hazard to the player once it touches the player, the player dies.
 * The bike changes its direction
 * @author ANEESH
 *
 */
public class Bike extends Sprite {
	private static final String ASSET_PATH = "assets/bike.png";
	private static final float SPEED = 0.2f;
	private boolean moveRight;
	
	
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}

    /**
     * This is the constructor for Bike
     * @param x             X coordinate of the Bike
     * @param y             Y coordinate of the Bike
     * @param moveRight     Direction of Bike movement
     */
	public Bike(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y, new String[] { Sprite.HAZARD });
		
		this.moveRight = moveRight;
	}
	
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		// check if the Bike has moved off the screen
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			if(this.moveRight==true)
				this.moveRight=false;
			else
				this.moveRight=true;
			setX(getInitialX());
		}
	}
}
