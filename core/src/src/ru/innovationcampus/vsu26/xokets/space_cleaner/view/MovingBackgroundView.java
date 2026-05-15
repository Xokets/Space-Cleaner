package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class MovingBackgroundView extends View {
    private Texture texture;
    private int speed;
    public MovingBackgroundView(String texturePath) {
        super(0, 0);
        speed = 500;
        texture = new Texture(texturePath);
        height = Settings.SCREEN_HEIGHT;
        width = Settings.SCREEN_WIDTH;
    }

    public void move(float delta) {
        y -= speed * delta;
        if (y + height <= 0) {
            y = 0;
        }
    }

    @Override
    public boolean isHit(float tx, float ty) {
        return false;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
        batch.draw(texture, x, y + height, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
