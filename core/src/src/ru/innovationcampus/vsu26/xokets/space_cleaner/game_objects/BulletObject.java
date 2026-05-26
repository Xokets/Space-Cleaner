package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class BulletObject extends GameObject {

    private boolean wasHit;

    public BulletObject(@NotNull String texturePath, float width, float height, float x, float y, @NotNull World world) {
        super(texturePath, width, height, x, y, world, Settings.BULLET_BIT);
        body.setLinearVelocity(new Vector2(0, Settings.BULLET_VELOCITY));
        body.setBullet(true);
        wasHit = false;

    }

    public boolean hasToBeDestroyed() {
        return wasHit || getY() + height / 2 > Settings.SCREEN_HEIGHT;
    }

    @Override
    public void hit() {
        wasHit = true;
    }
}
