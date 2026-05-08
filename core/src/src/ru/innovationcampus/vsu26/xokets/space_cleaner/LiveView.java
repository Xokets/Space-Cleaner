package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.graphics.g2d.Batch;

public class LiveView extends View {
    private static final int livePadding = 6;
    private String text;
    private int live;

    public LiveView(float x, float y) {
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
