package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen.ScreenGame;

public class MyGdxGame extends Game {
	public World world;
	public OrthographicCamera camera;
	public Batch batch;
	public Screen screenGame;
	public float accumulator;
	
	@Override
	public void create () {
		Box2D.init();
		world = new World(new Vector2(0, 0), true);
		screenGame = new ScreenGame(this);
		camera = new OrthographicCamera(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		setScreen(screenGame);
	}

	@Override
	public void dispose () {
		screenGame.dispose();
		batch.dispose();
	}

	public void stepWorld(float delta) {
		accumulator += delta;
		while (accumulator >= Settings.FIXED_TIME_STEP) {
			world.step(Settings.FIXED_TIME_STEP, Settings.VELOCITY_ITERATIONS, Settings.POSITION_ITERATIONS);
			accumulator -= Settings.FIXED_TIME_STEP;
		}
	}
}
