package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_objects;

import static src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame.rand;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;


public class TrashObject extends GameObject {

    private static final int PADDING_HORIZONTAL = 30;
    private short hitPoint;

    public TrashObject(@NotNull String texturePath, float width, float height, @NotNull World world) {
        super(texturePath, width, height, world, Settings.TRASH_BIT);
        setTargetPosition(-Settings.TRASH_VELOCITY);
        hitPoint = 1;
    }
    protected TrashObject() {
        hitPoint = 1;
        cBits = Settings.TRASH_BIT;
    }

    public boolean isInFrame() {
        return getY() + height / 2 >= 0;
    }

    @Override
    public void hit() {
        hitPoint--;
    }

    public boolean isAlive() {
        return hitPoint > 0;
    }

    protected void setTargetPosition(float velocity) {
        setY(Settings.SCREEN_HEIGHT + height / 2 + PADDING_HORIZONTAL);
        setX(width / 2 + PADDING_HORIZONTAL + (rand.nextInt((int) (Settings.SCREEN_WIDTH - 2 * PADDING_HORIZONTAL - width))));
        body.setLinearVelocity(new Vector2(0, velocity));
    }
}
