package src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;
import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class PlanetObject extends TrashObject {

    private PlanetType type;
    public PlanetObject(@NotNull World world, PlanetType planetType) {
        super();
        type = planetType;
        switch (type) {
            case EARTH:
                texture = new Texture(Resources.EARTH_INTERNAL_TEXTURE_PATH);
                width = Settings.EARTH_RADIUS;
                height = Settings.EARTH_RADIUS;
                break;
            case JUPITER:
                texture = new Texture(Resources.JUPITER_INTERNAL_TEXTURE_PATH);
                width = Settings.JUPITER_RADIUS;
                height = Settings.JUPITER_RADIUS;
                break;
            case NEPTUNE:
                texture = new Texture(Resources.NEPTUNE_INTERNAL_TEXTURE_PATH);
                width = Settings.NEPTUNE_RADIUS;
                height = Settings.NEPTUNE_RADIUS;
                break;
            case MARS:
                texture = new Texture(Resources.MARS_INTERNAL_TEXTURE_PATH);
                width = Settings.MARS_RADIUS;
                height = Settings.MARS_RADIUS;
                break;
            case MERCURY:
                texture = new Texture(Resources.MERCURY_INTERNAL_TEXTURE_PATH);
                width = Settings.MERCURY_RADIUS;
                height = Settings.MERCURY_RADIUS;
                break;
            case VENUS:
                texture = new Texture(Resources.VENUS_INTERNAL_TEXTURE_PATH);
                width = Settings.VENUS_RADIUS;
                height = Settings.VENUS_RADIUS;
                break;
            case SATURN:
                texture = new Texture(Resources.SATURN_INTERNAL_TEXTURE_PATH);
                width = Settings.SATURN_RADIUS;
                height = Settings.SATURN_RADIUS;
                break;
            case URANUS:
                texture = new Texture(Resources.URANUS_INTERNAL_TEXTURE_PATH);
                width = Settings.URANUS_RADIUS;
                height = Settings.URANUS_RADIUS;
                break;
        }
        body = createBody(0, 0, world);
        body.setLinearDamping(-4.6f);
        setTargetPosition(-Settings.TRASH_VELOCITY);
    }

    @Override
    protected Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) * Settings.SCALE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.3f;
        fixtureDef.friction = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        fixture.getFilterData().categoryBits = cBits;
        circleShape.dispose();

        body.setTransform(x * Settings.SCALE, y * Settings.SCALE, 0f);
        return body;
    }

    @Override
    public void hit() {}

}
