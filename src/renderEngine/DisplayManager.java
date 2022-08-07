package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    // Size of the rendered openGL window

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // Maximum number of frames per second

    private static final int FPS_CAP = 120;
    // Creation of the Screen when starting the program
    public static void createDisplay() {

        // Define the used openGL version
        ContextAttribs attribs = new ContextAttribs(3,2);
        // Comtabible with newer openGL versions
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("Surface Catalysis Buildup Tool");
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Top left and bottom right of the display (positions)
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    // Update the display every frame
    public static void updateDisplay() {

        // Synchronize to steady FPS (with cap), update the display FPS times a second
        Display.sync(FPS_CAP);
        Display.update();

    }

    // Close the display at the end
    public static void closeDisplay() {

        Display.destroy();
    }

}
