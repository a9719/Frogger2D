import utilities.BoundingBox;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Sprite {
	// this is a defined constant to avoid typos
	public final static String HAZARD = "hazard";
	public final static String HAZARDN = "not hazard";
	public static final String LIFE = "life";
	Boolean movobj;
	Sprite sprite;

	
	
	private BoundingBox bounds;
	private Image image;
	private float x;
	private float y;
	
	private String[] tags;
	
	public Sprite(String imageSrc, float x, float y) {
		setupSprite(imageSrc, x, y);
	}
	public Sprite(String imageSrc, float x, float y, String[] tags) {
		setupSprite(imageSrc, x, y);
		this.tags = tags;
	}
	
	private void setupSprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bounds = new BoundingBox(image, (int)x, (int)y);
		
		tags = new String[0];		
	}

	/**
	 * Sets the x position of the sprite.
	 * @param x	 the target x position
	 */
	public final void setX(float x) { this.x = x; bounds.setX((int)x); }
	/**
	 * Sets the y position of the sprite.
	 * @param y	 the target y position
	 */
	public final void setY(float y) { this.y = y; bounds.setY((int)y); }
	/**
	 * Accesses the x position of the sprite.
	 * @return	the x position of the sprite
	 */
	public final float getX() { return x; }
	/**
	 * Accesses the y position of the sprite.
	 * @return	the y position of the sprite
	 */
	public final float getY() { return y; }
	
	public final void move(float dx, float dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	public final boolean onScreen(float x, float y) {
		return !(x + World.TILE_SIZE / 2 > App.SCREEN_WIDTH || x - World.TILE_SIZE / 2 < 0
			 || y + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT || y - World.TILE_SIZE / 2 < 0);
	}
	
	public final boolean onScreen() {
		return onScreen(getX(), getY());
	}
	
	public final boolean collides(Sprite other) {
		return bounds.intersects(other.bounds);
	}
	
	public void update(Input input, int delta) { }
	
	public void onCollision(Sprite s1, Sprite other) { }
	public void solidobj(Sprite solid) {}
	
	public void render() {
		image.drawCentered(x, y);
	}
	
	public boolean hasTag(String tag) {
		for (String test : tags) {
			if (tag.equals(test)) {
				return true;
			}
		}
		return false;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	public void updatem(Input input, int delta, int m) {
		
		
	}
	public void wins(int i, int winnings, ArrayList<Sprite> sprites, ArrayList<Sprite> sprites_lives, ArrayList<Sprite> sprites_wins) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
