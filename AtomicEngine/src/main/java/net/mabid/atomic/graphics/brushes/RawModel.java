package net.mabid.atomic.graphics.brushes;

import net.mabid.atomic.graphics.Shader;
import net.mabid.atomic.graphics.VertexArray;

public class RawModel {

    private Shader shader;
    private VertexArray rawMesh;

    public RawModel(String vertPath, String fragPath, float[] vertices, int[] indices) {
        shader = new Shader(vertPath, fragPath);
        rawMesh = new VertexArray(vertices, indices);

        shader.unbind();
    }

    public void render() {
        shader.bind();
        rawMesh.bind();
        rawMesh.draw();
        rawMesh.unbind();
        shader.unbind();
    }

}
