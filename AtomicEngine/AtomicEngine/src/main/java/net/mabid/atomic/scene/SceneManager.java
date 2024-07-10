package net.mabid.atomic.scene;

import java.util.HashMap;

public class SceneManager {

    private static Scene currentScene = null;

    private static HashMap<String, Scene> scenes = new HashMap<>();

    private static SceneManager sceneManager = null;

    public SceneManager() {

    }

    public static SceneManager getInstance() {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }

        return sceneManager;
    }

    // Initialises the current scene.
    public static void init() {
        if (getInstance().currentScene == null) {
            System.err.println("Current scene is null!");
            return;
        }

        getInstance().currentScene.init();
    }

    // Updates the current scene.
    public static void update() {
        if (getInstance().currentScene == null) {
            System.err.println("Current scene is null!");
            return;
        }

        getInstance().currentScene.update();
    }

    // Renders the current scene.
    public static void render() {
        if (getInstance().currentScene == null) {
            System.err.println("Current scene is null!");
            return;
        }

        getInstance().currentScene.render();
    }

    public static void changeCurrentScene(String sceneName) {
        if (!scenes.containsKey(sceneName)) {
            System.err.println("Invalid scene name!");
            return;
        }

        getInstance().currentScene = scenes.get(sceneName);
    }

    public static void addScene(Scene newScene) {
        scenes.put(newScene.getSceneName(), newScene);
    }

    public static void addScene(String name, Scene newScene) {
        scenes.put(name, newScene);
    }

    public static void removeScene(Scene newScene) {
        scenes.remove(newScene.getSceneName());
    }

    public static void removeScene(String name) {
        scenes.remove(name);
    }

    public static Scene getCurrentScene() {
        return getInstance().currentScene;
    }

}
