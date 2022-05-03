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
    void drawCube(float cubeSpeed, Enemy e)
    {
        e.calculateAverageAmplitude();
        e.stroke(Enemy.map(e.getSmoothedAmplitude(), 0, 0.6f, 0, 255), 255, 255);
        e.strokeWeight(5);

        e.pushMatrix();

        e.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        e.translate(0, 0, -200);
        e.rotateX(angle);
        e.rotateZ(angle);       

        float boxSize = 27 + (120 * e.getSmoothedAmplitude());
        e.box(boxSize);

        e.popMatrix();

        angle += cubeSpeed * 0.4f;
    }

    // draws border
    void drawBorder(float smoothedAmplitude, float colour, Enemy e)
    {
        float border = Enemy.map(smoothedAmplitude, 0, 0.15f, 3, 70);
        e.fill(colour, 255, 255, 150);
        e.stroke(colour, 255, 255);
        e.rect(0, 0, e.width, border); // Top
        e.rect(e.width - border, 0, border, e.height); // Right
        e.rect(0, e.height - border, e.width, border); // Bottom
        e.rect(0, 0, border, e.height); // Left
    }

    // draws circles around smaller prisms
    void drawCircle(float size, Enemy e)
    {
        e.noFill();
        e.lights();
        e.circle(0, 0, size + 30);
        e.triangle(0, 0, 0, 0, 0, 0);
    }

    // draws random circles in the back depending on the amplitude
    float drawRandom(float colour, Enemy e)
    {
        float x, y;
        float h2 = e.random(7);
        float loop = Enemy.floor(Enemy.map(e.getSmoothedAmplitude(), 0, 0.5f, 0, 11));
        e.stroke(colour, 255, 255);
        
        // looping through and drawing random circles
        for (int i = 0; i < loop; i++)
        {
            x = e.random(e.width);
            y = e.random(e.height);
            e.circle(x, y, h2);
        }

        return loop;
    }

    // draws main prism shape in the middle of the screen spinning
    void drawPyramid(float t, float colour, Enemy e)
    { 
        // theres 3 vertexs for the 3 the 3 triangles on the prism
        // T is the size of the shape
        e.beginShape(PConstants.TRIANGLES);
        e.stroke(colour, 255, 255);
        
        // colour changes depending on the amplitude
        e.fill(colour, 255, 255, 150);
        e.vertex(-t, -t, -t);
        e.vertex( t, -t, -t);
        e.vertex( 0, 0, t);
      
        e.fill(colour, 255, 255, 150);
        e.vertex( t, -t, -t);
        e.vertex( t, t, -t);
        e.vertex( 0, 0, t);
      
        e.fill(colour, 255, 255, 150);
        e.vertex( t, t, -t);
        e.vertex(-t, t, -t);
        e.vertex( 0, 0, t);
      
        e.fill(colour, 255, 255, 150);
        e.vertex(-t, t, -t);
        e.vertex(-t, -t, -t); 
        e.vertex( 0, 0, t);
    
        e.endShape();
    }
    
    // main function that combines everything
    public void render(Enemy e)
    {
        e.colorMode(Visual.HSB);
        e.background(0);
        float smoothedAmplitude = 0;
        float size = 0;
        float c = 0;
        float c2 = 0;
        float speed = 0;
        float smallPyramidDist = 1.8f;
       
        smoothedAmplitude = e.getSmoothedAmplitude() / 8;

        // getting different colours
        size = Enemy.map(smoothedAmplitude, 0, 0.1f, 10, 50);
        c = Enemy.map(smoothedAmplitude, 0, 0.055f, 0, 255);
        c2 = Enemy.map(smoothedAmplitude, 0.08f, 0, 0, 255);
        
        // chnaging speed with amplitude      
        speed = smoothedAmplitude * 1.6f;
        theta += speed;

        drawBorder(smoothedAmplitude, c2, e);
        float end = drawRandom(c, e);
        
        
        // positioning big triangle to center of the screen and making it spin
        e.translate(e.width/2, e.height/2, 0);
        e.rotateX(theta);
        e.rotateY(theta);

        // drawing big pramid in the middle
        drawPyramid(size * 2.5f, c2, e);
        
        // drawing 4 smaller ones
        e.translate(150 * smallPyramidDist, 150 * smallPyramidDist);
        drawPyramid(size, c, e);
        drawCircle(size, e);

        e.translate(-300 * smallPyramidDist, -300 * smallPyramidDist);
        drawPyramid(size, c, e);
        drawCircle(size, e);

        e.translate(300 * smallPyramidDist, 0);
        drawPyramid(size, c, e);
        drawCircle(size, e);

        e.translate(-300 * smallPyramidDist, 300 * smallPyramidDist);
        drawPyramid(size, c, e);
        drawCircle(size, e);

        // if music just stops, continue a small spinning effect for visual appeal
        if (end == 0)
        {
            theta += 0.01f;
            speed = 0.01f;
        }

        // drawing big cube
        drawCube(speed, e);
    }
}
