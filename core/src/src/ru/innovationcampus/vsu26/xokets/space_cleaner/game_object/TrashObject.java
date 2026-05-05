package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import static src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame.rand;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;


public class TrashObject extends GameObject {

    private static final int PADDING_HORIZONTAL = 30;

    public TrashObject(@NotNull String texturePath, float width, float height, @NotNull World world) {
        super(texturePath, width, height, world);
        setY(Settings.SCREEN_HEIGHT + height / 2 + PADDING_HORIZONTAL);
        setX(rand.nextFloat(PADDING_HORIZONTAL + width / 2, Settings.SCREEN_WIDTH - width / 2 - PADDING_HORIZONTAL));
        body.setLinearVelocity(new Vector2(0, -Settings.TRASH_VELOCITY));
    }

    public boolean isInFrame() {
        return getY() + height / 2 >= 0;
    }
}
