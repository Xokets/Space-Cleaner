package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screens.ScreenGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screens.ScreenMenu;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screens.ScreenSettings;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.managers.AudioManager;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.utils.FontBuilder;

public class MyGdxGame extends Game {
	public World world;
	public OrthographicCamera camera;
	public Batch batch;
	public Screen screenGame;
	public Screen screenMenu;
	public Screen screenSettings;
	public AudioManager audioManager;
	public Vector3 touch;
	public static Random rand = new Random();
	public BitmapFont font1;
	public BitmapFont font1Large;
	public BitmapFont font1Black;
	
	@Override
	public void create() {
		Box2D.init();
		font1 = FontBuilder.generate(24, Color.WHITE, Resources.FONT1_INTERNAL_PATH);
		font1Black = FontBuilder.generate(24, Color.BLACK, Resources.FONT1_INTERNAL_PATH);
		font1Large = FontBuilder.generate(48, Color.WHITE, Resources.FONT1_INTERNAL_PATH);
		world = new World(new Vector2(0, 0), true);
		screenGame = new ScreenGame(this);
		screenMenu = new ScreenMenu(this);
		screenSettings = new ScreenSettings(this);
		camera = new OrthographicCamera(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		setScreen(screenMenu);
		audioManager = new AudioManager(world);
	}

	@Override
	public void dispose() {
		screenGame.dispose();
		batch.dispose();
		font1.dispose();
		font1Black.dispose();
		font1Large.dispose();
	}

	public void stepWorld(float delta) {
		world.step(delta, Settings.VELOCITY_ITERATIONS, Settings.POSITION_ITERATIONS);
	}
}
