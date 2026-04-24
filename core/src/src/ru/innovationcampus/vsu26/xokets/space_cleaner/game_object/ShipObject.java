package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import com.badlogic.gdx.physics.box2d.World;

public class ShipObject extends GameObject {

    private static final String TEXTURE_PATH = "Ship/";

    public ShipObject(String texturePath, float width, float height, float x, float y, World world) {
        super(MAIN_TEXTURE_PATH + TEXTURE_PATH + texturePath, width, height, x, y, world);
    }

}
