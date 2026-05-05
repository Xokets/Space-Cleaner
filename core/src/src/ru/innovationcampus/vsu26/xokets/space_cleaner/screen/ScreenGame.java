package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.GameSession;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.BulletObject;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.ShipObject;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.TrashObject;

public class ScreenGame extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    private ShipObject ship;
    private GameSession gameSession;
    private final ArrayList<TrashObject> trashArray = new ArrayList<>();
    private final ArrayList<BulletObject> bulletArray = new ArrayList<>();


    public ScreenGame(@NotNull MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        gameSession = new GameSession();
        ship = new ShipObject(

                Resources.SHIP_TEXTURE_NAME,
                Resources.SHIP_WIDTH,
                Resources.SHIP_HEIGHT,
                myGdxGame.world

        );
        ship.setY((float) (Settings.SCREEN_HEIGHT / 2 - Resources.SHIP_HEIGHT / 2));
        ship.setX((float) Settings.SCREEN_WIDTH / 2);
    }
    @Override
    public void show() {
        gameSession.startGame();
        ship.setBounce(Settings.SCREEN_WIDTH, (float) Settings.SCREEN_HEIGHT / 2, 0, 0);
    }

    @Override
    public void render(float delta) {
        updateObject();
        spawnObject();
        handleInput();
        draw();
        myGdxGame.stepWorld(delta);
    }

    @Override
    public void dispose() {
        ship.dispose();
        for (TrashObject trashObject : trashArray) trashObject.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
        ship.move(myGdxGame.touch);
    }

    private void draw() {
        myGdxGame.camera.update();
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        for (TrashObject trashObject : trashArray) trashObject.draw(myGdxGame.batch);
        for (BulletObject bulletObject : bulletArray) bulletObject.draw(myGdxGame.batch);
        ship.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    private void spawnObject() {
        if (gameSession.shouldSpawnTrash()) {
            trashArray.add(new TrashObject(

                    Settings.TRASH_INTERNAL_TEXTURE_PATH,
                    Settings.TRASH_WIDTH,
                    Settings.TRASH_HEIGHT,
                    myGdxGame.world

            ));
        }
        if (ship.needToShoot()) {
            bulletArray.add(new BulletObject(

                    Settings.BULLET_INTERNAL_TEXTURE_PATH,
                    Settings.BULLET_WIDTH,
                    Settings.BULLET_HEIGHT,
                    ship.getX(),
                    ship.getY() + ship.getHeight() / 2,
                    myGdxGame.world

            ));
        }
    }

    private void updateObject() {
        if (!ship.isAlive()) {
            System.out.println("Game over!");
        }
        for (int i = 0; i < trashArray.size(); i++) {
            if (!trashArray.get(i).isInFrame() || !trashArray.get(i).isAlive()) {
                myGdxGame.world.destroyBody(trashArray.get(i).getBody());
                trashArray.get(i).dispose();
                trashArray.remove(i--);
            }
        }
        for (int i = 0; i < bulletArray.size(); i++) {
            if (bulletArray.get(i).hasToBeDestroyed()) {
                myGdxGame.world.destroyBody(bulletArray.get(i).getBody());
                bulletArray.get(i).dispose();
                bulletArray.remove(i--);
            }
        }
    }
}
