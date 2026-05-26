package src.ru.innovationcampus.vsu26.xokets.space_cleaner.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import org.jetbrains.annotations.Nullable;

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

    public boolean isHit(@Nullable Vector3 vector3) {
        if (vector3 == null) return false;
        return vector3.x >= x && vector3.x <= x + width && vector3.y >= y && vector3.y <= y + height;
    }

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
