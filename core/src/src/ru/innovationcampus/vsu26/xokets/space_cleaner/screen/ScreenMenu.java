package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.utils.FontBuilder;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.ButtonView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.MovingBackgroundView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.TextView;

public class ScreenMenu extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    private MovingBackgroundView background;
    private TextView titleView;
    private ButtonView startButton;
    private ButtonView settingsButton;
    private ButtonView exitButton;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        background = new MovingBackgroundView(Resources.BACKGROUND_INTERNAL_TEXTURE_PATH);
        titleView = new TextView(180, 960, myGdxGame.font1Large, "Space Cleaner");
        startButton = new ButtonView(140, 646, 440, 70, Resources.INTERFACE_TEXT_BUTTON_BG_LONG_INTERNAL_TEXTURE_PATH, myGdxGame.font1Black, "start");
        settingsButton = new ButtonView(140, 551, 440, 70, Resources.INTERFACE_TEXT_BUTTON_BG_LONG_INTERNAL_TEXTURE_PATH, myGdxGame.font1Black, "settings");
        exitButton = new ButtonView(140, 456, 440, 70, Resources.INTERFACE_TEXT_BUTTON_BG_LONG_INTERNAL_TEXTURE_PATH, myGdxGame.font1Black, "exit");

    }
    @Override
    public void render(float delta) {
        handleInput();
        draw();
    }
    @Override
    public void show() {
    }

    @Override
    public void dispose() {
        background.dispose();
        titleView.dispose();
        exitButton.dispose();
        startButton.dispose();
        settingsButton.dispose();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));;
            if (startButton.isHit(myGdxGame.touch)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (settingsButton.isHit(myGdxGame.touch)) {
                myGdxGame.setScreen(myGdxGame.screenSettings);
            }
            if (exitButton.isHit(myGdxGame.touch)) {
                Gdx.app.exit();
            }
        }
    }
    private void draw() {
        myGdxGame.camera.update();
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        titleView.draw(myGdxGame.batch);
        exitButton.draw(myGdxGame.batch);
        settingsButton.draw(myGdxGame.batch);
        startButton.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }
}
