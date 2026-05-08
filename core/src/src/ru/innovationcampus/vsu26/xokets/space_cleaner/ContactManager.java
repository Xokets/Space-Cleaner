package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.game_object.GameObject;

public class ContactManager {
    private World world;

    public ContactManager(World world) {
        this.world = world;
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();
                int cDef = contact.getFixtureA().getFilterData().categoryBits;
                int cDef2 = contact.getFixtureB().getFilterData().categoryBits;
                if ((cDef == Settings.TRASH_BIT && (cDef2 == Settings.BULLET_BIT || cDef2 == Settings.SHIP_BIT))
                        || (cDef2 == Settings.TRASH_BIT && (cDef == Settings.BULLET_BIT || cDef == Settings.SHIP_BIT))) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                    System.out.println("test");
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }
}
