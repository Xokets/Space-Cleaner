package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextView extends View {
    private BitmapFont font;

    public TextView(float x, float y) {
        super(x, y);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        return false;
    }

    @Override
    public void draw(Batch batch) {

    }

    @Override
    public void dispose() {

    }
}
