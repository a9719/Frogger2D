
import java.util.ArrayList;

import org.newdawn.slick.Input;

/***
 * class Player controls the player, its death generation and also initiates the next level
 * It also checks what to do with the colliding object
 * @author ANEESH
 *
 */
public class Player extends Sprite {
	private static final String ASSET_PATH = "assets/frog.png";
	
	//m is the direction the player moves with the object
	static int m;
	//speed of player with the object
	static float speed;
	//lives1 indicates the no. of deaths 
	static int lives1=0;
	// to reposition the player if true
	static boolean repos=false;
	
	
	float prevX = 0 ,prevY = 0;
	private int n=0;//is 1 when turtles disappear and player is on it
	/*** 
	* Constructor for the Player class
     * @param x X coordinate of the Player
     * @param y Y coordinate of the Player
     */
	public Player(float x, float y) {
		super(ASSET_PATH, x, y);
		m=0;
	}
	
	
	@Override
	public void update(Input input, int delta) {
		int dx = 0,
			dy = 0;
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dx -= World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dx += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dy += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			dy -= World.TILE_SIZE;
		}
		//direction the player will ride with the object or pushed
		if(m==1 || m==-1)
		{
		move((float) (speed * delta * m), 0);
		m=0;
		}
		//if player dies
		if (repos)
		{
			if(n==1)
				{if (lives1==Lives.MAXLIVES+1)
					System.exit(0);
				relocate();}
			repos=false;
			setX(App.SCREEN_WIDTH / 2);
			setY((float)(App.SCREEN_HEIGHT - 48));
		}
		
		// make sure the frog stays on screen
		if (getX() + dx - World.TILE_SIZE / 2 < 0 || getX() + dx + World.TILE_SIZE / 2 	> App.SCREEN_WIDTH) {
			dx = 0;
		}
		if (getY() + dy - World.TILE_SIZE / 2 < 0 || getY() + dy + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT) {
			dy = 0;
		}
		
		move(dx, dy);
	}
	
	@Override
	public void onCollision(Sprite s1, Sprite other) {
		//Hazard kills the player, not hazard checks what object to ride on with and also the turtle conditions
		if (other.hasTag(Sprite.HAZARDN))
		{   
			if (parse1(other.toString())[0].equalsIgnoreCase("log"))
			speed=0.1f;
			else if(parse1(other.toString())[0].equalsIgnoreCase("longlog"))
			speed=0.07f;
			else if(parse1(other.toString())[0].equalsIgnoreCase("turtles"))
				{
				if (Turtles.disappear)
				{
					speed=0;
					repos=true;
					n=1;
				}
				else speed=0.085f;
				
				}
			
			if(other.movobj  && s1.collides(other))
			m=1;
			else
			{
			m=-1;
			}	
		}
		if (other.hasTag(Sprite.HAZARD)) {
			if (lives1==Lives.MAXLIVES+1)
				System.exit(0);
			else
			{
				relocate();
			}
		}
	}
	//solidobj tree to set the player accordingly
	public void solidobj (Sprite solid){
		this.setX(solid.getX());
		this.setY(solid.getY());
		}
	//if hole reached check if all holes reached then reset the game for the next level or else continue
	public void wins(int win, int winnings,ArrayList<Sprite> other,ArrayList<Sprite> sprites_lives, ArrayList<Sprite> sprites_wins)
	{
		if (win==1)
		{
			repos=true;
		}
		if (winnings==5)
		{
			other.clear();
			sprites_lives.clear();
			sprites_wins.clear();
			lives1=0;
			World.newextralife();
			Lives.MAXLIVES=2;
			World.game("assets/levels/1.lvl");
		}
	}
	//relocate player to original position
	public static void relocate()
	{
		lives1 +=1;
		repos=true;
	}
	
	private String[] parse1(String line)
	{ // use split or Scanner
	    return line.split("@");
	}
}
