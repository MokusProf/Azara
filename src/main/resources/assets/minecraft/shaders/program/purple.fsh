// Fragment Shader: purple.fsh
uniform sampler2D tex;        // Texture
uniform float blurSize;       // Blur size uniform

varying vec2 v_texCoord;

void main() {
    vec4 color = vec4(0.0);

    // Apply blur by sampling surrounding pixels
    color += texture2D(tex, v_texCoord + vec2(-blurSize, -blurSize));  // Top-left
    color += texture2D(tex, v_texCoord + vec2(0.0, -blurSize));       // Top
    color += texture2D(tex, v_texCoord + vec2(blurSize, -blurSize));   // Top-right

    color += texture2D(tex, v_texCoord + vec2(-blurSize, 0.0));       // Left
    color += texture2D(tex, v_texCoord);                              // Center
    color += texture2D(tex, v_texCoord + vec2(blurSize, 0.0));        // Right

    color += texture2D(tex, v_texCoord + vec2(-blurSize, blurSize));   // Bottom-left
    color += texture2D(tex, v_texCoord + vec2(0.0, blurSize));        // Bottom
    color += texture2D(tex, v_texCoord + vec2(blurSize, blurSize));    // Bottom-right

    // Normalize (average) the colors to apply the blur effect
    color /= 9.0;  // Since we sampled 9 pixels

    // Apply the purple tint
    color.r *= 0.5; // Red channel tint
    color.g *= 0.0; // Green channel tint
    color.b *= 1.0; // Blue channel tint

    gl_FragColor = color;
}