import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;



import java.io.*;

/***
 * World class to handle all the rendering and other controls of the game
 */
public class World {
	static Random rand = new Random();
	
    
	public static final int TILE_SIZE = 48;
    private static float prevX;
    private static float prevY;
    private static int winnings = 0;
    // list containing all the sprites except the live indicator
    static ArrayList < Sprite > sprites = new ArrayList < > ();
    // list containing all the current lives sprites
    static ArrayList < Sprite > sprites_lives = new ArrayList < > ();
    // list containing all the sprites wins in current game
    static ArrayList < Sprite > sprites_wins = new ArrayList < > ();
    
    //variable to select a random log to spawn the extra life on
    static int randomlog = 0;
    
    //variable to select a random time interval  between spawn the extra life on
    static int time_interval;
    static int random_log;
    int logcounter = 0;
    private static boolean extralife = true;
    private static int time;
    private static BufferedReader bufferedReader;
    
    //starting the game using constructor World
    /***
     * Constructor class to initialise the game from level 0
     * @throws IOException
     */
    public World() throws IOException {
    	
    	game("assets/levels/0.lvl");
        
        }
    
    //Method to create the entire game screen 
    public static void game(String level) {
        // create tiles
        try {
            readCsv(level);
        } catch (IOException random_log) {
            // TODO Auto-generated catch block
            random_log.printStackTrace();
        }
        // create player
        create();
        // create lives
        sprites_lives = Lives.lives1();
        newextralife();

    }
    
    //updating the sprites
    public void update(Input input, int delta){
    	for (Sprite sprite: sprites) {
    		sprite.update(input, delta);
    		}
    	
    	Turtles.timefrog += delta; //time in milliseconds for the turtle to work on, to disappear and appear
        time += delta;
        //crash variable to check if the hole(win) has already been reached if 
        
        int k, crash = 0;
        
        
        //to generate the extra life by checking if one isn't on screen (extralife variable) and using time interval
        if ((int)(time / 1000) == time_interval){
        	if (extralife == true)
                for (Sprite spritex: sprites){
                    if (((parse1(spritex.toString()))[0].equalsIgnoreCase("log") || (parse1(spritex.toString()))[0].equalsIgnoreCase("longlog"))) {
                        ++logcounter;//increase log counter to match the chosen log
                        if ((parse1(spritex.toString()))[0].equalsIgnoreCase("log")) {
                            ExtraLife.SPEED = 0.1f;
                        } else
                            ExtraLife.SPEED = 0.07f;
                        if (logcounter == random_log) {
                            sprites.add(new ExtraLife(spritex.getX(), spritex.getY(), spritex.movobj));
                            extralife = false;
                            logcounter = 0;
                            break;
                        }
                        }
                    }
        	}
        
        //matching the collision sprite with the player sprite and doing as per advised
        for (Sprite sprite1: sprites) {
            k = 1;
            if ((parse1(sprite1.toString()))[0].equalsIgnoreCase("player")) {
            	
                for (Sprite sprite2: sprites) {


                    for (Sprite spritex1: sprites) {
                    	
                        if (((parse1(spritex1.toString()))[0].equalsIgnoreCase("log") || (parse1(spritex1.toString()))[0].equalsIgnoreCase("longlog")) && sprite1.collides(spritex1)) {


                            sprite1.onCollision(sprite1, spritex1);
                            if (sprite1.getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2) {
                                sprite1.setX(sprite1.getX() - 48);
                            } else if (sprite1.getX() - World.TILE_SIZE / 2 < 0) {
                                sprite1.setX(sprite1.getX() + 48);
                            }


                            k = 0;
                        } else if ((parse1(spritex1.toString()))[0].equalsIgnoreCase("turtles") && sprite1.collides(spritex1)) {
                            sprite1.onCollision(sprite1, spritex1);
                            k = 0;
                            
                        } else if ((parse1(spritex1.toString()))[0].equalsIgnoreCase("tree") && sprite1.collides(spritex1)) {


                            sprite1.setX(prevX);
                            sprite1.setY(prevY);

                            sprite1.solidobj(sprite1);


                        } else if ((parse1(spritex1.toString()))[0].equalsIgnoreCase("Wins") && sprite1.collides(spritex1)) {
                            crash = 1;
                        } else if ((parse1(spritex1.toString()))[0].equalsIgnoreCase("bulldozer") && sprite1.collides(spritex1)) {
                            sprite1.setX(spritex1.getX() + 48);
                            if (sprite1.getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2) {
                                Player.relocate();
                            }

                        }
                       if (((parse1(spritex1.toString()))[0].equalsIgnoreCase("extralife") && sprite1.collides(spritex1))|| (int)(time/1000)==14) {

                            random_log = rand.nextInt(randomlog) + 0;
                            time = 0;
                            extralife = true;

                            spritex1.setX(-9999);
                            spritex1.setY(-9999);
                            Lives.MAXLIVES += 1;
                            sprites_lives = Lives.lives1();
                            time_interval = rand.nextInt(11) + 25;
                            
                            }
                        }
                    if (sprite1.collides(sprite2) && sprite1 != sprite2)
                        if (k == 1) {

                            sprite1.onCollision(sprite1, sprite2);
                            break;
                        }
                    }
                //storing the previous position of player so that to revert back once it makes contact with a tree(solid tile)
                prevX = sprite1.getX();
                prevY = sprite1.getY();
                //check for if hole has been reached(Wins)
                if (crash != 1) {
                    if (sprite1.getY() == 48 && ((sprite1.getX() > 48 && sprite1.getX() < 192) || (sprite1.getX() > 240 && sprite1.getX() < 384) || (sprite1.getX() > 432 && sprite1.getX() < 576) || (sprite1.getX() > 624 && sprite1.getX() < 768) ||
                            (sprite1.getX() > 816 && sprite1.getX() < 960))) {
                        winnings += 1;//increase by 1 if hole reached 
                        sprites.add(Wins.createHoleTile(sprite1.getX(), sprite1.getY()));


                        sprite1.wins(1, winnings, sprites, sprites_lives, sprites_wins);

                        break;

                    }
                    crash = 0;
                }

            }
        }
    }
    //render the sprites
    public void render(Graphics g) {
        int i;
        //render sprites no. of lives
        for (i = 0; i <= Lives.MAXLIVES - Player.lives1; ++i) {


            sprites_lives.get(i).render();
        }

        for (Sprite sprite: sprites) {
        	//if turtles disappear then don't render the turtles
            if ((parse1(sprite.toString()))[0].equalsIgnoreCase("turtles") && Turtles.disappear == true) {} 
            else
                sprite.render();
        }
    }


    public static void readCsv(String s) throws IOException {
        File file = new File(s);
        FileReader fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] tokenizedLine;
        while ((line = bufferedReader.readLine()) != null) {
            tokenizedLine = parse(line);
            // do stuff with your array

            if (tokenizedLine[0].equals("water")) {
                sprites.add(Water.createWaterTile(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2])));
            }
            if (tokenizedLine[0].equals("grass")) {
                sprites.add(Grass.createGrassTile(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2])));
            }
            if (tokenizedLine[0].equals("tree")) {
                sprites.add(Tree.createTreeTile(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2])));
            }
            if (tokenizedLine[0].equals("longLog")) {
                sprites.add(new Longlog(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
                ++randomlog;
            }
            if (tokenizedLine[0].equals("log")) {
                ++randomlog;
                sprites.add(new Log(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
            if (tokenizedLine[0].equals("bus")) {
                sprites.add(new Bus(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
            if (tokenizedLine[0].equals("racecar")) {
                sprites.add(new Racecar(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
            if (tokenizedLine[0].equals("bike")) {
                sprites.add(new Bike(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
            if (tokenizedLine[0].equals("turtle")) {
                sprites.add(new Turtles(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
            if (tokenizedLine[0].equals("bulldozer")) {
                sprites.add(new Bulldozer(Float.valueOf(tokenizedLine[1]), Float.valueOf(tokenizedLine[2]), Boolean.valueOf(tokenizedLine[3])));
            }
        }
    }
    
   
    private static String[] parse(String line) { // use split or Scanner
        return line.split(",");
    }
    private String[] parse1(String line) { // use split or Scanner
        return line.split("@");
    }
    //add Player sprite
    public static void create() {
        sprites.add(new Player(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT - TILE_SIZE));
    }
    //conditions for new exxtra life
    public static void newextralife() {
        random_log = rand.nextInt(randomlog) + 1;
        time = 0;
        extralife = true;
        time_interval = rand.nextInt(11) + 25;
    }
  }