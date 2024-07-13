package net.mabid.atomic.scene;

import net.mabid.atomic.graphics.brushes.RawModel;

import static org.lwjgl.opengl.GL11.*;

public class GameScene extends Scene {

    private RawModel test;

    public GameScene() {
        sceneName = "Game Scene";
    }

    @Override
    public void init() {
        float[] vertices = new float[] {
            -0.5f, -0.5f, 0.0f,
             0.5f, -0.5f, 0.0f,
             0.5f,  0.5f, 0.0f,
            -0.5f,  0.5f, 0.0f
        };

        int[] indices = new int[] {
             0, 1, 2,
             2, 3, 0
        };

        test = new RawModel("assets/shaders/default.vert", "assets/shaders/default.frag", vertices, indices);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        test.render();
    }

    @Override
    public String getSceneName() {
        return sceneName;
    }

}