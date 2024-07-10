package net.mabid.atomic.scene;

import net.mabid.atomic.input.KeyListener;

import static org.lwjgl.opengl.GL11.*;

public class GameScene extends Scene {

    public GameScene() {
        sceneName = "Game Scene";
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public String getSceneName() {
        return sceneName;
    }

}