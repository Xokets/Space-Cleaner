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

import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen.ScreenGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.utils.FontBuilder;

public class MyGdxGame extends Game {
	public World world;
	public OrthographicCamera camera;
	public Batch batch;
	public Screen screenGame;
	public Vector3 touch;
	public static Random rand = new Random();
	public BitmapFont font1;
	
	@Override
	public void create() {
		Box2D.init();
		font1 = FontBuilder.generate(24, Color.WHITE, Resources.FONT1_INTERNAL_PATH);
		world = new World(new Vector2(0, 0), true);
		screenGame = new ScreenGame(this);
		camera = new OrthographicCamera(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		setScreen(screenGame);
	}

	@Override
	public void dispose() {
		screenGame.dispose();
		batch.dispose();
		font1.dispose();
	}

	public void stepWorld(float delta) {
		world.step(delta, Settings.VELOCITY_ITERATIONS, Settings.POSITION_ITERATIONS);
	}
}
