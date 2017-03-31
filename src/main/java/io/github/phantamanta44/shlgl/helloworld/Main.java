package io.github.phantamanta44.shlgl.helloworld;

import io.github.phantamanta44.shlgl.SHLGL;
import io.github.phantamanta44.shlgl.engine.event.IListener;
import io.github.phantamanta44.shlgl.engine.event.impl.RenderEvent;
import io.github.phantamanta44.shlgl.graphics.texture.TextureInfo;
import io.github.phantamanta44.shlgl.graphics.texture.TextureManager;

public class Main implements IListener<RenderEvent> {

    private static final RenderHandler renderer = new RenderHandler();
    private static TextureInfo tex;

    public static void main(String[] args) {
        SHLGL.init(1280, 960, "Hello, world!");
        tex = TextureManager.getTextureInfo("hello.png");
        SHLGL.getInstance().setResolution(1280, 960);
        SHLGL.getInstance().getEventBus().on(RenderEvent.class, renderer);
        SHLGL.getInstance().getGameWindow().setVisible(true);
        SHLGL.getInstance().runMainLoop(20);
    }

    @Override
    public void onEvent(RenderEvent event) {
        TextureManager.bind(tex);
        event.getBuffer().drawRect(0, 0, 800, 450);
    }

}
