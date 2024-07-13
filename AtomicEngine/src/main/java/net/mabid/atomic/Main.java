package net.mabid.atomic;

import net.mabid.atomic.window.Window;

public class Main {

    private Window window;

    private void start() {
        window = Window.get();
        window.run();
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
