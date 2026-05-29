package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

public class Settings {

    public static final int MERCURY_RADIUS = 100;
    public static final int VENUS_RADIUS = 220;
    public static final int EARTH_RADIUS = 225;
    public static final int MARS_RADIUS = 160;
    public static final int JUPITER_RADIUS = 450;
    public static final int SATURN_RADIUS = 350;
    public static final int URANUS_RADIUS = 310;
    public static final int NEPTUNE_RADIUS = 300;
    public static final short RECORD_TABLE_ROWS_COUNT = 5;
    public static final int SHIP_WIDTH = 100;
    public static final int SHIP_HEIGHT = 100;
    public static final short SHIP_HIT_POINT_COUNT = 3;
    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 1280;
    public static final float FIXED_TIME_STEP = (float) 1/60;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 6;
    public static final float SCALE = 0.05f;
    public static final int SHIP_FORCE_RATIO = 10;
    public static final int TRASH_VELOCITY = 10;
    public static final long STARTING_TRASH_APPEARANCE_COOL_DOWN = 2000;
    public static final float TRASH_WIDTH = 140;
    public static final float TRASH_HEIGHT = 100;
    public static final int BULLET_WIDTH = 15;
    public static final int BULLET_HEIGHT = 45;
    public static final int BULLET_VELOCITY = 200;
    public static final long SHOOTING_COOL_DOWN = 1000;
    public static final short TRASH_BIT = 1;
    public static final short SHIP_BIT = 2;
    public static final short BULLET_BIT = 4;
}
