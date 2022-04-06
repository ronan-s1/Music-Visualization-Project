package C20391216;

import ie.tudublin.Visual;

public class Heathens extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    int mode = 1;
    boolean paused = false;
    public void keyPressed()
    {
        if (key >= '0' && key <= '4') {
			mode = key - '0';
		}
        switch(key)
        {
            case ' ':
            {
                if(paused)
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
        
        setFrameSize(256);

        startMinim();
        loadAudio("heathens.mp3");
        
    }

    public void draw()
    {
      switch(mode)
      {
          case 1: //Eoin SECTION 1
          {
            
            break;
          }
          case 2: //Kieran SECTION 2
          {
    
            break;
          }
          case 3: //Aman SECTION 3
          {
            colorMode(RGB);
            background(GRAY);

            //create an eye shape using this code
            stroke(0);
            strokeWeight(10);
            fill(255);
            beginShape();
            for(float i = 0; i < TWO_PI; i += 0.01f)
            {
                float r = width/2.5f;
                float x = r * cos(i);
                float y = r * pow(sin(i), 3) * 0.5f;
                //puts the eye in the center of the screen
                vertex(x+width/2, y+height/2);
            }
            endShape();

            float total = 0;
            float amplitude = 0;
            float smoothedAmplitude = 0;
            for(int i = 0 ; i < getAudioBuffer().size() ; i ++)
            {
                total += abs(getAudioBuffer().get(i));
            }
            amplitude = total / getAudioBuffer().size();
            smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.1f);
            //make visualizer more intense
            smoothedAmplitude = smoothedAmplitude * 5;

            //drawing the ring inside the eye
            noFill();
            strokeWeight(10);
            beginShape();
            stroke(139, 0, 0);
            strokeWeight(5);
            for(float i = 0; i < TWO_PI; i += 0.01f)
            {
                //make the ring go crazy and randomly but still on beat
                float r = width/14 + (getAudioBuffer().get((int) i) * random(50,70));
                float x = r * cos(i);
                float y = r * sin(i);
                //center the ring
                vertex(x+width/2, y+height/2);
            }
            endShape(CLOSE);

            //create the iris
            strokeWeight(10);
            stroke(0);
            for(int i = 0; i < getAudioBuffer().size(); i++)
            {
                //the iris
                float radius = map(smoothedAmplitude, 0, 0.6f, width/4, 500);	
                fill(255,0,0);	
                circle(width/2, height/2, radius);

                //draw the pupil for the eye
                float radius2 = map(smoothedAmplitude, 0, 0.7f, width/20, 500);
                fill(0);
                circle(width/2, height/2, radius2);
            }

            //drawing the "tomoe"

            pushMatrix();
            translate(width/2, height/2);
            rotate(degrees(getAudioPlayer().position()/125));
            for(int i = 0; i < getAudioBuffer().size(); i++)
            {
                float radius3 = map(smoothedAmplitude, 0, 3, width/60, 500);
                /*
                circle(width/2, height/2.7f, radius3);
                circle(width/1.79f, height/1.73f, radius3);
                circle(width/2.28f, height/1.73f, radius3);
                */
                ellipse(0, height/-7.5f, radius3, radius3);
                ellipse(width/16.5f, height/13.5f, radius3, radius3);
                ellipse(width/-16.5f, height/13.5f, radius3, radius3);
            }
            popMatrix();

            break;
          }
          case 4: //Ronan SECTION 4
          {
            break;
          }
      }
    }
}
