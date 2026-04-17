package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Disposable;

public abstract class GameObject implements Disposable {
    protected float width;
    protected float height;
    protected Texture texture;
    protected BodyDef bodyDef;

    public GameObject(float width, float height) {
        this.width = width;
        this.height = height;
        texture = new Texture("");

    }

    public abstract void draw(Batch batch);

    @Override
    public void dispose() {
        texture.dispose();
    }
}
