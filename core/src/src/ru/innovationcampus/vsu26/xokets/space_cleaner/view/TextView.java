package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.NotNull;

public class TextView extends View {
    private BitmapFont font;
    private String text;

    public TextView(float x, float y, @NotNull BitmapFont font, @NotNull String text) {
        super(x, y);
        this.font = font;
        this.text = text;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;

    }

    @Override
    public void draw(Batch batch) {
        font.draw(batch, text, x, y);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public void setText(@NotNull String text) {
        this.text = text;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }
}
