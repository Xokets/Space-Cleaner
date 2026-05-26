package src.ru.innovationcampus.vsu26.xokets.space_cleaner.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.World;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Resources;

public class AudioManager {
    private World world;

    private Music backgroundMusic;
    private Sound shootSound;
    private Sound explosionSound;

    public boolean isSoundOn;
    public boolean isMusicOn;

    public AudioManager(World world) {
        this.world = world;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(Resources.MUSIC_BACKGROUND_INTERNAL_TEXTURE_PATH));
        shootSound = Gdx.audio.newSound(Gdx.files.internal(Resources.SOUND_SHOOT_INTERNAL_TEXTURE_PATH));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal(Resources.SOUND_DESTROY_INTERNAL_TEXTURE_PATH));

        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.2f);

        updateSoundFlag();
        updateMusicFlag();
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Sound getShootSound() {
        return shootSound;
    }

    public Sound getExplosionSound() {
        return explosionSound;
    }

    public void updateMusicFlag() {
        isMusicOn = MemoryManager.loadIsMusicOn();
        if (isMusicOn) backgroundMusic.play();
        else backgroundMusic.stop();
    }

    public void updateSoundFlag() {
        isSoundOn = MemoryManager.loadIsSoundOn();
    }
}
