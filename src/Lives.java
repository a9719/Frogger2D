import java.util.ArrayList;
/***
 * Lives generates the no. of lives to be displayed on the screen
 * @author ANEESH
 *
 */
public class Lives extends Sprite{
	
	public static int life=0;
	private static String lifeImg= ("assets/lives.png");
	static ArrayList<Sprite> lives_sprite = new ArrayList<>();
	private static float lifeX;
	private static  float lifeY = 744;
	static int MAXLIVES=2;
	static int extralife=0;
	
	/**
	 * Constructor for Lives
	 * @param imageSrc	Image that will be rendered
	 * @param x			X position of the Tile
	 * @param y			Y position of the Tile
	 
	 */
	public Lives(String imageSrc, float x, float y) {
		super(lifeImg, lifeX, lifeY);
		
	}
	/***
	 * lives1 to add the no. of lives to be rendered
	 * @return lives_sprite
	 */
	public static ArrayList<Sprite> lives1() {
		lifeX=24;
		for(int i=0;i<=20;++i)
		{
			 lives_sprite.add(new Lives(lifeImg,lifeX,lifeY));
			 lifeX +=32;
			 
		}
		return lives_sprite;
		
		
	}
    
	
	
}
