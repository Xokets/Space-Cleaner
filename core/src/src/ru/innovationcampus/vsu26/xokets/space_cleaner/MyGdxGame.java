package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen.ScreenGame;

public class MyGdxGame extends Game {
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public Screen screenGame;
	
	@Override
	public void create () {
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
}
