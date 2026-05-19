package src.ru.innovationcampus.vsu26.xokets.space_cleaner.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.NotNull;

public class ButtonView extends View {
    private Texture texture;
    private BitmapFont font;
    private String text;

    private float textX, textY;
    private float textWidth, textHeight;

    public ButtonView(float x, float y, float width, float height, @NotNull String internalTexturePath, @NotNull BitmapFont font, @NotNull String text) {
        super(x, y, width, height);
        texture = new Texture(internalTexturePath);
        this.font = font;
        this.text = text;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        textWidth = glyphLayout.width;
        textHeight = glyphLayout.height;

        setX(x);
        setY(y);
    }

    public ButtonView(float x, float y, float width, float height, @NotNull String internalTexturePath) {
        super(x, y, width, height);
        texture = new Texture(internalTexturePath);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
        if (font == null) return;
        font.draw(batch, text, textX, textY);
    }

    @Override
    public void dispose() {
        texture.dispose();
        if (font == null) return;
        font.dispose();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        textX = x + (width - textWidth) / 2;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        textY = y + height / 2 + textHeight / 2;
    }
}
