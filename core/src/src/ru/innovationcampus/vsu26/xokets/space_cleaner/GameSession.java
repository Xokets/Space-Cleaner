package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    private long sessionTime;
    private long nextTrashSpawnTime;

    public void startGame() {
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
}
