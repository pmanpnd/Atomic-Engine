package net.mabid.atomic.window;

import net.mabid.atomic.input.KeyListener;
import net.mabid.atomic.input.MouseListener;
import net.mabid.atomic.scene.GameScene;
import net.mabid.atomic.scene.Scene;
import net.mabid.atomic.scene.SceneManager;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Window {

    private int width, height;

    private String title;

    private long glfwWindow;

    private static Window window = null;

    public Window() {
        this.width = 1280;
        this.height = 720;
        this.title = "Hello World!";
    }

    public static Window get() {
        if (window == null) {
            window = new Window();
        }

        return window;
    }

    public void run() {
        init();
        loop();

        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
    }

    public void init() {
        if (!glfwInit()) {
            System.err.println("Failed to initialise GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindow = glfwCreateWindow(width, height, title, 0, 0);

        if (glfwWindow == 0) {
            System.err.println("Failed to create window.");
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(glfwWindow, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);

        GL.createCapabilities();

        glfwShowWindow(glfwWindow);

        SceneManager.addScene("Game Scene", new GameScene());
        SceneManager.changeCurrentScene("Game Scene");
        SceneManager.init();
    }

    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();
            SceneManager.update();

            SceneManager.render();
            glfwSwapBuffers(glfwWindow);
        }
    }

}
