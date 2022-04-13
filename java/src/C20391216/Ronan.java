package C20391216;

import ie.tudublin.Visual;

public class Ronan extends Visual
{
    float theta = 0.0f;
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
        // colorMode(HSB);
        // noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("heathens.mp3");

        hint(ENABLE_DEPTH_SORT); // option that allows transparency
    }

    void drawCube(float cubeSpeed)
    {
        calculateAverageAmplitude();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        strokeWeight(5);

        pushMatrix();

        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -200);
        rotateX(angle);
        rotateZ(angle);       

        float boxSize = 40 + (200 * getSmoothedAmplitude()); 
        box(boxSize);

        popMatrix();

        angle += cubeSpeed / 3;
    }

    void drawCircle(float size)
    {
        noFill();
        lights();
        circle(0, 0, size + 30);
        triangle(0, 0, 0, 0, 0, 0);
    }
  
    void drawPyramid(float t, float colour)
    { 
        // theres 3 vertexs for the 3 the 3 triangles on the prism
        // T is the size of the shape
        beginShape(TRIANGLES);
        
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
        colorMode(HSB);
        background(0);

        float smoothedAmplitude = 0;
        float size = 0;
        float c = 0;
        float speed = 0;

        smoothedAmplitude = getSmoothedAmplitude() / 8;

        speed = smoothedAmplitude * 1.5f;
        theta += speed;

        translate(width/2, height/2, 0);
        rotateX(theta);
        rotateY(theta);

        size = map(smoothedAmplitude, 0, 0.1f, 10, 45);
        c = map(smoothedAmplitude, 0, 0.08f, 0, 255) / 2;

        drawPyramid(size * 2, c * 2);

        translate(150, 150);
        drawPyramid(size, c);
        drawCircle(size);

        translate(-300, -300);
        drawPyramid(size, c);
        drawCircle(size);

        translate(300, 0);
        drawPyramid(size, c);
        drawCircle(size);

        translate(-300, 300);
        drawPyramid(size, c);
        drawCircle(size);

        drawCube(speed);
    }
}