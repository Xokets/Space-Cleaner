package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

public abstract class View implements Disposable {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public View(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public View(float x, float y, float width, float height) {
        this(x, y);
        this.width = width;
        this.height = height;
    }

    public abstract boolean isHit(float tx, float ty);

    public abstract void draw(Batch batch);


    @Override
    public abstract void dispose();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
