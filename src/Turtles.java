import org.newdawn.slick.Input;
/***
 * Turtles class generates turtles which also can be ridden by the player. 
 * They appear and disappear from time to time
 * When disappear and player on it, it kills it 
 * @author ANEESH
 *
 */
public class Turtles extends Sprite {
    private static final String ASSET_PATH = "assets/turtles.png";
    private static final float SPEED = 0.085f;
    private static int time1 = 7000;
    private static int time2 = 9000;
    static int timefrog = 0;
    static boolean disappear = false;
    float a[][] = new float[10000][2];
    int i = 0, j = 0;
    static String tag = Sprite.HAZARDN;




    boolean moveRight;

    private final float getInitialX() {
        return moveRight ? -World.TILE_SIZE / 2 :
            App.SCREEN_WIDTH + World.TILE_SIZE / 2;
    }
    /**
     * Constructor for the Turtles class
     * @param x             X position of the Turtle
     * @param y             Y position of the Turtle
     * @param moveRight     Direction of movement of the Turtle
     */
    public Turtles(float x, float y, boolean moveRight) {
        super(ASSET_PATH, x, y, new String[] {
            tag
        });
        this.moveRight = moveRight;
        movobj = moveRight;
    }


    public void update(Input input, int delta) {


        //check if turtle sprite needs to appear or disappear, then through World class render it accordingly
        move(SPEED * delta * (moveRight ? 1 : -1), 0);
        if (timefrog <= time1) {

            a[i][0] = getX();
            a[i][1] = getY();
            ++i;
            disappear = false;
            // check if the Turtle has moved off the screen
            if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2 ||
                getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2)
                setX(getInitialX());
        } else {
            disappear = true;
        }

        if (disappear && timefrog >= time1 && timefrog <= time2) {
            disappear = true;
        }


        if (timefrog > time2 && disappear) {
            timefrog = 0;

            for (i = 0; a[i][0] != 0; ++i) {
                setX(a[i][0]);
                setY(a[i][1]);
            }

            disappear = false;

        }


    }





}