package net.mabid.atomic.scene;

public abstract class Scene {

    protected String sceneName;

    public Scene() {

    }

    public abstract void init();
    public abstract void update();
    public abstract void render();

    public abstract String getSceneName();

}
