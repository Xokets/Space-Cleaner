package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.Nullable;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;

public class LiveView extends View {
    private static final int LIVE_PADDING = 6;
    private String text;
    private int live;
    private Texture texture;

    public LiveView(float x, float y) {
        super(x, y);
        texture = new Texture(Resources.INTERFACE_LIVE_INTERNAL_TEXTURE_PATH);
        width = texture.getWidth();
        height = texture.getHeight();
        live = 0;
    }

    @Override
    public boolean isHit(@Nullable Vector3 vector3) {
        return false;
    }

    @Override
    public void draw(Batch batch) {
        if (live > 0) batch.draw(texture, x + (texture.getWidth() + LIVE_PADDING), y, width, height);
        if (live > 1) batch.draw(texture, x, y, width, height);
        if (live > 2) batch.draw(texture, x + 2 * (texture.getWidth() + LIVE_PADDING), y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }
}
