/***
 * Fills the whole with a copy of the player sprite
 * @author ANEESH
 *
 */
public class Wins extends Sprite{
    private static String Img= ("assets/frog.png");
    
	public static Wins createHoleTile(float x, float y) {
			return new Wins(Img, x, y, new String[] { Sprite.HAZARD});
		}
		
		
		private Wins(String imageSrc, float x, float y) {		
			super(imageSrc, x, y);
		}
		private Wins(String imageSrc, float x, float y, String[] tags) {		
			super(imageSrc, x, y, tags);
		}
	    
		
		
	

}
