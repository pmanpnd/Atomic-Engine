package net.mabid.atomic.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class MouseListener {

    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;

    private boolean[] mouseButtons = new boolean[3];
    private boolean isDragging;

    private static MouseListener instance;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener getInstance() {
        if (instance == null) {
            instance = new MouseListener();
        }

        return instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos) {
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
        getInstance().xPos = xPos;
        getInstance().yPos = yPos;
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < getInstance().mouseButtons.length) {
                getInstance().mouseButtons[button] = true;
            }
        } else {
            if (button < getInstance().mouseButtons.length) {
                getInstance().mouseButtons[button] = false;
                getInstance().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        getInstance().scrollY = xOffset;
        getInstance().scrollY = yOffset;
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static float getLastX() {
        return (float) getInstance().lastX;
    }

    public static float getLastY() {
        return (float) getInstance().lastY;
    }

    public static float getXPos() {
        return (float) getInstance().xPos;
    }

    public static float getYPos() {
        return (float) getInstance().yPos;
    }

    public static float getDx() {
        return (float) (getInstance().lastX - getInstance().xPos);
    }

    public static float getDy() {
        return (float) (getInstance().lastY - getInstance().yPos);
    }

    public static float getScrollX() {
        return (float) getInstance().scrollX;
    }

    public static float getScrollY() {
        return (float) getInstance().scrollY;
    }

    public static boolean getMouseButton(int button) {
        return getInstance().mouseButtons[button];
    }

}
