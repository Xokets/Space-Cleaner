package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import static src.ru.innovationcampus.vsu26.xokets.space_cleaner.MyGdxGame.rand;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;


public class TrashObject extends GameObject {

    private static final int PADDING_HORIZONTAL = 30;

    public TrashObject(@NotNull String texturePath, @Nullable float width, @Nullable float height, @Nullable float x, @Nullable float y, @NotNull World world) {
        super(texturePath, width, height, x, y, world);
        setY(rand.nextFloat(Settings.SCREEN_HEIGHT + height / 2));
        setX(PADDING_HORIZONTAL + width / 2 + rand.nextFloat(Settings.SCREEN_WIDTH - width / 2 - PADDING_HORIZONTAL));
        body.setLinearVelocity(new Vector2(0, -Settings.TRASH_VELOCITY));
    }

    public boolean isInFrame() {
        return getY() + height / 2 >= 0;
    }


}
