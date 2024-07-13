package net.mabid.atomic.graphics;

import net.mabid.atomic.utils.FIleUtils;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

public class Shader {

    private int ID;

    private boolean enabled = false;

    private HashMap<String, Integer> uniformCache = new HashMap<>();

    public Shader(String vertexPath, String fragmentPath) {
        ID = glCreateProgram();

        int vertexID = loadShader(vertexPath, GL_VERTEX_SHADER);
        int fragmentID = loadShader(fragmentPath, GL_FRAGMENT_SHADER);

        glAttachShader(ID, vertexID);
        glAttachShader(ID, fragmentID);

        glLinkProgram(ID);

        glValidateProgram(ID);

        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
    }

    private int loadShader(String shaderpath, int type) {
        int result = glCreateShader(type);

        glShaderSource(result, FIleUtils.loadFile(shaderpath));

        glCompileShader(result);

        if (glGetShaderi(result, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile shader. Reason: " + glGetShaderInfoLog(result));
            return -1;
        }

        return result;
    }

    private int getUniform(String name) {
        if (uniformCache.containsKey(name)) {
            return uniformCache.get(name);
        }

        int location = glGetUniformLocation(ID, name);

        if (location == -1) {
            System.err.println("Uniform of name '" + name + "' is bogus.");
            return -1;
        }

        uniformCache.put(name, location);

        return uniformCache.get(name);
    }

    public void setUniformMat4f(String name, Matrix4f mat) {
        if (!enabled) {
            bind();
        }

        FloatBuffer matbuf = BufferUtils.createFloatBuffer(4 * 4);
        mat.get(matbuf);
        glUniformMatrix4fv(getUniform(name), false, matbuf);
    }

    public void bind() {
        if (enabled) {
            return;
        }

        enabled = true;
        glUseProgram(ID);
    }

    public void unbind() {
        if (!enabled) {
            return;
        }

        enabled = false;
        glUseProgram(0);
    }

}
