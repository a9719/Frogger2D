/**
 * This class is a solid object meaning player cannot go on top of it
 * @author ANEESH
 *
 */

public class Tree extends Sprite {

	private static final String Tree_PATH = "assets/Tree.png";

    /**
     * This is generates tree tile
     * @param x             X coordinate of the Tree
     * @param y             Y coordinate of the Tree
    * @return	created Tile with the attributes of a  tree Tile
     */
	
	//tree tiles generator
	public static Tree createTreeTile(float x, float y) {
		return new Tree(Tree_PATH, x, y);
	}
	/**
	 * Constructor for Grass Tile
	 * @param imageSrc	Image that will be rendered
	 * @param x			X position of the Tile
	 * @param y			Y position of the Tile
	 */
	private Tree(String imageSrc, float x, float y) {		
		super(imageSrc, x, y);
	}
	
}