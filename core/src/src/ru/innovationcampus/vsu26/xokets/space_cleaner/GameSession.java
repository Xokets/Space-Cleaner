package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    private long sessionTime;
    private long nextTrashSpawnTime;
    private GameState state;
    private long lastPauseTime;

    public void startGame() {
        state = GameState.RUNNING;
        sessionTime = TimeUtils.millis();
        nextTrashSpawnTime = sessionTime + (long) (Settings.STARTING_TRASH_APPEARANCE_COOL_DOWN * getTrashPeriodCoolDown());

    }

    public boolean shouldSpawnTrash() {
        if (nextTrashSpawnTime <= TimeUtils.millis()) {
            nextTrashSpawnTime = TimeUtils.millis() + (long) (Settings.STARTING_TRASH_APPEARANCE_COOL_DOWN * getTrashPeriodCoolDown());
            return true;
        }
        return false;
    }

    private float getTrashPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionTime) / 1000);
    }

    public void pause() {
        state = GameState.PAUSED;
        lastPauseTime = TimeUtils.millis();
    }

    public void resume() {
        long pauseTime = TimeUtils.millis() - lastPauseTime;
        sessionTime -= pauseTime;
        state = GameState.RUNNING;
    }

    public GameState getState() {
        return state;
    }
}
