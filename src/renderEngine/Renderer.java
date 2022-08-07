package renderEngine;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

// Render a model from a VAO
public class Renderer {

    public void prepare() {

        // Define the color of the screen background (rgb colors: red, green, blue, alpha, from 0 to 1)

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(0, 1, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    // Render the actual model: bind it first and then load the attribute list!
    public void render(RawModel model) {
        GL30.glBindVertexArray(model.getVaoID());
        // List zero: positions
        GL20.glEnableVertexAttribArray(0);
        // Render triangles!
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        // unbind the VAO again
        GL30.glBindVertexArray(0);
    }


}
