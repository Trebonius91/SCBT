package engineTester;

import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainBuilderLoop {

    // The main method of the program

    public static void main(String[] args) {

        // Create the window
        DisplayManager.createDisplay();

        // Create loader and renderer for further usage!
        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        // Define example vertices for quad that shall be rendered

        float[] vertices =  {
                // Left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // Right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        // Load positional data as raw model
        RawModel model = loader.loadToVAO(vertices);

        // Loop through the updated frames, showing the molecules etc., render each frame
        // Leave the loop if the user wants to cose the display

        while(!Display.isCloseRequested()) {

            // For each cycle of the loop: Prepare the renderer!
            renderer.prepare();
            renderer.render(model);
            DisplayManager.updateDisplay();

        }

        // Delete all VAOs and VBOs after closing
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
