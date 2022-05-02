package C20391216;

import ie.tudublin.Visual;
import processing.core.PConstants;

public class Ronan
{
    // variable
    float theta = 0;
    int mode = 1;
    float angle = 0;
    boolean paused = false;

    // draws cube
    void drawCube(float cubeSpeed, Heathens h)
    {
        h.calculateAverageAmplitude();
        h.stroke(Heathens.map(h.getSmoothedAmplitude(), 0, 0.6f, 0, 255), 255, 255);
        h.strokeWeight(5);

        h.pushMatrix();

        h.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        h.translate(0, 0, -200);
        h.rotateX(angle);
        h.rotateZ(angle);       

        float boxSize = 30 + (125 * h.getSmoothedAmplitude());
        h.box(boxSize);

        h.popMatrix();

        angle += cubeSpeed * 0.4f;
    }

    // draws border
    void drawBorder(float smoothedAmplitude, float colour, Heathens h)
    {
        float border = Heathens.map(smoothedAmplitude, 0, 0.15f, 3, 70);
        h.fill(colour, 255, 255, 150);
        h.stroke(colour, 255, 255);
        h.rect(0, 0, h.width, border); // Top
        h.rect(h.width - border, 0, border, h.height); // Right
        h.rect(0, h.height - border, h.width, border); // Bottom
        h.rect(0, 0, border, h.height); // Left
    }

    // draws circles around smaller prisms
    void drawCircle(float size, Heathens h)
    {
        h.noFill();
        h.lights();
        h.circle(0, 0, size + 30);
        h.triangle(0, 0, 0, 0, 0, 0);
    }

    // draws random circles in the back depending on the amplitude
    float drawRandom(float colour, Heathens h)
    {
        float x, y;
        float h2 = h.random(7);
        float loop = Heathens.floor(Heathens.map(h.getSmoothedAmplitude(), 0, 0.5f, 0, 10));
        h.stroke(colour, 255, 255);
        for (int i = 0; i < loop; i++)
        {
            x = h.random(h.width);
            y = h.random(h.height);
            h.circle(x, y, h2);
        }

        return loop;
    }

    // draws main prism shape in the middle of the screen spinning
    void drawPyramid(float t, float colour, Heathens h)
    { 
        // theres 3 vertexs for the 3 the 3 triangles on the prism
        // T is the size of the shape
        h.beginShape(PConstants.TRIANGLES);
        h.stroke(colour, 255, 255);
        
        // colour changes depending on the amplitude
        h.fill(colour, 255, 255, 150);
        h.vertex(-t, -t, -t);
        h.vertex( t, -t, -t);
        h.vertex( 0, 0, t);
      
        h.fill(colour, 255, 255, 150);
        h.vertex( t, -t, -t);
        h.vertex( t, t, -t);
        h.vertex( 0, 0, t);
      
        h.fill(colour, 255, 255, 150);
        h.vertex( t, t, -t);
        h.vertex(-t, t, -t);
        h.vertex( 0, 0, t);
      
        h.fill(colour, 255, 255, 150);
        h.vertex(-t, t, -t);
        h.vertex(-t, -t, -t); 
        h.vertex( 0, 0, t);
    
        h.endShape();
    }
    
    // main function that combines everything
    public void render(Heathens h)
    {
        h.colorMode(Visual.HSB);
        h.background(0);
        float smoothedAmplitude = 0;
        float size = 0;
        float c = 0;
        float c2 = 0;
        float speed = 0;
       
        smoothedAmplitude = h.getSmoothedAmplitude() / 8;

        // getting different colours
        size = Heathens.map(smoothedAmplitude, 0, 0.1f, 10, 50);
        c = Heathens.map(smoothedAmplitude, 0, 0.055f, 0, 255);
        c2 = Heathens.map(smoothedAmplitude, 0.08f, 0, 0, 255);

        speed = smoothedAmplitude * 1.6f;
        theta += speed;

        drawBorder(smoothedAmplitude, c2, h);
        float end = drawRandom(c, h);

        h.translate(h.width/2, h.height/2, 0);
        h.rotateX(theta);
        h.rotateY(theta);

        // drawing big pramid in the middle
        drawPyramid(size * 2.4f, c2, h);
        
        // drawing 4 smaller ones
        h.translate(150 * 1.8f, 150 * 1.8f);
        drawPyramid(size, c, h);
        drawCircle(size, h);

        h.translate(-300 * 1.8f, -300 * 1.8f);
        drawPyramid(size, c, h);
        drawCircle(size, h);

        h.translate(300 * 1.8f, 0);
        drawPyramid(size, c, h);
        drawCircle(size, h);

        h.translate(-300 * 1.8f, 300 * 1.8f);
        drawPyramid(size, c, h);
        drawCircle(size, h);

        // if music just stops, continue a small spinning effect for visual appeal
        if (end == 0)
        {
            theta += 0.01f;
            speed = 0.01f;
        }

        // drawing big cube
        drawCube(speed, h);
    }
}
