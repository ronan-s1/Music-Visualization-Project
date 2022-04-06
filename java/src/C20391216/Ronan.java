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
        colorMode(HSB);
        // noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("heathens.mp3");

        hint(ENABLE_DEPTH_SORT); // option that allows transparency
    }

    void drawCube()
    {
        calculateAverageAmplitude();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        strokeWeight(5);
        noFill();
        lights();
        pushMatrix();
        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -200);
        rotateX(angle);
        rotateZ(angle);       
        float boxSize = 50 + (200 * getSmoothedAmplitude()); 
        box(boxSize);   
        popMatrix();
        angle += 0.01f;
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
        // this pyramid has 4 sides, each drawn as a separate triangle
        // each side has 3 vertices, making up a triangle shape
        // the parameter " t " determines the size of the pyramid
        beginShape(TRIANGLES);
      
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
        float total = 0;
        float amplitude = 0;
        float smoothedAmplitude = 0;
        float size = 0;
        float c = 0;
        float smallC = 0;
        // int borderStroke = 5;

        for(int i = 0 ; i < getAudioBuffer().size(); i ++)
        {
            total += abs(getAudioBuffer().get(i));
        }
        
        amplitude = total / getAudioBuffer().size();
        smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.1f);

        if (theta > 110)
        {
            theta += 0.01;
        }

        else
        {
            theta += smoothedAmplitude * 1.5;
        }
        
        translate(width/2, height/2, 0);
        rotateX(theta);
        rotateY(theta);

        size = map(smoothedAmplitude, 0, 0.1f, 10, 48);
        c = map(smoothedAmplitude, 0, 0.09f, 0, 255);
        smallC = map(c, 0, 255, 0, 127);
        println(smallC, c);
        drawPyramid(50, c);

        translate(150, 150);
        drawPyramid(size, smallC);
        drawCircle(size);

        translate(-300, -300);
        drawPyramid(size, smallC);
        drawCircle(size);

        translate(300, 0);
        drawPyramid(size, smallC);
        drawCircle(size);

        translate(-300, 300);
        drawPyramid(size, smallC);
        drawCircle(size);

        drawCube();
    }
}