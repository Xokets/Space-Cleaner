package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;


public abstract class GameObject implements Disposable {
    public static final String MAIN_TEXTURE_PATH = "Texture/";
    protected float width;
    protected float height;
    protected Texture texture;
    protected Body body;

    public GameObject(@NotNull String texturePath, @Nullable float width,@Nullable float height,@Nullable float x,@Nullable float y, @NotNull World world) {
        this.width = width;
        this.height = height;

        texture = new Texture(texturePath);
        body = createBody(x, y, world);
    }

    public void draw(@NotNull Batch batch) {
        batch.draw(texture, getX(), getY(), width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return body.getPosition().x / Settings.SCALE;
    }

    public float getY() {
        return body.getPosition().y / Settings.SCALE;
    }

    public void setX(float x) {
        body.setTransform(x * Settings.SCALE, body.getPosition().y, 0);
    }

    public void setY(float y) {
        body.setTransform(body.getPosition().x, y * Settings.SCALE, 0);
    }

    private Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) * Settings.SCALE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setTransform(x * Settings.SCALE, y * Settings.SCALE, 0f);
        return body;
    }
}
