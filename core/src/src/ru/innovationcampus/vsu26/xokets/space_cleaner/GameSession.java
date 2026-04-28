package src.ru.innovationcampus.vsu26.xokets.space_cleaner;

public class GameSession {

    long sessionTime;
    long nextTrashSpawnTime;

    public void startGame() {
        sessionTime = System.currentTimeMillis();
    }
}
