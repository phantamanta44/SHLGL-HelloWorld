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

    private static TextureInfo tex; // A texture object
    private static float hue = 0; // The hue cycled through during game ticks

    public static void main(String[] args) {
        SHLGL.init(1280, 960, "Hello, world!"); // Initialize SHLGL

        SHLGL.getInstance().setResolution(300, 300); // Set game resolution
        tex = TextureManager.getTextureInfo("hello.png"); // Load a texture

        SHLGL.getInstance().getEventBus().on(GameTickEvent.class, e -> hue = (hue + 0.05F) % 360F); // Cycle through hues on each game tick
        SHLGL.getInstance().getEventBus().on(RenderEvent.class, e -> { // Register render tick handler
            RenderBuffer buf = e.getBuffer(); // Retrieve the render buffer
            buf.bind(tex); // Bind the previously loaded texture

            buf.colour4F(1F, 1F, 1F, 1F); // Draw the background image in white
            buf.drawRect(0, 0, 300, 300);

            int colour = Color.HSBtoRGB(hue, 1F, 0.8F); // Resolve RGB colour from HSB
            buf.colour4F( // Set the new colour
                    ((colour & 0xFF0000) >> 16) / 255F,
                    ((colour & 0xFF00) >> 8) / 255F,
                    (colour & 0xFF) / 255F,
                    1F
            );
            buf.drawRect(100, 100, 100, 100); // Draw the center image

            buf.colour4F(1F, 0F, 0F, 1F); // Draw the right image in red
            buf.drawRect(200, 100, 100, 100);

            buf.colour4F(0F, 1F, 0F, 1F); // Draw the top image in green
            buf.drawRect(100, 200, 100, 100);

            buf.colour4F(0F, 0F, 1F, 1F); // Draw the left image in blue
            buf.drawRect(0, 100, 100, 100);

            buf.colour4F(1F, 1F, 0F, 1F); // Draw the bottom image in yellow
            buf.drawRect(100, 0, 100, 100);
        });

        SHLGL.getInstance().getGameWindow().setVisible(true); // Show the window
        SHLGL.getInstance().runMainLoop(20); // Start the main game loop
    }

}
