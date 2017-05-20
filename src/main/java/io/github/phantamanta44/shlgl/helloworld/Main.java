package io.github.phantamanta44.shlgl.helloworld;

import io.github.phantamanta44.shlgl.SHLGL;
import io.github.phantamanta44.shlgl.engine.event.IListener;
import io.github.phantamanta44.shlgl.engine.event.impl.GameTickEvent;
import io.github.phantamanta44.shlgl.engine.event.impl.RenderEvent;
import io.github.phantamanta44.shlgl.graphics.render.RenderBuffer;
import io.github.phantamanta44.shlgl.graphics.texture.TextureInfo;
import io.github.phantamanta44.shlgl.graphics.texture.TextureManager;

import java.awt.*;

public class Main {

    private static TextureInfo tex;
    private static float hue = 0;

    public static void main(String[] args) {
        SHLGL.init(1280, 960, "Hello, world!");

        SHLGL.getInstance().setResolution(1280, 960);
        tex = TextureManager.getTextureInfo("hello.png");

        SHLGL.getInstance().getEventBus().on(GameTickEvent.class, e -> hue = (hue + 0.05F) % 360F);
        SHLGL.getInstance().getEventBus().on(RenderEvent.class, e -> {
            RenderBuffer buf = e.getBuffer();
            buf.bind(tex);
            int colour = Color.HSBtoRGB(hue, 1F, 0.8F);
            buf.colour4F(
                    ((colour & 0xFF0000) >> 16) / 255F,
                    ((colour & 0xFF00) >> 8) / 255F,
                    (colour & 0xFF) / 255F,
                    1F
            );
            buf.drawRect(0, 0, 1280, 960);
            buf.colour4F(1F, 0F, 0F, 1F);
            buf.drawRect(1280, 0, 1280, 960);
            buf.colour4F(0F, 1F, 0F, 1F);
            buf.drawRect(0, 960, 1280, 960);
            buf.colour4F(0F, 0F, 1F, 1F);
            buf.drawRect(-1280, 0, 1280, 960);
            buf.colour4F(1F, 1F, 0F, 1F);
            buf.drawRect(0, -960, 1280, 960);
        });
        
        SHLGL.getInstance().getGameWindow().setVisible(true);
        SHLGL.getInstance().runMainLoop(20);
    }

}
