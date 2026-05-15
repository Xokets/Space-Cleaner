package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.utils.ContactManager;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.GameSession;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.ButtonView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.ImageView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.LiveView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.MovingBackgroundView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.view.TextView;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.BulletObject;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.ShipObject;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.TrashObject;

public class ScreenGame extends ScreenAdapter {
    private final MyGdxGame myGdxGame;
    private ShipObject ship;
    private GameSession gameSession;
    private ContactManager contactManager;
    private final ArrayList<TrashObject> trashArray = new ArrayList<>();
    private final ArrayList<BulletObject> bulletArray = new ArrayList<>();
    private MovingBackgroundView background;
    private ImageView topBlackOutView;
    private TextView scoreTextView;
    private LiveView liveView;
    private ButtonView pauseButton;
    private int point;

    public ScreenGame(@NotNull MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        gameSession = new GameSession();
        contactManager = new ContactManager(myGdxGame.world);
        ship = new ShipObject(

                Resources.SHIP_TEXTURE_NAME,
                Resources.SHIP_WIDTH,
                Resources.SHIP_HEIGHT,
                myGdxGame.world

        );
        ship.setY((float) (Settings.SCREEN_HEIGHT / 2 - Resources.SHIP_HEIGHT / 2));
        ship.setX((float) Settings.SCREEN_WIDTH / 2);
        background = new MovingBackgroundView(Resources.BACKGROUND_INTERNAL_TEXTURE_PATH);
        topBlackOutView = new ImageView(0, 0, Settings.SCREEN_WIDTH, 50, Resources.BLACKOUT_TOP_INTERNAL_TEXTURE_PATH);
        topBlackOutView.setY(Settings.SCREEN_HEIGHT - topBlackOutView.getHeight());
        liveView = new LiveView(0, 0);
        liveView.setY(Settings.SCREEN_HEIGHT - (liveView.getHeight()));
        liveView.setX(liveView.getWidth());
    }
    @Override
    public void show() {
        point = 0;
        scoreTextView = new TextView(Settings.SCREEN_WIDTH - 400, Settings.SCREEN_HEIGHT - 60, myGdxGame.font1, "Count: " + point);

        gameSession.startGame();
        ship.setBounce(Settings.SCREEN_WIDTH, (float) Settings.SCREEN_HEIGHT / 2, 0, 0);
        pauseButton = new ButtonView(0, 0, 40, 40, Resources.INTERFACE_BUTTON_PAUSE_INTERNAL_TEXTURE_PATH);
        pauseButton.setX(Settings.SCREEN_WIDTH - pauseButton.getWidth() - 6);
        pauseButton.setY(Settings.SCREEN_HEIGHT - pauseButton.getHeight() - 6);
    }

    @Override
    public void render(float delta) {

        updateObject(delta);
        cleanObject();
        spawnObject();
        handleInput();
        draw();
        myGdxGame.stepWorld(delta);
        scoreTextView.setText("Count: " + point);
    }

    @Override
    public void dispose() {
        ship.dispose();
        for (TrashObject trashObject : trashArray) trashObject.dispose();
        for (BulletObject bulletObject : bulletArray) bulletObject.dispose();
        background.dispose();
        topBlackOutView.dispose();
        liveView.dispose();
        scoreTextView.dispose();
        pauseButton.dispose();
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
        background.draw(myGdxGame.batch);
        for (TrashObject trashObject : trashArray) trashObject.draw(myGdxGame.batch);
        for (BulletObject bulletObject : bulletArray) bulletObject.draw(myGdxGame.batch);
        ship.draw(myGdxGame.batch);
        topBlackOutView.draw(myGdxGame.batch);
        liveView.draw(myGdxGame.batch);
        scoreTextView.draw(myGdxGame.batch);
        pauseButton.draw(myGdxGame.batch);
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

    private void updateObject(float delta) {
        background.move(delta);
        liveView.setLive(ship.getHitPoint());
    }

    private void cleanObject() {

        if (!ship.isAlive()) {
            System.out.println("Game over!");
            //FIXME
            throw new RuntimeException("Game over");
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
