package C20391216;

import ie.tudublin.Visual;

public class Ronan extends Visual
{
    float theta = 0;
    int mode = 1;
    float angle = 0;
    boolean paused = false;


    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }


    public void keyPressed()
    {
        if (key >= '0' && key <= '4')
        {
			mode = key - '0';
		}

        switch(key)
        {
            case ' ':
            {
                if (paused)
                {
                    getAudioPlayer().play();
                    paused = false;
                }

                else
                {
                    getAudioPlayer().pause();
                    paused = true;
                }
                break;
            }

            case '1':
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
                break;
            }

            case '2':
            {
                getAudioPlayer().cue(64900);
                getAudioPlayer().play();
                break;
            }

            case '3':
            {
                getAudioPlayer().cue(118000);
                getAudioPlayer().play();
                break;
            }

            case '4':
            {
                getAudioPlayer().cue(162000);
                getAudioPlayer().play();
                break;
            }
        }
    }


    public void setup()
    {
        colorMode(HSB);
        noCursor();
        frameRate(60);
        
        setFrameSize(256);

        startMinim();
        loadAudio("heathens.mp3");
    }


    void drawCube(float cubeSpeed)
    {
        calculateAverageAmplitude();
        stroke(map(getSmoothedAmplitude(), 0, 0.8f, 0, 255), 255, 255);
        strokeWeight(5);

        pushMatrix();

        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -200);
        rotateX(angle);
        rotateZ(angle);       

        float boxSize = 30 + (125 * getSmoothedAmplitude());
        box(boxSize);

        popMatrix();

        angle += cubeSpeed * 0.4f;
    }


    void drawBorder(float smoothedAmplitude, float colour)
    {
        float border = map(smoothedAmplitude, 0, 0.15f, 3, 70);
        fill(colour, 255, 255);
        stroke(colour, 255, 255);
        rect(0, 0, width, border); // Top
        rect(width - border, 0, border, height); // Right
        rect(0, height - border, width, border); // Bottom
        rect(0, 0, border, height); // Left
    }


    void drawCircle(float size)
    {
        noFill();
        lights();
        circle(0, 0, size + 30);
        triangle(0, 0, 0, 0, 0, 0);
    }


    float drawRandom(float colour)
    {
        float x, y;
        float h = random(7);
        float loop = floor(map(getSmoothedAmplitude(), 0, 0.5f, 0, 9));
        println(loop);
        stroke(colour, 255, 255);
        for (int i = 0; i < loop; i++)
        {
            x = random(width);
            y = random(height);
            circle(x, y, h);
        }

        return loop;
    }

  
    void drawPyramid(float t, float colour)
    { 
        // theres 3 vertexs for the 3 the 3 triangles on the prism
        // T is the size of the shape
        beginShape(TRIANGLES);
        stroke(colour, 255, 255);
        
        // colour changes depending on the amplitude
        fill(colour, 255, 255, 150);
        vertex(-t, -t, -t);
        vertex( t, -t, -t);
        vertex( 0, 0, t);
      
        fill(colour, 255, 255, 150);
        vertex( t, -t, -t);
        vertex( t, t, -t);
        vertex( 0, 0, t);
      
        fill(colour, 255, 255, 150);
        vertex( t, t, -t);
        vertex(-t, t, -t);
        vertex( 0, 0, t);
      
        fill(colour, 255, 255, 150);
        vertex(-t, t, -t);
        vertex(-t, -t, -t); 
        vertex( 0, 0, t);
    
        endShape();
    }
    

    public void draw()
    {
        background(0);
        float smoothedAmplitude = 0;
        float size = 0;
        float c = 0;
        float c2 = 0;
        float speed = 0;
       
        smoothedAmplitude = getSmoothedAmplitude() / 8;

        size = map(smoothedAmplitude, 0, 0.1f, 10, 50);
        c = map(smoothedAmplitude, 0, 0.07f, 0, 255);
        c2 = map(smoothedAmplitude, 0, 0.055f, 0, 255);

        speed = smoothedAmplitude * 1.6f;
        theta += speed;

        drawBorder(smoothedAmplitude, c);
        float end = drawRandom(c2);

        translate(width/2, height/2, 0);
        rotateX(theta);
        rotateY(theta);

        drawPyramid(size * 2.4f, c2);
        
        translate(150 * 1.8f, 150 * 1.8f);
        drawPyramid(size, c);
        drawCircle(size);

        translate(-300 * 1.8f, -300 * 1.8f);
        drawPyramid(size, c);
        drawCircle(size);

        translate(300 * 1.8f, 0);
        drawPyramid(size, c);
        drawCircle(size);

        translate(-300 * 1.8f, 300 * 1.8f);
        drawPyramid(size, c);
        drawCircle(size);

        if (end == 0)
        {
            theta += 0.01f;
            speed = 0.01f;
        }

        drawCube(speed);
    }
}