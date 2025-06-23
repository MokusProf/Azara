// Vertex Shader: purple.vsh
attribute vec4 vertexPosition;
attribute vec2 vertexTexCoord;
varying vec2 v_texCoord;

void main() {
    gl_Position = vertexPosition;
    v_texCoord = vertexTexCoord;
}