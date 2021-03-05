/***
 * Water Tiles
 * Hazard on contact with the player
 * @author ANEESH
 *
 */
public class Water extends Sprite {

	private static final String WATER_PATH = "assets/water.png";

	
	/**
	 * Creates a Water Tile
	 * @param x	X position of the created Water tile
	 * @param y Y position of the created Water tile
	 * @return	created Tile with the attributes of a Water Tile
	 */
	public static Water createWaterTile(float x, float y) {
		return new Water(WATER_PATH, x, y, new String[] { Sprite.HAZARD });
	}
	/**
	 * Constructor for Water Tile
	 * @param imageSrc	Image that will be rendered
	 * @param x			X position of the Tile
	 * @param y			Y position of the Tile
	  * @param tags			hazard or not
	 */
	
	
	private Water(String imageSrc, float x, float y, String[] tags) {		
		super(imageSrc, x, y, tags);
	}
}