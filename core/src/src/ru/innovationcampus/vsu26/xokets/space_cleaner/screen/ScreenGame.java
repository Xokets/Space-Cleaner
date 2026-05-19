package src.ru.innovationcampus.vsu26.xokets.space_cleaner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.GameState;
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
    private ImageView fullBlackOutView;
    private TextView scoreTextView;
    private TextView pauseTextView;
    private LiveView liveView;
    private ButtonView pauseButton;
    private ButtonView homeButton;
    private ButtonView continueButton;
    private int point;

    public ScreenGame(@NotNull MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        gameSession = new GameSession();
        contactManager = new ContactManager(myGdxGame.world);
        ship = new ShipObject(

                Resources.SHIP_INTERNAL_TEXTURE_PATH,
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
        fullBlackOutView = new ImageView(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, Resources.BLACKOUT_FULL_INTERNAL_TEXTURE_PATH);
        pauseTextView = new TextView(0, 0, myGdxGame.font1, "Pause");
        pauseTextView.setX((float) Settings.SCREEN_WIDTH / 2 - pauseTextView.getWidth() / 2);
        pauseTextView.setY((float) Settings.SCREEN_HEIGHT / 2 + 200);
        homeButton = new ButtonView(0, (float) Settings.SCREEN_HEIGHT / 2, 150, 80, Resources.INTERFACE_TEXT_BUTTON_BG_SHORT_INTERNAL_TEXTURE_PATH, myGdxGame.font1, "Home");
        homeButton.setX((float) Settings.SCREEN_WIDTH / 2 - homeButton.getWidth() / 2);
        continueButton = new ButtonView(pauseTextView.getX(), homeButton.getY() - homeButton.getWidth() - 50, 150, 80, Resources.INTERFACE_TEXT_BUTTON_BG_SHORT_INTERNAL_TEXTURE_PATH, myGdxGame.font1, "Continue");
        continueButton.setX((float) Settings.SCREEN_WIDTH / 2 - continueButton.getWidth() / 2);
    }
    @Override
    public void show() {
        restartGame();
        point = 0;
        scoreTextView = new TextView(Settings.SCREEN_WIDTH - 400, Settings.SCREEN_HEIGHT - 60, myGdxGame.font1, "Count: " + point);

        ship.setBounce(Settings.SCREEN_WIDTH, (float) Settings.SCREEN_HEIGHT / 2, 0, 0);
        pauseButton = new ButtonView(0, 0, 40, 40, Resources.INTERFACE_BUTTON_PAUSE_INTERNAL_TEXTURE_PATH);
        pauseButton.setX(Settings.SCREEN_WIDTH - pauseButton.getWidth() - 6);
        pauseButton.setY(Settings.SCREEN_HEIGHT - pauseButton.getHeight() - 6);
    }

    @Override
    public void render(float delta) {

        if (gameSession.getState() == GameState.RUNNING) {
            updateObject(delta);
            cleanObject();
            spawnObject();
            myGdxGame.stepWorld(delta);
        }
        handleInput();
        draw();
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
        fullBlackOutView.dispose();
        continueButton.dispose();
        homeButton.dispose();
        pauseTextView.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (touch.y >= 0 && touch.y <= (float) Settings.SCREEN_HEIGHT / 2) {
                myGdxGame.touch = touch;
            }

            if (Gdx.input.justTouched()) {
                myGdxGame.touch = touch;
                return;
            }
        }
        switch (gameSession.getState()) {
            case RUNNING:
                if (pauseButton.isHit(myGdxGame.touch)) {
                    myGdxGame.touch = null;
                    gameSession.pause();
                }
                ship.move(myGdxGame.touch);
                break;
            case PAUSED:
                if (continueButton.isHit(myGdxGame.touch)) {
                    myGdxGame.touch = null;
                    gameSession.resume();
                }
                if (homeButton.isHit(myGdxGame.touch)) {
                    myGdxGame.touch = null;
                    myGdxGame.setScreen(myGdxGame.screenMenu);
                }
                break;
        }
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
        if (gameSession.getState() == GameState.PAUSED) {
            fullBlackOutView.draw(myGdxGame.batch);
            pauseTextView.draw(myGdxGame.batch);
            homeButton.draw(myGdxGame.batch);
            continueButton.draw(myGdxGame.batch);
        }
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
    private void restartGame() {
        for (int i = 0; i < trashArray.size(); i++) {
            myGdxGame.world.destroyBody(trashArray.get(i).getBody());
            trashArray.get(i).dispose();
            trashArray.remove(i--);
        }
        if (ship != null) {
            myGdxGame.world.destroyBody(ship.getBody());
            ship.dispose();
            ship = new ShipObject(Resources.SHIP_INTERNAL_TEXTURE_PATH, Resources.SHIP_WIDTH, Resources.SHIP_HEIGHT, myGdxGame.world);
            ship.setY((float) (Settings.SCREEN_HEIGHT / 2 - Resources.SHIP_HEIGHT / 2));
            ship.setX((float) Settings.SCREEN_WIDTH / 2);
        }
        bulletArray.clear();
        gameSession.startGame();
    }
}
