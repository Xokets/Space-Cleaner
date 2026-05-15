package src.ru.innovationcampus.vsu26.xokets.space_cleaner.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import org.jetbrains.annotations.NotNull;

public class FontBuilder {
    public static BitmapFont generate(int size, @NotNull Color color, @NotNull String internalFontPath) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(internalFontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


        parameter.size = size;
        parameter.color = color;

        try {
            return generator.generateFont(parameter);
        } finally {
            generator.dispose();
        }
    }
}
