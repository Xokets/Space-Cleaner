package src.ru.innovationcampus.vsu26.xokets.space_cleaner.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MemoryManager {

    private static final String SOUND_OPTION = "isSoundOn";
    private static final String MUSIC_OPTION = "isMusicOn";
    private static final String RECORDS_TABLE = "recordsTable";
    private static final Preferences preferences = Gdx.app.getPreferences("User saves");

    public static void saveSoundSettings(boolean isOn) {
        preferences.putBoolean(SOUND_OPTION, isOn);
        preferences.flush();

    }

    public static boolean loadIsSoundOn() {
        return preferences.getBoolean(SOUND_OPTION, true);
    }

    public static void saveMusicSettings(boolean isOn) {
        preferences.putBoolean(MUSIC_OPTION, isOn);
        preferences.flush();
    }

    public static boolean loadIsMusicOn() {
        return preferences.getBoolean(MUSIC_OPTION, true);
    }

    public static Set<Integer> loadRecordsTable() {
        if (preferences.getString(RECORDS_TABLE) == null) {
            return null;
        }

        return new Json().fromJson(TreeSet.class, preferences.getString(RECORDS_TABLE));
    }

    public static void saveTableOfRecords(Collection<Integer> table) {
        preferences.putString(RECORDS_TABLE, new Json().toJson(table));
        preferences.flush();
    }
}
