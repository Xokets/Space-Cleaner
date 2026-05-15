package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ImageView extends View {

    private Texture texture;
    public ImageView(float x, float y, float width, float height, String texturePath) {
        super(x, y);
        this.width = width;
        this.height = height;
        texture = new Texture(texturePath);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        return false;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


}
