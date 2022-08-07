package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    // Memory management: all VAOs and VBOs shall be deleted after program closing
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();




    // Load data about an object (with positions) into a VAO
    public RawModel loadToVAO(float[] positions) {
        int vaoID = createVAO();

        // positions always first attribute in list
        storeDataInAttributeList(0, positions);
        unbindVAO();

        // Each vertex has three corners for its position
        return new RawModel(vaoID, positions.length/3);
    }

    // Create VAOs

    // Create an empty VAO and return its id (from openGL)
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        // Add the new VAO to the global list
        vaos.add(vaoID);
        unbindVAO();
        return vaoID;
    }

    // Create VBOs

    // Store data into one of the VAO attribute list (which number? and data)
    // Create a new VBO (a new list for an attribute)
    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        // Add the new VBO to the global list
        vbos.add(vboID);
        // Type of VBO: a GL array buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        // Data will not be edited once being stored into the VBO
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        // 1. number of attribute list in which data will be stored
        // 2. length of each vertex (here 3, since 3 dimensions, x, y and z)
        // 3. Data type
        // 4. If data is normalized
        // 5. Distance between vertices (any other data between them), here nothing
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT,false,0,0);
        // Which type of VBO?
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    // Delete all VBOs and VAOs
    public void cleanUp() {
        for (int vao:vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo:vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }

    // After using the VAO it must be unbind
    // Vertex array object will stay bound until it will be unbinded
    private void unbindVAO() {
        // Unbind the currently bound VAO
        GL30.glBindVertexArray(0);

    }
    // When a VBO is bound, data can be stored into it

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);

        // put data into the buffer
        buffer.put(data);
        // Finished writing such that it is ready to be read from
        buffer.flip();
        return buffer;
    }

}
