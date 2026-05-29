package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.managers.MemoryManager;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.views.ButtonView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.views.ImageView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.views.MovingBackgroundView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.views.TextView;

public class ScreenSettings extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    private MovingBackgroundView background;
    private ImageView blackout;
    private ButtonView returnButton;
    private TextView musicCheckbox;
    private TextView soundCheckbox;
    private TextView clearRecords;
    private TextView settingsTitle;

    public ScreenSettings(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        background = new MovingBackgroundView(Resources.BACKGROUND_INTERNAL_TEXTURE_PATH);
        settingsTitle = new TextView(256, 956, myGdxGame.font1Large, "Settings");
        returnButton = new ButtonView(280, 447, 160, 70, Resources.INTERFACE_TEXT_BUTTON_BG_SHORT_INTERNAL_TEXTURE_PATH, myGdxGame.font1Black, "return");
        musicCheckbox = new TextView(173, 717, myGdxGame.font1, "");
        soundCheckbox = new TextView(173, 658, myGdxGame.font1, "");
        clearRecords = new TextView(173, 599, myGdxGame.font1, "clear records");
        blackout = new ImageView(85, 365, 550, 700, Resources.BLACKOUT_FULL_INTERNAL_TEXTURE_PATH);
    }

    @Override
    public void render(float delta) {
        handleInput();
        musicCheckbox.setText("music: " + translateStateToText(MemoryManager.loadIsMusicOn()));
        soundCheckbox.setText("sound: " + translateStateToText(MemoryManager.loadIsSoundOn()));


        myGdxGame.camera.update();
        ScreenUtils.clear(Color.WHITE);
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();


        background.draw(myGdxGame.batch);
        blackout.draw(myGdxGame.batch);
        settingsTitle.draw(myGdxGame.batch);
        musicCheckbox.draw(myGdxGame.batch);
        soundCheckbox.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);
        clearRecords.draw(myGdxGame.batch);


        myGdxGame.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
        if (returnButton.isHit(myGdxGame.touch)) {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
        if (clearRecords.isHit(myGdxGame.touch)) {
            MemoryManager.saveTableOfRecords(List.of());
        }
        if (musicCheckbox.isHit(myGdxGame.touch)) {
            MemoryManager.saveMusicSettings(!MemoryManager.loadIsMusicOn());
            myGdxGame.audioManager.updateMusicFlag();
            musicCheckbox.setText("music: " + translateStateToText(MemoryManager.loadIsMusicOn()));
        }
        if (soundCheckbox.isHit(myGdxGame.touch)) {
            MemoryManager.saveSoundSettings(!MemoryManager.loadIsSoundOn());
            myGdxGame.audioManager.updateSoundFlag();
            soundCheckbox.setText("sound: " + translateStateToText(MemoryManager.loadIsSoundOn()));
        }
        myGdxGame.touch = null;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        background.dispose();
        returnButton.dispose();
        musicCheckbox.dispose();
        soundCheckbox.dispose();
        clearRecords.dispose();
        blackout.dispose();
    }

    private static String translateStateToText(boolean state) {
        return state ? "ON" : "OFF";
    }
}
