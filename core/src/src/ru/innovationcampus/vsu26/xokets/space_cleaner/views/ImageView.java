package src.ru.innovationcampus.vsu26.xokets.space_cleaner.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.Nullable;

public class ImageView extends View {

    private Texture texture;
    public ImageView(float x, float y, float width, float height, String texturePath) {
        super(x, y);
        this.width = width;
        this.height = height;
        texture = new Texture(texturePath);
    }

    @Override
    public boolean isHit(@Nullable Vector3 vector3) {
        return false;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        if (texture == null) return;
        texture.dispose();
    }


}
