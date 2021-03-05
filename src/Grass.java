/***
 * Grass Tiles
 * @author ANEESH
 *
 */
public class Grass extends Sprite {

	private static final String Grass_PATH = "assets/Grass.png";

	
	/**
	 * Creates a Grass Tile
	 * @param x	X position of the created grass tile
	 * @param y Y position of the created grass tile
	 * @return	created Tile with the attributes of a Grass Tile
	 */
	public static Grass createGrassTile(float x, float y) {
		return new Grass(Grass_PATH, x, y);
	}
	
	/**
	 * Constructor for Grass Tile
	 * @param imageSrc	Image that will be rendered
	 * @param x			X position of the Tile
	 * @param y			Y position of the Tile
	 */
	private Grass(String imageSrc, float x, float y) {		
		super(imageSrc, x, y);
	}
	
}