package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class ShipObject extends GameObject {

    private float maxX, maxY;
    private float minX, minY;

    private static final String TEXTURE_PATH = "Ship/";

    public ShipObject(String texturePath, float width, float height, float x, float y, World world) {
        super(MAIN_TEXTURE_PATH + TEXTURE_PATH + texturePath, width, height, x, y, world);
    }

    public void setBounce (float maxX, float maxY, float minX, float minY) {
        this.maxX = maxX;
        this.maxY = maxY + height / 2;
        this.minX = minX;
        this.minY = minY;
    }

    private void putInFrame() {
        if (getY() > maxY) {
            setY(maxY);
        }
        if (getX() < minX) {
            setX(minX);
        }
        if (getX() > maxY) {
            setX(maxX);
        }
        if (getY() < minY) {
            setY(0);
        }
    }

    @Override
    public void draw(Batch batch) {
        putInFrame();
        super.draw(batch);
    }

    public void move(Vector3 vector3) {
        if (vector3 == null) {
            return;
        }
        float fx = (vector3.x - getX()) * Settings.SHIP_FORCE_RATIO;
        float fy = (vector3.y - getY()) * Settings.SHIP_FORCE_RATIO;
        body.applyForceToCenter(fx, fy, true);
    }
    @Override
    public void setY(float y) {
        super.setY(y);
    }
}
