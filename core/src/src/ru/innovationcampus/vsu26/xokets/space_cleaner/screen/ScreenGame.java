package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;

public class ScreenGame extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        myGdxGame.camera.update();
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {

    }
}
