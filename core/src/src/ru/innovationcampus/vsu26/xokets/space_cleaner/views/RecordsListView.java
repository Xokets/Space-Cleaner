package src.ru.innovationcampus.vsu26.xokets.space_cleaner.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.TreeSet;

import src.ru.innovationcampus.vsu26.xokets.space_cleaner.Settings;

public class RecordsListView extends TextView {

    public RecordsListView(float y, @NotNull BitmapFont font) {
        super(0, y, font, " ");
    }

    public void setRecords(TreeSet<Integer> records) {
        int rowsCount = Math.min(records.size(), Settings.RECORD_TABLE_ROWS_COUNT);
        int i = 1;
        for (int record : records) {
            if (i > rowsCount) break;
            System.out.println(record);
            text += (String.format("%s. - %s\n", i, record));
            i++;
        }

        GlyphLayout gl = new GlyphLayout(getFont(), getText());
        x = (Settings.SCREEN_WIDTH - gl.width) / 2;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    @Override
    public boolean isHit(@Nullable Vector3 vector3) {
        return super.isHit(vector3);
    }

    @Override
    public void setText(@NotNull String text) {
        super.setText(text);
    }
}
