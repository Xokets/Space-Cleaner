package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.Set;
import java.util.TreeSet;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.managers.MemoryManager;

public class GameSession {
    private long sessionTime;
    private long nextTrashSpawnTime;
    private GameState state;
    private long lastPauseTime;

    private int score;
    private int destroyedTrashCount;

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

    public void incrementDestroyedTrashCount() {
        destroyedTrashCount++;
    }

    public void updateScore() {
        if (state == GameState.PAUSED || state == GameState.ENDED) return;
        score = (int) (TimeUtils.millis() - sessionTime) / 100 + destroyedTrashCount * 100;
    }

    public void pause() {
        state = GameState.PAUSED;
        lastPauseTime = TimeUtils.millis();
    }

    public void resume() {
        long pauseTime = TimeUtils.millis() - lastPauseTime;
        sessionTime += pauseTime;
        state = GameState.RUNNING;
    }

    public GameState getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void endGame() {
        updateScore();
        state = GameState.ENDED;
        Set<Integer> recordsTable = MemoryManager.loadRecordsTable();
        recordsTable.add(getScore());
        MemoryManager.saveTableOfRecords(recordsTable);

    }
}
