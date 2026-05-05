package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class ShipObject extends GameObject {

    private float maxX, maxY;
    private float minX, minY;
    private long lastShotTime;
    private short hitPoint;

    private static final String TEXTURE_PATH = "Ship/";

    public ShipObject(@NotNull String texturePath, float width, float height, @NotNull World world) {
        super(MAIN_TEXTURE_PATH + TEXTURE_PATH + texturePath, width, height, world, Settings.SHIP_BIT);
        body.setLinearDamping(10);
        hitPoint = Resources.SHIP_HIT_POINT_COUNT;
    }

    public void setBounce(float maxX, float maxY, float minX, float minY) {
        this.maxX = maxX;
        this.maxY = maxY - height / 2;
        this.minX = minX - width;
        this.minY = minY - height / 2;
    }

    private void putInFrame() {
        if (getY() > maxY) {
            setY(maxY);
        }
        if (getY() < minY) {
            setY(minY);
        }
        if (getX() > maxX) {
            setX(minX);
        }
        if (getX() < minX) {
            setX(maxX);
        }
    }

    @Override
    public void draw(@NotNull Batch batch) {
        putInFrame();
        super.draw(batch);
    }

    public void move(@Nullable Vector3 vector3) {
        if (vector3 == null) return;
        body.applyForceToCenter(new Vector2((vector3.x - (getX() + width / 2)) * Settings.SHIP_FORCE_RATIO, (vector3.y - (getY() + height / 2)) * Settings.SHIP_FORCE_RATIO), true);
    }

    public boolean needToShoot() {
        if (TimeUtils.millis() - lastShotTime >= Settings.SHOOTING_COOL_DOWN) {
            lastShotTime = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        return hitPoint > 0;
    }

    @Override
    public void hit() {
        hitPoint -= 1;
    }
}
