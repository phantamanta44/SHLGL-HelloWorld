package io.github.phantamanta44.shlgl.helloworld;

import io.github.phantamanta44.shlgl.SHLGL;
import io.github.phantamanta44.shlgl.engine.event.impl.GameTickEvent;
import io.github.phantamanta44.shlgl.engine.event.impl.RenderEvent;

public class Main {



    public static void main(String[] args) {
        SHLGL.init(1280, 960, "Hello, world!");
        SHLGL.getInstance().setResolution(1280, 960);
        SHLGL.getInstance().getEventBus().on(GameTickEvent.class, INSTANCE);
        SHLGL.getInstance().getEventBus().on(RenderEvent.class, INSTANCE);
        SHLGL.getInstance().runMainLoop(20);
    }

}
