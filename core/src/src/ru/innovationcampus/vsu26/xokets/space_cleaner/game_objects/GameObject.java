package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import org.jetbrains.annotations.NotNull;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;


public abstract class GameObject implements Disposable {
    public static final String MAIN_TEXTURE_PATH = "Texture/";
    protected float width;
    protected float height;
    protected Texture texture;
    protected Body body;
    protected short cBits;

    public GameObject(@NotNull String texturePath, float width, float height, float x, float y, @NotNull World world, short cBits) {
        this.cBits = cBits;
        this.width = width;
        this.height = height;


        texture = new Texture(texturePath);
        body = createBody(x, y, world);
    }

    public GameObject(@NotNull String texturePath, float width, float height, @NotNull World world, short cBits) {
        this(texturePath, width, height, 0f, 0f, world, cBits);
    }

    public GameObject() {}

    public void draw(@NotNull Batch batch) {
        batch.draw(texture, getX() - width / 2, getY() - height / 2, width, height);
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

    protected Body createBody(float x, float y, World world) {
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

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        fixture.getFilterData().categoryBits = cBits;
        circleShape.dispose();

        body.setTransform(x * Settings.SCALE, y * Settings.SCALE, 0f);
        return body;
    }

    public Body getBody() {
        return body;
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
    public abstract void hit();
}
