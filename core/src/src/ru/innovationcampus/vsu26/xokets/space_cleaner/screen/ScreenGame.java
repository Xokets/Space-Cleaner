package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.ShipObject;

public class ScreenGame extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    private ShipObject ship;
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        ship = new ShipObject(Resources.SHIP_TEXTURE_NAME,
                Resources.SHIP_WIDTH,
                Resources.SHIP_HEIGHT,
                (float) (Settings.SCREEN_WIDTH / 2 - Resources.SHIP_WIDTH / 2),
                (float) (Settings.SCREEN_HEIGHT / 2 - Resources.SHIP_HEIGHT / 2), myGdxGame.world);
    }
    @Override
    public void show() {
        ship.setY(0);
        ship.setBounce(Settings.SCREEN_WIDTH, (float) Settings.SCREEN_HEIGHT / 2, 0, 0);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
        System.out.println(ship.getY());
        ship.move(myGdxGame.touch);
        myGdxGame.stepWorld(delta);

        myGdxGame.camera.update();
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        ship.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        ship.dispose();
    }
}
