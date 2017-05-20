package io.github.phantamanta44.shlgl.helloworld;

import io.github.phantamanta44.shlgl.SHLGL;
import io.github.phantamanta44.shlgl.engine.event.IListener;
import io.github.phantamanta44.shlgl.engine.event.impl.RenderEvent;
import io.github.phantamanta44.shlgl.graphics.render.RenderBuffer;
import io.github.phantamanta44.shlgl.graphics.texture.TextureInfo;
import io.github.phantamanta44.shlgl.graphics.texture.TextureManager;

public class Main implements IListener<RenderEvent> {

    private static Main INSTANCE;
    private TextureInfo tex;

    public static void main(String[] args) {
        SHLGL.init(1280, 960, "Hello, world!");
        SHLGL.getInstance().setResolution(1280, 960);
        SHLGL.getInstance().getEventBus().on(RenderEvent.class, INSTANCE = new Main());
        SHLGL.getInstance().getGameWindow().setVisible(true);
        SHLGL.getInstance().runMainLoop(20);
    }

    private Main() {
        this.tex = TextureManager.getTextureInfo("hello.png");
    }

    @Override
    public void onEvent(RenderEvent event) {
        RenderBuffer buf = event.getBuffer();
        buf.bind(tex);
        buf.colour4F(1F, 1F, 1F, 1F);
        buf.drawRect(0, 0, 1280, 960);
        buf.colour4F(1F, 0F, 0F, 1F);
        buf.drawRect(1280, 0, 1280, 960);
        buf.colour4F(0F, 1F, 0F, 1F);
        buf.drawRect(0, 960, 1280, 960);
        buf.colour4F(0F, 0F, 1F, 1F);
        buf.drawRect(-1280, 0, 1280, 960);
        buf.colour4F(1F, 1F, 0F, 1F);
        buf.drawRect(0, -960, 1280, 960);
    }

}
